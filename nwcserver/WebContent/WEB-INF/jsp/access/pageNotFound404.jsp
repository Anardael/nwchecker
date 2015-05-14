<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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
	<div class="form-group centered" style="margin-top: 10%">
		<h1>
			<spring:message code="pageNotFound.page.caption" />
		</h1>
		<button class="btn btn-primary customButton centered"
			onclick="goBack()">
			<spring:message code="pageNotFound.backButton.caption" />
		</button>
	</div>
</body>
</html>