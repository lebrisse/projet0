angular.module('projetagricole').factory('ProduitResource',
		function($resource) {
			var resource = $resource('rest/produits/:ProduitId', {
				ProduitId : '@id'
			}, {
				'queryAll' : {
					method : 'GET',
					isArray : true
				},
				'query' : {
					method : 'GET',
					isArray : false
				},
				'update' : {
					method : 'PUT'
				},
				'create' :  {
					method : 'POST',
					headers: {'Content-Type': 'undefined'}
				}
			});
			return resource;
		});