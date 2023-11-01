package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.dws.winter.model.io.XMLFormatter;

public class PersonXMLFormatter extends XMLFormatter<Person> {

	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("keyPersons");
	}

	@Override
	public Element createElementFromRecord(Person record, Document doc) {
		Element person = doc.createElement("person");

		person.appendChild(createTextElement("name", record.getName(), doc));

		return person;
	}

}
