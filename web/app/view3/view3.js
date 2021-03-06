'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $scope.searchInput;
            $scope.resultList;
            $scope.isPopulated = false;
            $scope.searchType = "search";
            $scope.searchTypeHeading;
            

            $scope.setSearchType = function(searchType, searchTypeHeading){
                $scope.searchType = searchType;
                $scope.searchTypeHeading = searchTypeHeading;
            };

            $scope.search = function () {
                if($scope.searchInput === undefined || $scope.searchInput === ""){
                    alert("Input Field Empty");
                    return;
                }
                
                $http({
                    method: 'GET',
                    dataType: 'json',
                    url: 'http://cvrapi.dk/api?'+ $scope.searchType + '=' + $scope.searchInput.toLowerCase() + '&country=dk',
//                    url: 'http://cvrapi.dk/api?vat=3167%208021&country=dk',
                    skipAuthorization: true
                    
//                    
                }).then(function (response) {
//                    console.log(response.data);
//                    console.log(response.data.productionunits[0]);
                    $scope.resultList = response.data;
                    $scope.isPopulated = true;
                }, function (response) {
                    alert(response.data.error);
                    $scope.isPopulated = false;
                });
            };

        });