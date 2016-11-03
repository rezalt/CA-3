'use strict';

angular.module('myApp.allUsers', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/allUsers', {
    templateUrl: 'app/allUsers/allUsers.html'
  });
}]);