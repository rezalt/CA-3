'use strict';


// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngAnimate',
  'angular-jwt',
  'ui.bootstrap',
  'myApp.security',
  'myApp.allUsers',
  'myApp.home',
  'myApp.docs',
  'myApp.companyInfo',
  'myApp.exchangeRate',
  'myApp.calculator',
  'myApp.signUp',
  'myApp.login',
  'myApp.filters',
  'myApp.directives',
  'myApp.factories',
  'myApp.services'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/home'});
  
}]).
config(function ($httpProvider) {
   $httpProvider.interceptors.push('AuthInterceptor');
});
