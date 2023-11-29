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
package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.evaluation;

import java.util.HashSet;
import java.util.Set;

import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Person;
import de.uni_mannheim.informatik.dws.winter.datafusion.EvaluationRule;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

/**
 * {@link EvaluationRule} for the actors of {@link Company}s. The rule simply
 * compares the full set of actors of two {@link Company}s and returns true, in
 * case they are identical.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class KeyPersonEvaluationRule extends EvaluationRule<Company, Attribute> {

	@Override
	public boolean isEqual(Company record1, Company record2, Attribute schemaElement) {
		Set<String> keyperson1 = new HashSet<>();

		for (Person a : record1.getKeyPersons()) {
			// note: evaluating using the actor's name only suffices for simple
			// lists
			// in your project, you should have actor ids which you use here
			// (and in the identity resolution)
			keyperson1.add(a.getName());
		}

		Set<String> keyperson2 = new HashSet<>();
		for (Person a : record2.getKeyPersons()) {
			keyperson2.add(a.getName());
		}

		return keyperson1.containsAll(keyperson2) && keyperson2.containsAll(keyperson1);
	}

	/* (non-Javadoc)
	 * @see de.uni_mannheim.informatik.wdi.datafusion.EvaluationRule#isEqual(java.lang.Object, java.lang.Object, de.uni_mannheim.informatik.wdi.model.Correspondence)
	 */
	@Override
	public boolean isEqual(Company record1, Company record2,
			Correspondence<Attribute, Matchable> schemaCorrespondence) {
		return isEqual(record1, record2, (Attribute)null);
	}

}
