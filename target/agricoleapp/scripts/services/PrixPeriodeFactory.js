angular.module('projetagricole').factory('PrixPeriodeResource',
		function($resource) {
			var resource = $resource('rest/prixPeriodes/:PrixPeriodeId', {
				PrixPeriodeId : '@id'
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
				'searchByPeriode':{
					method: 'GET',
					params:{
					PrixPeriodeId: 'searchByPeriode'
					},
					isArray : false
				}
			});
			// var resource =
			// $resource('rest/prixPeriodes/:PrixPeriodeId',{PrixPeriodeId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
			return resource;
		});