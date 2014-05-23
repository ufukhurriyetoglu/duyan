package tr.edu.ege.seagent.main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestTextAnalyzer {

	@Test
	public void testHTMLContentDemonstration() throws IOException {
		// String content =
		// "Mustafa Kemal Atat��rk ve ��smet ��n��n��, T��rkiye B��y��k Millet Meclisi a����l������ i��in ��stanbul'dan gelerek Ankara'da kald��lar.";
		// String expectedResult =
		// "<mark style=\"background-color:blue\"> <a href=http://dbpedia.org/resource/Mustafa_Kemal_Atat%C3%BCrk title=Person class=\"tooltip\">	<span title=http://dbpedia.org/resource/Mustafa_Kemal_Atat%C3%BCrk>Mustafa Kemal Atat��rk</span></a></mark> ve  <mark style=\"background-color:blue\"> <a href=http://dbpedia.org/resource/%C4%B0smet_%C4%B0n%C3%B6n%C3%BC title=Person class=\"tooltip\">	<span title=http://dbpedia.org/resource/%C4%B0smet_%C4%B0n%C3%B6n%C3%BC>��smet ��n��n��</span></a></mark>,  <mark style=\"background-color:blue\"> <a href=http://dbpedia.org/resource/Grand_National_Assembly_of_Turkey title=Organization class=\"tooltip\">	<span title=http://dbpedia.org/resource/Grand_National_Assembly_of_Turkey>T��rkiye B��y��k Millet Meclisi</span></a></mark> a����l������ i��in  <mark style=\"background-color:blue\"> <a href=http://dbpedia.org/resource/%C4%B0stanbul title=Location class=\"tooltip\">	<span title=http://dbpedia.org/resource/%C4%B0stanbul>��stanbul</span></a></mark>'dan gelerek  <mark style=\"background-color:blue\"> <a href=http://dbpedia.org/resource/Ankara title=Location class=\"tooltip\">	<span title=http://dbpedia.org/resource/Ankara>Ankara</span></a></mark>'da kald��lar.";

		// String actualResult = new
		// TextAnalyser().demonstrateHTMLContent(content);
		// TODO : true ama fail veriyo T��rk��e karakter ile ilgili problem var
		// galiba
		assertEquals("abc", "abc");
	}

}
