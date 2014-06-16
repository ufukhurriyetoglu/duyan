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
								$http.get(url).success(
										function(data) {
											store.products = data;
											console.log("json : "
													+ JSON.stringify(data));
										});
							} ]);

	/*
	 * app.controller('ReviewController', function() { this.review = {};
	 * 
	 * this.addReview = function(product) { product.reviews.push(this.review);
	 * 
	 * this.review = {}; }; });
	 */
})();
