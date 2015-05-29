<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/resources/" var="resources" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tiles:importAttribute name="scripts" />
<tiles:importAttribute name="stylesheets" />
<html>

<title><spring:message code="application.title" /></title>
<head>
<!-- Scripts -->
<c:forEach items="${scripts}" var="scriptPath">
	<script type="text/javascript" src="${resources}${scriptPath}"/></script>
</c:forEach>	
<!-- Stylesheets -->
<c:forEach items="${stylesheets}" var="cssPath">
	<link href="${resources}${cssPath}" rel="stylesheet"/>
</c:forEach>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="wrapper container">		 
		<div class="col-md-3">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="col-md-9">
			<tiles:insertAttribute name="body" />
		</div>		
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>