angular.module('projetagricole').factory('PrixRegionResource',
		function($resource) {
			var resource = $resource('rest/prixRegions/:PrixRegionId', {
				PrixRegionId : '@id'
			}, {
				'queryAll' : {
					method : 'GET',
					isArray : true
				},
				'query' : {
					method : 'GET',
					isArray : false
				},
				'searchByRegion' : {
					method : 'GET',
					params : {
						PrixRegionId : 'searchByRegion'
					},
					isArray : false
				}
			});

			return resource;
		});

/*
 * var projetagricole = angular.module('projetagricole');
 * projetagricole.factory('PrixRegionResource', function($http,$q) {
 * this.getData = function(region,nomproduit) { deferred = $q.defer(); $http({
 * method: 'GET', url: 'https://rest/prixRegions/searchByRegion', params:
 * 'region,nomproduit' }).success(function(data){ // With the data succesfully
 * returned, we can resolve promise and we can access it in controller
 * deferred.resolve(); }).error(function(){ alert("error"); //let the function
 * caller know the error deferred.reject(error); }); return deferred.promise; }
 * });
 */