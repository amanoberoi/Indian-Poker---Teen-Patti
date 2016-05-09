<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teen Patti Game</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script>
var refresh = function(){
	setTimeout(function(){ window.location='Game';},10000);
	
}</script>
</head>
<body id="grad1" onload="refresh()">
<div align="center">
<img src="./cover.png" height="200" width="200"><br/><br/><br/><br/>
<s:form action = "Chaal" align="center">
<table align="center" class="textcolor">
<tr><td>Your cards are:
	<s:iterator value="displayCards" >
		<li><s:property></s:property></li>
	</s:iterator></td></tr>
	<tr><td>Minimum Bet is : <s:property value ="minBet"/> </td></tr>
	 <s:set var="current" value="currentTurn" />
	 <s:set var="your" value="yourTurn" />
	
	<tr><td><s:if test = "#current == #your">
	Its your turn
	</s:if></td></tr>
	<tr><td></td></tr>
<tr><td><s:textfield label ="Enter the betting value" name ="bet" /></td><td><s:submit value="chaal"/></td></tr>
<tr><td><s:submit action="Pack" value="pack"/></td></tr>
<tr><td>
	<s:submit action="Show" value="show" />
</td></tr>
</table>
</s:form>
</div>
</body>
</html>