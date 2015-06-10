<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<c:url var="news" value="/news.do" />

    <p><spring:message code="news.contests" /></p>
    <div class="block-center">
        <c:choose>
            <c:when test="${empty contest}">
                <spring:message code="news.contest.noContest" />
            </c:when>
            <c:otherwise>
                ${contest.title}<br/>
                        <span style="font-size: 15px; font-weight: normal">
                            <c:set var="date" value="${contest.starts}"/>
                            <spring:message code="news.contests.date" /> ${fn:substring(date, 0, 16)}
                        </span>
            </c:otherwise>
        </c:choose>
    </div>

    <br>

    <p><spring:message code="news.result" /></p>
    <div id="title">${title}</div>
    <c:url var="dataUrl" value="/resultsList.do?id=${contestId}" />

    <table id="competitorsList" class="table" data-classes="table" data-toggle="table"
           data-url="${dataUrl}" data-method="get" data-cache="false"
           data-search="true" data-clear-search="true" data-pagination="true" data-search-align="left"
           data-show-pagination-switch="true" data-sort-name="rank" >
        <thead>
        <tr>
            <th data-field="rank" data-align="center" data-sortable="true">
                <spring:message code="contest.results.tableHeader.place" />
            </th>
            <th data-field="displayName" data-align="center" >
                <spring:message code="contest.results.tableHeader.displayName" />
            </th>
            <th data-field="tasksPassedCount" data-align="center">
                <spring:message code="contest.results.tableHeader.tasksPassedCount" />
            </th>
            <th data-field="timePenalty" data-align="center" >
                <spring:message code="contest.results.tableHeader.timePenalty" />
            </th>
        </tr>
        </thead>
    </table>
</body>
</html>