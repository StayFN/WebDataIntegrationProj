package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model;

import org.w3c.dom.Node;

import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.XMLMatchableReader;

/**
 * A {@link XMLMatchableReader} for {@link Actor}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class PersonXMLReader extends XMLMatchableReader<Person, Attribute> {

	@Override
	public Person createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");

		// create the object with id and provenance information
		Person person = new Person(id, provenanceInfo);

		// fill the attributes
		person.setName(getValueFromChildElement(node, "name"));
		
		String founder = getValueFromChildElement(node, "founder");
		if (founder.toLowerCase()=="true") {
			person.setFounder(true);
		}
		else if (founder.toLowerCase()=="false"){
			person.setFounder(false);
		}
		//person.setFounder((getValueFromChildElement(node, "founder")));

		String ceo = getValueFromChildElement(node, "ceo");
		if (ceo.toLowerCase()=="true") {
			person.setCEO(true);
		}
		else if (ceo.toLowerCase()=="false"){
			person.setCEO(false);
		}
		
		String other = getValueFromChildElement(node, "other");
		if (other.toLowerCase()=="true") {
			person.setOther(true);
		}
		else if (other.toLowerCase()=="false"){
			person.setOther(false);
		}
		

		return person;
	}

}
