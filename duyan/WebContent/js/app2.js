angular
		.module('angularjs-starter', [ 'ngSanitize' ])
		.controller(
				'x',
				function($scope, $sce) {
					$scope.html = '<p style="color:blue">Hey!! Come and '
							+ '<em style="color:Red" onmouseover="this.textContent=\'Click\'">Mouse Hover</em>\n'
							+ 'Over Me</p>';
					$scope.withoutSanitize = function() {
						return $sce.trustAsHtml($scope.html);
					};
				});