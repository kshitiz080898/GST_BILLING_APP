angular.module("productBillingApp").controller("ProductController", ProductController);

ProductController.inject = [ '$scope', 'Product' ];

function ProductController($scope, Product) {
	
	$scope.products = Product.query();

	$scope.product = {};
	
	$scope.buttonText="Submit";
	
	$scope.saveProduct = function() {
		if ($scope.product.product_code !== undefined) {
			Product.update($scope.product, function() {
				$scope.products = Product.query();
				$scope.product = {};
				$scope.buttonText="Submit";
			});
		} else {
			Product.save($scope.product, function() {
				$scope.products = Product.query();
				$scope.product = {};
			});
		}
	}

	$scope.updateProductInit = function(product) {
		$scope.buttonText="Update";
		$scope.product = product;
	}

}