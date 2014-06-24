package tr.edu.ege.seagent.main;

import java.io.IOException;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TextAnalyserPoliticsTest {

	TestUtils tu = new TestUtils();

	// is there any necessary for tagging "ulkucu sehitler aniti"?
//	@Test
//	public void actualNewsIrrelevantNerTest() throws Exception {
//		String content = "Yolsuzluktan yüzü kapkara suçlular var MHP Genel Başkanı Devlet Bahçeli, ‘Ülkücü Şehitleri Anma Günü’ nedeniyle dün Kızılcahamam’da Ülkücü Şehitler Anıtı’nı ziyaret etti.";
//		String mhp = "\"entity1\":[\"T1\",\"Organization\", [ [ 39,42 ] ]]";
//		String dBahceli = "\"entity2\":[\"T2\",\"Person\", [ [ 57,71 ] ]]";
//		String kizilcahamam = "\"entity3\":[\"T3\",\"Location\", [ [ 116,128 ] ]]";
//
//		String expectedJsonResult = "{\"text\":\"" + content
//				+ "\",\"entities\":{" + mhp + "," + dBahceli + ","
//				+ kizilcahamam + "}}";
//		try {
//			JSONAssert.assertNotEquals(expectedJsonResult,
//					new TextAnalyser().analyzeText(content), false);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// "Abdullah" is written as "ABDULLAH"
//	@Test
//	public void allCharBigNerTest() throws Exception {
//		String content = "ABDULLAH Gül’e Cumhurbaşkanlığı yolunu açan en önemli hamle, MHP Lideri Devlet Bahçeli’nin, seçim için 367 şartını aramaksızın TBMM oturumuna katılacaklarını açıklaması oldu.";
//		String aGul = "\"entity1\":[\"T1\",\"Person\", [ [ 0,12 ] ]]";
//		String cumhur = "\"entity2\":[\"T2\",\"Organization\", [ [ 15,31 ] ]]";
//		String mhp = "\"entity3\":[\"T3\",\"Organization\", [ [ 61,64 ] ]]";
//		String dBahceli = "\"entity4\":[\"T4\",\"Person\", [ [ 72,86 ] ]]";
//		String tbmm = "\"entity5\":[\"T5\",\"Organization\", [ [ 127,131 ] ]]";
//
//		String expectedJsonResult = "{\"text\":\"" + content
//				+ "\",\"entities\":{" + aGul + "," + cumhur + "," + mhp + ","
//				+ dBahceli + "," + tbmm + "}}";
//
//		try {
//			JSONAssert.assertNotEquals(expectedJsonResult,
//					new TextAnalyser().analyzeText(content), false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// mhp -> MİLLİYETÇİ Hareket Partisi "Sezer Bahçeli’yi kapıda karşıladı."
//	// NER gerekli mi?
//	@Test
//	public void bigCharsTest() throws Exception {
//		String content = "MİLLİYETÇİ Hareket Partisi Genel Başkanı Devlet Bahçeli, ortak çatı aday kapsamında 10.Cumhurbaşkanı Ahmet Necdet Sezer’i Gölbaşı’nda bulunan evinde ziyaret etti.Sezer Bahçeli’yi kapıda karşıladı.";
//		String mhp = "\"entity1\":[\"T1\",\"Organization\", [ [ 0,26 ] ]]";
//		String dBahceli = "\"entity2\":[\"T2\",\"Person\", [ [ 41,55 ] ]]";
//		String aNSezer = "\"entity3\":[\"T3\",\"Person\", [ [ 101,119 ] ]]";
//		String golbasi = "\"entity4\":[\"T4\",\"Location\", [ [ 122,129 ] ]]";
//
//		String expectedJsonResult = "{\"text\":\"" + content
//				+ "\",\"entities\":{" + mhp + "," + dBahceli + "," + aNSezer
//				+ "," + golbasi + "}}";
//
//		try {
//			JSONAssert.assertNotEquals(expectedJsonResult,
//					new TextAnalyser().analyzeText(content), false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void abbreTest() throws Exception {
//		String content = "MHP Genel Başkanı Devlet Bahçeli, partisinin grup toplantısında Danıştay'ın 146.kuruluş yıldönümü etkinliğinde TBB Başkanı Metin Feyzioğlu ile Başbakan Erdoğan tartışmasına değindi.";
//		String mhp = "\"entity1\":[\"T1\",\"Organization\", [ [ 0,3 ] ]]";
//		String dBahceli = "\"entity2\":[\"T2\",\"Person\", [ [ 18,32 ] ]]";
//		String danistay = "\"entity3\":[\"T3\",\"Organization\", [ [ 64,72 ] ]]";
//		String tbb = "\"entity4\":[\"T4\",\"Organization\", [ [ 111,114 ] ]]";
//		String mFeyz = "\"entity5\":[\"T5\",\"Person\", [ [ 123,138 ] ]]";
//		String basbakan = "\"entity6\":[\"T6\",\"Person\", [ [ 143,159 ] ]]";
//
//		String expectedJsonResult = "{\"text\":\"" + content
//				+ "\",\"entities\":{" + mhp + "," + dBahceli + "," + danistay
//				+ "," + tbb + "," + mFeyz + "," + basbakan + "}}";
//
//		System.out.println(expectedJsonResult);
//		String word = "Başbakan Erdoğan";
//		tu.findBeginEndOffset(content, word, 4, tu.PER);
//
//		try {
//			JSONAssert.assertNotEquals(expectedJsonResult,
//					new TextAnalyser().analyzeText(content), false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
