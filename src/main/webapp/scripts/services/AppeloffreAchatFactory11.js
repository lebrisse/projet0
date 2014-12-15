angular.module('projetagricole').factory('AppeloffreAchatResource', function($resource){
	var resource = $resource('rest/appeloffreAchats/:AppeloffreAchatId',{AppeloffreAchatId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
	return resource;
});