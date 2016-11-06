'use strict';

angular.module('myApp.home', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/home', {
                    templateUrl: 'app/home/home.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {   
            }]);