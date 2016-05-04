<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teen Patti</title>
</head>
<body>
You have logged in!!
Welcome to the Teen Patti Game ! 

<form action="Game" method="post">
Click here to start a new game
<input type="submit" name="startgame" value="New Game"></input>
</form>
<br/>
<form action="Joingame" method="post">
Click here to join an existing game
<input type="submit" name="joingame" value="Join Existing Game"></input>
</form>
<br/>

<form action="Stats" method="post">
Click here to view your game statistics.
<input type="submit" name="stats" value="View Stats"></input>
</form>
<br/>

<form action="Logout" method="post">
Click here to logout
<input type="submit" name="logout" value="Logout"></input>
</form>
<br/>
</body>
</html>