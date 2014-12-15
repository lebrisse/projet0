

angular.module('projetagricole').controller('EditAppeloffreVenteController', function($scope, $routeParams, $location, AppeloffreVenteResource , ProducteurResource , CommandesResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.appeloffreVente = new AppeloffreVenteResource(self.original);
            ProducteurResource.queryAll(function(items) {
                $scope.nomproducteurSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproducteur
                    };
                    if($scope.appeloffreVente.producteur && item.id == $scope.appeloffreVente.producteur.id) {
                        $scope.producteurSelection = labelObject;
                        $scope.appeloffreVente.producteur = wrappedObject;
                        self.original.producteur = $scope.appeloffreVente.producteur;
                    }
                    return labelObject;
                });
            });
         //   $scope.commandes = new CommandesResource(self.original);
            CommandesResource.queryAll(function(items) {
                $scope.nomcommandeSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomcommande
                    };
                    if($scope.appeloffreVente.producteur && item.id == $scope.appeloffreVente.commandes.id) {
                        $scope.commandesSelection = labelObject;
                        $scope.appeloffreVente.commandes = wrappedObject;
                        self.original.commandes = $scope.appeloffreVente.commandes;
                    }
                    return labelObject;
                });
            });
            
        };
        var errorCallback = function() {
            $location.path("/AppeloffreVentes");
        };
        AppeloffreVenteResource.get({AppeloffreVenteId:$routeParams.AppeloffreVenteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.appeloffreVente);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.appeloffreVente.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/AppeloffreVentes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/AppeloffreVentes");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.appeloffreVente.$remove(successCallback, errorCallback);
    };
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST"  
    ];
    $scope.$watch("producteurSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.appeloffreVente.producteur = {};
            $scope.appeloffreVente.producteur.id = selection.value;
        }
    });
    
    $scope.get();
});