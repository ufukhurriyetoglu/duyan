package tr.edu.ege.seagent.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tr.edu.ege.seagent.main.TextAnalyser;

import com.hp.hpl.jena.util.FileUtils;

/**
 * Servlet implementation class DuyanServlet
 */
@WebServlet("/DuyanServlet")
public class DuyanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HtmlContentProvider hcp = new HtmlContentProvider();

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
		if (content.equals("")) {
			out.println(hcp.getEmptyContent());
		} else {
			String resultContent = hcp.colorifyNamedEntity(content);

			if (resultContent.equals("")) {
				out.println(hcp.getNullContent());
			} else {

				String selected = request.getParameter("outputtype");
				if (selected.equals("Json")) {
					String jsonResult = new TextAnalyser().analyzeText(content);
					out.println(hcp.getJsonContent(jsonResult));
				} else if (selected.equals("Vites")) {
					out.println(hcp.getVitesContent(resultContent));
				} else { // brat GUI solution
					String jsonResult = new TextAnalyser().analyzeText(content);
					out.println(hcp.getBratContent(jsonResult));
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
