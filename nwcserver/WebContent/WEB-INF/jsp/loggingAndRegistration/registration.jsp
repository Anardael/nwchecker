<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<link href="${resources}css/loggingAndRegistration/registration.css"
	rel="stylesheet" />
<html>
<body>
	<form:form modelAttribute="userRegistrationForm"
		action="registration.do" method="post" role="form"
		class="form-horizontal" id="registrationForm">
		<div class="form-group">
			<label class="col-sm-4 control-label"><spring:message
					code="reg.username.caption" />:</label>
			<div class="col-sm-4">
				<form:input path="username" class="form-control" name="username" />
				<form:errors path="username" Class="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"><spring:message
					code="reg.nickname.caption" />:</label>
			<div class="col-sm-4">
				<form:input path="displayName" class="form-control"
					name="displayName" />
				<form:errors path="displayName" Class="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"><spring:message
					code="reg.email.caption" />:</label>
			<div class="col-sm-4">
				<form:input path="email" class="form-control" name="email" />
				<form:errors path="email" Class="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"><spring:message
					code="reg.password.caption" />:</label>
			<div class="col-sm-4">
				<form:input path="password" class="form-control" type="password"
					name="password" />
				<form:errors path="password" Class="error" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"><spring:message
					code="reg.cpassword.caption" />:</label>
			<div class="col-sm-4">
				<form:input path="confirmPassword" class="form-control"
					type="password" name="confirmPassword" />
				<form:errors path="confirmPassword" Class="error" />
			</div>
		</div>
		<div class="form-group">
			<div class="form-actions centered">
				<input type="submit"
					value="<spring:message code="reg.button.caption" />"
					class="btn btn-primary customButton">
			</div>
		</div>
	</form:form>
</body>
<script>
	USERNAME_REQUIRED = "<spring:message code='reg.empty.username.caption' />";
	USERNAME_SIZE = "<spring:message code='reg.badUsername.caption' />";
	DISPLAYNAME_REQUIRED = "<spring:message code='reg.empty.displayName.caption' />";
	DISPLAYNAME_SIZE = "<spring:message code='reg.badDisplayName.caption' />";
	EMAIL_REQUIRED = "<spring:message code='reg.empty.email.caption' />";
	EMAIL_BAD = "<spring:message code='reg.badEmail.caption' />";
	PASSWORD_REQUIRED = "<spring:message code='reg.empty.password.caption' />";
	PASSWORD_SIZE = "<spring:message code='reg.badPassword.caption' />";
	CONFIRM_PASSWORD_REQUIRED = "<spring:message code='reg.empty.confirmPassword.caption' />";
	PASSWORD_EQUALS = "<spring:message code='reg.badConfirmPassword.caption' />";
</script>
</html>