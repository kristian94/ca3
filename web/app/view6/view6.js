'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html'
                });
            }])
        .controller('formController', ['$scope', '$http', function ($scope, $http) {
                $scope.create = function (user) {
                    if (user.userName.length < 1) {
                        $('#view6 .alert').remove();
                        $('#view6').prepend('<div class="alert alert-danger id="message-box">Enter a username</div>');
                        return;
                    } else if (user.password.length < 4) {
                        $('#view6 .alert').remove();
                        $('#view6').prepend('<div class="alert alert-danger id="message-box">Password length needs to be longer than 4</div>');
                        return;
                    }
                    var data = {
                        userName: user.userName,
                        password: user.password
                    };
                    $http.post('/SemesterSeed/api/createuser', data).success(function (data) {
                        var dataBool = data === "ok";
                        $('#view6 .alert').remove();
                        $('#view6').prepend('<div class="alert ' 
                                + (dataBool ? 'alert-success' : 'alert-danger') 
                                + '" id="message-box">' 
                                + (dataBool ? 'Account created' : 'Username taken') + '</div>');
                        if (dataBool) {
                            $('#createUserForm').remove();
                        }
                    });
                };
            }]);