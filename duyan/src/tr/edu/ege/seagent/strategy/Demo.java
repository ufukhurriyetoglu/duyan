package tr.edu.ege.seagent.strategy;

import tr.edu.ege.seagent.entity.Entity;

public class Demo {

	public static void main(String[] args) {
		 String content =
		 "Yerel seçimlerde CHP Ankara Büyükşehir Belediye Başkanlığı için aday gösterdiği eski MHP’li Mansur Yavaş, kendilerinden çalındığını düşündüğü seçim için hukuki mücadelesini sürdürüyor.";
//		String content = "MHP Genel Başkanı Devlet Bahçeli geldi.";
		Entity contentEntity = (new Entity(content));

		// CompositeStrategy compoStrategy = new CompositeStrategy();
		// compoStrategy.addStrategy(new MorphologicalAnalysisStrategy());
		// compoStrategy.addStrategy(new OntologyLookupStrategy());
		// compoStrategy.execute(contentEntity);

		CapitalLetterCompositeStrategy capLetCompStrategy = new CapitalLetterCompositeStrategy();
		capLetCompStrategy.execute(contentEntity);
	}
}
