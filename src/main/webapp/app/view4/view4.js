'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/exRI', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'Currency',
                    controllerAs: "ctrl"
                });
            }])

        .controller('Currency', function ($http, $rootScope) {
            var self = this;
            self.currenciesFound = false;
            self.currencies = [];
            $http.get("api/currency/dailyrates")
                    .success(function (data) {
                        self.currencies = data;
                        self.currenciesFound = true;
                    }).error(function (data) {
                $rootScope.error = data.error + data.message;
            });
        });