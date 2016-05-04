<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<div align="center"><p>PA Games Inc.</p></div><br/>
<div align="center">
<form name="login" action="Login" method="post">
<label>Email:</label><input type="text" name="emailid"/><br/>
<label>Password:</label><input type="password" name="pwd"/><br/>
<input type="submit" value="Login"/><br/>
<input type="reset" value="Clear"/><br/>
<a href="Register.jsp">Click here to register!</a><br/>
</form>
</div>
</body>
</html>