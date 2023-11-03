/*
 * Copyright (c) 2017 Data and Web Science Group, University of Mannheim, Germany (http://dws.informatik.uni-mannheim.de/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators;

import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Movie;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.Comparator;
import de.uni_mannheim.informatik.dws.winter.matching.rules.comparators.ComparatorLogger;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.similarity.string.LevenshteinSimilarity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IndustryComparator implements Comparator<Company, Attribute> {

	private static final long serialVersionUID = 1L;
	private LevenshteinSimilarity sim = new LevenshteinSimilarity();
	private ComparatorLogger comparisonLog;
	private static final double SIMILARITY_SCORE_THRESHOLD = 0.5;

	// Preprocess the industry names to standardize them.
	private String preprocess(String industry) {
		return industry.toLowerCase().trim();
	}

	// Calculate the Overlap Coefficient.
	private double calculateOverlapCoefficient(Set<String> set1, Set<String> set2) {
		int intersectionSize = 0;
		for (String element : set1) {
			if (findSimilarWithScore(element, set2) >= SIMILARITY_SCORE_THRESHOLD) {
				intersectionSize++;
			}
		}
		return (double) intersectionSize / Math.min(set1.size(), set2.size());
	}

	// Find the maximum similarity score for an element within a set.
	private double findSimilarWithScore(String element, Set<String> set) {
		double maxScore = 0.0;
		for (String target : set) {
			double score = calculate(element, target);
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private double calculate(String s1, String s2) {
		// Placeholder for the actual similarity function.
		// return LevenshteinDistance.computeLevenshteinDistance(source, target);
		return sim.calculate(s1, s2);
	}

	// Compare two records based on their industry lists.
	@Override
	public double compare(
			Company record1,
			Company record2,
			Correspondence<Attribute, Matchable> schemaCorrespondences) {

		List<String> industries1 = record1.getIndustries();
		List<String> industries2 = record2.getIndustries();

		// Preprocess and transform lists into sets for comparison.
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		for (String industry : industries1) {
			set1.add(preprocess(industry));
		}
		for (String industry : industries2) {
			set2.add(preprocess(industry));
		}

		// Compute similarity using the overlap coefficient and additional similarity rule.
		return calculateOverlapCoefficient(set1, set2);
	}

	@Override
	public ComparatorLogger getComparisonLog() {
		return this.comparisonLog;
	}

	@Override
	public void setComparisonLog(ComparatorLogger comparatorLog) {
		this.comparisonLog = comparatorLog;
	}


	public static void testCompare () {
		// Create your IndustryComparator instance
		IndustryComparator comparator = new IndustryComparator();

		// Create some test records with industries
		Company record1 = new Company("1", "test1");
		Company record2 = new Company("2", "test2");

		List<String> industries1 = Arrays.asList("Technology", "Software");
		List<String> industries2 = Arrays.asList("Technologies"/*, "IT", "Software Development"*/);

		record1.setIndustries(industries1);
		record2.setIndustries(industries2);
		double similarityScore = comparator.compare(record1, record2, null);

		int x = 0;
		// Assert that the similarity score is as expected (this threshold is just an example)
	}


	/* For quick testing

	public static void main(String[] args) throws Exception {

		testCompare();
	}
	*/
}
