angular.module('projetagricole').factory('CommandesResource', function($resource){
    var resource = $resource('rest/commandess/:CommandesId',{CommandesId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});