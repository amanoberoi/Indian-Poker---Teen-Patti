<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body id="grad1">
<div align="center"><p id="main">PA Games Inc.</p></div><br/>
<div align="center">
<img src="./cover.png" height="200" width="200"><br/><br/><br/>
<form name="login" action="Login" method="post">
<label id="interim">Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="emailid"/><br/>
<label id="interim">Password: </label><input type="password" name="pwd"/><br/><br/><br/>
<input type="submit" value="Login"/>
<input type="reset" value="Clear"/><br/><br/>
<a href="Register.jsp" id="formlabel">Click here to register!</a><br/>
</form>
</div>
</body>
</html>