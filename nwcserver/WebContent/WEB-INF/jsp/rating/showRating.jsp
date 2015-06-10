<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/" var="resources" />

<html>
    <div style="font-size: large; text-align: center">
        <c:choose>
            <c:when test="${dynamic}">
                <a href="<c:url value='/passContest.do?id=${contestId}'/>">
                        ${contestTitle}
                </a>
            </c:when>
            <c:otherwise>
                ${contestTitle}
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        <b><spring:message code="contest.results.started.caption" /></b>
        ${contestStart}<br/>
        <b><spring:message code="contest.results.continued.caption" /></b>
        ${contestDurationHours}
        <spring:message code="contest.results.hours.description" />
        ${contestDurationMinutes}
        <spring:message code="contest.results.minutes.description" />
    </div>
	<!-- Statistic table -->
    <c:url var="dataUrl" value="/resultsList.do?id=${contestId}" />
    <table id="competitorsList" class="table" data-toggle="table"
           data-url="${dataUrl}" data-method="get" data-cache="false"
           data-search="true" data-clear-search="true" data-pagination="true"
           data-show-pagination-switch="true" data-sort-name="rank">
        <thead>
        <tr>
            <th data-field="rank" data-align="center" data-sortable="true">
                <spring:message code="contest.results.tableHeader.place" /></th>
            <th data-field="displayName" data-align="center" data-formatter="displayNameFormatter">
                <spring:message code="contest.results.tableHeader.displayName" /></th>
            <th data-field="tasksPassedCount" data-align="center" data-formatter="tasksPassedCountFormatter">
                <spring:message code="contest.results.tableHeader.tasksPassedCount" /></th>
            <th data-field="timePenalty" data-align="center" data-formatter="timePenaltyFormatter">
                <spring:message code="contest.results.tableHeader.timePenalty" /></th>
        </tr>
        </thead>
    </table>
</html>
