<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="form-group">
				<div class="form-actions centered">
					<input type="submit" value="<spring:message code="login.button.caption" />" class="btn btn-primary customButton">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 centered">
					<spring:message code="login.registration.label.caption" /> <a href="registration.do"><spring:message code="login.reglink.caption" /></a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>