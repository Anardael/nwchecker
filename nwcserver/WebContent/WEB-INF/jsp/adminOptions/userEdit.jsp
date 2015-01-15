<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<!--Including head -->
    <jsp:include page="../fragments/staticFiles.jsp" />
    <link href="${resources}css/userEdit.css" rel="stylesheet"/>
	<body>
		<div class="wrapper container">
			<!--Including bodyHead -->
			<!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="home"/>
            </jsp:include>
            
            <!-- TODO -->
            <!-- Username and E-mail -->
            <span class="row header">
            	<spring:message code="adminPanel.userEdit.username" />
            	<span class="username">"${user.username}"	</span>
            	<spring:message code="adminPanel.userEdit.email" />
            	<span class="username">"${user.email}"</span>
           	</span>
           	
           	<!-- TODO -->
           	<form:form modelAttribute=""
           			   class="form-horizontal" role="form"
           			   action="" method="post">
           		<div class="form-group">
					<label class="col-sm-4 control-label">
						<spring:message code="adminPanel.userEdit.displayName" />
					</label>
					<div class="col-sm-4">
						<form:input path="displayName" class="form-control" name="displayName" />
						<form:errors path="displayName" Class="error"/>
					</div>
				</div>
				
   				<!-- TODO -->
   				
   				<div class="form-group">
					<label class="col-sm-4 control-label">
						<spring:message code="adminPanel.userEdit.department" />
					</label>
					<div class="col-sm-4">
						<form:input path="department" class="form-control" name="department" />
						<form:errors path="department" Class="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">
						<spring:message code="adminPanel.userEdit.info" />
					</label>
					<div class="col-sm-4">
						<form:textarea path="info" class="form-control" name="info" rows="4" />
						<form:errors path="info" Class="error"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4" align="right">
						<input type="submit" class="btn updateBtn"
							value=<spring:message code="adminPanel.userEdit.updateBtn" />>
						<input type="submit" class="btn deleteBtn"
							value=<spring:message code="adminPanel.userEdit.deleteBtn" />>
					</div>
				</div>
           	</form:form>
    	</div>
    	<jsp:include page="../fragments/footer.jsp" />
	</body>
</html>