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
		if (founder.equalsIgnoreCase("true")) {
			person.setFounder(true);
		}
		else if (founder.equalsIgnoreCase("false")){
			person.setFounder(false);
		}
		//person.setFounder((getValueFromChildElement(node, "founder")));

		String ceo = getValueFromChildElement(node, "ceo");
		if (ceo.equalsIgnoreCase("true")) {
			person.setCEO(true);
		}
		else if (ceo.equalsIgnoreCase("false")) {
			person.setCEO(false);
		}
		
		String other = getValueFromChildElement(node, "other");
		if (other.equalsIgnoreCase("other")) {
			person.setOther(true);
		}
		
		else if (other.equalsIgnoreCase("false")){
			person.setOther(false);
		}
		

		return person;
	}

}
