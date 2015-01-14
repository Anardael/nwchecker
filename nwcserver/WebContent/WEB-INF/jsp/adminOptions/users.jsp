<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
	<!--Including head -->
    <jsp:include page="../fragments/staticFiles.jsp" />
    <!-- Including users table formatters -->
	<jsp:include page="usersFormatters.jsp" />
	<script type="text/javascript" src="${resources}js/usersEvents.js" ></script>
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
            	<table id="usersData" data-toggle="table" data-url="${dataUrl}" data-method="get" 
            			data-cache="false" data-search="true">
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
            				<th data-field="department" data-halign="center" data-sortable="true">
            					<spring:message code="adminPanel.users.tableHeader.department"/>
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
            </div>
    	</div>
	</body>
</html>