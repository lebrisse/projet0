
angular.module('projetagricole').controller('NewProducteurController', function ($scope, $location, locationParser, ProducteurResource , ProduitResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.producteur = $scope.producteur || {};
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST" 
    ];
    
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
            $scope.producteur.produits = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.producteur.produits.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Producteurs/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProducteurResource.save($scope.producteur, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Producteurs");
    };
});