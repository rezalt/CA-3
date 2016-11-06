'use strict';

angular.module('myApp.signUp', ['ngRoute','angularCSS'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signup', {
    templateUrl: 'app/signUp/signUp.html',
    css: 'assets/css/login.css',
    controller: 'AppLoginCtrl'
  });
}]);