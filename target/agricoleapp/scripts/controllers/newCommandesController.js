
angular.module('projetagricole').controller('NewCommandesController', function ($scope, $location, locationParser, CommandesResource , ProduitResource, ClientResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.commandes = $scope.commandes || {};
    
    $scope.nomproduitList = ProduitResource.queryAll(function(items){
        $scope.nomproduitSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.nomproduit
            });
        });
    });
    $scope.$watch("nomproduitSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.commandes.nomproduit = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.commandes.nomproduit.push(collectionItem);
            });
        }
    });
    
    $scope.nomclientList = ClientResource.queryAll(function(items){
        $scope.nomclientSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.nomclient
            });
        });
    });
    $scope.$watch("nomclientSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.commandes.nomclient = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.commandes.nomclient.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Commandess/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CommandesResource.save($scope.commandes, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Commandess");
    };
});