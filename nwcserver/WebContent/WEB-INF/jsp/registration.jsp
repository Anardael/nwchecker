<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <jsp:include page="fragments/staticFiles.jsp" />
    <!-- include special css for registration:-->
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="registration"/>
            </jsp:include>
            <div class="container">
                <div class="row centered-form">
                    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><spring:message code="reg.header.caption" /></h3>
                            </div>
                            <div class="panel-body">
                                <form:form modelAttribute="userRegistrationForm" method="post"  role="form">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <div class="form-group">
                                                <input type="text" name="username" id="username" class="form-control input-sm" placeholder="<spring:message code="reg.username.caption" />">
                                                <form:errors path="username" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <div class="form-group">
                                                <input type="text" name="displayName" id="displayName" class="form-control input-sm" placeholder="<spring:message code="reg.nickname.caption" />">
                                                <form:errors path="displayName" cssClass="error"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="text" name="email" id="email" class="form-control input-sm" placeholder="<spring:message code="reg.email.caption" />">
                                        <form:errors path="email" cssClass="error"/>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" class="form-control input-sm" placeholder="<spring:message code="reg.password.caption" />">
                                                <form:errors path="password" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <div class="form-group">
                                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-sm" placeholder="<spring:message code="reg.cpassword.caption" />">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                    <input type="submit" value="<spring:message code="reg.button.caption" />" class="btn btn-info btn-block">
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>