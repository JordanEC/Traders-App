angular.module('app.services', [])
.service('AuthService', function() {
	return {
		user : null,
		authorization: null,
	}
})
.factory('AuthInterceptor', function($rootScope, AuthService, localStorageService) {  
	var service = this;
    service.request = function(config) {
    	if(!AuthService.user){
    		auth = localStorageService.get('AuthService');
    		if (auth){
    			AuthService.user = auth.user;
    			$rootScope.user = auth.user;
    			AuthService.authorization = auth.authorization;
    		}
    	}

        if (AuthService.authorization) {
        	if (!config.headers.Authorization)
        		config.headers.Authorization = AuthService.authorization;
        }
        return config;
    };
    return service;
})
.factory('User', function($resource, AuthService) {
	return $resource('/api/v2/users/:id', { id: '@id' }, {update: { method:'PUT'}});
})
.factory('Supplier', function($resource, AuthService) {
	return $resource('/api/v2/suppliers/:id', { id: '@id' }, {update: { method:'PUT'}});
})
.factory('Category', function($resource) {
	return $resource('/api/v2/categories/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.factory('Product', function($resource) {
	return $resource('/api/v2/products/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
})