

angular.module('projetagricole').controller('EditCommandesController', function($scope, $routeParams, $location, CommandesResource , ProduitResource, ClientResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.commandes = new CommandesResource(self.original);
            ProduitResource.queryAll(function(items) {
                $scope.nomproduitSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproduit
                    };
                    if($scope.commandes.nomproduit){
                        $.each($scope.commandes.nomproduit, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.nomproduitSelection.push(labelObject);
                                $scope.commandes.nomproduit.push(wrappedObject);
                            }
                        });
                        self.original.nomproduit = $scope.commandes.nomproduit;
                    }
                    return labelObject;
                });
            });
            ClientResource.queryAll(function(items) {
                $scope.nomclientSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomclient
                    };
                    if($scope.commandes.nomclient){
                        $.each($scope.commandes.nomclient, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.nomclientSelection.push(labelObject);
                                $scope.commandes.nomclient.push(wrappedObject);
                            }
                        });
                        self.original.nomclient = $scope.commandes.nomclient;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Commandess");
        };
        CommandesResource.get({CommandesId:$routeParams.CommandesId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.commandes);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.commandes.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Commandess");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Commandess");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.commandes.$remove(successCallback, errorCallback);
    };
    
    $scope.nomproduitSelection = $scope.nomproduitSelection || [];
    $scope.$watch("nomproduitSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.commandes) {
            $scope.commandes.nomproduit = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.commandes.nomproduit.push(collectionItem);
            });
        }
    });
    $scope.nomclientSelection = $scope.nomclientSelection || [];
    $scope.$watch("nomclientSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.commandes) {
            $scope.commandes.nomclient = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.commandes.nomclient.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});