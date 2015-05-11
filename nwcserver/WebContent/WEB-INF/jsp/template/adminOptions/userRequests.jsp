<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<body>
<script type="text/javascript">
    ROLE_ADMIN = '<spring:message code="userRequests.users.table.role.admin" />';
    ROLE_TEACHER = '<spring:message code="userRequests.users.table.role.teacher" />';
    ROLE_USER = '<spring:message code="userRequests.users.table.role.user" />';
    UNDEFINED = '<spring:message code="userRequests.users.table.role.undefined" />';
</script>
    <!-- Users Requests table -->
    <div class="row">
        <c:url var="dataUrl" value="/getUsersWithRequests.do"/>
        <table id="usersData" data-toggle="table" data-striped="true"
               data-url="${dataUrl}" data-method="get" data-cache="false"
               data-sort-name="username" data-sort-order="asc"
               data-pagination="true" data-show-pagination-switch="true"
               data-search="true" data-clear-search="true"
               data-show-columns="true" data-minimum-count-columns="2"
               data-click-to-select="true">
            <thead>
            <tr>
                <th data-field="checked" data-checkbox="true"></th>
                <th data-field="username" data-align="center" data-sortable="true" data-switchable="false">
                    <spring:message code="userRequests.users.tableHeader.username"/>
                </th>
                <th data-field="displayName" data-align="center" data-sortable="true">
                    <spring:message code="userRequests.users.tableHeader.displayName"/>
                </th>
                <th data-field="roles" data-align="center" data-formatter="rolesFormatter" data-sortable="true"
                    data-sorter="usersSorter">
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
                <th data-field="requests" data-align="center" data-sortable="true" data-formatter="requestsFormatter"
                    data-switchable="false">
                    <spring:message code="userRequests.users.tableHeader.requests"/>
                </th>
            </tr>
            </thead>
        </table>
        <br>
        <div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-primary btn-block" onclick="acceptUserRequests()">
                    <spring:message code="userRequests.buttonAccept.caption"/>
                </button>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-warning btn-block" onclick="declineUserRequests()">
                    <spring:message code="userRequests.buttonDecline.caption"/>
                </button>
            </div>
        </div>
    </div>
</body>
</html>
