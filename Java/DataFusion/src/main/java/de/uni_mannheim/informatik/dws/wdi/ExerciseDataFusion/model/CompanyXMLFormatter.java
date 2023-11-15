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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.dws.winter.model.io.XMLFormatter;

/**
 * {@link XMLFormatter} for {@link Company}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class CompanyXMLFormatter extends XMLFormatter<Company> {

	CompanyXMLFormatter companyFormatter = new CompanyXMLFormatter();

	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("companies");
	}

	@Override
	public Element createElementFromRecord(Company record, Document doc) {
		Element Company = doc.createElement("Company");

		Company.appendChild(createTextElement("id", record.getIdentifier(), doc));

		Company.appendChild(createTextElementWithProvenance("Company Name",
				record.getCompanyName(),
				record.getMergedAttributeProvenance(Company.COMPANYNAME), doc));
		Company.appendChild(createTextElementWithProvenance("Country",
				record.getCountry(),
				record.getMergedAttributeProvenance(Company.COUNTRY), doc));
		Company.appendChild(createTextElementWithProvenance("Industry", record
				.getIndustries().toString(), record
				.getMergedAttributeProvenance(Company.INDUSTRY), doc));
		Company.appendChild(createTextElementWithProvenance("Year Founded", record
				.getFoundedYear().toString(), record
				.getMergedAttributeProvenance(Company.YEARFOUNDED), doc));
		Company.appendChild(createTextElementWithProvenance("Industry", record
				.getIndustry().toString(), record
				.getMergedAttributeProvenance(Company.INDUSTRY), doc));
		Company.appendChild(createTextElementWithProvenance("Key Persons", record
				.getKeyPersons().toString(), record
				.getMergedAttributeProvenance(Company.KEYPERSONS), doc));
		Company.appendChild(createTextElementWithProvenance("Revenue", record
				.getRevenue().toString(), record
				.getMergedAttributeProvenance(Company.REVENUE), doc));
		Company.appendChild(createTextElementWithProvenance("Assets", record
				.getAssets().toString(), record
				.getMergedAttributeProvenance(Company.ASSETS), doc));
		Company.appendChild(createActorsElement(record, doc));

		return Company;
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
