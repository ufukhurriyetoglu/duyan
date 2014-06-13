package tr.edu.ege.seagent.politics;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class PoliticsTask {

	private static final String FOAF_PERSON = "http://xmlns.com/foaf/0.1/FullName";
	private static final String DBPEDIA_ORG = "http://dbpedia.org/ontology/Organisation";
	private static final String DBPEDIA_LOC =	"http://dbpedia.org/ontology/location";
	
	private static final String DBPEDIA_NAMES = "files/dbpediaNames.csv";
	private static final String POLITICONTO_OWL = "files/politico.rdf";

	private static Logger log = Logger.getLogger(PoliticsTask.class.getName());

	public static void main(String[] args) {
		
		 PropertyConfigurator.configure("log4j/log4j.properties");
		
		Model model = ModelFactory.createDefaultModel();
		// use the file manager to read an RDF document into the model
		// read the RDF/XML file
		model.read(POLITICONTO_OWL, "RDF/XML");

		Property rdfType = model
				.createProperty(DBPEDIA_LOC);
		StmtIterator baseIter = model.listStatements(null, rdfType,
				(RDFNode) null);

		log.info("hee");

		while (baseIter.hasNext()) {

			Statement nextStatement = baseIter.nextStatement();
			String subj = nextStatement.getSubject().toString();
			String obj = nextStatement.getObject().toString();
			log.info(subj + " is leaderOf : " + obj);
		}
		// log.debug("Loaded the model with no. statements = " +
		// baseIter.next().asTriple().toString());
	}

}
