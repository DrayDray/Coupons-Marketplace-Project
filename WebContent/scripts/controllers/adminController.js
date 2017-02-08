(function() {
	
	////creating & registering the Admin Controller(as an anonymous function) to the Controllers Module
	angular.module('CtrlsModule').controller('AdminController', function($scope, $http, couponServices){
		$scope.adminView = '';

		this.changeAdminView = function(view){
			$scope.adminView = view;
		};

		this.showAllCustomers = function(){
			$http.get('rest/customers/getAllCustomers')
			.success(function (response){
				$scope.fullListOfCustomers = response.customer;
			})
			.error(function(reponse){
				alert("Failed to retrieve customer list.");
			})
		};

		this.showAllCompanies = function(){
			$http.get('rest/companies/getAllCompanies')
			.success(function (response){
				$scope.fullListOfCompanies = response.company;
			})
			.error(function(reponse){
				alert("Failed to retrieve customer list.");
			})
		};

		this.showCustomer = function(){
			$http.get('rest/customers/getCustomer/' + $scope.customerId)
			.success(function(response){
				$scope.customer = response;
				$scope.adminView = "showOneCustomer";
			})
			.error(function(response){
				showErrorMessage(response.message);
				//alert("Failed to retrieve customer.");	
			})
		};

		this.showCompany = function(){
			$http.get('rest/companies/getCompany/' + $scope.companyId)
			.success(function(response){
				$scope.company = response;
				$scope.adminView = "showOneCompany";
			})
			.error(function(reponse){
				alert("Failed to retrieve company.");
			})
		};

		this.removeCompany = function(){
			$http.delete('rest/companies/' + $scope.companyId)
			.success(function(){
				alert("Company successfully deleted");
			})
			.error(function(){
				alert("Failed to delete company");
			})
		};

		this.removeCustomer = function(){
			$http.delete('rest/customers/' + $scope.customerId)
			.success(function(){
				alert("Customer successfully deleted");
			})
			.error(function(){
				alert("Failed to delete customer");
			})
		};

		//NEW - TEST
		this.seeAllCouponsByCompany = function(){
			couponServices.getAllCouponsByCompany()
			.then(function(response){
				$scope.fullListOfCoupons = response.coupon;
				$scope.adminView = "showCoupons";
			})
			.error(function(reponse){
				alert("Failed to retrieve company's coupons.");
			})
		};



	});
})();