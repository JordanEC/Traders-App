angular.module('app.controllers', [])
.controller('LoginCtrl',function($rootScope, $http, $location) {
	var self = this
	self.called = false;
	var authenticate = function(credentials, callback) {
		var headers = credentials ? {authorization : "Basic "
			+ btoa(credentials.username + ":" + credentials.password)
			} : {};

		$http.post('login', {headers : headers}).then(function(response) {
			console.log(response);
			if (response.data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}, function() {
			$rootScope.authenticated = false;
			callback && callback();
			}
		);
	
		}

		  authenticate();
		  self.credentials = {};
		  self.login = function() {
			  self.called = true;
		      authenticate(self.credentials, function() {
		        if ($rootScope.authenticated) {
		          $location.path("/");
		          self.error = false;
		        } else {
		          $location.path("/login");
		          self.error = true;
		        }
		      });
		  };
		})
/*.controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'LoginService', 'AlertService', function($scope, $rootScope, $location, LoginService, AlertService) {
    $scope.credentials = {
        username: '',
        password: ''
    };

    $scope.login = function() {
        LoginService.login($scope.credentials).then(
            function(user) {
                $rootScope.user = user;
                $location.path('/')
                console.log('authenticated: ', user);
            },
            function(error) {
                $rootScope.user = null;
                AlertService.error(error.data.message);
                console.error(error.data);
            });
    }

}])*/
	
	////////
/*.controller('Navigation', function($rootScope, $scope, $http, $location) {
	$scope.login = function(){
		$http.post('/login', $scope.authenticationUser).
	    success(function(data, status, headers, config) {
	        console.log(data);
	      }).
	      error(function(data, status, headers, config) {
	      });
	};*/
	
	/*
		  var authenticate = function(credentials, callback) {

		  var headers = credentials ? {authorization : "Basic "
		        + btoa(credentials.username + ":" + credentials.password)
		    } : {};
			  var headers = {credentials.email + credentials.password};
		    $http.get('user', {headers : headers}).success(function(data) {
		      if (data.email) {
		        $rootScope.authenticated = true;
		      } else {
		        $rootScope.authenticated = false;
		      }
		      callback && callback();
		    }).error(function() {
		      $rootScope.authenticated = false;
		      callback && callback();
		    });

		  }

		  authenticate();
		  $scope.credentials = {};
		  $scope.login = function() {
		      authenticate($scope.credentials, function() {
		        if ($rootScope.authenticated) {
		          $location.path("/");
		          $scope.error = false;
		        } else {
		          $location.path("/login");
		          $scope.error = true;
		        }
		      });
		  };
})*/
.controller('SupplierListController', function($scope, $state, popupService, $window, Supplier) {
	$scope.suppliers = Supplier.query(); //fetch all suppliers. Issues a GET to /api/vi/suppliers
	//console.log(Supplier.query());
	$scope.deleteSupplier = function(supplier) { // Delete a Supplier. Issues a DELETE to /api/v1/suppliers/:id
		if (popupService.showPopup('Really delete this?')) {
			supplier.$delete(function() {
		        $scope.suppliers = Supplier.query(); 
		        $state.go('suppliers');
			});
	    }
	};

})
.controller('SupplierViewController', function($scope, $stateParams, Supplier) {
	$scope.supplier = Supplier.get({ id: $stateParams.id }); //Get a single supplier.Issues a GET to /api/v1/suppliers/:id
})
.controller('SupplierCreateController', function($scope, $state, $stateParams, Supplier) {
	$scope.supplier = new Supplier();  //create new supplier instance. Properties will be set via ng-model on UI
	$scope.addSupplier = function() { //create a new supplier. Issues a POST to /api/v1/suppliers
    $scope.supplier.$save(function() {
    	$state.go('suppliers'); // on success go back to the list i.e. suppliers state.
    });
  };
})
.controller('SupplierEditController', function($scope, $state, $stateParams, Supplier) {
	$scope.updateSupplier = function() { //Update the edited supplier. Issues a PUT to /api/v1/suppliers/:id
	    $scope.supplier.$update(function() {
	    	$state.go('suppliers'); // on success go back to the list i.e. suppliers state.
	    });
	};

	$scope.loadSupplier = function() { //Issues a GET request to /api/v1/suppliers/:id to get a supplier to update
		$scope.supplier = Supplier.get({ id: $stateParams.id });
	};
	
	$scope.loadSupplier(); // Load a supplier which can be edited on UI
})


.controller('CategoryListController', function($scope, $state, popupService, $window, Category) {
	$scope.categories = Category.query(); //fetch all categories. Issues a GET to /api/vi/categories
	$scope.deleteCategory = function(category) { // Delete a Category. Issues a DELETE to /api/v1/categories/:id
		if (popupService.showPopup('Really delete this?')) {
			category.$delete(function() {
		        $scope.categories = Category.query(); 
		        $state.go('categories');
			});
	    }
	};
})
.controller('CategoryViewController', function($scope, $stateParams, Category) {
	$scope.category = Category.get({ id: $stateParams.id }); //Get a single category.Issues a GET to /api/v1/categories/:id
})
.controller('CategoryCreateController', function($scope, $state, $stateParams, Category) {
	$scope.category = new Category();  //create new category instance. Properties will be set via ng-model on UI
	$scope.addCategory = function() { //create a new category. Issues a POST to /api/v1/categories
    $scope.category.$save(function() {
    	$state.go('categories'); // on success go back to the list i.e. categories state.
    });
  };
})
.controller('CategoryEditController', function($scope, $state, $stateParams, Category) {
	$scope.updateCategory = function() { //Update the edited category. Issues a PUT to /api/v1/categories/:id
	    $scope.category.$update(function() {
	    	$state.go('categories'); // on success go back to the list i.e. categories state.
	    });
	};

	$scope.loadCategory = function() { //Issues a GET request to /api/v1/categories/:id to get a category to update
		$scope.category = Category.get({ id: $stateParams.id });
	};
	
	$scope.loadCategory(); // Load a category which can be edited on UI
})


.controller('ProductListController', function($scope, $state, popupService, $window, Product) {
	$scope.products = Product.query(); //fetch all products. Issues a GET to /api/vi/products
	$scope.deleteProduct = function(product) { // Delete a Product. Issues a DELETE to /api/v1/products/:id
		if (popupService.showPopup('Really delete this?')) {
			product.$delete(function() {
		        $scope.products = Product.query(); 
		        $state.go('products');
			});
	    }
	};
})
.controller('ProductViewController', function($scope, $stateParams, Product) {
	$scope.product = Product.get({ id: $stateParams.id }); //Get a single product.Issues a GET to /api/v1/products/:id
})
.controller('ProductCreateController', function($scope, $state, $stateParams, Product, Category, Supplier) {
	$scope.categories=Category.query();
	$scope.suppliers=Supplier.query();	
	$scope.product = new Product();  //create new product instance. Properties will be set via ng-model on UI
	$scope.addProduct = function() { //create a new product. Issues a POST to /api/v1/products
    $scope.product.$save(function() {
    	$state.go('products'); // on success go back to the list i.e. products state.
    });
  };
})
.controller('ProductEditController', function($scope, $state, $stateParams, Product, Category, Supplier) {
	$scope.categories=Category.query();
	$scope.suppliers=Supplier.query();
	$scope.updateProduct = function() { //Update the edited product. Issues a PUT to /api/v1/products/:id
	    $scope.product.$update(function() {
	    	$state.go('products'); // on success go back to the list i.e. products state.
	    });
	};

	$scope.loadProduct = function() { //Issues a GET request to /api/v1/products/:id to get a product to update
		$scope.product = Product.get({ id: $stateParams.id });
	};
	
	$scope.loadProduct(); // Load a product which can be edited on UI
});
