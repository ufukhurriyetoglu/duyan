package tr.edu.ege.seagent.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.deasciifier.TurkishDeasciifier;
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.json.JSONGenerator;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.main.TextAnalyser;
import tr.edu.ege.seagent.strategy.CapitalLetterCompositeStrategy;
import tr.edu.ege.seagent.strategy.JsonStrategyGenerator;

import com.hp.hpl.jena.util.FileUtils;

/**
 * Servlet implementation class DuyanServlet
 */
@WebServlet("/DuyanServlet")
public class DuyanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HtmlContentProvider hcp = new HtmlContentProvider();
	public final String DBPEDIA_CNTRL_URL = "http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+%3Fs+%3Fp+%3Fo+where+%7B%3Fs+%3Fp+%3Fo%7D+LIMIT+1&format=text%2Fhtml&timeout=30000&debug=on";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DuyanServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(FileUtils.encodingUTF8);
		response.setCharacterEncoding(FileUtils.encodingUTF8);
		String content = request.getParameter("content");
		response.setCharacterEncoding(FileUtils.encodingUTF8);
		response.setContentType("text/javascript; charset=UTF-8");
		PrintWriter out = response.getWriter();

		ServletContext perContext = getServletContext();
		ServletContext locContext = getServletContext();
		ServletContext orgContext = getServletContext();
		String perFullPath = perContext
				.getRealPath("/WEB-INF/files/dbpediaNames.csv");
		String locFullPath = locContext
				.getRealPath("/WEB-INF/files/dbpediaLocations.csv");
		String orgFullPath = orgContext
				.getRealPath("/WEB-INF/files/DbpediaOrg.csv");

		String deasciiFullPath = orgContext
				.getRealPath("/WEB-INF/files/turkishPatternTable");

		// URL url2 = new URL(DBPEDIA_CNTRL_URL);
		// HttpURLConnection urlh = (HttpURLConnection) url2.openConnection();
		// int status = urlh.getResponseCode();
		int status = 200;
		// if DBpedia does not work (200 means everything is OK.)
		if (status == 502) {
			out.println(hcp.getDbpediNotWorkingContent());
		} else {

			if (content.equals("")) {
				out.println(hcp.getEmptyContent());
			} else {
				String resultContent = "";

				try {

					// Turkish Deasciifier
					content = new TurkishDeasciifier().deasciifySentence(
							content, deasciiFullPath);

					ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = new TextAnalyser()
							.regexCapitalLetterLookupPipeline(content,
									perFullPath, locFullPath, orgFullPath);
					resultContent = new JSONGenerator().createJsonRegex(
							content, regexCapitalLetterLookupPipeline);
					out.println(resultContent);
				} catch (SAXException | TransformerException
						| ParserConfigurationException | URISyntaxException e) {
					e.printStackTrace();
				}

			}
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
