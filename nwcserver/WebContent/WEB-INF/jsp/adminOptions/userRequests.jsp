<%--
  Created by IntelliJ IDEA.
  User: ReaktorDTR
  Date: 25.01.2015
  Time: 15:07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<head>
    <jsp:include page="../fragments/staticFiles.jsp" />
    <link href="${resources}css/bootstrap-table.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrapTables/locale/bootstrap-table-${pageContext.response.locale}.min.js"></script>
    <script type="text/javascript" src="${resources}js/usersFormatters.js" ></script>
    <script type="text/javascript" src="${resources}js/userRequests.js" ></script>
    <style>
        .customButton {
            width: 150px;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    ROLE_ADMIN   = '<spring:message code="userRequests.users.table.role.admin" />';
    ROLE_TEACHER = '<spring:message code="userRequests.users.table.role.teacher" />';
    ROLE_USER    = '<spring:message code="userRequests.users.table.role.user" />';
    UNDEFINED    = '<spring:message code="userRequests.users.table.role.undefined" />';
</script>
<div class="wrapper container">
    <!--Including bodyHead -->
    <!-- Send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="userRequests"/>
    </jsp:include>

    <div class="row" style="height: 50%;">
        <c:url var="dataUrl" value="/getUsersWithRequests.do"/>
        <table id="usersData" data-toggle="table" data-striped="true"
               data-url="${dataUrl}" data-method="get" data-cache="false"
               data-sort-name="username" data-sort-order="asc"
               data-show-columns="true"
               data-pagination="true" data-search="true" data-click-to-select="true">
            <thead>
            <tr>
                <th data-field="action" data-checkbox="true"></th>
                <th data-field="userId" data-align="center" data-sortable="true" data-switchable="false">
                    <spring:message code="userRequests.users.tableHeader.username"/>
                </th>
                <th data-field="username" data-align="center" data-sortable="true" data-switchable="false">
                    <spring:message code="userRequests.users.tableHeader.username"/>
                </th>
                <th data-field="displayName" data-align="center" data-sortable="true">
                    <spring:message code="userRequests.users.tableHeader.displayName"/>
                </th>
                <th data-field="roles" data-align="center" data-formatter="rolesFormatter">
                    <spring:message code="userRequests.users.tableHeader.roles"/>
                </th>
                <th data-field="email" data-align="center" data-sortable="true">
                    <spring:message code="userRequests.users.tableHeader.email"/>
                </th>
                <th data-field="department" data-visible="false" data-align="center" data-sortable="true">
                    <spring:message code="userRequests.users.tableHeader.department"/>
                </th>
                <th data-field="info" data-visible="false" data-align="center" data-sortable="true">
                    <spring:message code="userRequests.users.tableHeader.info"/>
                </th>
                <th data-field="requests" data-align="center" data-sortable="true" data-formatter="requestsFormatter" data-switchable="false">
                <spring:message code="userRequests.users.tableHeader.requests"/>
                </th>
            </tr>
            </thead>
        </table>
        <div class="form-group ">
            <div class="form-actions pull-centered">
                <a class="btn btn-primary customButton" type="button" href="acceptUserRequests.do"><spring:message code="userRequests.buttonAccept.caption"/></a>
                <input type="button" value="<spring:message code="profile.changePasswordButton.caption" />"
                       class="btn btn-primary customButton" onclick="acceptedUserRequests()"/>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
