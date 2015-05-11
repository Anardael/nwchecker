<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<!-- include special css for registration:-->
<style>
.centered {
	text-align: center;
}

.customButton {
	width: 150px;
}
</style>
<script>
	function goBack() {
		window.history.back()
	}
</script>
<body>
	<!--including bodyHead -->
	<!-- send name of current page-->
	<div class="form-group centered">
		<h1>
			<spring:message code="inDevelopment.page.caption" />
		</h1>
		<button class="btn btn-primary customButton centered"
			onclick="goBack()">
			<spring:message code="inDevelopment.buttonBack.caption" />
		</button>
	</div>
</body>
</html>