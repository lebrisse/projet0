
angular.module('projetagricole').controller('NewCategorieProduitController', function ($scope, $location, locationParser, CategorieProduitResource , ProduitResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.categorieProduit = $scope.categorieProduit || {};
    
    $scope.produitsList = ProduitResource.queryAll(function(items){
        $scope.produitsSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.nomproduit
            });
        });
    });
    $scope.$watch("produitsSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.categorieProduit.produits = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categorieProduit.produits.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/CategorieProduits/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CategorieProduitResource.save($scope.categorieProduit, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/CategorieProduits");
    };
});