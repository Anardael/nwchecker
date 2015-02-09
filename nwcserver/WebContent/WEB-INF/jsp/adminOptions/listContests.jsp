<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
    <link href="${resources}js/bootstrapTables/bootstrap-table.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="${resources}js/bootstrapTables/locale/bootstrap-table-${pageContext.response.locale}.min.js"></script>
    <script type="text/javascript" src="${resources}js/adminOptions/listContestsFormatters.js"></script>
    <script type="text/javascript" src="${resources}js/adminOptions/listContests.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestUsers.js"></script>
</head>
<body>

<div class="wrapper container">
    <!--Including bodyHead -->
    <!-- Send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="listContests"/>
    </jsp:include>
    <input type="text" id="id" value="" hidden="true"/>
    <contest:usersList contestId=""/>
    <div class="row" style="height: 50%;">
        <c:url var="dataUrl" value="/getListOfContests.do"/>
        <table id="contestsData" data-toggle="table" data-striped="true"
               data-url="${dataUrl}" data-method="get" data-cache="false"
               data-sort-name="title" data-sort-order="asc"
               data-pagination="true" data-show-pagination-switch="true"
               data-search="true" data-clear-search="true"
               data-show-columns="true" data-minimum-count-columns="2">
            <thead>
            <tr>
                <th data-field="id" data-align="center" data-sortable="true" data-switchable="false">
                    <spring:message code="listContests.contests.tableHeader.id"/>
                </th>
                <th data-field="title" data-align="center" data-sortable="true">
                    <spring:message code="listContests.contests.tableHeader.title"/>
                </th>
                <th data-field="users" data-align="center" data-formatter="usersFormatter">
                    <spring:message code="listContests.contests.tableHeader.users"/>
                </th>
                <th data-field="starts" data-align="center" data-sortable="true" data-formatter="dateFormatter">
                    <spring:message code="listContests.contests.tableHeader.starts"/>
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
