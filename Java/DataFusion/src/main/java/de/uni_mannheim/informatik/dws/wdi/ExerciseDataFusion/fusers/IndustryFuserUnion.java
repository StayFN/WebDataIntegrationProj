package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.fusers;


import java.util.List;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Actor;
import de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model.Company;
import de.uni_mannheim.informatik.dws.winter.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.dws.winter.datafusion.conflictresolution.list.Union;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.FusedValue;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroup;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;

/**
 * {@link AttributeValueFuser} for the actors of {@link Company}s.
 * 
 * @author Robert Meusel (robert@dwslab.de)
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class IndustryFuserUnion extends
		AttributeValueFuser<List<String>, Company, Attribute> {

	public IndustryFuserUnion() {
		super(new Union<String, Company, Attribute>());
	}

	
	@Override
	public void fuse(RecordGroup<Company, Attribute> group, Company fusedRecord, Processable<Correspondence<Attribute, Matchable>> schemaCorrespondences, Attribute schemaElement) {
		FusedValue<List<String>, Company, Attribute> fused = getFusedValue(group, schemaCorrespondences, schemaElement);
		fusedRecord.setIndustries(fused.getValue());
		fusedRecord
				.setAttributeProvenance(Company.INDUSTRY, fused.getOriginalIds());
	}
	
	/*
	@Override
	public void fuse(RecordGroup<Company, Attribute> group, Company fusedRecord, Processable<Correspondence<Attribute, Matchable>> schemaCorrespondences, Attribute schemaElement) {

		// get the fused value
		FusedValue<String, Company, Attribute> fused = getFusedValue(group, schemaCorrespondences, schemaElement);

		// set the value for the fused record
		fusedRecord.setCompanyName(fused.getValue());

		// add provenance info
		fusedRecord.setAttributeProvenance(Company.COMPANYNAME, fused.getOriginalIds());
	}
	*/
	
	
	
	@Override
	public boolean hasValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.hasValue(Company.INDUSTRY);
	}

	@Override
	public List<String> getValue(Company record, Correspondence<Attribute, Matchable> correspondence) {
		return record.getIndustries();
	}

	

}
