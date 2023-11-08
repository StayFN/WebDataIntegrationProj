package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators;


import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.Comparator;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.ComparatorLogger;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;

import de.uni_mannheim.informatik.dws.winter.similarity.numeric.PercentageSimilarity;


public class AssetsComparatorPercentage implements Comparator<Company, Attribute> {

	private static final long serialVersionUID = 1L;
	private PercentageSimilarity sim = new PercentageSimilarity(0.99);
	
	private ComparatorLogger comparisonLog;
	
	@Override
	public double compare(
			Company record1,
			Company record2,
			Correspondence<Attribute, Matchable> schemaCorrespondences) {
    	
		if(record1.getAssets() == null || record2.getAssets() == null) {
			return 0.0;
		}
		
		double record1_double = record1.getAssets();
		double record2_double = record2.getAssets();
		
		
    	double similarity = sim.calculate(record1_double, record2_double);
    
    	
		if(this.comparisonLog != null){
			this.comparisonLog.setComparatorName(getClass().getName());
		
			this.comparisonLog.setRecord1Value(String.valueOf(record1_double));
			this.comparisonLog.setRecord2Value(String.valueOf(record2_double));
    	
			this.comparisonLog.setSimilarity(Double.toString(similarity));
		}
		return similarity;

	}

	@Override
	public ComparatorLogger getComparisonLog() {
		return this.comparisonLog;
	}

	@Override
	public void setComparisonLog(ComparatorLogger comparatorLog) {
		this.comparisonLog = comparatorLog;
	}
}
