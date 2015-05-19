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
<body>
	<div class="col-md-12">
		<div class="page-header">
			<h2>
				<c:choose>
					<c:when test="${dynamic}">
						<a href="<c:url value='/passContest.do?id=${contestId}'/>"
							style="font-size: 25px; color: #101010"> ${contestTitle} </a>
					</c:when>
					<c:otherwise>
                            ${contestTitle}
                        </c:otherwise>
				</c:choose>
				<small class="pull-right"> <spring:message
						code="contest.results.started.caption" /> <b>${contestStart}</b> (<spring:message
						code="contest.results.continued.caption" /> <b>${contestDurationHours}
						<spring:message code="contest.results.hours.description" />
						${contestDurationMinutes} <spring:message
							code="contest.results.minutes.description" />
				</b>)
				</small>
			</h2>
		</div>
	</div>
	<!-- Statistic table -->
	<div class="col-md-12">
		<c:url var="dataUrl" value="/resultsList.do?id=${contestId}" />
		<table id="competitorsList" class="table" data-toggle="table"
			data-url="${dataUrl}" data-method="get" data-cache="false"
			data-search="true" data-clear-search="true" data-pagination="true"
			data-show-pagination-switch="true">
			<thead>
				<tr>
					<th data-field="rank" data-align="center"
						data-formatter="positionFormatter"><spring:message
							code="contest.results.tableHeader.place" /></th>
					<th data-field="displayName" data-align="center"
						data-formatter="displayNameFormatter"><spring:message
							code="contest.results.tableHeader.displayName" /></th>
					<th data-field="tasksPassedCount" data-align="center"
						data-formatter="tasksPassedCountFormatter"><spring:message
							code="contest.results.tableHeader.tasksPassedCount" /></th>
					<th data-field="timePenalty" data-align="center"
						data-formatter="timePenaltyFormatter"><spring:message
							code="contest.results.tableHeader.timePenalty" /></th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
