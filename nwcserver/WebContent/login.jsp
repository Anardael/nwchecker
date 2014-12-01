<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<spring:message code="languageCode"/>" lang="<spring:message code="languageCode"/>">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Login Page</title>
<link type="text/css" rel="stylesheet" href="css/NwcServer.css" />
<style type="text/css">
	*+html #login-dialog {
		height: 117px;
	}
	
	#mobile-login {
		height: 100%;
	}
	
	#mobile-login input[type=text], #mobile-login input[type=password] {
		width: 90%;
		height: 66px;
		font-size: 2.4em;
		margin: 0px;
	}
	
	.header {
		width: 100%;
		color: white;
		text-align: center;
		vertical-align: middle;
		line-height: 100px;
	}
	
	.header-text {
		min-height: 1.1em;
		text-align: center;
		font-size: 2.1em;
		display: block;
		padding: 0;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
		outline: 0 !important;
		color: #fff /*{a-bar-color}; */;
		font-weight: bold;
	}
	
	.error-text-wrap {
		position: relative;
		top: 12%;
	}
	
	.error-text {
		min-height: 1.1em;
		text-align: center;
		font-size: 2.1em;
		display: block;
		padding: 0;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
		outline: 0 !important;
		color: red;
		font-weight: bold;
	}
	
	.ui_bar {
		border: 1px solid #333 /*{a-bar-border}*/;
		background: #111 /*{a-bar-background-color}*/;
		color: #fff /*{a-bar-color}*/;
		font-weight: bold;
		text-shadow: 0 /*{a-bar-shadow-x}*/ -1px /*{a-bar-shadow-y}*/ 1px /*{a-bar-shadow-radius}*/ #000 /*{a-bar-shadow-color}*/;
		background-image: -webkit-gradient(linear, left top, left bottom, from( #3c3c3c /*{a-bar-background-start}*/), to( #111 /*{a-bar-background-end}*/));
		background-image: -webkit-linear-gradient( #3c3c3c /*{a-bar-background-start}*/, #111 /*{a-bar-background-end}*/);
		background-image: -moz-linear-gradient( #3c3c3c /*{a-bar-background-start}*/, #111 /*{a-bar-background-end}*/);
		background-image: -ms-linear-gradient( #3c3c3c /*{a-bar-background-start}*/, #111 /*{a-bar-background-end}*/);
		background-image: -o-linear-gradient( #3c3c3c /*{a-bar-background-start}*/, #111 /*{a-bar-background-end}*/);
		background-image: linear-gradient( #3c3c3c /*{a-bar-background-start}*/, #111 /*{a-bar-background-end}*/);
	}
	
	.submit-button-mobile {
		top: 20%;
		position: relative;
		border-color: rgba(0,0,0,.3);
		width: 90%;
		height: 66px;
		border-radius: 13px;
		font-size: 2.4em;
		color: #222;
		background-color: #f0f0f0;
	}
	
	.submit-button-mobile-wrap {
		position: absolute; 
		top:150%;
		width: 100%;
	}
	
	.mobile-login-form {
		top: 15%;
		position: relative;
	}
	
	.lock-img {
		top: 10%;
		position: relative;
	}
</style>
</head>
<body id = "login-body">
	<div id="login-dialog"
		style="<% if (request.getParameter("authfailed")!=null || (Exception)request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")!=null || request.getParameter("sessionInvalid")!=null || request.getParameter("sessionExpired")!=null) out.write("height: 146px;");%>"
	>
		<form action="./j_spring_security_check" method="post">
			<span style="color: red"> <%
          Exception error = (Exception)request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			if (error!=null &&error.getMessage()!=null){

          	out.write(error.getMessage());
			}
         %> 
			</span>
			<table class="mars">
				<thead>
						<th colspan="2">
						Please Login		
</th>
				</thead>
				<tbody>
					<tr>
						<th><label for="j_username">Account</label>:&nbsp;</th>
						<td class="left"><input id="j_username" name="j_username"
							size="20" maxlength="50" type="text" autocomplete="on"/></td>
					</tr>
					<tr>
						<th><label for="j_password">Password</label>:&nbsp;</th>
						<td class="left"><input id="j_password" name="j_password"
							size="20" maxlength="50" type="password"  autocomplete="on"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" align="center"><input style="width: 100%;" class="login-submit" type="submit" value="Sign In"></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>
