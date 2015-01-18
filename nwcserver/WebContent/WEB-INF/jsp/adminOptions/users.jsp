<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--Including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />
    <head>
        <!-- Including users table formatters -->
        <script type="text/javascript" src="${resources}js/usersFormatters.js" ></script>
    <body>
        <div class="wrapper container">
            <!--Including bodyHead -->
            <!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="admin"/>
            </jsp:include>
            <div class="row" style="height: 47.3%;">
                <c:url var="dataUrl" value="/getUsers.do"/>
                <table id="usersData" data-toggle="table" data-striped="true"
                       data-url="${dataUrl}" data-method="get" data-cache="false"
                       data-sort-name="username" data-sort-order="asc"
                       data-pagination="true" data-search="true">
                    <thead>
                        <tr>
                            <th data-field="username" data-halign="center" data-formatter="usernameFormatter" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.username"/>
                            </th>
                            <th data-field="displayName" data-halign="center" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.displayName"/>
                            </th>
                            <th data-field="roles" data-halign="center" data-formatter="rolesFormatter" data-sortable="true">
                                <spring:message code="adminPanel.users.tableHeader.roles"/>
                            </th>
                            <th data-field="email" data-halign="center" data-sortable="true">
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