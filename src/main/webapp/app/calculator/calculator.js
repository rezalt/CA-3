'use strict';

angular.module('myApp.calculator', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/calculator', {
                    templateUrl: 'app/calculator/calculator.html',
                    controller: 'calculatorCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .filter("convert", function ()
        {
            return function (number, from, to)
            {

                number = number || 0;
              
                var multiplier = from/to;
                
                var out = number * multiplier;
                
                // så der ikke bliver skrevet NaN, når man kun har valgt én currency
                out = out || 0;
                
                // så vi ikke har så mange decimaler
                var places = 3;
                var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                return Math.round(out* factor) / factor;
            };
        })

        .controller('calculatorCtrl', function ($http, $rootScope) {
            var self = this;
            self.currencies = [];
            
            
            self.input=1;
            $http.get('api/currency/dailyrates')
                    .success(function (data) {
                        self.currencies = data;
                        self.currenciesFound = true;
                    }).error(function (data) {
                $rootScope.error = data.error + data.message;
            });
        });