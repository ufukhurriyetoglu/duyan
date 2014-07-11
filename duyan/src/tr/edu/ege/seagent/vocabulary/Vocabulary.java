package tr.edu.ege.seagent.vocabulary;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class Vocabulary {

	public final String PER = "Person";
	public final String ORG = "Organization";
	public final String LOC = "Location";

	public final int WHOLE_UPPERCASE_THRESHOLD = 4;
	public final String FOAF_URI = "http://xmlns.com/foaf/0.1/";
	public final String DBPEDIA_OWL_URI = "http://dbpedia.org/ontology/";
	public final String WIKI_DISAMBIGUATES_PROPERTY = "wikiPageDisambiguates";
	
	public Property wikiPageDisambiguates = ResourceFactory.createProperty(
			DBPEDIA_OWL_URI, WIKI_DISAMBIGUATES_PROPERTY);

}
