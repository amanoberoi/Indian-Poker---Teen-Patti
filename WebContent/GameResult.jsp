<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank you for playing</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body id="grad1">
<div class="textcolor" align="center">
<p> Thank you for playing Teen Patti on PA Games.</p>
Player 1 cards are :
<s:iterator value="player1Cards" >
		<li><s:property></s:property></li>
	</s:iterator>
Player 2 cards are :
<s:iterator value="player2Cards" >
		<li><s:property></s:property></li>
	</s:iterator><br/><br/>
The Winner is :
<s:property value="winner"></s:property><br/><br/>
<a href="Game.jsp" id="formlabel">Click here to return to home!</a>
</div>
</body>
</html>