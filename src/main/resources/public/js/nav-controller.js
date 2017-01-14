angular.module('navController', [])
	.controller('nav', function($scope, $state) {
		$scope.title = 'Traders app';

		// returns true if the current router url matches the passed in url
		// so views can set 'active' on links easily
		$scope.isUrl = function(url) {
			if (url === '#') return false;
			return ('#' + $state.$current.url.source + '/').indexOf(url + '/') === 0;
		};

		$scope.pages = [
			{
				name: 'Suppliers',
				url: '#/suppliers'
			},
			{
				name: 'Categories',
				url: '#/categories'
			},
			{
				name: 'Products',
				url: '#/products'
			},
			{
				name: 'Login',
				url: '/login'		//#/login
			},
			{
				name: 'Logout',
				url: '/logout'		//#/login
			}
		]
	});