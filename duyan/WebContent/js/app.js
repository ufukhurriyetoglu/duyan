(function() {
	var app = angular.module('gemStore', []);

	app
			.controller(
					'StoreController',
					[
							'$http',
							function($http) {
								var url = "http://localhost:8080/duyan/DuyanServlet?content=Yap%C4%B1lan+d%C3%BC%C4%9F%C3%BCnde+Arda+Turan+ve+Hakan+%C5%9E%C3%BCk%C3%BCr+haz%C4%B1r+bulundu.&outputtype=Json";
								var store = this;
								store.products = [];
								$http.get('mybr.json').success(
										function(data) {
											store.products = data;
											console.log("json : "
													+ JSON.stringify(data));
										});
								
//							     angular.forEach(store.products, function(value, key) {
//							    	    /* do something for all key: value pairs */
//							         console.log("hh" + value);
//							    	});
//							     
//							     angular.forEach(store.products,function(value,index){
//						                alert(value.name);
//						            });
								
							} ]);

	/*
	 * app.controller('ReviewController', function() { this.review = {};
	 * 
	 * this.addReview = function(product) { product.reviews.push(this.review);
	 * 
	 * this.review = {}; }; });
	 */
	
//	app.directive('parseUrl', function() {
//		  var urlPattern = /(http|ftp|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&amp;:\/~+#-]*[\w@?^=%&amp;\/~+#-])?/gi;
//		  return {    
//		    restrict: 'A',    
//		    require: 'ngModel',
//		    replace: true,   
//		    scope: { props: '=parseUrl', ngModel: '=ngModel' },
//		    link: function compile(scope, element, attrs, controller) {         
//		        scope.$watch('ngModel', function(value) {         
//		            angular.forEach(value.match(urlPattern), function(url) {
//		                value = value.replace(url, "<a target=\"" + scope.props.target + "\" href="+ url + ">" + url +"</a>");
//		            });
//		            element.html(value + " | " + scope.props.otherProp);        
//		          });                
//		    }
//		  };  
//		});
})();

