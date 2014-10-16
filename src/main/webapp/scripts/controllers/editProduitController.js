

angular.module('projetagricole').controller('EditProduitController', function($scope, $routeParams, $location, ProduitResource , ProducteurResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produit = new ProduitResource(self.original);
            ProducteurResource.queryAll(function(items) {
                $scope.producteurSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproducteur
                    };
                    if($scope.produit.producteur && item.id == $scope.produit.producteur.id) {
                        $scope.producteurSelection = labelObject;
                        $scope.produit.producteur = wrappedObject;
                        self.original.producteur = $scope.produit.producteur;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Produits");
        };
        ProduitResource.get({ProduitId:$routeParams.ProduitId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produit);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produit.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Produits");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Produits");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produit.$remove(successCallback, errorCallback);
    };
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST"  
    ];
    $scope.$watch("producteurSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produit.producteur = {};
            $scope.produit.producteur.id = selection.value;
        }
    });
    
    $scope.get();
});