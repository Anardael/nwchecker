<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
	<!--including head -->
    <jsp:include page="../fragments/staticFiles.jsp" />
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
            	<jsp:param name="selectedOption" value="users"/>
            </jsp:include>
            <div class="col-xs-9" style="height: 70%;">
            	<c:url var="dataUrl" value="/admin/getUsers.do"/>
            	<table id="usersData" data-toggle="table" data-url="${dataUrl}" data-method="get" data-cache="false" data-search="true">
            		<thead>
            			<tr>
            				<th data-field="username" data-halign="center" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.username"/>
            				</th>
        					<th data-field="accessLevel" data-halign="center" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.accessLevel"/>
            				</th>
            				<th data-field="displayName" data-halign="center" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.displayName"/>
            				</th>
            				<th data-field="email" data-halign="center" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.email"/>
            				</th>
            				<th data-field="department" data-halign="left" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.department"/>
            				</th>
            				<th data-field="info" data-halign="left" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.info"/>
            				</th>
            				<th data-field="banTime" data-halign="center" data-formatter="banTimeFormatter" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.isBanned"/>
            				</th>
            				<th data-field="enabled" data-halign="center" data-formatter="enabledFormatter" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.confirmed"/>
            				</th>
            			</tr>
            		</thead>
            	</table>
            	<!-- Including page JavaScript -->
            	<script type="text/javascript">
            		function banTimeFormatter(value) {
            			if (value > 0) {
            	    		return '<h3><label class="label label-danger"><spring:message code="adminPanel.users.tableHeader.isBanned.yes"/></label></h3>';
            	    	}
            	    	return '<h3><label class="label label-success"><spring:message code="adminPanel.users.tableHeader.isBanned.no"/></label></h3>';
            		}
            		function enabledFormatter(value) {
            			if (value) {
            		    	return '<h3><label class="label label-success"><spring:message code="adminPanel.users.tableHeader.confirmed.yes"/></label></h3>';
            		    }
            		    return '<h3><label class="label label-danger"><spring:message code="adminPanel.users.tableHeader.confirmed.no"/></label></h3>';
            		}
            	</script>
            </div>
    	</div>
	</body>
</html>