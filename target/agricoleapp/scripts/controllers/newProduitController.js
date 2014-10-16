
angular.module('projetagricole').controller('NewProduitController', function ($scope, $location, locationParser, ProduitResource , ProducteurResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produit = $scope.produit || {};
    
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
            $scope.produit.nomproducteur = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
            $scope.produit.nomproducteur.push(collectionItem);
            });
        }
    });
    
    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Produits/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProduitResource.save($scope.produit, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produits");
    };
});