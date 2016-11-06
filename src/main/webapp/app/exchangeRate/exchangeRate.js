'use strict';

angular.module('myApp.exchangeRate', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/exchangeRate', {
                    templateUrl: 'app/exchangeRate/exchangeRate.html',
                    controller: 'Currency',
                    controllerAs: "ctrl"
                });
            }])


        .controller('Currency', function ($http, $rootScope) {
            var self = this;
            self.currencies = [];
            $http.get('api/currency/dailyrates')
                    .success(function (data) {
                        self.currencies = data;
                        self.currenciesFound = true;
                    }).error(function (data) {
                $rootScope.error = data.error + data.message;
            });
        })
        
         .filter('setDecimal', function ($filter) {
            return function (input, places) {
                if (isNaN(input))
                    return input;
                // If we want 1 decimal place, we want to mult/div by 10
                // If we want 2 decimal places, we want to mult/div by 100, etc
                // So use the following to create that factor
                var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                return Math.round(input * factor) / factor;
            };
        });
