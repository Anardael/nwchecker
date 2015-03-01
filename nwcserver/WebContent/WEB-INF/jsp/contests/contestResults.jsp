<%--
  Created by IntelliJ IDEA.
  User: Станіслав
  Date: 01.03.2015
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<head>
<jsp:include page="../fragments/staticFiles.jsp" />

<link href="${resources}js/bootstrapTables/bootstrap-table.min.css" rel="stylesheet"/>

<script type="text/javascript" src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
<script type="text/javascript"
        src="${resources}js/bootstrapTables/locale/bootstrap-table-${pageContext.response.locale}.min.js"></script>
</head>
<body>
    <div class="wrapper container">
        <!--including bodyHead -->
        <!-- send name of current page-->
        <jsp:include page="../fragments/bodyHeader.jsp">
            <jsp:param name="pageName" value="contest"/>
        </jsp:include>
        <!-- Statistic table -->
        <div class="row" style="height:70%">
            <c:url var="dataUrl" value="/resultsList.do?id=${contestId}"/>
            <table id="competitorsList" data-toggle="table" data-striped="true"
                   data-url="${dataUrl}" data-method="get" data-cache="false">
                <thead>
                    <tr>
                        <!-- TODO -->
                        <th data-field="username" data-align="center">
                            <spring:message code="contest.results.tableHeader.username"/>
                        </th>
                        <th data-field="displayName" data-align="center">
                            <spring:message code="contest.results.tableHeader.displayName"/>
                        </th>
                        <th data-field="tasksCount" data-align="center">
                            <spring:message code="contest.results.tableHeader.tasksCount"/>
                        </th>
                        <th data-field="passedCount" data-align="center">
                            <spring:message code="contest.results.tableHeader.passedCount"/>
                        </th>
                        <th data-field="timePenalty" data-align="center">
                            <spring:message code="contest.results.tableHeader.timePenalty"/>
                        </th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp" />
</body>
</html>
