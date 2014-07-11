package tr.edu.ege.seagent.lowercase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import tr.edu.ege.seagent.deasciifier.TurkishDeasciifier;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.timer.TimeWatch;
import tr.edu.ege.seagent.vocabulary.Vocabulary;

public class LuceneOperator {

	private static final int MAX_HITS_SIZE = 20;

	private static final String PUNCT_PATTERN = "['\"‘’,.:-;!-?(){}\\[\\]<>%]";

	public static final String FILES_TO_INDEX_DIRECTORY = "/home/etmen/Desktop/files/";
	public static final String INDEX_DIRECTORY = "/home/etmen/Desktop/index/";

	public static final String FIELD_PATH = "path";
	public static final String FIELD_URL = "url";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CONTENTS = "contents";

	public static Logger LOG = Logger.getLogger(LuceneOperator.class);

	public static void main(String[] args) {

		TimeWatch watch = TimeWatch.start();
		String deasciiFullPath = "files/turkishPatternTable";

//		String content = "mhp genel başkanı devlet bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday belirleme temasları çerçevesinde chp genel başkanı kemal kılıçdaroğlu ile görüştü.";

		String content = "AK Parti istifa eden İstanbul Bağımsız Milletvekili devlet bahçeli, Twitter'dan dershanelerin kapatılması ve PKK'nın kaçırdığı çocuklarla ilgili açıklama yaptı.";

		// deasciify process
		content = new TurkishDeasciifier().deasciifySentence(content,
				deasciiFullPath);
		// new LuceneOperator().createIndex(FILES_TO_INDEX_DIRECTORY,
		// INDEX_DIRECTORY);

		new LuceneOperator().searchIndex(content, INDEX_DIRECTORY);
		long passedTimeInSeconds = watch.time(TimeUnit.MILLISECONDS);
		LOG.info("Passed Time : " + passedTimeInSeconds + " MILLISECONDS.");
	}

