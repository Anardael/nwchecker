<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:url value="/resources/" var="resources" />

<html>
<script type="text/javascript">
    var statusCaptionArchive = "<spring:message code="listContests.status.archive" />";
    var statusCaptionGoing = "<spring:message code="listContests.status.going" />";
    var statusCaptionPreparing = "<spring:message code="listContests.status.preparing" />";
    var statusCaptionRelease = "<spring:message code="listContests.status.release" />";
	$(document).ready(
			function() {
				$('#ratingContest').on('click-row.bs.table',
						function(e, row, $element) {
							location.href = 'results.do?id=' + row['id']
						});
			});
</script>
		<c:url var="dataURL" value="/ratingContest.do" />
		<table id="ratingContest" class="table" data-toggle="table"
			data-url="${dataURL}" data-method="get" data-cache="false"
			data-search="true" data-clear-search="true" data-pagination="true"
			data-show-pagination-switch="true" data-search-align="left">
			<thead>
				<tr>
                    <th data-field="starts" data-align="center" data-sortable="true" data-formatter="dateTimeFormatter">
                        <spring:message code="contest.results.started.caption" /></th>
					<th data-field="title" data-align="center" data-sortable="true">
                        <spring:message code="contest.table.title" /></th>
					<th data-field="description" data-align="center" data-sortable="true">
                        <spring:message code="contest.table.description" /></th>
					<th data-field="status" data-align="center" data-sortable="true" data-formatter="statusFormatter">
                        <spring:message code="listContests.contests.tableHeader.status" /></th>
				</tr>
			</thead>
		</table>
</html>