package tr.edu.ege.seagent.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tr.edu.ege.seagent.json.Entities;
import tr.edu.ege.seagent.main.TextAnalyser;

/**
 * Servlet implementation class DuyanServlet
 */
@WebServlet("/DuyanServlet")
public class DuyanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DuyanServlet() {
		super();
	}

	// changes the color of "mark" html tag to the convenient one.
	private String identifyBackgroundColor(String type) {
		String color = "";
		if (type.equals("Person"))
			color = "#9CC2E6";
		else if (type.equals("Organization"))
			color = "#C1E19E";
		else
			color = "#DFB4B4";
		return color;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String charSet = "UTF-8";
//		String charSet = "windows-1254";
		request.setCharacterEncoding(charSet);
		response.setCharacterEncoding(charSet);
		String content = request.getParameter("content");
		String jsonResult = new TextAnalyser().analyzeText(content);
		ArrayList<Entities> analyzeTextList = new TextAnalyser()
				.analyzeTextList(content);
		String oldNamedEntity, newNamedEntity = "";
		String resultContent = content;
		for (Entities entities : analyzeTextList) {
			oldNamedEntity = content.substring(entities.getStart(),
					entities.getEnd());
			String coloringStart = " <mark style=\"background-color:"
					+ identifyBackgroundColor(entities.getType()) + "\"> "
					+ " <a href=" + entities.getDbpediaUri() + " title="
					+ entities.getType() + " class=\"tooltip\">"
					+ "	<span title=" + entities.getDbpediaUri() + ">";
			String coloringEnd = "</span></a></mark>";
			// renklendirilmis varlik ismini oluştur.
			newNamedEntity = coloringStart + oldNamedEntity + coloringEnd;
			// mevcut content içindeki varlik ismini yeni renklendirilmiş hali
			// ile değiştir.
			resultContent = resultContent.replace(oldNamedEntity,
					newNamedEntity);
		}

		String selected = request.getParameter("outputtype");
		response.setCharacterEncoding(charSet);
		PrintWriter out = response.getWriter();

		String htmlHeadCode = "<html> \n "
				+ " <head> "
				+ " <META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset="
				+ charSet + "\"> "
				+ " <META HTTP-EQUIV=\"Content-language\" CONTENT=\"tr\"> ";

		String title = "<h1>Vites : Türkçe Varlık İsmi Tanımlama Sistemi</h1>";

		if (selected.equals("Json")) {
			out.println(htmlHeadCode + "</head> \n" + "<body> \n" + jsonResult
					+ "</body> \n" + "</html>");
		} else if (selected.equals("Vites")) {
			out.println(htmlHeadCode + " <style type=\"text/css\"> "
					+ " .tooltip{ " + "	display: inline; "
					+ "	position: relative; }" + "	.tooltip:hover:after{ "
					+ "	background: #333; " + "background: rgba(0,0,0,.8);"
					+ "border-radius: 5px;" + "bottom: 26px;" + "color: #fff;"
					+ "content: attr(title);" + "left: 25%;"
					+ "text-align:center;" + "padding: 5px 5px;"
					+ "position: absolute;" + "z-index: 98;" + "width: 80px; }"

					+ ".tooltip:hover:before{" + "	border: solid;"
					+ " border-color: #333 transparent; "
					+ "	border-width: 6px 6px 0 6px;" + "	bottom: 20px;"
					+ "	content: \"\";" + "	left: 50%;"
					+ "	position: absolute;" + "	z-index: 99; } </style> "
					+ "</head> \n" + "<body> " + title + "<hr/>"
					+ "<div><br/><br/><br/><p> " + " <br/><br/>"
					+ resultContent + "</p></div>" + " </body> \n" + "</html>");
		} else { // brat GUI solution
			out.println(htmlHeadCode
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
					+ "</body></html>" + "");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();

		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject joUser = null;
		try {
			joUser = (JSONObject) parser.parse(sb.toString());
			System.out.println(joUser);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String user = (String) joUser.get("content");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("A new user " + user + " has been created.");
		out.flush();
		out.close();
	}
}
