'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view4', {
    templateUrl: 'app/view4/view4.html',
    controller: 'View4Ctrl',
    controllerAs : 'ctrl'
  });
}]).controller('View4Ctrl', ['$scope', '$http', function($scope, $http) {
    $http.get('/SemesterSeed/api/rate').then(
    function(response) {
        console.log(response);
        $scope.currencies = response.data;
    }, function(response) {
        console.log(response);
    });
}]);