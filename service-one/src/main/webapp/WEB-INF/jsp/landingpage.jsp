<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<script type="text/javascript">
	$(document).ready(function() {
		console.log("document ready!")
		var userName = "${userName}";
		console.log("Current logged in user" + userName);
	});
</script>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div>
				<h1>We are now at service-one!</h1>
				<br>
			</div>
			<div class = "logindetails">
				<h2>Current Logged in user details :</h2>
				<br>
				<ul>
					<li>Username : ${userName}</li>
					<li>Authorities : ${authorities}</li>
					<li>Is this account expired? : ${!isNonExpired}</li>
					<li>Is this account locked? : ${!isNonLocked}</li>
					<li>Has this user's password expired? : ${!hasPasswordExpired}</li>
				</ul>

			</div>
			<div>
				<button type="button" class="btn btn-primary">
					<a href="/logout">Logout</a>
				</button>
			</div>

		</div>

	</div>

</body>
</html>

