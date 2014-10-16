angular.module('projetagricole').factory('CategorieProduitResource', function($resource){
    var resource = $resource('rest/categorieproduits/:CategorieProduitId',{CategorieProduitId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});