'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view4', {
    templateUrl: 'app/view4/view4.html',
    controller: 'View4Ctrl',
    controllerAs : 'ctrl'
  });
}]).controller('View4Ctrl', ['$scope', '$http', function($scope, $http) {
    $scope.amount;
    $scope.fromCurrency = "AUD";
    $scope.toCurrency = "AUD";
    $scope.convertedAmount;
    $scope.convertedCurrency;
//    $scope.hasConverted = false;
    
    $scope.setFromCurrency = function(countryCode){
        $scope.fromCurrency = countryCode;
    };
    
    $scope.setToCurrency = function(countryCode) {
        $scope.toCurrency = countryCode;
    };
    
    $scope.convert = function(){
        console.log("converting");
        $http.get('/SemesterSeed/api/rate/' + $scope.amount + '/' + $scope.fromCurrency + '/' + $scope.toCurrency).then(
            function(response){
                $scope.convertedAmount = response.data;
                $scope.convertedCurrency = $scope.toCurrency;
//                $scope.hasConverted = true;
            },
            function(response){
                console.log(response);
            });
                
    };
    
    $http.get('/SemesterSeed/api/rate').then(
    function(response) {
        console.log(response);
        $scope.currencies = response.data;
    }, function(response) {
        console.log(response);
    });
}]);