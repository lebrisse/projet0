

angular.module('projetagricole').controller('EditAppeloffreAchatController', function($scope, $routeParams, $location, AppeloffreAchatResource , ProducteurResource , CommandesResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.appeloffreAchat = new AppeloffreAchatResource(self.original);
            ProducteurResource.queryAll(function(items) {
                $scope.nomproducteurSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomproducteur
                    };
                    if($scope.appeloffreAchat.producteur && item.id == $scope.appeloffreAchat.producteur.id) {
                        $scope.producteurSelection = labelObject;
                        $scope.appeloffreAchat.producteur = wrappedObject;
                        self.original.producteur = $scope.appeloffreAchat.producteur;
                    }
                    return labelObject;
                });
            });
            CommandesResource.queryAll(function(items) {
                $scope.nomcommandeSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.nomcommande
                    };
                    if($scope.appeloffreAchat.producteur && item.id == $scope.appeloffreAchat.commandes.id) {
                        $scope.commandesSelection = labelObject;
                        $scope.appeloffreAchat.commandes = wrappedObject;
                        self.original.commandes = $scope.appeloffreAchat.commandes;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/AppeloffreAchats");
        };
        AppeloffreAchatResource.get({AppeloffreAchatId:$routeParams.AppeloffreAchatId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.appeloffreAchat);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.appeloffreAchat.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/AppeloffreAchats");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/AppeloffreAchats");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.appeloffreAchat.$remove(successCallback, errorCallback);
    };
    
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST"  
    ];
    $scope.$watch("producteurSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.appeloffreAchat.producteur = {};
            $scope.appeloffreAchat.producteur.id = selection.value;
        }
    });
    
    $scope.get();
});