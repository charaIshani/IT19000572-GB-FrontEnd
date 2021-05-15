 <%@page import = "com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>

<head>
	
	<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/users.js"></script>
	<meta charset="ISO-8859-1">
</head>

<body>
		<div class="container"><div class="row"><div class="col-6">
		<h1>User Management</h1>
		<form id="formItem" name="formItem">
			userName:
			<input id="UserName" name="UserName" type="text"class="form-control form-control-sm">
			<br> 
			UserMail:
			<input id="UserMail" name="UserMail" type="text"class="form-control form-control-sm">
			<br> 
			UserType:
			<input id="UserType" name="UserType" type="text"class="form-control form-control-sm">
			<br> 
			
			<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
			<input type="hidden" id="hidItemIDSave"name="hidItemIDSave" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
		<%
			User userObj = new User();
			out.print(userObj.readUsers());
		%>
		</div>
		</div> </div> </div>
</body>
</html>