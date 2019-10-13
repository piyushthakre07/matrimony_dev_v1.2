$(document).ready(function() {
	$("#userRegistration").validate({
		rules: {
			profileFor: {
				valueNotEquals: "0"
			},
			firstName: {
				required: true,
				minlength: 3,
				maxlength: 20
			},
			lastName: {
				required: true,
				minlength: 3,
				maxlength: 20
			},
			userName: {
				required: true,
				minlength: 4,
				maxlength: 15
			},
			password: {
				required: true,
				minlength: 4,
				maxlength: 15
			},
			confirmPasword: {
				required: true,
				equalTo: "#password"
			},
			emailId: {
				required: true,
				email: true
			}
			
		},
		messages: {
			profileFor: {
				valueNotEquals: "Please select profile for"
			},
			firstName: {
				required: "Please enter a first name ",
				minlength: "Your first name must consist of at least 3 characters",
			    maxlength:"Your first name must consist of maximum 20 characters"
			},
			lastName: {
				required: "Please enter a last name ",
				minlength: "Your last name must consist of at least 3 characters",
			    maxlength:"Your last name consist of maximum 20 characters"
			},
			
			userName: {
				required: "Please enter a user name",
				minlength: "Your user name must consist of at least 4 characters",
			    maxlength:"Your user name consist of maximum 15 characters"
			},
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				maxlength:"Your password consist of maximum 15 characters"
			},
			confirmPasword: {
				required: "Please provide a password",
				equalTo: "Password and Confirm Password  must be same"
			},
			emailId: "Please enter a valid email address"
			
		}
	});
	
	$("#userRegistration").on("submit", function(e) {
		var formData = $('#userRegistration').serializeArray();
		var s = '';
		var data = {};
		for (s in formData) {
			data[formData[s]['name']] = formData[s]['value']
		}
		$.ajax({
			type : 'POST',
			url : '/matrimonyapp/registration/tempsave',
			data : JSON.stringify(data),
			contentType : "application/json",
			beforeSend : function() {
				$("#wait").css("display", "block");
			},
			complete : function() {
				$("#wait").css("display", "none");
			},
			success : function(result) {
				$("#signupformId").hide();
				$("#otpVerificationDivId").show();
			}
		});
	});
	
	$("#otpVerificationFormId").on("submit", function(e) {
		e.preventDefault();
		var formData = $('#otpVerificationFormId').serializeArray();
		var s = '';
		var data = {};
		for (s in formData) {
			data[formData[s]['name']] = formData[s]['value']
		}
		$.ajax({
			type : 'POST',
			url : '/matrimonyapp/registration/submituser',
			data : JSON.stringify(data),
			contentType : "application/json",
			beforeSend : function() {
				$("#wait").css("display", "block");
			},
			complete : function() {
				$("#wait").css("display", "none");
			},
			success : function(result) {
				$("#signupformId").hide();
				$("#otpVerificationDivId").hide();
				$("#successDiv").show();
				$("#successDiv").append("<h3>Congrats.. You have been Successfully Registration..</h3>");
			}
		});
	});
});