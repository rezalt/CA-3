'use strict';

/* Place your Global Filters in this file */

angular.module('myApp.filters', []).
  filter('checkmark', function () {
    return function(input) {
      return input ? '\u2713' : '\u2718';
    };
  })
          .filter("searchTypePlaceholder", function (){
              return function (input) {
                  
                  if (input === undefined || input === null || input === ""){
                      
                      return "Search Type";
                  }
                  
                  return input;
              };
  })
          .filter("nullFilter", function (){
              return function (input){
                  if (input === undefined || input === null || input === ""){
                      return  "--unknown--";
                  }
                  
                  return input;
              };
  });
  
  
