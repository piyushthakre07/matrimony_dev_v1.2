<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>New Registration-Registration FREE</title>
	<style>
	.error {
		color: red;
	}
	</style>
<!-- Font Icon -->
<link rel="stylesheet"
	href="../resources/registration/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="../resources/registration/css/style.css">
</head>
<body>
	<div class="main">
		<div class="container">
			<div class="signup-content">
				<div class="signup-img">
					<img src="../resources/registration/images/signup-img.jpg" alt="">
				</div>
				<div class="signup-form" id="signupformId">
					<form class="register-form" id="userRegistration">
						<h2>Register FREE</h2>
						<div class="form-group">
							<label for="course">Profile For :</label>
							<div class="form-select">
								<select name="profileFor" id="profileFor">
									<option value="-1">Select</option>
									<option value="1">Myself</option>
									<option value="2">Son</option>
									<option value="3">Daughter</option>
									<option value="4">Brother</option>
									<option value="5">Sister</option>
									<option value="6">Relative</option>
									<option value="7">Friend</option>
									<option value="0">Other</option>
								</select> 
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="firstName">First Name :</label> <input type="text"
									name="firstName" id="firstName" required />
							</div>
							<div class="form-group">
								<label for="lastName">Last Name :</label> <input type="text"
									name="lastName" id="lastName" required />
							</div>
						</div>
						<div class="form-radio">
							<label for="gender" class="radio-label">Gender :</label>
							<div class="form-radio-item">
								<input type="radio" name="gender" id="male"> <label
									for="male">Male</label> <span class="check"></span>
							</div>
							<div class="form-radio-item">
								<input type="radio" name="gender" id="female"> <label
									for="female">Female</label> <span class="check"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="dateOfBirth">Date of birth :</label> <input
								type="date" class="form-control has-feedback-left"
								name="dateOfBirth" id="dateOfBirth">
						</div>
						<div class="form-group">
							<label for="contactNumber">Mobile Number :</label> <input
								type="text" name="contactNumber" id="contactNumber">
						</div>
						<div class="form-group">
							<label for="emailId">Email ID :</label> <input type="email"
								name="emailId" id="emailId" />
						</div>
						<div class="form-group">
							<label for="userName">User Name :</label> <input type="text"
								name="userName" id="userName">
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="password">Password :</label> <input type="password"
									name="password" id="password">
							</div>
							<div class="form-group">
								<label for="confirmPassword">Confirm Password :</label> <input
									type="password" name="confirmPasword" id="confirmPasword">
							</div>
						</div>
						<div class="form-submit">
							<input type="reset" value="Reset All" class="submit" name="reset"
								id="reset" /> <input type="submit" value="Submit"
								class="submit" name="submit" id="submit" />
						</div>
					</form>
				</div>
				<div class="signup-form" style="display: none;"
					id="otpVerificationDivId">
					<form class="register-form" id="otpVerificationFormId">
						<h3>OTP Send to you mobile number.please enter OTP</h3>
						<div class="form-group">
							<label for="otp">Enter Otp :</label> <input type="text"
								name="otp" id="otp">
						</div>
						<input type="submit" value="Submit Form" class="submit"
							name="submit" id="submit" />
					</form>
				</div>
				<div class="signup-form" style="display: none;" id="successDiv">
					<h3></h3>
				</div>
				<div class="signup-form" style="display: none;" id="failureDiv">
					<h3></h3>
				</div>
			</div>
		</div>

	</div>
	<div id="wait"
		style="display: none; width: 100%; height: 100%; border: 1px solid black; position: absolute; top: 1%; left: 1%;">
		<img src='../resources/registration/images/loadingImage.gif'
			width="100%" height="100%" /><br>Loading..
	</div>
	<!-- JS -->
	<script src="../resources/common/js/jquery.min.js"></script>
	<script src="../resources/registration/js/registration.js"></script>
	<script src="../resources/validation/js/jquery.validate.js"></script>
	<script src="../resources/validation/js/jquery.form.js"></script>
</body>
</html>