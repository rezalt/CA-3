'use strict';

angular.module('myApp.allUsers', ['ngRoute'])


        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/allUsers', {
                    templateUrl: 'app/allUsers/allUsers.html',
                    controller: 'AllUsers',
                    controllerAs: "ctrl"
                });
            }])

        .controller('AllUsers', function ($http, $rootScope) {
            var self = this;
            self.currencies = [];
            $http.get("api/admin/users")
                    .success(function (data) {
                        self.users = data;
                        self.usersFound = true;
                    }).error(function (data) {
                $rootScope.error = data.error + data.message;
            });
            self.getUsers = function () {
                $http.get("api/admin/users")
                        .success(function (data) {
                            self.users = data;
                            self.usersFound = true;
                        }).error(function (data) {
                    $rootScope.error = data.error + data.message;
                });
            };

            self.deleteUser = function (data) {
                $http({
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: "api/admin/user",
                    data: {userName: data}
                })
                        .success(function ()
                        {
                            self.getUsers();

                        }).error(function () {

                });
            };
        });
