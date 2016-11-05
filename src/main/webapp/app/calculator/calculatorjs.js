'use strict'

angular.module('myApp.calculator', ['ngRoute'])
        .config(['$routeProvider', function($routePovider){
                $routeProvider.when('/calculator', 
        {
            templateUrl: 'app/calculator/calculator.html',
            controller: 'calculatorCtrl'
        });
        
}])
    .controller('calculatorCtrl', function(){
        
});