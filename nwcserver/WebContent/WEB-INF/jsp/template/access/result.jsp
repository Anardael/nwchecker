<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<head>
</head>
<body>
	<!--including bodyHead -->
	<!-- send name of current page-->
	<section>
		<c:set var="result" value="${result}" />
		<c:if test="${not empty result}">
			<c:if test="${result=='access denied'}">
				<h4>
					<spring:message code="accessDenied" />
				</h4>
			</c:if>
		</c:if>
	</section>
</body>
</html>