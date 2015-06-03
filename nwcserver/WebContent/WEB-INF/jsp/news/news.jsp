<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<c:url var="news" value="/news.do" />
	<div class="main-block">
		<div>
			<ul>
				<li
					class="list-group-item list-group-item-heading list-group-item-info"
					style="text-align: left; font-size: large"><spring:message
						code="news.contests" /></li>
				<c:choose>
					<c:when test="${empty contest}">
						<li class="list-group-item"><spring:message
								code="news.contest.noContest" /></li>
					</c:when>
					<c:otherwise>
						<li class="list-group-item">${contest.title}<br> <spring:message
								code="news.contests.date" /> ${contest.starts}
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<br>
		<div>
			<ul>
				<li
					class="list-group-item list-group-item-heading list-group-item-info"
					style="text-align: left; font-size: large"><spring:message
						code="news.result" />
				<li class="list-group-item ">${title}</li>
				<li class="list-group-item "
					style="text-align: left; font-size: large"><c:url
						var="dataUrl" value="/resultsList.do?id=${contestId}" />
					<table id="competitorsList" class="table" data-toggle="table"
						data-url="${dataUrl}" data-method="get" data-cache="false"
						data-search="true" data-clear-search="true" data-pagination="true"
						data-show-pagination-switch="true" data-sort-name="rank">
						<thead>
							<tr>
								<th data-field="rank" data-align="center"
									data-formatter="positionFormatter" data-sortable="true"><spring:message
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
					</table></li>
			</ul>
		</div>
	</div>
</body>
</html>