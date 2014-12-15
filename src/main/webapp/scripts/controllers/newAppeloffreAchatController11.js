
angular.module('projetagricole').controller('NewAppeloffreAchatController', function ($scope, $location, locationParser, AppeloffreAchatResource , ProducteurResource , CommandesResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.appeloffreAchat = $scope.appeloffreAchat || {};
    
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
            $scope.appeloffreAchat.nomproducteur = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
            $scope.appeloffreAchat.nomproducteur.push(collectionItem);
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
            $scope.appeloffreAchat.nomcommande = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
            $scope.appeloffreAchat.nomcommande.push(collectionItem);
            });
        }
    });
    
    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/AppeloffreAchats/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        AppeloffreAchatResource.save($scope.appeloffreAchat, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/AppeloffreAchats");
    };
});