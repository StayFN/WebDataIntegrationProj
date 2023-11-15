package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.solution;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.evaluation.ActorsEvaluationRule;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.evaluation.DateEvaluationRule;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.evaluation.DirectorEvaluationRule;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.evaluation.TitleEvaluationRule;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers.ActorsFuserUnion;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers.DateFuserFavourSource;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers.DateFuserVoting;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers.DirectorFuserLongestString;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers.TitleFuserShortestString;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.FusibleCompanyFactory;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.TitleFuserLongestString;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.CompanyXMLFormatter;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.CompanyXMLReader;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeFuser;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeFusionLogger;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.dws.winter.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEngine;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEvaluator;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionStrategy;
import de.uni_mannheim.informatik.dws.winter.model.DataSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleDataSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleHashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroup;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroupFactory;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import org.slf4j.Logger;

public class DataFusion_Main_Company 
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
		// Load the Data into FusibleDataSet
		logger.info("*\tLoading datasets\n*");
		FusibleDataSet<Company, Attribute> ds1 = new FusibleHashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/forbes_integrated.xml"), "/Companys/Company", ds1);
		ds1.printDataSetDensityReport();

		FusibleDataSet<Company, Attribute> ds2 = new FusibleHashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/sbti_integrated.xml"), "/Companys/Company", ds2);
		ds2.printDataSetDensityReport();

		FusibleDataSet<Company, Attribute> ds3 = new FusibleHashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/input/dbpedia_integrated.xml"), "/Companys/Company", ds3);
		ds3.printDataSetDensityReport();

		// Maintain Provenance
		// Scores (e.g. from rating)
		ds1.setScore(2.0);
		ds2.setScore(3.0);
		ds3.setScore(1.0);
		
		// Date (e.g. last update)
/*		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
		        .appendPattern("yyyy-MM-dd")
		        .parseDefaulting(ChronoField.CLOCK_HOUR_OF_DAY, 0)
		        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
		        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
		        .toFormatter(Locale.ENGLISH);
		
		ds1.setDate(LocalDateTime.parse("2012-01-01", formatter));
		ds2.setDate(LocalDateTime.parse("2010-01-01", formatter));
		ds3.setDate(LocalDateTime.parse("2008-01-01", formatter));
*/
		// load correspondences
		logger.info("*\tLoading correspondences\t*");
		CorrespondenceSet<Company, Attribute> correspondences = new CorrespondenceSet<>();
		correspondences.loadCorrespondences(new File("data/correspondences/forbes_2_sbti_correspondences.csv"),ds2, ds1);
		correspondences.loadCorrespondences(new File("data/correspondences/forbes_2_dbpedia_correspondences.csv"),ds3, ds1);
		correspondences.loadCorrespondences(new File("data/correspondences/dbpedia_2_forbes_correspondences.csv"),ds3, ds2);

		// write group size distribution
		correspondences.printGroupSizeDistribution();

		// load the gold standard 
		DataSet<Company, Attribute> gs = new FusibleHashedDataSet<>();
		new CompanyXMLReader().loadFromXML(new File("data/goldstandard/dbpedia_forbes_goldstandard.csv"), "/Companys/Company", gs);
	
		
		// define the fusion strategy
		DataFusionStrategy<Company, Attribute> strategy = new DataFusionStrategy<>(new CompanyXMLReader());
		
		// write debug results to file
		strategy.activateDebugReport("data/output/debugResultsDatafusion.csv", 1000, gs);
		
		// add attribute fusers
		strategy.addAttributeFuser(Company.COMPANYNAME, new CompanyNameFuserShortestString(),new CompanyNameEvaluationRule());
		strategy.addAttributeFuser(Company.COUNTRY,new CountryFuserFavourSource(), new DirectorEvaluationRule());
		strategy.addAttributeFuser(Company.INDUSTRY, new IndustryFuserIntersection(),new DateEvaluationRule());
		strategy.addAttributeFuser(Company.KEYPERSONS,new KeyPersonsFuserUnion(),new ActorsEvaluationRule());
		strategy.addAttributeFuser(Company.ASSETS,new AssetFuserMean(),new ActorsEvaluationRule());
		strategy.addAttributeFuser(Company.REVENUE,new RevenueFuserMean(),new ActorsEvaluationRule());
		strategy.addAttributeFuser(Company.YEARFOUNDED,new YearFoundedFuserMostRecent(),new ActorsEvaluationRule());
		// create the fusion engine
		DataFusionEngine<Company, Attribute> engine = new DataFusionEngine<Company, Attribute>(strategy);

		// print consistency report
		engine.printClusterConsistencyReport(correspondences, null);
		
		// run the fusion
		logger.info("*\tRunning data fusion\t*");
		FusibleDataSet<Company, Attribute> fusedDataSet = engine.run(correspondences, null);
		fusedDataSet.printDataSetDensityReport();
		// write the result
		new CompanyXMLFormatter().writeXML(new File("data/output/fused.xml"), fusedDataSet);

		// evaluate
		logger.info("*\tEvaluating results\t*");
		DataFusionEvaluator<Company, Attribute> evaluator = new DataFusionEvaluator<>(
				strategy, new RecordGroupFactory<Company, Attribute>());
		double accuracy = evaluator.evaluate(fusedDataSet, gs, null);

		logger.info(String.format("Accuracy: %.2f", accuracy));
    }
    
    
}
