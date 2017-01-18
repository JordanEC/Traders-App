angular.module('navController', [])
	.controller('nav', function($rootScope, $scope, $state, $http, AuthService, localStorageService) {
		$scope.title = 'Traders app';
		$scope.isUrl = function(url) {
			if (url === '#') return false;
			return ('#' + $state.$current.url.source + '/').indexOf(url + '/') === 0;
		};

		$scope.login = function() {
			var base64Credential = btoa($scope.user.username + ':' + $scope.user.password);
			AuthService.authorization = 'Basic ' + base64Credential;
			$http.get('login', {}).success(function(res) {
				if (res.authenticated) {
					$scope.message = null;
					AuthService.user = res;
					$rootScope.user = AuthService.user;
					localStorageService.set('AuthService',{'user':AuthService.user,'authorization':AuthService.authorization})
					$state.go('home');
				} else {
					$scope.message = 'Wrong credentials.';
				}
			}).error(function(error) {
				$scope.message = 'Wrong credentials.';
			});
		};	
		
		$scope.logout = function(){
			$rootScope.user = null;
			AuthService.user = null;
			AuthService.authorization = null;	
			localStorageService.remove('AuthService');
			$state.go('login');
		};
		
		$scope.pages = [
		    {
			   name: 'Users',
			   url: '#/users'
		    },		       
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
			}
		]
	});