<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources"/>
<html>
<!--including head -->
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
</head>
<script type="text/javascript" src="${resources}js/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="${resources}js/validators/userProfileValidator_${pageContext.response.locale}.js"></script>
<script type="text/javascript" src="${resources}js/profileOptions/profile.js"></script>
<!-- include special css for profile:-->
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

    #changePassword label.error {
        color: red;
    }

    #changePassword input.error {
        border: 1px solid red;
    }

</style>
<body>
<script type="text/javascript">
    $('body').ready(function () {
        showUserRoles([
            <c:forEach var="role" items="${userProfile.roles}">
            "${role.role}",
            </c:forEach>
        ]);
    });
</script>
<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="profile"/>
    </jsp:include>
    <form:form modelAttribute="userProfile" action="profile.do" method="post" role="form" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-4 control-label"><spring:message code="profile.username.caption"/>:</label>

            <div class="col-sm-4">
                <label class="control-label">${userProfile.username}</label> <input type="hidden" name="username"
                                                                                    value="${userProfile.username}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"><spring:message code="profile.nickname.caption"/>:</label>

            <div class="col-sm-4">
                <form:input path="displayName" class="form-control" name="displayName"/>
                <form:errors path="displayName" Class="error"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"><spring:message code="profile.email.caption"/>:</label>

            <div class="col-sm-4">
                <label class="control-label">${userProfile.email}</label> <input type="hidden" name="email"
                                                                                 value="${userProfile.email}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"><spring:message code="profile.department.caption"/>:</label>

            <div class="col-sm-4">
                <form:input path="department" class="form-control" name="department"/>
                <form:errors path="department" Class="error"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"><spring:message code="profile.info.caption"/>:</label>

            <div class="col-sm-4">
                <form:textarea path="info" class="form-control" name="info" style="resize:none"/>
                <form:errors path="info" Class="error"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">
                <spring:message code="profile.roles.caption"/>
            </label>
            <input type="text" id="rolesDesc" name="rolesDesc" hidden="true"/>

            <div class="col-sm-4">
                <input type="checkbox" id="admin" onclick="return false"/>
                <label for="admin">
                    <spring:message code="profile.roles.role.admin.caption"/>
                </label>
                <br/>
                <input type="checkbox" id="teacher" onclick="return false"/>
                <label for="teacher">
                    <spring:message code="profile.roles.role.teacher.caption"/>
                </label>
                <br/>
                <input type="checkbox" id="user" onclick="return false"/>
                <label for="user">
                    <spring:message code="profile.roles.role.user.caption"/>
                </label>
                <c:set var="contains" value="false"/>
                <c:forEach var="item" items="${userProfile.roles}">
                    <c:if test="${item.role eq 'ROLE_TEACHER'}">
                        <c:set var="contains" value="true"/>
                    </c:if>
                </c:forEach>
                <c:if test="${fn:length(userProfile.requests) == 0 && contains eq 'false'}">
                    <div class="col-sm-12" id="userRequestTeacher">
                        <hr>
                        <button type="button" class="btn btn-primary btn-block" onclick="setRequestsWantRoleTeacher()">
                            <spring:message code="profile.roles.role.request.caption"/>
                        </button>
                        <hr>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${userUpdated == 'true'}">
            <div class="form-group">
                <label class="col-sm-4 control-label"></label>

                <div class="col-sm-4 centered">
                    <label class="control-label centered"><spring:message
                            code="profile.userUpdatedSuccess.caption"/></label>
                </div>
            </div>
        </c:if>
        <c:if test="${passwordChanged == 'true'}">
            <div class="form-group">
                <label class="col-sm-4 control-label"></label>

                <div class="col-sm-4 centered">
                    <label class="control-label centered"><spring:message
                            code="profile.changePasswordSuccess.caption"/></label>
                </div>
            </div>
        </c:if>
        <c:if test="${passwordChanged == 'false'}">
            <div class="form-group">
                <label class="col-sm-4 control-label"></label>

                <div class="col-sm-4 centered">
                    <label class="control-label error centered"><spring:message
                            code="profile.changePasswordError.caption"/></label>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <div class="form-actions centered">
                <input type="submit" value="<spring:message code="profile.applyButton.caption" />"
                       class="btn btn-primary customButton"/>
                <input type="button" data-toggle="modal" data-target="#myModal"
                       value="<spring:message code="profile.changePasswordButton.caption" />"
                       class="btn btn-primary customButton"/>
            </div>
        </div>
    </form:form>

    <!-- Modal -->
    <form action="changePassword.do" method="post" role="form" class="form-horizontal" id="changePassword">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h2 class="modal-title" id="myModalLabel">
                            <spring:message code="profile.changePassword.caption"/>
                        </h2>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><spring:message
                                    code="profile.oldPassword.caption"/>:</label>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><spring:message
                                    code="profile.newPassword.caption"/>:</label>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="newPassword" name="newPassword"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><spring:message
                                    code="profile.confirmPassword.caption"/>:</label>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="confirmPassword"
                                       name="confirmPassword"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" value="<spring:message code="profile.changePasswordButton.caption" />"
                                   class="btn btn-primary customButton"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                <spring:message code="profile.cancelButton.caption"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>