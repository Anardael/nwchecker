<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="../fragments/staticFiles.jsp" />
	</head>
	<body>
	<c:forEach items="${taskPassList}" var="taskPass">
		<c:out value="${taskPass.executionTime}"/>
		<c:out value="${taskPass.user.displayName}"/>
	</c:forEach>
	
	</body>
</html>