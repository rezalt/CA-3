'use strict';

angular.module('myApp.view6', ['ngRoute','angularCSS'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'app/view6/view6.html',
    css: 'app/view6/login.css',
    controller: 'AppLoginCtrl'
  });
}]);
