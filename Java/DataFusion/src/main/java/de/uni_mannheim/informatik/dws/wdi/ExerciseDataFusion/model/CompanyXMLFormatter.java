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
//import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
//import de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model.Person;
import de.uni_mannheim.informatik.dws.winter.model.io.XMLFormatter;

/**
 * {@link XMLFormatter} for {@link Company}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class CompanyXMLFormatter extends XMLFormatter<Company> {

	
	    
	
	//CompanyXMLFormatter companyFormatter = new CompanyXMLFormatter();
	PersonXMLFormatter personFormatter = new PersonXMLFormatter();
	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("companies");
	}


	@Override
	public Element createElementFromRecord(Company record, Document doc) {
		Element company = doc.createElement("Company");

		
		company.appendChild(createTextElement("ID", record.getIdentifier(), doc));
		
		company.appendChild(createTextElement("CompanyName", record.getCompanyName(), doc));

		company.appendChild(createTextElement("ISIN", record.getIsin(), doc));
		
		company.appendChild(createTextElement("LEI",
				record.getLei(),
				doc));
		company.appendChild(createTextElement("forbes2022Rating", String.valueOf(record
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
		company.appendChild(industriesElement);
		
		
		company.appendChild(createTextElement("Country",
				record.getCountry(),
				doc));
		
		/*//This is how its done for the moviecase:
		company.appendChild(createTextElementWithProvenance("Country",
				record.getCountry(),
				record.getMergedAttributeProvenance(Company.COUNTRY), doc));
		//leads to <Country provenance=""/> in the output file 
		
		//for comparison how its done in the movie case:
		movie.appendChild(createTextElementWithProvenance("director",
				record.getDirector(),
				record.getMergedAttributeProvenance(Movie.DIRECTOR), doc));
		*/
		
		company.appendChild(createTextElement("Region",
				record.getRegion(),
				doc));
		
		company.appendChild(createTextElement("keyPersons",
				String.valueOf(record.getKeyPersons()),
				doc));
		
		company.appendChild(createTextElement("Revenue",
				String.valueOf(record.getRevenue()),
				doc));
		company.appendChild(createTextElement("Asset",
				record.getRegion(),
				doc));
		company.appendChild(createTextElement("Profit",
				String.valueOf(record.getProfit()),
				doc));

		//if any of the following are being uncommented we get error: 
		//Exception in thread "main" org.w3c.dom.DOMException: INVALID_CHARACTER_ERR: An invalid or illegal XML character is specified. 
		/*
		company.appendChild(createTextElement("Market Value",
				String.valueOf(record.getMarketValue()),
				doc));
		company.appendChild(createTextElement("Size Employees",
				String.valueOf(record.getSizeEmployees()),
				doc));
		company.appendChild(createTextElement("Size Category",
				String.valueOf(record.getSizeEmployees()),
				doc));
		company.appendChild(createTextElement("Legal Type",
				record.getLegalType(),
				doc));*/
		company.appendChild(createTextElement("SustGoalDescription",
				record.getSustGoalDescription(),
				doc));
		company.appendChild(createTextElement("SustGoalStatus_NearTerm",
				record.getSustGoalStatus_NearTerm(),
				doc));
		company.appendChild(createTextElement("SustGoalStatus_LongTerm",
				record.getSustGoalStatus_LongTerm(),
				doc));
		company.appendChild(createTextElement("SustGoalStatus_NearTerm",
				record.getRegion(),
				doc));
		company.appendChild(createTextElement("SustGoalClassification_NearTerm",
				record.getSustGoalClassification_NearTerm(),
				doc));
		company.appendChild(createTextElement("SustGoalClassification_LongTerm",
				record.getSustGoalClassification_LongTerm(),
				doc));
		company.appendChild(createTextElement("SustGoalYear_NearTerm",
				String.valueOf(record.getSustGoalYear_NearTerm()),
				doc));
		company.appendChild(createTextElement("SustGoalYear_LongTerm",
				String.valueOf(record.getSustGoalYear_NearTerm()),
				doc));
		company.appendChild(createTextElement("NetZeroCommitted",
				String.valueOf(record.isNetZeroCommitted()),
				doc));
		company.appendChild(createTextElement("NetZeroCommittedYear",
				String.valueOf(record.getNetZeroCommittedYear()),
				doc));
		
		
		 
		return company;
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

	/*
	protected Element createCompaniesElement(Company record, Document doc) {
		Element personRoot = personFormatter.createRootElement(doc);
		personRoot.setAttribute("provenance",
				record.getMergedAttributeProvenance(Company.KEYPERSONS));

		for (Person a : record.getKeyPersons()) {
			personRoot.appendChild(personFormatter
					.createElementFromRecord(a, doc));
		}

		return personRoot;
	}
*/
}
