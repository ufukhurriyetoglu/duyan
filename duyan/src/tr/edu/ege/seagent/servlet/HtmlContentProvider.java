package tr.edu.ege.seagent.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.main.TextAnalyser;
import tr.edu.ege.seagent.strategy.CapitalLetterCompositeStrategy;
import tr.edu.ege.seagent.task.CapitalLetterTask;

import com.hp.hpl.jena.util.FileUtils;

public class HtmlContentProvider {

	private static final String EMPTY_TEXT_MESSAGE = "Lütfen önce metin giriniz!";
	private static final String NULL_TEXT_MESSAGE = "Vites varlık ismi tanımlayamadan boş sonuç gönderdi!";
	private static final String DBPEDIA_TEXT_MESSAGE = "DBpedia geçici olarak hizmet dışıdır.";

	public String htmlHeadCode = "<html> \n "
			+ " <head> "
			+ " <META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset="
			+ FileUtils.encodingUTF8 + "\"> "
			+ " <META HTTP-EQUIV=\"Content-language\" CONTENT=\"tr\"> ";

	public String title = "<h1>Vites : Türkçe Varlık İsmi Tanımlama Sistemi</h1>";

	public String getVitesContent(String resultContent) {
		String HTML = htmlHeadCode + " <style type=\"text/css\"> "
				+ " .tooltip{ " + "	display: inline; "
				+ "	position: relative; }" + "	.tooltip:hover:after{ "
				+ "	background: #333; " + "background: rgba(0,0,0,.8);"
				+ "border-radius: 5px;" + "bottom: 26px;" + "color: #fff;"
				+ "content: attr(title);" + "left: 25%;" + "text-align:center;"
				+ "padding: 5px 5px;" + "position: absolute;" + "z-index: 98;"
				+ "width: 80px; }"

				+ ".tooltip:hover:before{" + "	border: solid;"
				+ " border-color: #333 transparent; "
				+ "	border-width: 6px 6px 0 6px;" + "	bottom: 20px;"
				+ "	content: \"\";" + "	left: 50%;" + "	position: absolute;"
				+ "	z-index: 99; } </style> " + "</head> \n" + "<body> "
				+ title + "<hr/>" + "<div><br/><br/><br/><p> " + " <br/><br/>"
				+ resultContent + "</p></div>" + " </body> \n" + "</html>";
		return HTML;
	}

	public String getBratContent(String jsonResult) {
		return htmlHeadCode
				+ " <link rel=\"stylesheet\" type=\"text/css\" href=\"http://weaver.nlplab.org/~brat/demo/v1.3/style-vis.css\" /> "
				+ " <script type=\"text/javascript\" src=\"http://weaver.nlplab.org/~brat/demo/v1.3/client/lib/head.load.min.js\"></script>"
				+ " <script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js\"></script>"
				+ " <script type=\"text/javascript\"> "
				+ " var bratLocation = 'http://weaver.nlplab.org/~brat/demo/v1.3'; "
				+ " head.js( "
				+ " bratLocation + '/client/lib/jquery.min.js', bratLocation "
				+ " + '/client/lib/jquery.svg.min.js', bratLocation"
				+ " + '/client/lib/jquery.svgdom.min.js', "
				+ " bratLocation + '/client/src/configuration.js', bratLocation + '/client/src/util.js', "
				+ " bratLocation + '/client/src/annotation_log.js', bratLocation + '/client/lib/webfont.js', "
				+ " bratLocation + '/client/src/dispatcher.js', bratLocation + '/client/src/url_monitor.js', bratLocation + '/client/src/visualizer.js'); "
				+ " var webFontURLs = [ bratLocation + '/static/fonts/Astloch-Bold.ttf', "
				+ " bratLocation + '/static/fonts/PT_Sans-Caption-Web-Regular.ttf',"
				+ " bratLocation + '/static/fonts/Liberation_Sans-Regular.ttf' ];"
				+ " head.ready(function() { "
				+ "	Util.embed('brat', collData, docData, webFontURLs); }); "
				+ " var collData = { " + "	entity_types : [ { "
				+ "		\"type\" : \"Person\","
				+ "		\"labels\" : [ \"Person\", \"Per\" ],"
				+ "		\"bgColor\" : \"#9CC2E6\","
				+ "		\"borderColor\" : \"darken\"" + "	}, {"
				+ "		\"type\" : \"Organization\","
				+ "		\"labels\" : [ \"Organization\", \"Org\" ],"
				+ "		\"bgColor\" : \"#F0DCBF\","
				+ "		\"borderColor\" : \"darken\"" + "	}, {"
				+ "		\"type\" : \"Location\","
				+ "		\"labels\" : [ \"Location\", \"Loc\" ],"
				+ "		\"bgColor\" : \"#FFB9B9\","
				+ "		\"borderColor\" : \"darken\"" + "	} ] " + " };"
				+ " var docData = " + jsonResult + "</script></head>"
				+ "<body>" + title + "	<div id=\"brat\">" + "</div>"
				+ "</body></html>" + "";
	}

