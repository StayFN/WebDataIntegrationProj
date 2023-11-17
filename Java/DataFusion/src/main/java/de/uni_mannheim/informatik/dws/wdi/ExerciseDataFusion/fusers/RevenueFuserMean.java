package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers;

import java.time.LocalDateTime;

import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Movie;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.dws.winter.datafusion.conflictresolution.Voting;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.FusedValue;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroup;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
/**
 * {@link AttributeValueFuser} for the date of {@link Movie}s. 
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */

public class RevenueFuserMean extends AttributeValueFuser<Integer, Company, Attribute> {

	public RevenueFuserMean() {
		super(new Voting<Integer, Company, Attribute>());
	}
	
	@Override
	public boolean hasValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.hasValue(Company.REVENUE);
	}
	
	/*@Override
	public Integer getValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.getRevenue();
	}*/
	
	@Override
	public Integer getValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
	    Long revenue = record.getRevenue();
	    return revenue != null ? revenue.intValue() : null;
	}


	@Override
	public void fuse(RecordGroup<Company, Attribute> group, Company fusedRecord, Processable<Correspondence<Attribute, Matchable>> schemaCorrespondences, Attribute schemaElement) {
		FusedValue<Integer, Company, Attribute> fused = getFusedValue(group, schemaCorrespondences, schemaElement);
		fusedRecord.setRevenue(fused.getValue());
		fusedRecord.setAttributeProvenance(Company.REVENUE, fused.getOriginalIds());
	}

}
