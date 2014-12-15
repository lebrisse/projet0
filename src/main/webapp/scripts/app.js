'use strict';

angular.module('projetagricole',['ngRoute','ngResource'])
.config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider) {

	$routeProvider
	.when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
	.when('/AppeloffreAchats',{templateUrl:'views/AppeloffreAchat/search.html',controller:'SearchAppeloffreAchatController'})
      .when('/AppeloffreAchats/new',{templateUrl:'views/AppeloffreAchat/detail.html',controller:'NewAppeloffreAchatController'})
      .when('/AppeloffreAchats/edit/:AppeloffreAchatId',{templateUrl:'views/AppeloffreAchat/detail.html',controller:'EditAppeloffreAchatController'})
      .when('/AppeloffreVentes',{templateUrl:'views/AppeloffreVente/search.html',controller:'SearchAppeloffreVenteController'})
      .when('/AppeloffreVentes/new',{templateUrl:'views/AppeloffreVente/detail.html',controller:'NewAppeloffreVenteController'})
      .when('/AppeloffreVentes/edit/:AppeloffreVenteId',{templateUrl:'views/AppeloffreVente/detail.html',controller:'EditAppeloffreVenteController'})
	.when('/CategorieProduits',{templateUrl:'views/CategorieProduit/search.html',controller:'SearchCategorieProduitController'})
	.when('/CategorieProduits/new',{templateUrl:'views/CategorieProduit/detail.html',controller:'NewCategorieProduitController'})
	.when('/CategorieProduits/edit/:CategorieProduitId',{templateUrl:'views/CategorieProduit/detail.html',controller:'EditCategorieProduitController'})
	.when('/Clients',{templateUrl:'views/Client/search.html',controller:'SearchClientController'})
	.when('/Clients/new',{templateUrl:'views/Client/detail.html',controller:'NewClientController'})
	.when('/Clients/edit/:ClientId',{templateUrl:'views/Client/detail.html',controller:'EditClientController'})
	.when('/Commandess',{templateUrl:'views/Commandes/search.html',controller:'SearchCommandesController'})
	.when('/Commandess/new',{templateUrl:'views/Commandes/detail.html',controller:'NewCommandesController'})
	.when('/Commandess/edit/:CommandesId',{templateUrl:'views/Commandes/detail.html',controller:'EditCommandesController'})
	.when('/Producteurs',{templateUrl:'views/Producteur/search.html',controller:'SearchProducteurController'})
	.when('/Producteurs/new',{templateUrl:'views/Producteur/detail.html',controller:'NewProducteurController'})
	.when('/Producteurs/edit/:ProducteurId',{templateUrl:'views/Producteur/detail.html',controller:'EditProducteurController'})
	.when('/Produits',{templateUrl:'views/Produit/search.html',controller:'SearchProduitController'})
	.when('/Produits/new',{templateUrl:'views/Produit/detail.html',controller:'NewProduitController'})
	.when('/Produits/edit/:ProduitId',{templateUrl:'views/Produit/detail.html',controller:'EditProduitController'})
	.when('/PrixRegions',{templateUrl:'views/PrixRegion/search.html',controller:'SearchPrixRegionController'})
	.when('/PrixPeriodes',{templateUrl:'views/PrixPeriode/search.html',controller:'SearchPrixPeriodeController'})
	.when('/connexion',{templateUrl:'login.html'})
	.when('/logout',{templateUrl:'views/landing.html'})
	.when('/contact',{templateUrl:'views/html/contact-us.html'})

	.otherwise({
		redirectTo: '/'
	});
}])

.controller('LandingPageController', function LandingPageController() {
})

.controller('NavController', function NavController($scope, $location) {
	$scope.matchesRoute = function(route) {
		var path = $location.path();
		return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
	};
});
