angular.module('projetagricole').factory('httpinterceptor',['$q','$location',function($q,$location){
	 return function (promise) {
	      var success = function (response) {
	    	  $location.url('/Produits');
	          return response;
	      },

	      var error = function (response) {
	    	  // AGROBOOK server react on 403 forbiden and not on 4 a01. We can keep both.
	          if (response.status === 401 || response.status === 403) {
	        	  // Forward to login page and return response after setting the location, if not
	        	  // the application will display an error.
	              $location.url('/connexion');
	              return response;
	          }else  if( response.status === 408 || response.status === 404){
	                 $location.url('/Produits');
	              return response;
	          }else{
	              return $q.reject(response);
	          }  
	      };
	      return promise.then(success, error);
	  };
	  
	}]);
