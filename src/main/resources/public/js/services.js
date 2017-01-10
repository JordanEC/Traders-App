angular.module('app.services', [])
.factory('User', function($resource) {
	return $resource('/api/v2/users', { email: '@email', password: '@password' }, {update: {method: 'PUT'}});
})
.factory('Supplier', function($resource) {
	return $resource('/api/v2/suppliers/:id', { id: '@id' }, {update: {method: 'PUT'}});
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
});
