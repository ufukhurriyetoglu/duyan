package tr.edu.ege.seagent.politics;

import java.util.Iterator;
import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.letter.LetterOperator;
import tr.edu.ege.seagent.similarity.JaroWinkler;

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

	public TreeSet<SemanticTag> searchInOnto(TreeSet<SemanticTag> sTagList,
			TreeSet<String> nerList, String perFilePath, String locFilePath,
			String orgFilePath) {

		// TreeSet<SemanticTag> sTagList = new TreeSet<SemanticTag>();
		Model m = ModelFactory.createDefaultModel();

		// String perFilePath = "/files/dbpediaNames.csv";
		// String locFilePath = "/files/dbpediaLocations.csv";
		// String orgFilePath = "/files/DbpediaOrg.csv";

		generateModel(m, FOAF.Person, FOAF.name, "Person", perFilePath);
		generateModel(m, FOAF.Project, FOAF.name, "Location", locFilePath);
		generateModel(m, FOAF.Organization, FOAF.name, "Organization",
				orgFilePath);

		// m.write(System.out, FileUtils.langNTriple);

		// for (String ner : nerList) {
		// searchInModel(sTagList, m, ner);
		// if (!sTagList.isEmpty())
		// nerList.remove(new String(ner));
		// }

		Iterator<String> i = nerList.iterator();
		while (i.hasNext()) {
			String ner = i.next(); // must be called before you can call
									// i.remove()
			searchInModel(sTagList, m, ner);
			if (!sTagList.isEmpty())
				// nerList.remove(new String(ner));
				i.remove();
		}

		return sTagList;
	}

	private void searchInModel(TreeSet<SemanticTag> sTagList, Model m,
			String nerOld) {
		StmtIterator baseIter = m.listStatements();
		while (baseIter.hasNext()) {
			Statement nextStatement = baseIter.nextStatement();
			String subj = nextStatement.getSubject().toString();
			String obj = nextStatement.getObject().toString().replace("\"", "");

			String ner = "";
			// hepsi büyük harfli kelimeler için
//			System.out.println("HOOO : " + obj);
			if (obj.length() > 4 && Character.isUpperCase(obj.charAt(4))) {
				ner = new LetterOperator().convertStartWithUppercase(nerOld);
			} else
				ner = nerOld;

			if (obj.equals(ner)) {
				String objType = m
						.listStatements(nextStatement.getSubject(), RDF.type,
								(RDFNode) null).next().getObject().toString();
//				System.out.println(" THE MOST : " + ner + " sim : " + obj
//						+ " - " + ner);
//				System.out.println("Name : " + obj + " URI : " + subj
//						+ " Type :" + defineType(objType));
				sTagList.add(new SemanticTag(obj, ner, defineType(objType),
						subj));
			} else {
				String mostWord = new JaroWinkler().mostSimilarString(obj,
						nerOld);
				if (obj.equals(mostWord)) {
					String objType = m
							.listStatements(nextStatement.getSubject(),
									RDF.type, (RDFNode) null).next()
							.getObject().toString();
//					System.out.println(" THE MOST : " + mostWord + " sim : "
//							+ obj + " - " + ner);
//					System.out.println("Name : " + obj + " URI : " + subj
//							+ " Type :" + defineType(objType));
					sTagList.add(new SemanticTag(obj, ner, defineType(objType),
							subj));
				}
			}
		}
	}

	private String defineType(String obj) {
		String type;
		if (obj.equals("http://xmlns.com/foaf/0.1/Person"))
			type = "Person";
		else if (obj.equals("http://xmlns.com/foaf/0.1/Organization"))
			type = "Organization";
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

}
