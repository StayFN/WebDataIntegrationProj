
package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators;

import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.Comparator;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.ComparatorLogger;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.similarity.numeric.AbsoluteDifferenceSimilarity;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;


public class FoundedYearComparator implements Comparator<Company, Attribute> {

	private static final long serialVersionUID = 1L;
	
	private ComparatorLogger comparisonLog;

	private AbsoluteDifferenceSimilarity sim = new AbsoluteDifferenceSimilarity(2);
	
	@Override
	public double compare(
			Company record1,
			Company record2,
			Correspondence<Attribute, Matchable> schemaCorrespondences) {
    	
		double record1_double = record1.getFoundedYear();
		double record2_double = record2.getFoundedYear();   
		double similarity = sim.calculate(record1_double, record2_double);
		
		if(this.comparisonLog != null){
			this.comparisonLog.setComparatorName(getClass().getName());
		
			this.comparisonLog.setRecord1Value(record1.toString());
			this.comparisonLog.setRecord2Value(record2.toString());
    	
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

