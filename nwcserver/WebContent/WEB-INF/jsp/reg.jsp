<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                                <form role="form">
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name="username" id="login" class="form-control input-sm" placeholder="<spring:message code="reg.username.caption" />">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name="username" id="username" class="form-control input-sm" placeholder="<spring:message code="reg.nickname.caption" />">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="email" name="email" id="email" class="form-control input-sm" placeholder="<spring:message code="reg.email.caption" />">
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" class="form-control input-sm" placeholder="<spring:message code="reg.password.caption" />">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="<spring:message code="reg.cpassword.caption" />">
                                            </div>
                                        </div>
                                    </div>
                                    <input type="submit" value="<spring:message code="reg.button.caption" />" class="btn btn-info btn-block">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>