package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Blocking.CompanyBlockingKeyByNameGenerator;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Blocking.MovieBlockingKeyByTitleGenerator;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators.*;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.CompanyXMLReader;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Movie;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.MovieXMLReader;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEngine;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEvaluator;
import de.uni_mannheim.informatik.dws.winter.matching.algorithms.RuleLearner;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.StandardRecordBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.rules.WekaMatchingRule;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.MatchingGoldStandard;
import de.uni_mannheim.informatik.dws.winter.model.Performance;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.CSVCorrespondenceFormatter;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import org.ejml.ops.CommonOps;
import org.slf4j.Logger;

import java.io.File;

public class IR_DBPedia_Forbes_using_machine_learning {
	
	/*
	 * Logging Options:
	 * 		default: 	level INFO	- console
	 * 		trace:		level TRACE     - console
	 * 		infoFile:	level INFO	- console/file
	 * 		traceFile:	level TRACE	- console/file
	 *  
	 * To set the log level to trace and write the log to winter.log and console, 
	 * activate the "traceFile" logger as follows:
	 *     private static final Logger logger = WinterLogManager.activateLogger("traceFile");
	 *
	 */

	private static final Logger logger = WinterLogManager.activateLogger("default");
	
    public static void main( String[] args ) throws Exception
    {
		// loading data
		logger.info("*\tLoading datasets\t*");
		HashedDataSet<Company, Attribute> dataForbes = new HashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/forbes_integrated.xml"), "/Companies/Company", dataForbes);

		HashedDataSet<Company, Attribute>  dataDbPedia = new HashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/dbpedia_integrated.xml"), "/Companies/Company", dataDbPedia);

		// load the training set
		logger.info("*\tLoading gold standard\t*");
		MatchingGoldStandard gsDbpedia_Forbes = new MatchingGoldStandard();
		gsDbpedia_Forbes.loadFromCSVFile(new File(
				"data/goldstandard/dbpedia_forbes_goldstandard_train.csv"));


		// create a matching rule
		String options[] = new String[] {""};
		//String modelType = "SimpleLogistic"; // use a logistic regression
		String modelType = "AdaBoostM1"; // u
		//String modelType = "Bayes";


		WekaMatchingRule<Company, Attribute> matchingRule = new WekaMatchingRule<>(0.1, modelType, options);
		matchingRule.activateDebugReport("data/output/debugResultsMatchingRule.csv", 1000, gsDbpedia_Forbes);
		
		// add comparators
		matchingRule.addComparator(new CompanyNameComparatorJaroWrinkler());
		matchingRule.addComparator(new IndustryComparator());
		matchingRule.addComparator(new CountryComparatorJaroWrinkler());
		matchingRule.addComparator(new FoundedYearComparator());
		matchingRule.addComparator(new RevenueComparatorPercentage());
		matchingRule.addComparator(new ProfitComparatorPercentage());
		matchingRule.addComparator(new AssetsComparatorPercentage());

		
		// train the matching rule's model
		logger.info("*\tLearning matching rule\t*");
		RuleLearner<Company, Attribute> learner = new RuleLearner<>();
		learner.learnMatchingRule(dataDbPedia, dataForbes, null, matchingRule, gsDbpedia_Forbes);
		logger.info(String.format("Matching rule is:\n%s", matchingRule.getModelDescription()));
		
		// create a blocker (blocking strategy)
		StandardRecordBlocker<Company, Attribute> blocker = new StandardRecordBlocker<Company, Attribute>(new CompanyBlockingKeyByNameGenerator());
//		SortedNeighbourhoodBlocker<Movie, Attribute, Attribute> blocker = new SortedNeighbourhoodBlocker<>(new MovieBlockingKeyByDecadeGenerator(), 1);
		blocker.collectBlockSizeData("data/output/debugResultsBlocking.csv", 100);
		
		// Initialize Matching Engine
		MatchingEngine<Company, Attribute> engine = new MatchingEngine<>();

		// Execute the matching
		logger.info("*\tRunning identity resolution\t*");
		Processable<Correspondence<Company, Attribute>> correspondences = engine.runIdentityResolution(
				dataDbPedia, dataForbes, null, matchingRule,
				blocker);

		// write the correspondences to the output file
		new CSVCorrespondenceFormatter().writeCSV(new File("data/output/dbpedia_forbes_correspondences_ML_Ada.csv"), correspondences);

		// load the gold standard (test set)
		logger.info("*\tLoading gold standard\t*");
		MatchingGoldStandard gsTest = new MatchingGoldStandard();
		gsTest.loadFromCSVFile(new File(
				"data/goldstandard/dbpedia_forbes_goldstandard_test.csv"));
		
		// evaluate your result
		logger.info("*\tEvaluating result\t*");
		MatchingEvaluator<Company, Attribute> evaluator = new MatchingEvaluator<Company, Attribute>();
		Performance perfTest = evaluator.evaluateMatching(correspondences,
				gsTest);
		
		// print the evaluation result
		logger.info("DBPedia <-> Forbes");
		logger.info(String.format(
				"Precision: %.4f",perfTest.getPrecision()));
		logger.info(String.format(
				"Recall: %.4f",	perfTest.getRecall()));
		logger.info(String.format(
				"F1: %.4f",perfTest.getF1()));
    }
}
