package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.Comparator;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.ComparatorLogger;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.similarity.string.LevenshteinSimilarity;

public class CountryComparatorLevenshtein implements Comparator<Company, Attribute> {

	private static final long serialVersionUID = 1L;
	private LevenshteinSimilarity sim = new LevenshteinSimilarity();
	
	private ComparatorLogger comparisonLog;

	@Override
	public double compare(
			Company record1,
			Company record2,
			Correspondence<Attribute, Matchable> schemaCorrespondences) {
		
		String s1 = record1.getCountry();
		String s2 = record2.getCountry();
    	
		// preprocessing
		s1 = preprocessCompanyName(s1);
		s2 = preprocessCompanyName(s2);
		
    	double similarity = sim.calculate(s1, s2);
    	
		if(this.comparisonLog != null){
			this.comparisonLog.setComparatorName(getClass().getName());
		
			this.comparisonLog.setRecord1Value(s1);
			this.comparisonLog.setRecord2Value(s2);
    	
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
		
	// Preprocessing method to remove specific substrings and text within brackets
    private String preprocessCompanyName(String companyName) {
        if (companyName != null) {
            // Standardize USA
            companyName = companyName.replaceAll("U.S.", "United States");
            companyName = companyName.replaceAll("US", "United States");
            companyName = companyName.replaceAll("USA", "United States");
            companyName = companyName.replaceAll("United States of America", "United States");
            companyName = companyName.replaceAll("U.S", "United States");
           // Standardize UK
            companyName = companyName.replaceAll("UK", "United Kingdom");
            companyName = companyName.replaceAll("England", "United Kingdom");
        

            // Trim leading and trailing spaces
            companyName = companyName.trim();
            companyName = companyName.toLowerCase();
    		} 
        else {
			companyName = "";
		}
        return companyName;
        }
}
