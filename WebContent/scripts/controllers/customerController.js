(function() {
	
	//creating & registering the company Controller(as an anonymous function) to the Controllers Module
	angular.module('CtrlsModule').controller('CustomerController', function($scope, $http, couponServices){
	
	$scope.customerView = '';
	
	$scope.changeCustomerView = function(view) {
		$scope.customerView = view;
	};
	
	this.updateCustomer = function (){
		var customerDetailsUpdate = this.customerDetailsUpdate;	
		$http.put('rest/customers', customerDetailsUpdate)
		.success(function(){
			alert("You have successfully updated your password.");
			$window.location.assign('#Customer');
		})
		.error(function(reponse){
			alert("Password Update Failed");
		})
	};
	

});
})();