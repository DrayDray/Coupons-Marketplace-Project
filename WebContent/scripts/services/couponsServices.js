(function() {
	angular.module('servicesModule', []).service('couponServices', function($http){


	//NEW
	this.fullListOfCoupons = {};
	this.getAllCouponsByCompany = function(){
		return($http.get('rest/coupons/getAllCouponsByCompany/' + $scope.companyId)
				.success(function(response){
					this.fullListOfCoupons = response.coupon;
					return this.fullListOfCoupons;
				})
				.error(function(reponse){
					alert("Failed to retrieve company's coupons.");
					return response;
				})

		);
	}
});	
})();