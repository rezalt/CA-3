'use strict';

angular.module('myApp.login', ['ngRoute','angularCSS'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'app/login/login.html',
    css: 'assets/css/login.css',
    controller: 'AppLoginCtrl'
  });
}]);
