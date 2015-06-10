<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<form:form modelAttribute="userRegistrationForm" action="login.do"
		method="get" role="form" class="form-horizontal">
        <h2>
            <spring:message code="userCreated.username.caption" />
            <b>${userRegistrationForm.username}</b>
            <spring:message code="userCreated.created.caption" />
        </h2>
        <input type="submit" value="<spring:message code="userCreated.button.caption" />"
               class="btn btn-primary">
	</form:form>
</body>
</html>