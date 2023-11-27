package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution;


import java.io.File;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators.*;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.CompanyXMLReader;
import org.slf4j.Logger;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Blocking.CompanyBlockingKeyByNameGenerator;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Blocking.MovieBlockingKeyByTitleGenerator;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Movie;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.MovieXMLReader;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEngine;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEvaluator;
import de.uni_mannheim.informatik.dws.winter.matching.algorithms.MaximumBipartiteMatchingAlgorithm;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.Blocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.NoBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.SortedNeighbourhoodBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.StandardRecordBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.rules.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.MatchingGoldStandard;
import de.uni_mannheim.informatik.dws.winter.model.Performance;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.CSVCorrespondenceFormatter;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import org.w3c.dom.Attr;

public class IR_SBTI_Forbes_using_linear_combination
{
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

		HashedDataSet<Company, Attribute>  dataSBTI = new HashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/sbti_integrated.xml"), "/Companies/Company", dataSBTI);

		//load the gold standard (test set)
		logger.info("*\tLoading gold standard\t*");
		MatchingGoldStandard gsSBTI_Forbes = new MatchingGoldStandard();
		gsSBTI_Forbes.loadFromCSVFile(new File(
				"data/goldstandard/sbti_forbes_goldstandard_test.csv"));

		
		// create a matching rule
		LinearCombinationMatchingRule<Company, Attribute> matchingRule = new LinearCombinationMatchingRule<>(
				0.8);
		matchingRule.activateDebugReport("data/output/debugResultsMatchingRule.csv", 1000, gsSBTI_Forbes);

		matchingRule.addComparator(new IndustryComparator(), 0.1);
		//matchingRule.addComparator(new CompanyNameComparatorJaro(), 1);
		matchingRule.addComparator(new CompanyNameComparatorJaroWrinkler(), 0.7);
		//matchingRule.addComparator(new CompanyNameComparatorLevenshtein(), 0.8);
		matchingRule.addComparator(new CountryComparatorJaroWrinkler(), 0.2);



		// create a blocker (blocking strategy)
		StandardRecordBlocker<Company, Attribute> blocker = new StandardRecordBlocker<Company, Attribute>(new CompanyBlockingKeyByNameGenerator());
		//NoBlocker<Company, Attribute> blocker = new NoBlocker<>();
		//SortedNeighbourhoodBlocker<Company, Attribute, Attribute> blocker = new SortedNeighbourhoodBlocker<>(new CompanyBlockingKeyByNameGenerator(), 1);
		blocker.setMeasureBlockSizes(true);
		//Write debug results to file:
		blocker.collectBlockSizeData("data/output/debugResultsBlocking.csv", 100);
		
		// Initialize Matching Engine
		MatchingEngine<Company, Attribute> engine = new MatchingEngine<>();

		// Execute the matching
		logger.info("*\tRunning identity resolution\t*");
		Processable<Correspondence<Company, Attribute>> correspondences = engine.runIdentityResolution(
				dataSBTI, dataForbes, null, matchingRule,
				blocker);
		
		// Create a top-1 global matching
		correspondences = engine.getTopKInstanceCorrespondences(correspondences, 1, 0.0);
/*
		 Alternative: Create a maximum-weight, bipartite matching
//		 MaximumBipartiteMatchingAlgorithm<Movie,Attribute> maxWeight = new MaximumBipartiteMatchingAlgorithm<>(correspondences);
//		 maxWeight.run();
//		 correspondences = maxWeight.getResult();
*/
		// write the correspondences to the output file
		new CSVCorrespondenceFormatter().writeCSV(new File("data/output/sbti_forbes_correspondences_LC.csv"), correspondences);
		
		logger.info("*\tEvaluating result\t*");
		// evaluate your result
		MatchingEvaluator<Company, Attribute> evaluator = new MatchingEvaluator<Company, Attribute>();
		Performance perfTest = evaluator.evaluateMatching(correspondences,
				gsSBTI_Forbes);

		// print the evaluation result
		logger.info("SBTI <-> Forbes2000");
		logger.info(String.format(
				"Precision: %.4f",perfTest.getPrecision()));
		logger.info(String.format(
				"Recall: %.4f",	perfTest.getRecall()));
		logger.info(String.format(
				"F1: %.4f",perfTest.getF1()));
    }
}