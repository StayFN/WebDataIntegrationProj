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
package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers;

import java.time.LocalDateTime;

import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Movie;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.dws.winter.datafusion.conflictresolution.Voting;
import de.uni_mannheim.informatik.dws.winter.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.dws.winter.datafusion.conflictresolution.numeric.Median;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.FusedValue;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroup;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import org.w3c.dom.Attr;

/**
 * {@link AttributeValueFuser} for the date of {@link Movie}s. 
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class AssetFuserMean extends AttributeValueFuser<Double, Company, Attribute> {

	public AssetFuserMean()  {
		super(new Average<Company, Attribute>());
	}

	@Override
	public boolean hasValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.hasValue(Company.ASSETS);
	}
	
	/*@Override
	public Integer getValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.getAssets();
	}*/
	
	@Override
	public Double getValue(Company record, Correspondence<Attribute, Matchable> correspondence) {

	    Double assets = (double) record.getAssets() / 1000000000;
	    return assets;
	}


	@Override
	public void fuse(RecordGroup<Company, Attribute> group, Company fusedRecord, Processable<Correspondence<Attribute, Matchable>> schemaCorrespondences, Attribute schemaElement) {
		FusedValue<Double, Company, Attribute> fused = getFusedValue(group, schemaCorrespondences, schemaElement);
		Double value = fused.getValue();
		Long convertedValue = (long) (value * 1000000000);
		fusedRecord.setAssets(convertedValue);
		fusedRecord.setAttributeProvenance(Company.ASSETS, fused.getOriginalIds());
	}

}
