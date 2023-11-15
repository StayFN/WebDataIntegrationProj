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
package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
//import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Person;
import de.uni_mannheim.informatik.dws.winter.model.io.XMLFormatter;

/**
 * {@link XMLFormatter} for {@link Company}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class CompanyXMLFormatter extends XMLFormatter<Company> {

	CompanyXMLFormatter companyFormatter = new CompanyXMLFormatter();
	PersonXMLFormatter personFormatter = new PersonXMLFormatter();
	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("companies");
	}


	@Override
	public Element createElementFromRecord(Company record, Document doc) {
		Element Company = doc.createElement("Company");

		Company.appendChild(createTextElement("ID", record.getIdentifier(), doc));

		Company.appendChild(createTextElement("ISIN",
				record.getISIN(),
				doc));
		Company.appendChild(createTextElement("LEI",
				record.getLEI(),
				doc));
		Company.appendChild(createTextElement("forbes2022Rating", String.valueOf(record
				.getForbes2022Rating()), doc));
		
		List<String> industries = record.getIndustries(); // Assuming 'record' is an object that has a method 'getIndustries' that returns a list of industries.

		// Create the "Industries" parent element
		Element industriesElement = doc.createElement("Industries");
		for (String industry : industries) {
		    // Create a text element for each industry and append it to the "Industries" element
		    Element industryElement = doc.createElement("Industry");
		    industryElement.appendChild(doc.createTextNode(industry));
		    industriesElement.appendChild(industryElement);
		}
		Company.appendChild(industriesElement);
		
		
		Company.appendChild(createTextElement("Country",
				record.getCountry(),
				doc));
		Company.appendChild(createTextElement("Region",
				record.getRegion(),
				doc));
		Company.appendChild(createTextElement("keyPersons",
				String.valueOf(record.getKeyPersons()),
				doc));
		Company.appendChild(createTextElement("Revenue",
				String.valueOf(record.getRevenue()),
				doc));
		Company.appendChild(createTextElement("Asset",
				record.getRegion(),
				doc));
		Company.appendChild(createTextElement("Profit",
				String.valueOf(record.getProfit()),
				doc));
		Company.appendChild(createTextElement("Revenue",
				String.valueOf(record.getRevenue()),
				doc));
		Company.appendChild(createTextElement("Market Value",
				String.valueOf(record.getMarketValue()),
				doc));
		Company.appendChild(createTextElement("Size Employees",
				String.valueOf(record.getSizeEmployees()),
				doc));
		Company.appendChild(createTextElement("Size Category",
				String.valueOf(record.getSizeEmployees()),
				doc));
		Company.appendChild(createTextElement("Legal Type",
				record.getLegalType(),
				doc));
		Company.appendChild(createTextElement("SustGoalDescription",
				record.getSustGoalDescription(),
				doc));
		Company.appendChild(createTextElement("SustGoalStatus_NearTerm",
				record.getSustGoalStatus_NearTerm(),
				doc));
		Company.appendChild(createTextElement("SustGoalStatus_LongTerm",
				record.getSustGoalStatus_LongTerm(),
				doc));
		Company.appendChild(createTextElement("SustGoalStatus_NearTerm",
				record.getRegion(),
				doc));
		Company.appendChild(createTextElement("SustGoalClassification_NearTerm",
				record.getSustGoalClassification_NearTerm(),
				doc));
		Company.appendChild(createTextElement("SustGoalClassification_LongTerm",
				record.getSustGoalClassification_LongTerm(),
				doc));
		Company.appendChild(createTextElement("SustGoalYear_NearTerm",
				String.valueOf(record.getSustGoalYear_NearTerm()),
				doc));
		Company.appendChild(createTextElement("SustGoalYear_LongTerm",
				String.valueOf(record.getSustGoalYear_NearTerm()),
				doc));
		Company.appendChild(createTextElement("NetZeroCommitted",
				String.valueOf(record.isNetZeroCommitted()),
				doc));
		Company.appendChild(createTextElement("NetZeroCommittedYear",
				String.valueOf(record.getNetZeroCommittedYear()),
				doc));
		
		Company.appendChild(createKeyPersonsElement(record, doc));

		return Company;
	}


	protected Element createKeyPersonsElement(Company record, Document doc) {
		Element personRoot = personFormatter.createRootElement(doc);

		for (Person a : record.getKeyPersons()) {
			personRoot.appendChild(personFormatter.createElementFromRecord(a, doc));
		}

		return personRoot;
	}

	protected Element createTextElementWithProvenance(String name,
			String value, String provenance, Document doc) {
		Element elem = createTextElement(name, value, doc);
		elem.setAttribute("provenance", provenance);
		return elem;
	}

	/*protected Element createActorsElement(Company record, Document doc) {
		Element actorRoot = actorFormatter.createRootElement(doc);
		actorRoot.setAttribute("provenance",
				record.getMergedAttributeProvenance(Company.ACTORS));

		for (Actor a : record.getActors()) {
			actorRoot.appendChild(actorFormatter
					.createElementFromRecord(a, doc));
		}

		return actorRoot;
	}*/

}
