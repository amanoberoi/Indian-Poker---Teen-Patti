<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wait</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script>
var refresh = function(){
	setTimeout(function(){ window.location='Game';},5000);
	
}</script>
</head>
<body id="grad1" onload="refresh()" >
<div align="center">
<img src="./wait.gif" height="200" width="200"><br/><br/><br/>
<p id="interim"> Please wait for other players to join the game. </p>
</div>
</body>
</html>