'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/exRI', {
    templateUrl: 'app/view4/view4.html'
  });
}]);