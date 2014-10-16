

angular.module('projetagricole').controller('EditCategorieProduitController', function($scope, $routeParams, $location, CategorieProduitResource , ProduitResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.categorieProduit = new CategorieProduitResource(self.original);
            ProduitResource.queryAll(function(items) {
                $scope.produitsSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproduit
                    };
                    if($scope.categorieProduit.produits){
                        $.each($scope.categorieProduit.produits, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produitsSelection.push(labelObject);
                                $scope.categorieProduit.produits.push(wrappedObject);
                            }
                        });
                        self.original.produits = $scope.categorieProduit.produits;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/CategorieProduits");
        };
        CategorieProduitResource.get({CategorieProduitId:$routeParams.CategorieProduitId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.categorieProduit);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.categorieProduit.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/CategorieProduits");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/CategorieProduits");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.categorieProduit.$remove(successCallback, errorCallback);
    };
    
    $scope.produitsSelection = $scope.produitsSelection || [];
    $scope.$watch("produitsSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.categorieProduit) {
            $scope.categorieProduit.produits = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categorieProduit.produits.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});