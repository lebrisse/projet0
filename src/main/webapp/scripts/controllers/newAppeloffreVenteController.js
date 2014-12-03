
angular.module('projetagricole').controller('NewAppeloffreVenteController', function ($scope, $location, locationParser, AppeloffreVenteResource , ProducteurResource , CommandesResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.appeloffreVente = $scope.appeloffreVente || {};
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST" 
    ];
    
    $scope.nomproducteurList = ProducteurResource.queryAll(function(items){
        $scope.nomproducteurSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.nomproducteur
            });
        });
    });
    
   /* $scope.$watch("producteurSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produit.producteur = {};
            $scope.produit.producteur.id = selection.value;
        }
    });*/
    
    $scope.$watch("nomproducteurSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.appeloffreVente.nomproducteur = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
            $scope.appeloffreVente.nomproducteur.push(collectionItem);
            });
        }
    });
    
    $scope.nomcommandeList = CommandesResource.queryAll(function(items){
        $scope.nomcommandeSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.nomcommande
            });
        });
    });
    $scope.$watch("nomcommandeSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.appeloffreVente.nomcommande = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
            $scope.appeloffreVente.nomcommande.push(collectionItem);
            });
        }
    });
    
    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/AppeloffreVentes/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProduitResource.save($scope.appeloffreVente, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/AppeloffreVentes");
    };
});