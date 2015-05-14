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
		class="form-horizontal">
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
</html>