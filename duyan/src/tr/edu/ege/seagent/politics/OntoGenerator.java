package tr.edu.ege.seagent.politics;

import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.fileio.FileOperator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class OntoGenerator {

	// public static void main(String[] args) {
	//
	// // m.write(System.out, FileUtils.langNTriple);
	//
	// // String ontFilePath = "files/foafExample.rdf";
	// // FileWriter out;
	// // try {
	// // out = new FileWriter(fileName);
	// // m.write(out, FileUtils.langTurtle);
	// // } catch (IOException e) {
	// // e.printStackTrace();
	// // }
	//
	// // m.read(ontFilePath, FileUtils.langNTriple);
	//
	// // new OntoGenerator().searchInOnto("Devlet Bahçeli");
	//
	// }

	public TreeSet<SemanticTag> searchInOnto(TreeSet<String> nerList,
			String perFilePath, String locFilePath, String orgFilePath) {

		TreeSet<SemanticTag> sTagList = new TreeSet<SemanticTag>();
		Model m = ModelFactory.createDefaultModel();

		// String perFilePath = "/files/dbpediaNames.csv";
		// String locFilePath = "/files/dbpediaLocations.csv";
		// String orgFilePath = "/files/DbpediaOrg.csv";

		generateModel(m, FOAF.Person, FOAF.name, "Person", perFilePath);
		generateModel(m, FOAF.Project, FOAF.name, "Location", locFilePath);
		generateModel(m, FOAF.Organization, FOAF.name, "Organization",
				orgFilePath);

		// m.write(System.out, FileUtils.langNTriple);

		for (String ner : nerList) {
			searchInModel(sTagList, m, ner);
		}
		return sTagList;
	}

	private void searchInModel(TreeSet<SemanticTag> sTagList, Model m,
			String ner) {
		StmtIterator baseIter = m.listStatements();
		while (baseIter.hasNext()) {
			Statement nextStatement = baseIter.nextStatement();
			String subj = nextStatement.getSubject().toString();
			String obj = nextStatement.getObject().toString();

			if (obj.equals("\"" + ner + "\"")) {
				String objType = m
						.listStatements(nextStatement.getSubject(), RDF.type,
								(RDFNode) null).next().getObject().toString();
				System.out.println("Name : " + obj + " URI : " + subj
						+ " Type :" + defineType(objType));
				sTagList.add(new SemanticTag(obj, defineType(objType), subj));
			}
		}
	}

	private String defineType(String obj) {
		String type;
		if (obj.equals("http://xmlns.com/foaf/0.1/Person"))
			type = "Person";
		else if (obj.equals("http://xmlns.com/foaf/0.1/Organization"))
			type = "Organisation";
		else
			type = "Location";
		return type;
	}

	private static void generateModel(Model m, Resource typeProp,
			Property nameProp, String type, String perFilePath) {
		TreeSet<SemanticTag> readFileDbpediaTree = new FileOperator()
				.ReadFileDbpedia(perFilePath, type);

		for (SemanticTag semanticTag : readFileDbpediaTree) {
			Resource r = m.createResource(semanticTag.getUri());

			r.addProperty(RDF.type, typeProp);
			r.addProperty(nameProp, semanticTag.getName());
		}
	}

}
