

angular.module('projetagricole').controller('SearchPrixPeriodeController', function($scope, $http, PrixPeriodeResource,dateFilter ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.prixMoyen = 0;
    

    $scope.numberOfPages = function() {
        var result = Math.ceil($scope.filteredResults.length/$scope.pageSize);
        var max = (result == 0) ? 1 : result;
        $scope.pageRange = [];
        for(var ctr=0;ctr<max;ctr++) {
            $scope.pageRange.push(ctr);
        }
        return max;
    };
    $scope.regionList = [
                         "CENTRE",
                         "LITTORAL",
                         "SUD",
                         "EST"
    ];
    
    $scope.searchByPeriode = function() {
    	var data=PrixPeriodeResource.searchByPeriode($scope.search,function(){
    		console.log(data);
            $scope.prixp=data.prixMoyen;
            window.alert('prix: '+ $scope.prixp);
        });
    };

    $scope.performSearch = function() {
        $scope.searchResults = PrixPeriodeResource.queryAll(function(){
            $scope.numberOfPages();
        });
        var div=$scope.filteredResults.length;
    	var prixmoyen=0;        	
    	for(var i=0;i<div;i++){
    		prixmoyen=prixmoyen+$scope.filteredResults[i].prix;
    	}
    	$scope.prixMoyen = prixmoyen/div;
    	
    };
    
    $scope.previous = function() {
       if($scope.currentPage > 0) {
           $scope.currentPage--;
       }
    };
    
    $scope.next = function() {
       if($scope.currentPage < ($scope.numberOfPages() - 1) ) {
           $scope.currentPage++;
       }
    };
    
    $scope.setPage = function(n) {
       $scope.currentPage = n;
    };

    $scope.performSearch();
});