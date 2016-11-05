'use strict';

angular.module('myApp.allUsers', ['ngRoute'])


        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/allUsers', {
                    templateUrl: 'app/allUsers/allUsers.html',
                    controller: 'AllUsers',
                    controllerAs: "ctrl"
                });
            }])
        
        .directive('ngConfirmClick', [
            function () {
                return {
                    link: function (scope, element, attr) {
                        var msg = attr.ngConfirmClick || "Are you sure?";
                        var clickAction = attr.confirmedClick;
                        element.bind('click', function (event) {
                            if (window.confirm(msg)) {
                                scope.$eval(clickAction);
                            }
                        });
                    }
                };
            }])
        
        .controller('AllUsers', function ($http, $rootScope) {
            var self = this;
            self.users = [];
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
