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
package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model;

import de.uni_mannheim.informatik.dws.winter.model.DataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.XMLMatchableReader;
import org.w3c.dom.Node;

import java.lang.reflect.Method;
import java.util.List;

/**
 * A {@link XMLMatchableReader} for {@link Company}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class CompanyXMLReader extends XMLMatchableReader<Company, Attribute>  {

	/* (non-Javadoc)
	 * @see de.uni_mannheim.informatik.wdi.model.io.XMLMatchableReader#initialiseDataset(de.uni_mannheim.informatik.wdi.model.DataSet)
	 */
	@Override
	protected void initialiseDataset(DataSet<Company, Attribute> dataset) {
		super.initialiseDataset(dataset);
		
	}
	
	@Override
	public Company createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "ID");

		// create the object with id and provenance information
		Company company = new Company(id, provenanceInfo);

		// fill the attributes
		setAttributeIfPresent(company, "CompanyName", node, "setCompanyName");
		setAttributeIfPresent(company, "ISIN", node, "setISIN");
		setAttributeIfPresent(company, "LEI", node, "setLEI");
		setIntegerAttributeIfPresent(company, "Forbes2022Rating", node, "setForbes2022Rating");
		setListAttributeIfPresent(company, "Industries", node, "setIndustries");
		setIntegerAttributeIfPresent(company, "FoundedYear", node, "setFoundedYear");
		setAttributeIfPresent(company, "Country", node, "setCountry");
		setAttributeIfPresent(company, "Region", node, "setRegion");


		List<Person> persons = getObjectListFromChildElement(node, "KeyPersons", "Person",
				new PersonXMLReader(), provenanceInfo);

		if (persons != null && !persons.isEmpty()) {
			company.setKeyPersons(persons);
		}

		setLongAttributeIfPresent(company, "Revenue", node, "setRevenue");
		setLongAttributeIfPresent(company, "Assets", node, "setAssets");
		setLongAttributeIfPresent(company, "Profit", node, "setProfit");
		setLongAttributeIfPresent(company, "MarketValue", node, "setMarketValue");
		setIntegerAttributeIfPresent(company, "SizeEmployees", node, "setSizeEmployees");
		setAttributeIfPresent(company, "SizeCategory", node, "setSizeCategory");
		setAttributeIfPresent(company, "LegalType", node, "setLegalType");
		setAttributeIfPresent(company, "SustGoalDescription", node, "setSustGoalDescription");
		setAttributeIfPresent(company, "SustGoalStatus_NearTerm", node, "setSustGoalStatus_NearTerm");
		setAttributeIfPresent(company, "SustGoalStatus_LongTerm", node, "setSustGoalStatus_LongTerm");
		setAttributeIfPresent(company, "SustGoalClassification_NearTerm", node, "setSustGoalClassification_NearTerm");
		setAttributeIfPresent(company, "SustGoalClassification_LongTerm", node, "setSustGoalClassification_LongTerm");
		setIntegerAttributeIfPresent(company, "SustGoalYear_NearTerm", node, "setSustGoalYear_NearTerm");
		setIntegerAttributeIfPresent(company, "SustGoalYear_LongTerm", node, "setSustGoalYear_LongTerm");
		setBooleanAttributeIfPresent(company, "NetZeroCommitted", node, "setNetZeroCommitted");
		setIntegerAttributeIfPresent(company, "NetZeroCommittedYear", node, "setNetZeroCommittedYear");

		return company;
	}
	private void setAttributeIfPresent(Company company, String elementName, Node node, String setterName) {
		String value = getValueFromChildElement(node, elementName);
		if (value != null && !value.isEmpty()) {
			try {
				Method setter = Company.class.getMethod(setterName, String.class);
				setter.invoke(company, value);
			} catch (Exception e) {
				e.printStackTrace(); // Handle or log the exception as needed
			}
		}
	}

	private void setIntegerAttributeIfPresent(Company company, String elementName, Node node, String setterName) {
		String value = getValueFromChildElement(node, elementName);
		if (value != null && !value.isEmpty()) {
			try {
				int intValue = Integer.parseInt(value);
				Method setter = Company.class.getMethod(setterName, int.class);
				setter.invoke(company, intValue);
			} catch (Exception e) {
				e.printStackTrace(); // Handle or log the exception as needed
			}
		}
	}

	private void setLongAttributeIfPresent(Company company, String elementName, Node node, String setterName) {
		String value = getValueFromChildElement(node, elementName);
		if (value != null && !value.isEmpty()) {
			try {
				long longValue = Long.parseLong(value);
				Method setter = Company.class.getMethod(setterName, long.class);
				setter.invoke(company, longValue);
			} catch (Exception e) {
				e.printStackTrace(); // Handle or log the exception as needed
			}
		}
	}

	private void setListAttributeIfPresent(Company company, String elementName, Node node, String setterName) {
		List<String> values = getListFromChildElement(node, elementName);
		if (values != null && !values.isEmpty()) {
			try {
				Method setter = Company.class.getMethod(setterName, List.class);
				setter.invoke(company, values);
			} catch (Exception e) {
				e.printStackTrace(); // Handle or log the exception as needed
			}
		}
	}

	private void setBooleanAttributeIfPresent(Company company, String elementName, Node node, String setterName) {
		String value = getValueFromChildElement(node, elementName);
		if (value != null && !value.isEmpty()) {
			try {
				boolean booleanValue = Boolean.parseBoolean(value);
				Method setter = Company.class.getMethod(setterName, boolean.class);
				setter.invoke(company, booleanValue);
			} catch (Exception e) {
				e.printStackTrace(); // Handle or log the exception as needed
			}
		}
	}


}
