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
                <jsp:param name="pageName" value="login"/>
            </jsp:include>
            <div class="container">
                <div class="row centered-form">
                    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><spring:message code="login.header.caption" /></h3>
                            </div>
                            <div class="panel-body">
                                <form role="form">
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name="username" id="username" class="form-control input-sm" placeholder="<spring:message code="login.username.caption" />">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" class="form-control input-sm" placeholder="<spring:message code="login.password.caption" />">
                                            </div>
                                        </div>
                                    </div>
                                    <input type="submit" value="<spring:message code="login.button.caption" />" class="btn btn-info btn-block">
                                </form>
                                <div class="col-xs-12 col-sm-12 col-md-12" align="center">
                                    <spring:message code="login.registration.label.caption" /> <a href="registration.do" ><spring:message code="login.reglink.caption" /></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>