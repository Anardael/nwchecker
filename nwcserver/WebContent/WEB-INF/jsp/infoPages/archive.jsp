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
	<div id="taskDetailsModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 id="pageHeader" class="modal-title"></h4>
				</div>
				<div id="taskBody" class="modal-body"></div>
				<div class="row">
					<div class="col-sm-offset-4 col-sm-4 modal-body" align="center">
						<h4>
							<spring:message code="contest.passing.timeLimit.caption" />
							<br /> <b id="timeLimit"></b>
						</h4>
						<h4>
							<spring:message code="contest.passing.memoryLimit.caption" />
							<br /> <b id="memoryLimit"></b>
						</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
