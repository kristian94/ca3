'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'app/view3/view3.html',
    controller: 'View3Ctrl'
  });
}])

.controller('View3Ctrl', function($http,$scope) {
    $scope.searchInput;
    $scope.resultList;
    
    $http({
       method: 'GET',
       header: "",
       url: 'http://cvrapi.dk/api?search=' + $scope.searchInput + '&country=dk',
       
       success: function(response){
           $scope.resultList = response.data;
       }
    });
    
    $scope.search = function(){
        $http.get('http://cvrapi.dk/api?search=' + $scope.searchInput + '&country=dk')
            .success(function (data, status, headers, config) {
                $scope.resultList = data.data;
            })
            .error(function (data, status, headers, config) {
              
            });
    };
    
    $scope.test = function(){
        $scope.searchInput = "Copenhagen";
        $scope.search();
        console.log($scope.resultList);
        
    }; 
 
    $scope.test();
});