	public String getJsonContent(String jsonResult) {
		// return htmlHeadCode + title + "<hr/>" + "</head> \n" + "<body> \n"
		// + jsonResult + "</body> \n" + "</html>";
		// aşağıdaki parantezler olmazsa angularjs çalışmıyor...
//		return "[" + jsonResult + "]";
		return jsonResult;
	}

	public String getNullContent() {
		return htmlHeadCode + title + "<hr/>" + "</head> \n" + "<body> \n"
				+ "<p><font color=\"red\"><b>" + NULL_TEXT_MESSAGE
				+ "</b></font></p>" + "</body> \n" + "</html>";
	}

	public String getEmptyContent() {
		return htmlHeadCode + title + "<hr/>" + "</head> \n" + "<body> \n"
				+ "<p><font color=\"red\"><b>" + EMPTY_TEXT_MESSAGE
				+ "</b></font></p>" + "</body> \n" + "</html>";
	}

	public String colorifyNamedEntityStrategy(String content,
			String perFilePath, String locFilePath, String orgFilePath)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {
		// Entity entityJson = new CapitalLetterCompositeStrategy()
		// .doOperation(new Entity(content));

		// ArrayList<JsonEntity> analyzeTextList =
		// entityJson.getEntityJsonList();
		ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = new TextAnalyser()
				.regexCapitalLetterLookupPipeline(content, perFilePath,
						locFilePath, orgFilePath);
		String oldNamedEntity, newNamedEntity = "";
		String resultContent = content;
		if (regexCapitalLetterLookupPipeline.isEmpty()) {
			resultContent = "";
		} else {
			for (JsonEntity entities : regexCapitalLetterLookupPipeline) {
				oldNamedEntity = content.substring(entities.getStart(),
						entities.getEnd());
				String coloringStart = " <mark style=\"background-color:"
						+ identifyBackgroundColor(entities.getType()) + "\"> "
						+ " <a href=" + entities.getDbpediaUri() + " title="
						+ entities.getType() + " class=\"tooltip\">"
						+ "	<span title=" + entities.getDbpediaUri() + ">";
				String coloringEnd = "</span></a></mark>";
				newNamedEntity = coloringStart + oldNamedEntity + coloringEnd;
				resultContent = resultContent.replace(oldNamedEntity,
						newNamedEntity);
			}
		}
		return resultContent;
	}

	public String colorifyNamedEntity(String content) throws IOException,
			SAXException, TransformerException, ParserConfigurationException {
		// ArrayList<Entities> analyzeTextList = new TextAnalyser()
		// .analyzeTextList(content);

		// String jsonResult = new CapitalLetterTask().perform(content);
		// ArrayList<Entities> analyzeTextList = new
		// CapitalLetterTask().generateEntities(content);

		Entity entityJson = new CapitalLetterCompositeStrategy()
				.doOperation(new Entity(content));

		ArrayList<JsonEntity> analyzeTextList = entityJson.getEntityJsonList();

		String oldNamedEntity, newNamedEntity = "";
		String resultContent = content;
		if (analyzeTextList.isEmpty()) {
			resultContent = "";
		} else {
			for (JsonEntity entities : analyzeTextList) {
				oldNamedEntity = content.substring(entities.getStart(),
						entities.getEnd());
				String coloringStart = " <mark style=\"background-color:"
						+ identifyBackgroundColor(entities.getType()) + "\"> "
						+ " <a href=" + entities.getDbpediaUri() + " title="
						+ entities.getType() + " class=\"tooltip\">"
						+ "	<span title=" + entities.getDbpediaUri() + ">";
				String coloringEnd = "</span></a></mark>";
				newNamedEntity = coloringStart + oldNamedEntity + coloringEnd;
				resultContent = resultContent.replace(oldNamedEntity,
						newNamedEntity);
			}
		}
		return resultContent;
	}

	// changes the color of "mark" html tag to the convenient one.
	public String identifyBackgroundColor(String type) {
		String color = "";
		if (type.equals("Person"))
//			color = "#9CC2E6";
			color = "btn btn-warning btn-xs active";
		else if (type.equals("Organisation"))
//			color = "#C1E19E";
			color = "btn btn-default btn-xs active";
		else
//			color = "#DFB4B4";
		color = "btn btn-info btn-xs active";
		return color;
	}

	public String getDbpediNotWorkingContent() {
		return htmlHeadCode + title + "<hr/>" + "</head> \n" + "<body> \n"
				+ "<p><font color=\"red\"><b>" + DBPEDIA_TEXT_MESSAGE
				+ "</b></font></p>" + "</body> \n" + "</html>";
	}
}
