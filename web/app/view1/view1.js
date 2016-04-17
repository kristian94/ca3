'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ['AuthFactory','$scope', '$rootScope', function (authFactory, $scope, $rootScope) {

                $scope.isUser = $rootScope.isUser;
                $scope.$authScope = authFactory.getScope();

                $(document).ready(function () {
                    $("#vector1,#vector2,#vector3").hover(function () {

                        var selector = "#" + $(this).attr("title");
                        $(selector).fadeIn(100);

                    }, function () {

                        var selector = "#" + $(this).attr("title");
                        $(selector).fadeOut(100);

                    });



                });

            }]);