

//Defining my app module:
(function() {

	var app = angular.module("myApp", ['ngRoute', 'directivesModule', 'CtrlsModule']);
	app.config(function($routeProvider){

		//configuration of urls: which html file will open based upon the url
		$routeProvider
		.when("/", {
			templateUrl : "login.htm"
		})
		.when("/Company", {
			templateUrl : "Company.htm"
		})
		.when("/Admin", {
			templateUrl : "Admin.htm"
		})
		.when("/Customer", {
			templateUrl : "Customer.htm"
		})
		.when("/Register", {
			templateUrl : "Register.html"
		})
	})
}());

//My other JavaScript Functions
function showErrorMessage(errorMessage){
	if(errorMessage === "CustomerDoesNotExist"){
		alert("No customer with that ID exists. Please try another ID.");
	}
	else{
		alert("Unsuccessful attempt.");
	}
}
	

/*TO DO:
1. Make a proper message appear when the adding of a customer or company fails. (Ex: Name already exists, ID already exists, etc.)
(extract & print a proper message to the screen)
 */


/*Controllers, before I separated them into independent files:
 * 
	//creating and registering our Login Controller
	app.controller('LoginController', ['$http', '$scope', '$window', function($http, $scope, $window){

		this.login = function(){
			var user = this.user;		
			$http.post('rest/userservices/login', user)
			.success(function(){			
				if (user.clientType==="customer"){
					$window.location.assign('#Customer');	
				} 
				else if(user.clientType==="company"){
					$window.location.assign('#Company');
				}
				else{
					$window.location.assign('#Admin');
				}				
			})
			.error(function(reponse){
				alert("Fail");
			})

		};

		this.forwardToRegistration = function (){
			$window.location.assign('#Register');
		}

		this.register = function (){
			var newUser = this.newUser;		
			if (newUser.clientType==="customer"){
				$http.post('rest/customers', newUser)
				.success(function(){	
					alert("You have successfully registered. Welcome!");
					$window.location.assign('#Customer');
				})
				.error(function(reponse){
					alert("Failed to add customer.");
					$window.location.assign('#/');
				})
			} 
			else if(newUser.clientType==="company"){
				$http.post('rest/companies', newUser)
				.success(function(){	
					alert("You have successfully registered. Welcome!");
					$window.location.assign('#Company');
				})
				.error(function(reponse){
					alert("Failed to add company.");
					$window.location.assign('#/');
				})
			};
		}
	}])

	
	app.controller('CustomerController', ['$http', '$scope', '$window', function($http, $scope, $window){

		$scope.adminView = '';
		
		$scope.changeCustomerView = function(view){
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
	}])

	app.controller('CompanyController', ['$http', '$scope', '$window', function($http, $scope, $window){

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

	}])

	app.controller('AdminController', ['$http', '$scope', '$window', function($http, $scope, $window){
		$scope.adminView = '';
		
		$scope.changeAdminView = function(view){
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
				.error(function(reponse){
					alert("Failed to retrieve customer.");
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
		
	}])
*/

