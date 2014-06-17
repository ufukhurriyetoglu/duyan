var app = angular.module("MyApp", ["ngResource"]);

function MyCtrl($scope, $resource) {
  $scope.twitterAPI = $resource("http://localhost:8080/duyan/DuyanServlet?content="+$scope.searchTerm+"&outputtype=Json",
    { callback: "JSON_CALLBACK" },
    { get: { method: "JSONP" }});

  $scope.search = function() {
    $scope.searchResult = $scope.twitterAPI.get({ q: $scope.searchTerm });
  };
}