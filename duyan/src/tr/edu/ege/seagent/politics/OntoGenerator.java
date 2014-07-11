package tr.edu.ege.seagent.politics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.letter.LetterOperator;
import tr.edu.ege.seagent.lowercase.LuceneOperator;
import tr.edu.ege.seagent.similarity.JaroWinkler;
import tr.edu.ege.seagent.vocabulary.Vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.FileUtils;
import com.hp.hpl.jena.vocabulary.RDF;

public class OntoGenerator {

	// for general vocabulary terms
	Vocabulary voc = new Vocabulary();
	
	public TreeSet<SemanticTag> searchInOnto(TreeSet<SemanticTag> sTagList,
			TreeSet<String> nerList, String perFilePath, String locFilePath,
			String orgFilePath) {

		Model m = ModelFactory.createDefaultModel();

		generateModel(m, FOAF.Person, FOAF.name, "Person", perFilePath);

		// String fileName = "your_file_name_here.rdf";
		// FileWriter out;
		// try {
		// out = new FileWriter(fileName);
		// m.write(out, FileUtils.langNTriple);
		// out.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		generateModel(m, FOAF.Project, FOAF.name, "Location", locFilePath);
		generateModel(m, FOAF.Organization, FOAF.name, "Organization",
				orgFilePath);

		// removes items from TreeSet iteratively
		Iterator<String> i = nerList.iterator();
		while (i.hasNext()) {
			String ner = i.next();
			searchInModel(m, ner, sTagList);
			if (!sTagList.isEmpty())
				i.remove();
		}

		return sTagList;
	}

	public void searchInModel(Model m, String nerOld,
			TreeSet<SemanticTag> sTagList) {
		StmtIterator baseIter = m.listStatements();
		while (baseIter.hasNext()) {
			Statement nextStatement = baseIter.nextStatement();
			String subj = nextStatement.getSubject().toString();
			String obj = nextStatement.getObject().toString().replace("\"", "")
					.replace("_", "");
			// System.out.println(" s : " + subj + " - o :" + obj);

			String ner = "";
			// words having whole upper case letters.
			if (obj.length() > voc.WHOLE_UPPERCASE_THRESHOLD
					&& Character.isUpperCase(obj
							.charAt(voc.WHOLE_UPPERCASE_THRESHOLD))) {
				ner = new LetterOperator().convertStartWithUppercase(nerOld);
			} else
				ner = nerOld;
			if (obj.equals(ner)) {
				String objType = m
						.listStatements(nextStatement.getSubject(), RDF.type,
								(RDFNode) null).next().getObject().toString();
				String objDisambiguate = m
						.listStatements(nextStatement.getSubject(),
								voc.wikiPageDisambiguates, (RDFNode) null)
						.next().getObject().toString();

				String comparedSubj = subj.replace(
						"http://dbpedia.org/resource/", "");
				// double similarity = new
				// JaroWinkler().similarity(comparedSubj, ner);
				// System.out.println(ner + similarity + comparedSubj);
				// int longestSubstr = new WSD().longestSubstr(comparedSubj,
				// ner);
				// if (longestSubstr > 2)
				sTagList.add(new SemanticTag(obj, ner, objDisambiguate,
						defineType(objType), subj));
			} else {
				String mostWord = new JaroWinkler().mostSimilarString(obj,
						nerOld);
				if (obj.equals(mostWord)) {
					String objType = m
							.listStatements(nextStatement.getSubject(),
									RDF.type, (RDFNode) null).next()
							.getObject().toString();
					String objDisambiguate = m
							.listStatements(nextStatement.getSubject(),
									voc.wikiPageDisambiguates, (RDFNode) null)
							.next().getObject().toString();

					String comparedSubj = subj.replace(
							"http://dbpedia.org/resource/", "");
					// double similarity = new
					// JaroWinkler().similarity(comparedSubj, ner);
					// System.out.println(ner + similarity + comparedSubj);
					// int longestSubstr = new WSD().longestSubstr(comparedSubj,
					// ner);
					// if (longestSubstr > 2)
					sTagList.add(new SemanticTag(obj, ner, objDisambiguate,
							defineType(objType), subj));
				}
			}
		}

		for (SemanticTag semanticTag : sTagList) {
			System.out.println(semanticTag.getName() + semanticTag.getUri());
		}

	}

	/*
	 * Model has two properties; first one is rdf:type that contains Person,
	 * Location or Organization and second property is
	 * DBpedia-owl:wikiDisambiguates related to Word Sense Disambiguation(WSD)
	 */
	public void generateModel(Model m, Resource typeProp, Property nameProp,
			String type, String perFilePath) {
		ArrayList<SemanticTag> readFileDbpediaTree = new FileOperator()
				.ReadFileDbpedia(perFilePath, type);

		for (SemanticTag semanticTag : readFileDbpediaTree) {
			Resource r = m.createResource(semanticTag.getUri());

			r.addProperty(RDF.type, typeProp);
			r.addProperty(nameProp, semanticTag.getName());

			r.addProperty(voc.wikiPageDisambiguates,
					semanticTag.getDisambiguatedName());
		}
	}

	public void writeModelToFile(Model m, String filePath) {
		FileWriter out;
		try {
			out = new FileWriter(filePath);
			m.write(out, FileUtils.langNTriple);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * sets the type of the given object in the model.
	 */
	public String defineType(String obj) {
		String type;
		if (obj.equals(voc.FOAF_URI + voc.PER))
			type = voc.PER;
		else if (obj.equals(voc.FOAF_URI + voc.ORG))
			type = voc.ORG;
		else
			type = voc.LOC;
		return type;
	}

}
