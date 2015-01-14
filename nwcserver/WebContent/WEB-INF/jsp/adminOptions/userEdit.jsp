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
            <!--Including admin optionsMenu -->
            <!-- Send name of selected option-->
            <jsp:include page="optionsMenu.jsp">
            	<jsp:param name="selectedOption" value="user edit"/>
            </jsp:include>
            <span class="col-md-9 header">
            	<spring:message code="adminPanel.userEdit.username" />
            	<span class="username">Test</span>
           	</span>
           	<div class="col-md-9 content">
            	<form:form modelAttribute="" action="" method="post" role="form"
					class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">
							<spring:message code="adminPanel.userEdit.password" />
						</label>
						<div class="col-sm-4">
							<input class="form-control" name="password" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">
							<spring:message code="adminPanel.userEdit.displayName" />
						</label>
						<div class="col-sm-4">
							<input class="form-control" name="displayName" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">
							<spring:message code="adminPanel.userEdit.email" />
						</label>
						<div class="col-sm-4">
							<input class="form-control" name="email" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">
							<spring:message code="adminPanel.userEdit.department" />
						</label>
						<div class="col-sm-4">
							<input class="form-control" name="department" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">
							<spring:message code="adminPanel.userEdit.info" />
						</label>
						<div class="col-sm-4">
							<textarea class="form-control" name="info" rows="4"></textarea>
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
    	</div>
	</body>
</html>