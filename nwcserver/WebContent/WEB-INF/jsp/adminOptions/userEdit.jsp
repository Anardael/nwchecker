<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--Including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />
    <head>
    <body>
        <div class="wrapper container">
            <!--Including bodyHead -->
            <!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="admin"/>
            </jsp:include>

            <form:form modelAttribute="userData" method="post" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.username.caption" />
                    </label>
                    <div class="col-sm-4">
                        <label class="control-label">${userData.username}</label> 
                        <form:hidden path="username" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.displayName.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="displayName" class="form-control" name="displayName" />
                        <form:errors path="displayName" Class="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.email.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="email" class="form-control" name="email" />
                        <form:errors path="email" Class="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.password.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="password" type="password" class="form-control" name="password" />
                        <form:errors path="password" Class="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.confirmPass.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="confirmPassword" type="password" class="form-control" name="confirmPassword" />
                        <form:errors path="confirmPassword" Class="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.info.caption" />
                    </label>
                    <div class="col-sm-4">
                        <c:forEach items="${userData.roles}" var="role">
                            <label class="control-label">${role.role}</label>
                            <br>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.department.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="department" class="form-control" name="department" />
                        <form:errors path="department" Class="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.info.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:textarea path="info" class="form-control" rows="4" name="info" style="resize:none"/>
                        <form:errors path="info" Class="error" />
                    </div>
                </div>		
                <div class="form-group">
                    <div class="col-sm-offset-4 ">
                        <div  class="col-sm-3">
                            <input type="submit" class="btn btn-info btn-block" formaction="changeUser.do"
                                   value=<spring:message code="adminPanel.userEdit.update.button" />>
                        </div>
                        <div class="col-sm-3">
                        	<security:authentication var="currentUser" property="principal.username" />
                        	<c:if test="${userData.username != currentUser}">
                            	<input type="submit" class="btn btn-warning btn-block" formaction="deleteUser.do"
                                	   value=<spring:message code="adminPanel.userEdit.delete.button" />>
                            </c:if>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
        <jsp:include page="../fragments/footer.jsp" />
    </body>
</html>