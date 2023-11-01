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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;

/**
 * A {@link XMLMatchableReader} for {@link Commpany}s.
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
		company.setCompanyName(getValueFromChildElement(node, "CompanyName"));
		company.setISIN(getValueFromChildElement(node, "ISIN"));
		company.setLEI(getValueFromChildElement(node, "LEI"));
		company.setForbes2022Rating(Integer.parseInt(getValueFromChildElement(node, "Forbes2022Rating")));
		company.setIndustries(getListFromChildElement(node, "Industries" ));
		company.setFoundedYear(Integer.parseInt(getValueFromChildElement(node, "FoundedYear")));
		company.setCountry(getValueFromChildElement(node, "Country"));
		company.setRegion(getValueFromChildElement(node, "Region"));
// Set KeyPersons

		company.setRevenue(Long.parseLong(getValueFromChildElement(node, "Revenue")));
		company.setAssets(Long.parseLong(getValueFromChildElement(node, "Assets")));
		company.setProfit(Long.parseLong(getValueFromChildElement(node, "Profit")));
		company.setMarketValue(Long.parseLong(getValueFromChildElement(node, "MarketValue")));
		company.setSizeEmployees(Integer.parseInt(getValueFromChildElement(node, "SizeEmployees")));
		company.setSizeCategory(getValueFromChildElement(node, "SizeCategory"));
		company.setLegalType(getValueFromChildElement(node, "LegalType"));
		company.setSustGoalDescription(getValueFromChildElement(node, "SustGoalDescription"));
		company.setSustGoalStatus_NearTerm(getValueFromChildElement(node, "SustGoalStatus_NearTerm"));
		company.setSustGoalStatus_LongTerm(getValueFromChildElement(node, "SustGoalStatus_LongTerm"));
		company.setSustGoalClassification_NearTerm(getValueFromChildElement(node, "SustGoalClassification_NearTerm"));
		company.setSustGoalClassification_LongTerm(getValueFromChildElement(node, "SustGoalClassification_LongTerm"));
		company.setSustGoalYear_NearTerm(Integer.parseInt(getValueFromChildElement(node, "SustGoalYear_NearTerm")));
		company.setSustGoalYear_LongTerm(Integer.parseInt(getValueFromChildElement(node, "SustGoalYear_LongTerm")));
		company.setNetZeroCommitted(Boolean.parseBoolean(getValueFromChildElement(node, "NetZeroCommitted")));
		company.setNetZeroCommittedYear(Integer.parseInt(getValueFromChildElement(node, "NetZeroCommittedYear")));


		// load the list of actors
		//List<Actor> actors = getObjectListFromChildElement(node, "actors",
		//		"actor", new ActorXMLReader(), provenanceInfo);
		//movie.setActors(actors);

		return company;
	}

}
