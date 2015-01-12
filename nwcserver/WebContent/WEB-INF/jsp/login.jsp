<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<jsp:include page="fragments/staticFiles.jsp" />
<!-- include special css for registration:-->
<style>
.centered {
	text-align: center;
}

.customButton {
	width: 150px;
}

.error {
	color: #FF0000;
}
</style>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="login" />
		</jsp:include>

		<form class="form-horizontal" action="<c:url value="/j_spring_security_check"/>" method="POST">
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="login.username.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" name="j_username">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><spring:message code="login.password.caption" />:</label>
				<div class="col-sm-4">
					<input class="form-control" type="password" name="j_password">
				</div>
			</div>
			<c:if test="${not empty error}">
				<div class="form-group">
					<div class="col-sm-12 centered error">
						<spring:message code="login.error.caption" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<div class="form-actions centered">
					<input type="submit" value="<spring:message code="login.button.caption" />" class="btn btn-primary customButton">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 centered">
					<spring:message code="login.registration.label.caption" />
					<a href="registration.do"><spring:message code="login.reglink.caption" /></a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>