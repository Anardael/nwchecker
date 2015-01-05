<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<jsp:include page="fragments/staticFiles.jsp" />
<!-- include special css for registration:-->
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="registration" />
		</jsp:include>
		<form:form modelAttribute="userRegistrationForm" action="registration.do" method="post" role="form"
			class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="reg.username.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" name="username" value="${fn:escapeXml(param.username)}">
					<c:if test="${usernameEmpty eq true}">
						<span class="text-warning"><spring:message code="reg.empty.username.caption" /></span>
					</c:if>
					<c:if test="${badUsername eq true}">
						<span class="text-warning"><spring:message code="reg.badUsername.caption" /></span>
					</c:if>
					<c:if test="${usernameNotUnique eq true}">
						<span class="text-warning"><spring:message code="reg.usernameNotUnique.caption" /></span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="reg.nickname.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" name="displayName" value="${fn:escapeXml(param.displayName)}">
					<c:if test="${displayNameEmpty eq true}">
						<span class="text-warning"><spring:message code="reg.empty.displayName.caption" /></span>
					</c:if>
					<c:if test="${badDisplayName eq true}">
						<span class="text-warning"><spring:message code="reg.badDisplayName.caption" /></span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="reg.email.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" name="email" value="${fn:escapeXml(param.email)}">
					<c:if test="${emailEmpty eq true}">
						<span class="text-warning"><spring:message code="reg.empty.email.caption" /></span>
					</c:if>
					<c:if test="${badEmail eq true}">
						<span class="text-warning"><spring:message code="reg.badEmail.caption" /></span>
					</c:if>
					<c:if test="${emailNotUnique eq true}">
						<span class="text-warning"><spring:message code="reg.emailNotUnique.caption" /></span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="reg.password.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" type="password" name="password">
					<c:if test="${passwordEmpty eq true}">
						<span class="text-warning"><spring:message code="reg.empty.password.caption" /></span>
					</c:if>
					<c:if test="${badPassword eq true}">
						<span class="text-warning"><spring:message code="reg.badPassword.caption" /></span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="reg.cpassword.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" type="password" name="confirmPassword">
					<c:if test="${confirmPasswordEmpty eq true}">
						<span class="text-warning"><spring:message code="reg.empty.confirmPassword.caption" /></span>
					</c:if>
					<c:if test="${badConfirmPassword eq true}">
						<span class="text-warning"><spring:message code="reg.badConfirmPassword.caption" /></span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<c:if test="${success eq true}">
					<div class="allert allert-success centered">
						<label class="control-label text-succes"><strong><spring:message code="reg.success.caption" /></strong></label>
					</div>
				</c:if>
			</div>
			<div class="form-group">
				<div class="form-actions centered">
					<input type="submit" value="<spring:message code="reg.button.caption" />" class="btn btn-primary customButton">
				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>