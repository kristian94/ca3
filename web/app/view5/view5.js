'use strict';

angular.module('myApp.view5', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view5', {
    templateUrl: 'app/view5/view5.html',
    controller: 'View5Ctrl',
    controllerAs : 'ctrl'
  });
}]).controller('View5Ctrl', ['$scope', '$http', function($scope, $http) {
    $scope.deleted = null;
    $http.get('/SemesterSeed/api/admin/users').then(
    function(response) {
        $scope.users = response.data;
    }, function(response) {
        console.log(response);
    });
    
    $scope.removeUser = function($event, id) {
        
        $http.delete('/SemesterSeed/api/admin/user/' + id).then(function(response) {
            $($event.currentTarget).closest('tr').fadeOut(300, function() {
                $(this).remove();
            });
        }, function(response) {
            console.log(response);
        });
    }
    
//    $scope.allUsers = 
}]).filter('commaSeperatedList', function() {
    return function(input) {
        return input.map(val => val.rolename).join(',');
        
    };
});
