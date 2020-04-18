 angular.module('productBillingApp').factory('Product', Product);

Product.$inject = [ '$resource' ];

function Product($resource) {
	var resourceUrl = 'api/product/:product_code';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}