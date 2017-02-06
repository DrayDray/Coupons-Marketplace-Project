(function() {
	
	////creating & registering the company Controller(as an anonymous function) to the Controllers Module
	angular.module('CtrlsModule').controller('CompanyController', function($scope, $http, couponServices){
	
	$scope.companyView = '';
	
	$scope.changeCompanyView = function(view){
		$scope.companyView = view;
	};
	
	this.updateCompany = function(){
		var companyDetailsUpdate = this.companyDetailsUpdate;	
		$http.put('rest/companies', companyDetailsUpdate)
		.success(function(){
			alert("You have successfully updated your password.");
			$window.location.assign('#Company');
		})
		.error(function(reponse){
			alert("Password Update Failed");
		})
	};
	
	this.addCouponForACompany = function(){
		var couponsCreationData = this.couponsCreationData;	
		$http.post('rest/coupons', couponsCreationData)
		.success(function(){
			alert("You have successfully added a new coupon.");
			$window.location.assign('#Company');
		})
		.error(function(reponse){
			alert("Failed to add coupon.");
		})
	}


});
})();