package tr.edu.ege.seagent.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.fileio.FileOperator;
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
		PrintWriter out = response.getWriter();
		
		
		ServletContext perContext = getServletContext();
		ServletContext locContext = getServletContext();
		ServletContext orgContext = getServletContext();
		String perFullPath = perContext.getRealPath("/WEB-INF/files/dbpediaNames.csv");
		String locFullPath = locContext.getRealPath("/WEB-INF/files/dbpediaLocations.csv");
		String orgFullPath = orgContext.getRealPath("/WEB-INF/files/DbpediaOrg.csv");
		

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
					resultContent = hcp.colorifyNamedEntityStrategy(content,perFullPath,locFullPath,orgFullPath);
				} catch (SAXException | TransformerException
						| ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (resultContent.equals("")) {
					out.println(hcp.getNullContent());
				} else {

					String selected = request.getParameter("outputtype");
					if (selected.equals("Json")) {
						// String jsonResult = new TextAnalyser()
						// .analyzeText(content);
						// jsonResult = new CapitalLetterTask()
						// .perform(content);
						Entity entityJson = new CapitalLetterCompositeStrategy()
								.doOperation(new Entity(content));
						out.println(hcp
								.getJsonContent(new JsonStrategyGenerator()
										.generateJson(entityJson)));
					} else if (selected.equals("Vites")) {
						
				out.println(hcp
						.getVitesContent(resultContent));
						
					} else { // brat GUI solution
						Entity entityJson = new CapitalLetterCompositeStrategy()
								.doOperation(new Entity(content));
						out.println(hcp
								.getBratContent(new JsonStrategyGenerator()
										.generateJson(entityJson)));
					}
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
