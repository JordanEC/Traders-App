(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.controllers', 'app.services', 'multipleSelect'])

	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	// in the state definition call add property `resolve: req('/views/ui.js')`
	// or `resolve: req(['/views/ui.js'])`
	// or `resolve: req('views/ui')`
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}

	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider, $httpProvider){
		var origController = app.controller
		app.controller = function (name, constructor){
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		}

		var viewsPrefix = 'views/';

		// For any unmatched url, send to /
		$urlRouterProvider
		/*.when('/login', {					//
			templateUrl : viewsPrefix + 'login.html',
			controller : 'navigation'
		})		*/							//
		//.when(viewsPrefix + '/login', ['$match', '$stateParams', function ($match, $stateParams) {
		    /*if ($state.$current.navigable != state || !equalForKeys($match, $stateParams)) {
		        $state.transitionTo(state, $match, false);
		    }
		}]);*/
		.otherwise("/")

		$stateProvider
			// you can set this to no template if you just want to use the html in the page
			.state('home', {
				url: "/",
				templateUrl: viewsPrefix + "home.html",
				data: {
					pageTitle: 'Home'
				}
			})
			.state('login', {
				url: "/login",
				templateUrl: viewsPrefix + "login.html",
				controller:'LoginCtrl',
				controllerAs: 'controller'
			})
			.state('suppliers',{
		        url:'/suppliers',
		        templateUrl: viewsPrefix + 'supplier/suppliers.html',
		        controller:'SupplierListController'
		    })
		    .state('viewSupplier',{
			       url:'/suppliers/:id/view',
			       templateUrl: viewsPrefix + 'supplier/supplier-view.html',
			       controller:'SupplierViewController'
		    })
		    .state('newSupplier',{
			        url:'/suppliers/new',
			        templateUrl: viewsPrefix + 'supplier/supplier-add.html',
			        controller:'SupplierCreateController'
		    })
		    .state('editSupplier',{
			        url:'/suppliers/:id/edit',
			        templateUrl: viewsPrefix + 'supplier/supplier-edit.html',
			        controller:'SupplierEditController'
		    })	    
			.state('categories',{
		        url:'/categories',
		        templateUrl: viewsPrefix + 'category/categories.html',
		        controller:'CategoryListController'
		    })
		    .state('viewCategory',{
			       url:'/categories/:id/view',
			       templateUrl: viewsPrefix + 'category/category-view.html',
			       controller:'CategoryViewController'
		    })
		    .state('newCategory',{
			        url:'/categories/new',
			        templateUrl: viewsPrefix + 'category/category-add.html',
			        controller:'CategoryCreateController'
		    })
		    .state('editCategory',{
			        url:'/categories/:id/edit',
			        templateUrl: viewsPrefix + 'category/category-edit.html',
			        controller:'CategoryEditController'
		    })
			.state('products',{
		        url:'/products',
		        templateUrl: viewsPrefix + 'product/products.html',
		        controller:'ProductListController'
		    })
		    .state('viewProduct',{
			       url:'/products/:id/view',
			       templateUrl: viewsPrefix + 'product/product-view.html',
			       controller:'ProductViewController'
		    })
		    .state('newProduct',{
			        url:'/products/new',
			        templateUrl: viewsPrefix + 'product/product-add.html',
			        controller:'ProductCreateController'
		    })
		    .state('editProduct',{
			        url:'/products/:id/edit',
			        templateUrl: viewsPrefix + 'product/product-edit.html',
			        controller:'ProductEditController'
		    })
		    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	})
	/*.run(['$rootScope', '$location', 'LoginService', 'AlertService', function($rootScope, $location, LoginService, AlertService) {

    $rootScope.user = null;

    $rootScope.$on('$routeChangeStart', function(ev, next, curr) {
        AlertService.reset();

        if (next.$$route) {
            if (!curr) { //on page reload
                authenticateOnServer(LoginService, $rootScope, $location);
            } else {
                if ($rootScope.user) {
                    if (next.$$route.originalPath == '/login') {
                        $location.path('/')
                    }
                } else {
                    $location.path('/login');
                }
            }
        }
        function authenticateOnServer(LoginService, $rootScope, $location) {
            LoginService.login().then(
                function(user) {
                    if (user) {
                        $rootScope.user = user;
                        console.log('authenticated: ', user);
                    } else {
                        $rootScope.user = null
                        $location.path('/login')
                    }
                },
                function() {
                    $rootScope.user = null
                    $location.path('/login')
                });
        }
    });
}])*/
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	]);
}());
