function UserController($scope, $http)
{
  $scope.user = {};
 
  $scope.createUser = function() 
  {
    $http({
      method: 'POST',
      url: 'http://localhost:8080/duyan/DuyanServlet',
      headers: {'Content-Type': 'application/json'},
      data:  $scope.user
    }).success(function (data) 
      {
    	$scope.status=data;
      });
  };
}