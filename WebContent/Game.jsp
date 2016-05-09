<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body id="grad1">
<div align="center">
<p id="main"> Welcome to the Teen Patti Game ! </p>
<img src="./cover.png" height="200" width="200"><br/><br/><br/><br/>
<form action="Game" method="post" id="formlabel">
Click here to start a new game: &nbsp;&nbsp;&nbsp;
<input type="submit" name="startgame" value="New Game"></input>
</form>
<br/>

<form action="Stats" method="post" id="formlabel">
Click here to view your game statistics : &nbsp;
<input type="submit" name="statsButton" value="View Stats"></input>
</form>
<br/>

<form action="Logout" method="post" id="formlabel">
Click here to logout : &nbsp;
<input type="submit" name="logout" value="Logout"></input>
</form>
<br/>
</div>
</body>
</html>