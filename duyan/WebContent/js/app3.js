var app = angular.module('angularjs-starter', [ 'jsonService', 'ngSanitize' ]);



app.controller('MainCtrl', function($scope, JsonService) {

	$scope.content = 'Yapılan düğünde Arda Turan hazır bulundu.';
	JsonService.get(function(data) {
		$scope.text = data.text;
		$scope.entities = data.entities;

		var txtm = $scope.text;
		for (var i = 0; i <= $scope.entities.length; i++) {
			if ($scope.entities[i] && $scope.entities[i].id) {
				var ner = $scope.text.substring($scope.entities[i].start,
						$scope.entities[i].end);
				var changedNer = '<mark style="background-color: '
						+ $scope.entities[i].color + '">' + '<a href="'
						+ $scope.entities[i].dbpediaUri + '" title='
						+ $scope.entities[i].type + ' class="tooltip">'
						+ '<span title="' + $scope.entities[i].dbpediaUri + '">' + ner
						+ '</span></a></mark>';
				if ($scope.text.indexOf(ner) > -1) {
					txtm = txtm.replace(ner, changedNer);
				}
			}
		}
		$scope.text = txtm;
	});
});

app.directive('myDirective', function() {

	return {

		restrict : 'AE',
		transclude : true,
		replace : true,
		scope : {
			myUrl : '@',
			myType : '@',
			myName : '@',
			myColor : '@',
			myText : '@'
		},
		// template : '<mark style="background-color: {{myColor}}">'
		// + '<a ng-href="{{myUrl}}" title={{myType}} class="tooltip">'
		// + '<span title="{{myUrl}}">{{myName}}</span></a></mark></div>'
		template : '<div ng-transclude>{{myText}}</div>'
	};
});
