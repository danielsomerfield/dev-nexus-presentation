var phonebookAppControllers = angular.module('phonebookAppControllers', []);

phonebookAppControllers.controller('PhonebookController', ['$scope', '$http', function ($scope, $http) {
    $http.get("/services/entries").then(function(result){
        $scope.phonebookEntries = result.data;
    });
}]);