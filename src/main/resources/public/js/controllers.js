angular.module('app.controllers', [])
.controller('RegisterController',function($scope, $http){
	$scope.user = {};
	$scope.show = true;
	$scope.required = true;
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.registerUser = function(){
		if($scope.user.password != $scope.user.confirmpassword)
			$scope.error = 'Password Not Matching';
		else
			$http.post('register', $scope.user).success(function(res) {
				$scope.success = 'Registration successfull!';
				$scope.user = {};
			}).error(function(error) {
				$scope.error = error.message;
			});
	}
})
.controller('UserListController', function($scope, $state, popupService, $window, User, AuthService) {
	$scope.users = User.query();

	if(!AuthService.user)
		$scope.message = 'You are not logged in.';
	
	$scope.deleteUser = function(user) {
		if (popupService.showPopup('Really delete this?')) {
			user.$delete(function() {
		        $scope.users = User.query(); 
		        $state.go('users');
			});
	    }
	};

})
.controller('UserViewController', function($scope, $stateParams, User) {
	$scope.user = User.get({ id: $stateParams.id });
})
.controller('UserCreateController', function($scope, $state, $stateParams, User) {
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.show = true;
	$scope.required = true;
	$scope.user = new User();
	
	$scope.addUser = function() {
		$scope.user.$save(function() {
    	$state.go('users');
    });
  };
})
.controller('UserEditController', function($scope, $state, $stateParams, User, AuthService) {
	$scope.userid = AuthService.user.principal.id;
	$scope.show = $state.current.name == 'editUser' && $state.params.id == $scope.userid;
	$scope.required = $state.params.id == $scope.userid;
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.$state = $state;
	$scope.updateUser = function() {
	    $scope.user.$update(function() {
	    	$state.go('users');
	    });
	};

	$scope.loadUser = function() {
		$scope.user = User.get({ id: $stateParams.id });
	};
	
	$scope.loadUser();
})
.controller('SupplierListController', function($scope, $state, popupService, $window, Supplier, AuthService) {
	$scope.suppliers = Supplier.query();

	if(!AuthService.user)
		$scope.message = 'You are not logged in.';
	
	$scope.deleteSupplier = function(supplier) {
		if (popupService.showPopup('Really delete this?')) {
			supplier.$delete(function() {
		        $scope.suppliers = Supplier.query(); 
		        $state.go('suppliers');
			});
	    }
	};
})
.controller('SupplierViewController', function($scope, $stateParams, Supplier) {
	$scope.supplier = Supplier.get({ id: $stateParams.id });
})
.controller('SupplierCreateController', function($scope, $state, $stateParams, Supplier) {
	$scope.supplier = new Supplier();
	$scope.addSupplier = function() {
    $scope.supplier.$save(function() {
    	$state.go('suppliers');
    });
  };
})
.controller('SupplierEditController', function($scope, $state, $stateParams, Supplier) {
	$scope.updateSupplier = function() {
	    $scope.supplier.$update(function() {
	    	$state.go('suppliers');
	    });
	};

	$scope.loadSupplier = function() {
		$scope.supplier = Supplier.get({ id: $stateParams.id });
	};
	
	$scope.loadSupplier();
})


.controller('CategoryListController', function($scope, $state, popupService, $window, Category, AuthService) {
	$scope.categories = Category.query();
	
	if(!AuthService.user)
		$scope.message = 'You are not logged in.';
	
	$scope.deleteCategory = function(category) {
		if (popupService.showPopup('Really delete this?')) {
			category.$delete(function() {
		        $scope.categories = Category.query(); 
		        $state.go('categories');
			});
	    }
	};
})
.controller('CategoryViewController', function($scope, $stateParams, Category) {
	$scope.category = Category.get({ id: $stateParams.id });
})
.controller('CategoryCreateController', function($scope, $state, $stateParams, Category) {
	$scope.category = new Category();
	$scope.addCategory = function() { 
    $scope.category.$save(function() {
    	$state.go('categories');
    });
  };
})
.controller('CategoryEditController', function($scope, $state, $stateParams, Category) {
	$scope.updateCategory = function() { 
	    $scope.category.$update(function() {
	    	$state.go('categories');
	    });
	};

	$scope.loadCategory = function() {
		$scope.category = Category.get({ id: $stateParams.id });
	};
	
	$scope.loadCategory();
})


.controller('ProductListController', function($rootScope, $scope, $state, popupService, $window, Product, AuthService) {
	$scope.products = Product.query();
	if(!AuthService.user)
		$scope.message = 'You are not logged in.';
	$scope.deleteProduct = function(product) {
		if (popupService.showPopup('Really delete this?')) {
			product.$delete(function() {
		        $scope.products = Product.query(); 
		        $state.go('products');
			});
	    }
	};
})
.controller('ProductViewController', function($scope, $stateParams, Product) {
	$scope.product = Product.get({ id: $stateParams.id });
})
.controller('ProductCreateController', function($scope, $state, $stateParams, Product, Category, Supplier) {
	$scope.categories=Category.query();
	$scope.suppliers=Supplier.query();	
	$scope.product = new Product(); 
	$scope.addProduct = function() {
    $scope.product.$save(function() {
    	$state.go('products');
    });
  };
})
.controller('ProductEditController', function($scope, $state, $stateParams, Product, Category, Supplier) {
	$scope.categories=Category.query();
	$scope.suppliers=Supplier.query();
	$scope.updateProduct = function() {
	    $scope.product.$update(function() {
	    	$state.go('products');
	    });
	};

	$scope.loadProduct = function() {
		$scope.product = Product.get({ id: $stateParams.id });
	};
	
	$scope.loadProduct();
});
