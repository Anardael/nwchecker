<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<security:authentication var="currentUser" property="principal.username" />
<html>
    <!--Including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />
        
        <link href="${resources}css/bootstrap-dialog.css" rel="stylesheet"/>
        
        <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
        <script type="text/javascript" src="${resources}js/adminOptions/userEdit.js"></script>
    </head>
    <body>
    	<c:if test="${roles == null}">
    		<c:set var="roles" value="${userData.roles}"/>
    	</c:if>
    	<script type="text/javascript">
    		DELETE_DIALOG_TITLE   = '<spring:message code="adminPanel.userEdit.deleteDialog.title"/>';
			DELETE_DIALOG_MESSAGE = '<spring:message code="adminPanel.userEdit.deleteDialog.message"/>';
			CANCEL_BUTTON         = '<spring:message code="adminPanel.userEdit.deleteDialog.cancelButton"/>';
			CONFIRM_BUTTON        = '<spring:message code="adminPanel.userEdit.deleteDialog.confirmButton"/>';
    	
    		$('body').ready(function() {	
    			showUserRoles([
    				<c:forEach var="role" items="${roles}">
    			    	"${role.role}",
    			    </c:forEach>
    			]);
    			if ('${currentUser}' == '${userData.username}') {
    				disableDangerousOptions();
    			}
    			resetRolesDesc();
    		});
    		$('body').on('click', '#updateBtn', function() {
    			$('#formUdpateBtn').click();
    		});
    		$('body').on('click', '#deleteBtn', function() {
    			showDeleteDialog();
    		});
    	</script>
    
        <div class="wrapper container">
            <!--Including bodyHead -->
            <!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="admin"/>
            </jsp:include>

            <form:form id="userEditForm" accept-charset="UTF-8"
            	 modelAttribute="userData" method="post"
            	 class="form-horizontal">
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
                        <form:errors path="displayName" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.email.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="email" class="form-control" name="email" />
                        <form:errors path="email" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.password.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="password" type="password" class="form-control" name="password" />
                        <form:errors path="password" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.confirmPass.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="confirmPassword" type="password" class="form-control" name="confirmPassword" />
                        <form:errors path="confirmPassword" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.roles.caption" />
                    </label>
                    <input type="text" id="rolesDesc" name="rolesDesc" hidden="true" />
                    <div class="col-sm-4">
                    	<input type="checkbox" id="admin" onclick="resetRolesDesc()" />
                    	<label for="admin">
                    		<spring:message code="adminPanel.userEdit.role.admin.caption" />
                    	</label>
                    	<br/>
                    	<input type="checkbox" id="teacher" onclick="resetRolesDesc()" />
                    	<label for="teacher">
                    		<spring:message code="adminPanel.userEdit.role.teacher.caption" />
                    	</label>
                    	<br/>
                    	<input type="checkbox" id="user" onclick="resetRolesDesc()" />
                    	<label for="user">
                    		<spring:message code="adminPanel.userEdit.role.user.caption" />
                    	</label>
                    	<br/>
                    	<form:errors path="roles" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.department.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:input path="department" class="form-control" name="department" />
                        <form:errors path="department" class="text-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <spring:message code="adminPanel.userEdit.info.caption" />
                    </label>
                    <div class="col-sm-4">
                        <form:textarea path="info" class="form-control" rows="4" name="info" style="resize:none"/>
                        <form:errors path="info" class="text-danger" />
                    </div>
                </div>		
                <input type="submit" id="formUdpateBtn" formaction="changeUser.do" hidden="true"/>
                <input type="submit" id="formDeleteBtn" formaction="deleteUser.do" hidden="true"/>
            </form:form>
            <div class="col-sm-offset-4 ">
            	<div  class="col-sm-3">
                	<button class="btn btn-info btn-block" id="updateBtn">
                    	<spring:message code="adminPanel.userEdit.update.button" />
                    </button>
                </div>
                <div class="col-sm-3">
                  	<button class="btn btn-warning btn-block" id="deleteBtn">
                      	<spring:message code="adminPanel.userEdit.delete.button" />
                    </button>
                </div>
            </div>
        </div>
        <jsp:include page="../fragments/footer.jsp" />
    </body>
</html>