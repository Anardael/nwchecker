<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <script>
        var isFirstLogin = '${isFirstLogin}';
        var nickname = '${nickname}';
        var email = '${email}';
    </script>
    <div class="jumbotron">
        <div class="container">
            <p>"<spring:message code="index.quote" />"</p>
            <spring:message code="index.quoteAuthor" />
        </div>
    </div>
    <div id="info">
        <b>NWCServer</b>
        <p>
            <spring:message code="index.content.header" />
        </p>
        <b><spring:message code="index.content.goal" /></b>
        <p>
            <spring:message code="index.content.goalBody" />
        </p>
    </div>

    <div id="facebookModal" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-header-info">
                    <span id="title-text" class="h4">You logged like </span>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="mdl-body" class="modal-body" >
                    <div id="alert">, we send your login and password on </div>
                    <div>You can login with your account or through Facebook.</div>
                </div>
                <div id="mdl-footer" class="modal-footer">
                    <button type="button" class="btn btn-sm btn-info" data-dismiss="modal" aria-label="Close">
                        OK
                    </button>
                </div>
            </div>
        </div>
    </div>
</html>