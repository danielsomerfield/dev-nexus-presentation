//DISABLED
xdescribe('PhonebookController', function(){

    var scope;
    var controller;

    beforeEach(module('phonebookApp'));

    beforeEach(inject(function($rootScope, $controller, $httpBackend) {
        scope = $rootScope.$new();
        controller = $controller('PhonebookController', {$scope: scope, $http:$httpBackend});
    }));

    it('contains expected phonebook entries', function() {
        expect(scope.phonebookEntries).toBeDefined();
        expect(scope.phonebookEntries.length).toBe(1);
    });

});