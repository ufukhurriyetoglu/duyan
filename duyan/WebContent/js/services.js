angular.module('jsonService', ['ngResource'])
.factory('JsonService', function($resource) {
	console.log('URL : ', $resource.text);
  return $resource('/duyan/DuyanServlet?content=Yap%C4%B1lan+d%C3%BC%C4%9F%C3%BCnde+Arda+Turan+haz%C4%B1r+bulundu.',  {
  });
});

