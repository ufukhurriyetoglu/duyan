package tr.edu.ege.seagent.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.util.FileUtils;

@WebServlet("/DuyanServlet")
public class DuyanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String TEXTAREA_ID = "content";
	private static final String RESPONSE_CHARSET_UTF_8 = "text/javascript; charset=UTF-8";

	HtmlContentProvider hcp = new HtmlContentProvider();
	public final String DBPEDIA_CNTRL_URL = "http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+%3Fs+%3Fp+%3Fo+where+%7B%3Fs+%3Fp+%3Fo%7D+LIMIT+1&format=text%2Fhtml&timeout=30000&debug=on";

	public DuyanServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		String content = "";
		PrintWriter out = null;
		try {
			request.setCharacterEncoding(FileUtils.encodingUTF8);
			response.setCharacterEncoding(FileUtils.encodingUTF8);
			content = request.getParameter(TEXTAREA_ID);
			response.setCharacterEncoding(FileUtils.encodingUTF8);
			response.setContentType(RESPONSE_CHARSET_UTF_8);
			out = response.getWriter();
		} catch (IOException err) {
			err.printStackTrace();
		}

		generateWebPageContent(content, out);
	}

	public void generateWebPageContent(String content, PrintWriter out) {
		// ServletContext perContext = getServletContext();
		// ServletContext locContext = getServletContext();
		// ServletContext orgContext = getServletContext();
		// String perFullPath = perContext
		// .getRealPath("/WEB-INF/files/dbpediaDisambiNames.csv");
		// String locFullPath = locContext
		// .getRealPath("/WEB-INF/files/dbpediaLocations.csv");
		// String orgFullPath = orgContext
		// .getRealPath("/WEB-INF/files/DbpediaOrg.csv");

		ServletContext deasciiContext = getServletContext();
		String deasciiFullPath = deasciiContext
				.getRealPath("/WEB-INF/files/turkishPatternTable");

		ServletContext luceneIndexDir = getServletContext();
		String INDEX_DIRECTORY = luceneIndexDir.getRealPath("/WEB-INF/index/");
		
		// System.out.println(INDEX_DIRECTORY);
		if (content.equals("")) {
			out.println(hcp.getEmptyContent());
		} else {
			// hcp.obtainRegexTaskContent(content, out, perFullPath,
			// locFullPath,
			// orgFullPath, deasciiFullPath);
			hcp.obtainLuceneTaskContent(content, out, deasciiFullPath,
					INDEX_DIRECTORY);

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
	}
}
