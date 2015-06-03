<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<c:url var="dataURL" value="/archiveTable.do" />
	<table id="archiveTable" class="table" data-toggle="table"
		data-striped="true" data-url="${dataURL}"
		data-side-pagination="server" data-pagination="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
		data-clear-search="true" data-sort-name="id"
		data-sort-order="asc">
		<thead>
			<tr>
				<th data-field="id" data-align="center" data-sortable="true" class="col-md-1"></th>
				<th data-field="title" data-align="center" data-sortable="true"><spring:message code="archive.taskTitle" /></th>
				<th data-field="complexity" data-align="center" data-sortable="true" class="col-md-2"><spring:message code="archive.taskComplexity" /></th>
				<th data-field="rate" data-align="center" data-sortable="true"class="col-md-2">
					<spring:message code="archive.taskRate" /></th>
				<th data-field="timeLimit" data-align="center" data-sortable="true"class="col-md-2"><spring:message code="archive.timeLimit" /></th>
			</tr>
		</thead>
	</table>
</body>
</html>
