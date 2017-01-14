angular.module('app.services', [])
/*.factory('LoginService', ['$http', 'Base64Service', function($http, Base64Service) {
//	return $resource('/api/v2/users', { email: '@email', password: '@password' }, {update: {method: 'PUT'}});
	return {
        login: function(credentials) {
            if (credentials) {
                var auth = 'Basic ' + Base64Service.encode(credentials.username + ':' + credentials.password);
            }
            return $http({
                method: 'GET',
                url: '/login',
                headers: credentials ? {
                    'Authorization': auth
                } : {}
            }).then(function(response) {
                return response.data;
            });
        },
        logout: function() {
            return $http.get('/api/auth/logout')
        }
    }
}])*/
.factory('Base64Service', function() {
    var keyStr = 'ABCDEFGHIJKLMNOP' + 'QRSTUVWXYZabcdef' + 'ghijklmnopqrstuv' + 'wxyz0123456789+/' + '=';
    return {
        encode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                }
                else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + keyStr.charAt(enc3) + keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                alert("There were invalid base64 characters in the input text.\n" + "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" + "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };
})

/*.factory('Supplier', function($resource,Base64Service) {
	//if (credentials) {
    	//var auth = 'Basic ' + Base64Service.encode(credentials.username + ':' + credentials.password);
    //}
	var auth = 'Basic ' + Base64Service.encode('admin:Aa123456');
	return $resource('/api/v2/suppliers/:id', { id: '@id' }, {
        //'query':  {method:'GET', isArray:false, headers: {'Authorization': auth}},
        'update': { method:'PUT', headers: {'Authorization': auth} }
        
		/*get: {
			method: 'GET',
			isArray: false,
	        headers: {'Authorization': auth}
		}*/	
/*		update: {
			method: 'PUT'}*/
//	});
//})
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
.factory('AlertService', ['$rootScope', '$location', '$anchorScroll', function($rootScope, $location, $anchorScroll) {
    return {
        success: function(msg, link, timeMillis) {
            var alert = {
                message: msg ? msg : 'success',
                type: 'success',
                link: link,
                id: new Date().getTime()
            }

            doAlert($rootScope, $location, $anchorScroll, alert, timeMillis, 1000)
        },
        error: function(msg, link, timeMillis) {
            var alert = {
                message: msg ? msg : 'fail',
                type: 'danger',
                link: link,
                id: new Date().getTime()
            }
            doAlert($rootScope, $location, $anchorScroll, alert, timeMillis, 5000)
        },
        reset: function() {
            $rootScope.alerts = [];
        }
    }
}]);

function doAlert($rootScope, $location, $anchorScroll, alert, timeMillis, defaultTimeMillis) {
    var alertId = 'alert.' + alert.id;

    $rootScope.alerts = [];
    $rootScope.alerts.push(alert);
    autoHide(alertId, (timeMillis ? timeMillis : defaultTimeMillis))

    $location.hash(alertId);
    $anchorScroll();
}

function autoHide(alertId, timeMillis) {
    setTimeout(function() {
        console.log($('#alert'));
        $('#alert').fadeOut('slow')
    }, timeMillis);
};
