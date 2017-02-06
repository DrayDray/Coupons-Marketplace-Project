(function () {

	"use strict";
	
	//creating our Login controller
	var LoginController = function ($http, $scope, $window) {

		this.login = function () {
			var user = this.user;
			$http.post('rest/userservices/login', user)
				.success(function () {
					if (user.clientType === "customer") {
						$window.location.assign('#Customer');
					}
					else if (user.clientType === "company") {
						$window.location.assign('#Company');
					}
					else {
						$window.location.assign('#Admin');
					}
				})
				.error(function (reponse) {
					window.alert("Login Failed");
					user.userId = "";
					user.clientType = "";
					user.password = ""
				});
		};

		this.forwardToRegistration = function () {
			$window.location.assign('#Register');
		};

		this.register = function () {
			var newUser = this.newUser;
			if (newUser.clientType === "customer"){
				$http.post('rest/customers', newUser)
				.success(function () {
					window.alert("You have successfully registered. Welcome!");
					$window.location.assign('#Customer');
				})
				.error(function (reponse) {
					window.alert("Failed to add customer.");
					$window.location.assign('#Register');
				});
			}
			else if (newUser.clientType === "company"){
				$http.post('rest/companies', newUser)
				.success(function() {
					alert("You have successfully registered. Welcome!");
					$window.location.assign('#Company');
				})
				.error(function(reponse) {
					window.alert("Failed to add company.");
					$window.location.assign('#Register');
				});
			}
		};
	};
	
	//actually registering the Login Controller to the Controllers Module
	//in the other three controllers I created & registered the Controller in one step (as an anonymous function)
	angular.module('CtrlsModule').controller("LoginController", ['$http', '$scope', '$window', LoginController]);

}());