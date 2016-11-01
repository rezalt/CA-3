'use strict';

angular.module('myApp.view5', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/allUsers', {
    templateUrl: 'app/view5/view5.html',
     css: 'app/view5/login.css',
    controller: 'AppLoginCtrl'
  });

}]);