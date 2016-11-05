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
            self.currencies = [];
            alert("requesting!");
            $http.get('api/currency/dailyrates')
                    .success(function (data) {
                        self.currencies = data;
                      alert("success!" + data);
                        self.currenciesFound = true;
                    }).error(function (data) {
                        alert("error!" + data);
                $rootScope.error = data.error + data.message;
            });
        });