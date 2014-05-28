package tr.edu.ege.seagent.main;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TextAnalyzerTest {

	TestUtils tu = new TestUtils();

	// text has correct syntactic ordering and correct named entities.
	@Test
	public void correctJSONContentTest() {
		String content = "Mustafa Kemal Atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.";

		String mka = "\"entity1\":[\"T1\",\"Person\", [ [ 0,21 ] ]]";
		String ii = "\"entity2\":[\"T2\",\"Person\", [ [ 25,36 ] ]]";
		String expectedJsonResult = "{\"text\":\""
				+ content
				+ "\",\"entities\":{"
				+ ii
				+ ","
				+ mka
				+ ",\"entity5\":[\"T5\",\"Location\", [ [ 101,107 ] ]],\"entity3\":[\"T3\",\"Organization\", [ [ 38,66 ] ]],\"entity4\":[\"T4\",\"Location\", [ [ 80,88 ] ]]}}";
		System.out.println(expectedJsonResult);
		String actualJsonResult;
		try {
			actualJsonResult = new TextAnalyser().analyzeText(content);
			JSONAssert
					.assertEquals(expectedJsonResult, actualJsonResult, false);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}

	// text contains unrecognized named entities starting with small letters
	@Test
	public void smallLetterJSONContentTest() throws Exception {
		String content = "mustafa kemal atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.";

		String expectedJsonResult = "{\"text\":\"mustafa kemal atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.\","
				+ " \"entities\":{\"entity2\":[\"T2\",\"Organization\", [ [ 38,66 ]"
				+ " ]],\"entity1\":[\"T1\",\"Person\", [ [ 25,36 ]"
				+ " ]],\"entity3\":[\"T3\",\"Location\", [ [ 80,88 ]"
				+ " ]],\"entity4\":[\"T4\",\"Location\", [ [ 101,107 ] ]]}} ";
		String actualJsonResult;
		try {
			actualJsonResult = new TextAnalyser().analyzeText(content);
			JSONAssert
					.assertEquals(expectedJsonResult, actualJsonResult, false);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}

	// not filled in the blanks in text box
	@Test
	public void emptyJsonContentTest() throws Exception {
		String content = "";

		String expectedJsonResult = tu.EMPTY_JSON;
		System.out.println(expectedJsonResult);
		String actualJsonResult;
		try {
			actualJsonResult = new TextAnalyser().analyzeText(content);
			JSONAssert
					.assertEquals(expectedJsonResult, actualJsonResult, false);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}

	// "null"
	@Test
	public void nullJsonContentTest() throws Exception {
		String content = "Yapılan düğünde Arda Turan hazır bulundu.";
		try {
			JSONAssert.assertEquals(tu.EMPTY_JSON,
					new TextAnalyser().analyzeText(content), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void dbpediaBadGateAwayTest() throws Exception {
		URL url2 = new URL(tu.DBPEDIA_CNTRL_URL);
		HttpURLConnection urlh = (HttpURLConnection) url2.openConnection();
		int status = urlh.getResponseCode();
		assertNotEquals(502, status);
	}

}
