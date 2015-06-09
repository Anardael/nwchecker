<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<c:url var="news" value="/news.do" />
	<div>
		<div>
			<dl>
				<dt>
                    <spring:message code="news.contests" />
                </dt>
				<c:choose>
					<c:when test="${empty contest}">
						<dt>
                            <spring:message code="news.contest.noContest" />
                        </dt>
					</c:when>
					<c:otherwise>
						<dt>${contest.title}
                            <spring:message code="news.contests.date" />
                            ${contest.starts}
						</dt>
					</c:otherwise>
				</c:choose>
			</dl>
		</div>
		<br>
		<div>
			<dl>
				<dt>
                    <spring:message code="news.result" />
				<dt>${title}</dt>
				<dt>
                    <c:url var="dataUrl" value="/resultsList.do?id=${contestId}" />
					<table id="competitorsList" class="table" data-toggle="table"
						data-url="${dataUrl}" data-method="get" data-cache="false"
						data-search="true" data-clear-search="true" data-pagination="true"
						data-show-pagination-switch="true" data-sort-name="rank">
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
                </dt>
			</dl>
		</div>
	</div>
</body>
</html>