
var demoModule = angular.module("demo", []);

function DemoController($scope, $http) {

    $scope.peopleArray = [
                          {sentence : "MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü."},
                          {sentence : "İstanbul Valisi Hüseyin Avni Mutlu, Twitter hesabından yaptığı paylaşımlarla yine dikkat çekti. Dünyanın çivisi çıktı diyorlar."},
                          {sentence : "Yerel seçimlerde CHP’nin Ankara Büyükşehir Belediye Başkanlığı için aday gösterdiği eski MHP’li Mansur Yavaş hukuki mücadelesini sürdürüyor."},
                          {sentence : "6 gündür kapalı olan Diyarbakır - Bingöl karayolunun Lice mevkiinde bulunan göstericilere jandarma ekipleri müdahale etti."},
                          {sentence : "Cumhurbaşkanı Abdullah Gül ve eşi Hayrünnisa Gül, Harvard Üniversitesi’nde ‘’Bilişim’’ okuyan oğulları Mehmet’in mezuniyet törenine katıldılar."},
                          {sentence : "CHP Lideri Kılıçdaroğlu bu sabah Ankara’da, Cumhurbaşkanlığı adaylığına ilişkin görüş alışverişinde bulunmak için Türkiye Kamu Sen’i ziyaret etti."},
                          {sentence : "CHP Genel Başkan Yardımcısı Sezgin Tanrıkulu, TBMM Başkanı Cemil Çiçek’e Meclis’in bünyesindeki Saraylar için yapılan deprem hazırlıklarını sordu."},
                          {sentence: "MHP Genel Başkanı Devlet Bahçeli geldi."},
                          {sentence: "Yapılan düğünde Arda Turan ve Hakan Şükür hazır bulundular."},
                          {sentence: "Zafer Çağlayan, Egemen Bağış, Muammer Güler ve Erdoğan Bayraktar’la ilgili TBMM Genel Kurulu’nda karar alınmasına karşın, soruşturma komisyonu kurulamadı."}
                        ];
    
    $scope.peopleArrayValue1 = $scope.peopleArray[0]; // first sentence
	
   $scope.query = {
      content: "MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday belirleme temasları çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü."
   };
   
   $scope.lookupResult = {
      displayValue: "Not yet retrieved"
   };
   
   $scope.selectAction = function() {
	   console.log($scope.peopleArrayValue1.sentence);
	   $scope.query.content = $scope.peopleArrayValue1.sentence;
	   };
   

   $scope.performPostcodeLookup = function () {
//	   $scope.query.content = $scope.peopleArrayValue1.sentence;
      var url = "/duyan/DuyanServlet?" +
    	  "content=" + $scope.query.content + "&callback=JSON_CALLBACK";

      $http.get(url).
      success(function(data) {
         console.log(data);
         $scope.lookupResult.displayValue = data.text;
         
 		$scope.text = data.text;
		$scope.entities = data.entities;

		var txtm = $scope.text;
		for (var i = 0; i <= $scope.entities.length; i++) {
			if ($scope.entities[i] && $scope.entities[i].id) {
				var ner = $scope.text.substring($scope.entities[i].start,
						$scope.entities[i].end);
				// <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="Tooltip on left">
//			    Tooltip on left
//			    </button>
//				var changedNer = '<mark style="background-color: '
//						+ $scope.entities[i].color + '">' + '<a href="'
//						+ $scope.entities[i].dbpediaUri + '" title='
//						+ $scope.entities[i].type + ' class="tooltip">'
//						+ '<span title="' + $scope.entities[i].dbpediaUri + '">' + ner
//						+ '</span></a></mark>';
				// <button id="tooltip1" type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" data-original-title="Tooltip on left">
//			    Tooltip on left
//			    </button>
				
				var changedNer = '<a href="'+$scope.entities[i].dbpediaUri+'" class="'+$scope.entities[i].color+'" role="button" data-toggle="popover" title="'+$scope.entities[i].type+'"> '
								+ ner+'</a>';
				if ($scope.text.indexOf(ner) > -1) {
					txtm = txtm.replace(ner, changedNer);
				}
			}
		}
		$scope.text = txtm;
         
      });
      

      
//      $http.jsonp(url).then(function (response) {
////         var data = response.data;
//         console.log("jsonp : " + response.data.entities[0].name);
//         if (data.text) {
//            $scope.lookupResult.displayValue = "Error: " + response.data.text;
//         } else {
////            if (data.postalcodes.length === 0) {
////               $scope.lookupResult.displayValue = "No data found";
////            } else {
//               $scope.lookupResult.displayValue = response.data.entities[0].name;
////            }
//         }
//      }, function (data) {
//         $scope.lookupResult.displayValue = "HTTP Error - " + data.text;
//      });
   };

}