	/**
	 * 
	 * fetches the named entities from given content.
	 * 
	 * @param content
	 * @param index_directory
	 * @return generatedHitsList
	 */
	public HashSet<JsonEntity> searchIndex(String content,
			String index_directory) {
		HashSet<JsonEntity> generatedHitsList = new HashSet<JsonEntity>();
		IndexReader reader;
		try {
			reader = DirectoryReader.open(FSDirectory.open(new File(
					index_directory)));

			content = content.replaceAll(PUNCT_PATTERN, " ");
			generatedHitsList = generateMentionList(reader, content);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return generatedHitsList;
	}

	/**
	 * 
	 * searches hits in the content and then generates mentions from matched
	 * hits. Mentions are the references of the entities seen on the interface.
	 * 
	 * @param content
	 * @param generatedMentionList
	 * @param reader
	 * @return generatedHitsList
	 */
	public HashSet<JsonEntity> generateMentionList(IndexReader reader,
			String content) {
		IndexSearcher searcher = new IndexSearcher(reader);
		HashSet<JsonEntity> generatedMentionList = new HashSet<JsonEntity>();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
		QueryParser parser = new QueryParser(Version.LUCENE_4_9, FIELD_NAME,
				analyzer);
		Query query;
		try {
			query = parser.parse(content);

			TopDocs results = searcher.search(query, MAX_HITS_SIZE);
			ScoreDoc[] hits = results.scoreDocs;

			if (hits.length > 0) {
				LOG.info("Content:" + content);
				for (int i = 0; i < hits.length; i++) {
					Document document = searcher.doc(hits[i].doc);
					String uri = document.get(FIELD_URL);
					if (uri != null) {
						// String name = document.get(FIELD_NAME).toLowerCase()
						// .replaceAll(PUNCT_PATTERN, "");
						String name = document.get(FIELD_NAME).toLowerCase()
								.replace("\"", "");
						String path = document.get(FIELD_PATH);

						// indicates the start and end positions of entities
						// used for highlighting them on the interface.
						acquireEntities(content, uri, name, path,
								generatedMentionList);
					} else
						LOG.info("No indexed data for this document.");
				}
			}
		} catch (ParseException | IOException e) {
			LOG.error(MessageFormat.format("{0} : şundan hata : {1}",
					e.getMessage(), e.getCause()));
		}
		return generatedMentionList;
	}

	/**
	 * 
	 * finds the start and end positions of the named entities in given content.
	 * 
	 * @param content
	 * @param uri
	 * @param name
	 * @param path
	 * @param generatedMentionList
	 * @return nerList
	 */
	public HashSet<JsonEntity> acquireEntities(String content, String uri,
			String name, String path, HashSet<JsonEntity> generatedMentionList) {
		int cnt = 1;
		if (content.toLowerCase().contains(name)) {

			// find all occurrences forward
			for (int beginOffset = -1; (beginOffset = content.toLowerCase()
					.indexOf(name, beginOffset + 1)) != -1;) {

				int endOffset = name.length() + beginOffset;

				if (!generatedMentionList.contains(new JsonEntity(name,
						beginOffset, endOffset))) {
					LOG.info("-name : " + name + " begin :" + beginOffset
							+ " -end :" + endOffset);
					generatedMentionList.add(new JsonEntity(name, uri, "T"
							+ cnt, defineEntityType(path), beginOffset,
							endOffset));
				}
				cnt++;
			}
		}
		return generatedMentionList;
	}

	/**
	 * 
	 * creates index directory from files in the filesToOndexDir
	 * 
	 * @param filesToIndexDir
	 * @param index_directory
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void createIndex(String filesToIndexDir, String index_directory) {

		try {
			Directory dir = FSDirectory.open(new File(INDEX_DIRECTORY));
			// TODO Additional stop words can be inserted for specialized for
			// Turkish.
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_9,
					analyzer);

			// Create a new index in the directory, removing any
			// previously indexed documents:
			// iwc.setOpenMode(OpenMode.CREATE);
			// iwc.setRAMBufferSizeMB(256.0);
			IndexWriter writer = new IndexWriter(dir, iwc);
			indexFileContent(writer, filesToIndexDir);

			// writer.forceMerge(1);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * index each file content(uri, name, path) of files in the index directory.
	 * 
	 * @param filesToIndexDir
	 * @param indexWriter
	 */
	public void indexFileContent(IndexWriter indexWriter, String filesToIndexDir) {

		try {
			File dir = new File(filesToIndexDir);
			if (!dir.exists() || !dir.canRead())
				System.out.println(dir.getAbsolutePath()
						+ " does not exist or is not readable");
			if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (File file : files) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(new FileInputStream(file),
									StandardCharsets.UTF_8));

					String line = "";
					String cvsSplitBy = ",";
					while ((line = br.readLine()) != null) {
						Document doc = new Document();
						// comma as delimiter
						String[] contentList = line.split(cvsSplitBy);
						doc.add(new Field(FIELD_URL, contentList[0],
								identifyFieldType()));
						doc.add(new Field(FIELD_NAME, contentList[1],
								identifyFieldType()));
						doc.add(new Field(FIELD_PATH, file.getPath(),
								identifyFieldType()));
						indexWriter.addDocument(doc);
					}
					br.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * indicates type of the selected named entity based on the file(dbpedia
	 * sparql resultset of Turkish names,organizations and locations).
	 * 
	 * @param path
	 * @return type
	 */
	public String defineEntityType(String path) {
		String type = "";
		if (path.contains("Names")) {
			type = new Vocabulary().PER;
		} else if (path.contains("Org")) {
			type = new Vocabulary().ORG;
		} else {
			type = new Vocabulary().LOC;
		}
		return type;
	}

	public FieldType identifyFieldType() {
		FieldType fieldType = new FieldType();
		fieldType.setIndexed(true);
		fieldType.setStored(true);
		fieldType.setTokenized(true);
		fieldType.setStoreTermVectors(true);
		// fieldType
		// .setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		return fieldType;
	}
}
