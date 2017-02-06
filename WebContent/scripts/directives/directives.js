
angular.module('directivesModule', [])
	
	//ADMIN
	.directive('allCustomersView', function() {
		return{
		restrict: 'E',
		templateUrl: 'templates/admin/all-customers-view.html'
	};	
	})
	
	.directive('allCompaniesView', function() {
		return{
		restrict: 'E',
		templateUrl: 'templates/admin/all-companies-view.html'
	};	
	})
	
	.directive('chooseCustomerView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/choose-customer-view.html'
	};
	})
	
	.directive('chooseCompanyView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/choose-company-view.html'
	};
	})
	
	.directive('showOneCustomerView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/show-one-customer-view.html'
	};
	})
	
	.directive('showOneCompanyView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/show-one-company-view.html'
	};
	})
	
	.directive('unsubscribeCustomerView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/unsubscribe-customer-view.html'
	};
	})
	
	.directive('unsubscribeCompanyView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/unsubscribe-company-view.html'
	};
	})
	
		.directive('getAllCouponsByCompanyView', function() {
	return{
		restrict: 'E',
		templateUrl: 'templates/admin/get-all-coupons-by-company-view.html'
	};
	})
	
	//COMPANY
	.directive('changeCompanyPasswordView', function() {
		return{
			restrict: 'E',
			templateUrl: 'templates/company/change-company-password-view.html'
		};
	})
	
		.directive('companyRegisterView', function() {
		return{
			restrict: 'E',
			templateUrl: 'templates/company/company-register-view.html'
		};
	})
	
		.directive('addCouponView', function() {
		return{
			restrict: 'E',
			templateUrl: 'templates/company/add-coupon-view.html'
		};
	})
	
	//CUSTOMER
	.directive('changeCustomerPasswordView', function() {
		return{
			restrict: 'E',
			templateUrl: 'templates/customer/change-customer-password-view.html'
		};
	})
	
	.directive('customerRegisterView', function() {
		return{
			restrict: 'E',
			templateUrl: 'templates/customer/customer-register-view.html'
		};
	})
	
	;
