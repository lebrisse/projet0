angular.module('projetagricole').factory('AppeloffreVenteResource', function($resource){
	var resource = $resource('rest/appeloffreVentes/:AppeloffreVenteId',{AppeloffreVenteId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
	return resource;
});