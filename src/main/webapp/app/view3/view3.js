'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/companyInfo', {
    templateUrl: 'app/view3/view3.html',
    controller: 'View3Ctrl'
  });
}])
    
.controller('View3Ctrl', function($scope,$http) {
  $scope.searchInput;
  $scope.resultList;
  $scope.isPopulated = false;
  $scope.searchType = "search";
  $scope.searchTypeHeading;
  
  $scope.setSearchType = function(searchType, searchTypeHeading){
      $scope.searchType = searchType;
      $scope.searchTypeHeading = searchTypeHeading;
      
  };
  
  $scope.search = function (){
     
      if ($scope.searchInput === undefined || $scope.searchInput === ""){
          alert("Input Field Empty");
          return;
      }
      $http({
          method: 'GET',
          dataType: 'json',
          url: 'http://cvrapi.dk/api?'+ $scope.searchType + '=' + $scope.searchInput.toLowerCase() + '&country=dk',
          skipAuthorization: true
      }).then(function (response){
          $scope.resultList = response.data;
          $scope.isPopulated = true;
      }, function (response){
          alert(response.data.error);
          $scope.isPopulated = false;
      });
  };
 
});