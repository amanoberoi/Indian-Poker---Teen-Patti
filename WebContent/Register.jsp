<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<style>
BODY {
background: linear-gradient(lightgray, white, lightgray);
}
</style>
<script>

	function validateForm() {
		var result = null;
		var emailid = document.forms["register"]["emailid"].value;
		var pnumber = document.forms["register"]["phone"].value;
		var name = document.forms["register"]["name"].value;
		var pwd = document.forms["register"]["pwd"].value;
		var cpwd = document.forms["register"]["cpwd"].value;
		var phonepattern = /^\([2-9][0-9]{2}\) [1-9][0-9]{2}-[0-9]{4}$/;
		var namepattern = /^[A-Z][A-Z,a-z]+ [A-Z,a-z ]+$/;
		var emailpattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
		var passwordpattern = /^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).+$/;

		if (name == null || name == "") {
			alert("Name is mandatory!!");
			return false;
		} else if (name.match(namepattern) == null
				|| name.match(namepattern) == "") {
			alert("Name can only have characters and full name is required(First name followed by the Last name)");
			return false;
		} else if (emailid == null || emailid == "") {
			alert("Email ID is mandatory");
			return false;
		} else if (emailid.match(emailpattern) == null
				|| emailid.match(emailpattern) == "") {
			alert("Email should be of format abc@xyz.com");
			return false;
		} else if (pnumber.match(phonepattern) == null
				|| pnumber.match(phonepattern) == "") {
			alert("Contact number should contain numbers in standard format (123) 123-1234");
			return false;
		}
		else if (pnumber == null || pnumber == "") {
			alert("Contact number is mandatory");
			return false;
		}
		else if (pwd.match(passwordpattern) == null || pwd.match(passwordpattern) == "") {
			alert("Password must follow given rules.");
			return false;
		} else if (cpwd == null || cpwd == "") {
			alert("Confirm password field is mandatory and should be the same as password.");
			return false;

		} else if (pwd == null || pwd == "") {
			alert("Password is mandatory.");
			return false;
		} else if (cpwd != pwd) {
			alert("Password and Confirm password must match.");
			return false;
		}

		return true;
	}
</script>

</head>
<body>
<div align="center">

 <form name="register" action="Registration"  onsubmit="return validateForm()" method="post" >
 <br></br>
 <table>
<tr><td><h1 style="font-size: 150%;" align="center">Registration Form</h1><br/></td></tr>
<tr><td><label>*Full Name</label></td>
	<td><input name="name" type="text" maxlength="20" id="name"/></td></tr>
<tr><td><label>*Email Id:</label></td>
	<td><input name="emailid" type="text" maxlength="25" id="emailid"/></td></tr>
<tr><td><label>*Contact Number:</label></td>
	<td><input name="phone" type="text" maxlength="17" id="phone" /></td></tr>
<tr><td><label>*Password:</label></td>
	<td><input name="pwd" type="password" maxlength="15" id="pwd"/></td></tr>
<tr><td><label>*Confirm Password :</label></td>
<td><input name="cpwd" type="password" maxlength="15" id="cpwd" /></td></tr>
<tr><td align="center"><br/><input name="" type="submit" value="Register" />
	<td align="center"><br/><input name="" type="reset" value="Reset" /></td></tr>
	
	<tr><td><p>Already registered?</p><a href="Login.jsp">Click here to Login.</a></td></tr>
         
         <tr><td colspan="2" ><br/><br/><label> Fields indicated by asterisk(*) are mandatory</label><br/><br/></td></tr>
         
         <tr> <td colspan="2" >Password Rules:<br/>
                 a) Should have at least two upper case letters
             <br></br>
                 b) Should have at least one special character(!%$) and have two digits
             <br></br>
                  c) Should have at least three lower case letters
             <br></br>
                  d) Length should be greater than or equal to 8 
             </td></tr>
 </table>
 </form>
</div>
</body>
</html>