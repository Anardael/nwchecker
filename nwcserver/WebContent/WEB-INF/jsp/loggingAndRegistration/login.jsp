<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<body>
	<form class="form-horizontal" action="<c:url value="/j_spring_security_check"/>" method="POST">
		<div class="form-group">
			<label class="col-sm-4 control-label">
                <spring:message code="login.username.caption" />:
            </label>
			<div class="col-sm-4">
				<input id="input-username" class="form-control" name="j_username">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">
                <spring:message code="login.password.caption" />:
            </label>
			<div class="col-sm-4">
				<input id="input-password" class="form-control" type="password" name="j_password">
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
				<input type="submit" value="<spring:message code="login.button.caption" />"
					class="btn btn-primary customButton">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-12 centered">
				<spring:message code="login.registration.label.caption" />
				<a href="registration.do"><spring:message code="login.reglink.caption" />.</a>
			</div>
		</div>
	</form>

    <div style="text-align: center">
        <p>or login as facebook user</p>
        <fb:login-button scope="public_profile,email" onlogin="login()" />
    </div>

    <div id="connectionErrorModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-header-danger">
                    <span class="h4">Facebook connection error!</span>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="mdl-body" class="modal-body" >
                    <div>Please, try login again.</div>
                </div>
                <div id="mdl-footer" class="modal-footer">
                    <button type="button" class="btn btn-sm btn-info" data-dismiss="modal" aria-label="Close">
                        OK
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>