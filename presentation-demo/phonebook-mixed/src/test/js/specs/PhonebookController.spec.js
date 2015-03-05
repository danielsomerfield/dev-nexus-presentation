describe('PhonebookController', function () {

    var scope;
    var controller;
    var httpBackend;

    var entryResults = [{
        "id": "123",
        "lastName": "last-name",
        "firstName": "first-name",
        "emailAddress": "email@example.com"
    }];

    beforeEach(module('phonebookApp'));

    beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
        scope = $rootScope.$new();
        httpBackend = $httpBackend;
        controller = $controller('PhonebookController', {$scope: scope, $http: $http});
    }));

    it('contains no entries when service has returned none', function () {
        httpBackend.when("GET", "/services/entries").respond([]);
        httpBackend.flush();
        expect(scope.phonebookEntries).toEqual([]);
    });

    it('contains entries service has returned', function () {
        httpBackend.when("GET", "/services/entries").respond(entryResults);
        httpBackend.flush();
        expect(scope.phonebookEntries).toEqual(entryResults);
    });
});