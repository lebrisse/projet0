

angular.module('projetagricole').controller('EditProducteurController', function($scope, $routeParams, $location, ProducteurResource , ProduitResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.producteur = new ProducteurResource(self.original);
            ProduitResource.queryAll(function(items) {
                $scope.produitsSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproduit
                    };
                    if($scope.producteur.produits){
                        $.each($scope.producteur.produits, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produitsSelection.push(labelObject);
                                $scope.producteur.produits.push(wrappedObject);
                            }
                        });
                        self.original.produits = $scope.producteur.produits;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Producteurs");
        };
        ProducteurResource.get({ProducteurId:$routeParams.ProducteurId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.producteur);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.producteur.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Producteurs");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Producteurs");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.producteur.$remove(successCallback, errorCallback);
    };
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST"   
    ];
    $scope.produitsSelection = $scope.produitsSelection || [];
    $scope.$watch("produitsSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.producteur) {
            $scope.producteur.produits = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.producteur.produits.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});