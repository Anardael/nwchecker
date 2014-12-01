<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<spring:message code="languageCode"/>" lang="<spring:message code="languageCode"/>">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="refresh" content="2;URL=login.jsp"/>
<link type="text/css" rel="stylesheet" href="css/NwcServer.css" />
<title>Logout successfully</title>
<style type="text/css">
	h2 {
		display: block;
		visibility: visible;
		font-size: 120%;
		top: 50%;
		position: relative;
		margin-top: -13px;
	}
</style>
</head>
	<body id = "login-body">
		<f:view>
		<div id  = "login-dialog">
			<h2>Logout was successful</h2>
		</div>
	</f:view>
	</body>
</html>
