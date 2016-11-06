'use strict';

angular.module('myApp.docs', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/docs', {
              templateUrl: 'app/docs/docs.html',
              controller: 'View2Ctrl'
            });
          }])
      
      

        .controller('View2Ctrl', function ($http, $scope) {
         

        });