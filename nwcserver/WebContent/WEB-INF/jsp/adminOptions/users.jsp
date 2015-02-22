<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--Including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />
        <link href="${resources}js/bootstrapTables/bootstrap-table.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
        <script type="text/javascript" src="${resources}js/bootstrapTables/locale/bootstrap-table-${pageContext.response.locale}.min.js"></script>
    	<script type="text/javascript" src="${resources}js/adminOptions/usersFormatters.js" ></script>
    </head>
    <body>
    	<script type="text/javascript">
    		ROLE_ADMIN   = '<spring:message code="adminPanel.users.table.role.admin" />';
			ROLE_TEACHER = '<spring:message code="adminPanel.users.table.role.teacher" />';
			ROLE_USER    = '<spring:message code="adminPanel.users.table.role.user" />';
			UNDEFINED    = '<spring:message code="adminPanel.users.table.role.undefined" />';
    	</script>
    	<!-- BODY -->
        <div class="wrapper container">
            <!--Including bodyHead -->
            <!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="admin"/>
            </jsp:include>
            <div class="row" style="height: 60%;">
            	<!-- TABLE -->
                <c:url var="dataUrl" value="/getUsers.do"/>
                <table id="usersData" data-toggle="table" data-striped="true"
                       data-url="${dataUrl}" data-method="get" data-cache="false"
                       data-sort-name="username" data-sort-order="asc"
                       data-pagination="true" data-show-pagination-switch="true"
                       data-search="true" data-clear-search="true"
                       data-show-columns="true" data-minimum-count-columns="2">
                    <thead>
                        <tr>
                            <th data-field="username" data-align="center" data-formatter="usernameFormatter" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.username"/>
                            </th>
                            <th data-field="displayName" data-align="center" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.displayName"/>
                            </th>
                            <th data-field="roles" data-align="center" data-formatter="rolesFormatter" data-sortable="true"
                                data-sorter="usersSorter">
                                <spring:message code="adminPanel.users.tableHeader.roles"/>
                            </th>
                            <th data-field="email" data-align="center" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.email"/>
                            </th>
                            <th data-field="department" data-halign="center" data-formatter="infoFormatter" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.department"/>
                            </th>
                            <th data-field="info" data-halign="center" data-formatter="infoFormatter" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.info"/>
                            </th>
                        </tr>
                    </thead>
                </table>
            </div>
		</div>
        <jsp:include page="../fragments/footer.jsp" />
    </body>
</html>