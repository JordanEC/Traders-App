angular.module('app.services', [])
.factory('Supplier', function($resource) {
	return $resource('/api/v1/suppliers/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.factory('Category', function($resource) {
	return $resource('/api/v1/categories/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.factory('Product', function($resource) {
	return $resource('/api/v1/products/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
