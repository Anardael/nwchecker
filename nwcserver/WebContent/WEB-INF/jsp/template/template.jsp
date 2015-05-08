<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:url value="/resources/" var="resources"/>
	<jsp:include page="../fragments/staticFiles.jsp" />
</head>
<body>
	<div class="wrapper container">
		<tiles:insertAttribute name="header" />
		<div id="tasks" class="col-md-3">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="col-md-9"> 
			<tiles:insertAttribute name="body"/>
		</div>		
	</div>
</body>
</html>