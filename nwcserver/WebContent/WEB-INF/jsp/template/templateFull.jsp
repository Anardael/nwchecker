<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tiles:importAttribute name="scripts" />
<tiles:importAttribute name="stylesheets" />
<html>
<title><spring:message code="application.title" /></title>
<head>
<c:url value="/resources/" var="resources" />

<!-- Stylesheets -->
<c:forEach items="${stylesheets}" var="cssPath">
	<link href="${resources}${cssPath}" rel="stylesheet" />
</c:forEach>
<!-- Scripts -->
<c:forEach items="${scripts}" var="scriptPath">
	<script type="text/javascript" src="${resources}${scriptPath}" /></script>
</c:forEach>
</head>
<body>
	<div class="wrapper container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
