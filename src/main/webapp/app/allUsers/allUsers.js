'use strict';

angular.module('myApp.allUsers', ['ngRoute'])


        .config(['$routeProvider', function($routeProvider) {
            $routeProvider.when('/allUsers', {
                templateUrl: 'app/allUsers/allUsers.html',
                controller: 'AllUsers'
              });
            }])
        
        .controller('AllUsers', function ($http, $rootScope) {
            var self = this;
            self.usersFound = false;
            self.users = [];
            $http.get("api/admin/users")
                    .success(function (data) {
                        self.users = data;
                        self.usersFound = true;
                    }).error(function (data) {
                $rootScope.error = data.error + data.message;
            });

            self.deleteUser = function (data) {
                $http({
                    method: 'PUT',
                    url: "api/admin/user",
                    data: {userName: data}
                });
            };
        });
