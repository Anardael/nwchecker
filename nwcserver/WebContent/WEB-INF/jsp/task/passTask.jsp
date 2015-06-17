<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	FILE_NOT_SELECTED_TITLE = '<spring:message code="contest.passing.fileNotSelectedDialog.title"/>';
	FILE_NOT_SELECTED_MESSAGE = '<spring:message code="contest.passing.fileNotSelectedDialog.message"/>';

	FILE_TOO_LARGE_TITLE = '<spring:message code="contest.passing.fileTooLargeDialog.title"/>';
	FILE_TOO_LARGE_MESSAGE = '<spring:message code="contest.passing.fileTooLargeDialog.message"/>';

	TASK_SUBMIT_ERROR_TITLE = '<spring:message code="contest.passing.taskSubmitErrorDialog.title"/>';
	TASK_SUBMIT_ERROR_MESSAGE = '<spring:message code="contest.passing.taskSubmitErrorDialog.message"/>';

	ACCESS_DENIED_TITLE = '<spring:message code="contest.passing.accessDeniedDialog.title"/>';
	ACCESS_DENIED_MESSAGE = '<spring:message code="contest.passing.accessDeniedDialog.message"/>';

	RESULT_SUCCESS_TITLE = '<spring:message code="contest.passing.resultSuccessDialog.title"/>';
	RESULT_SUCCESS_MESSAGE = '<spring:message code="contest.passing.resultSuccessDialog.message"/>';

	RESULT_FAIL_TITLE = '<spring:message code="contest.passing.resultFailDialog.title"/>';
	
	DYNAMIC = "${(not empty taskSuccessRate)&&contest.typeContest.dynamic||(contest.status == ARCHIVE)}";
	RESULT_SUCCESSFUL = '<spring:message code="contest.passing.resultDialog.successfulTests"/>'
	RESULT_TOTAL = '<spring:message code="contest.passing.resultDialog.totalTests"/>'
	DYNAMIC_MESSAGE = '<spring:message code="contest.passing.resultDialog.dynamicMessage"/>';
	RESULT_TIME = '<spring:message code="contest.passing.resultDialog.time"/>';
	RESULT_MEMORY = '<spring:message code="contest.passing.resultDialog.memory"/>';
	RESULT_ERROR_MESSAGE = '<spring:message code="contest.passing.resultDialog.message"/>';

	TIME_END_TITLE = '<spring:message code="contest.passing.timeIsOverDialog.title"/>';
	TIME_END_MESSAGE = '<spring:message code="contest.passing.timeIsOverDialog.message"/>';

	ALL_COMPLETE_TITLE = '<spring:message code="contest.passing.allCompleteDialog.title"/>';
	ALL_COMPLETE_MESSAGE = '<spring:message code="contest.passing.allCompleteDialog.message"/>';

	UPLOAD_FILE = '<spring:message code="contest.passing.uploadSourceFile.button"/>';
	TASK_ID = "${currentTask.id}";

	<c:if test="${not empty taskResults[currentTask.id]}">
	CURRENT_TASK_SUCCESS = ${taskResults[currentTask.id]};
	</c:if>
	<c:if test="${not empty contest}">	
	END_TIME_GTM_MILLISECONDS = ${contestEndTimeGTM};	
	var isArchive = ${isArchive};
	</c:if>
	$(document).ready(function() {
		<c:if test="${not empty contest}">
		if (allTasksComplete())
			endContest();
		disableDangerousOptions();
		if (!isArchive)
			startTimer();
		</c:if>
		$('#showModal').click(function(){
			tryToShowStatistic()
		})
	});
</script>
<!-- Current Task information -->
<c:set var="displayStatistic"
	value="${(not empty taskSuccessRate)&&contest.typeContest.dynamic||(contest.status == ARCHIVE)}" />
<c:if test="${displayStatistic}">
	<div style="float: right;">
		<button type="button" class="btn btn-default" data-style="zoom-out"
			onclick="tryToShowStatistic();">
			<spring:message code="task.statistic.modalTitle" />
		</button>
	</div>
</c:if>
<div class="page-header">
	<h2>
		${currentTask.title} <small> (<spring:message
				code="contest.passing.rate.caption" /> <b>${currentTask.rate}</b>)
			<c:if test="${displayStatistic}">
				<b id="showModal"><fmt:formatNumber value="${taskSuccessRate}"
						maxFractionDigits="2" minIntegerDigits="2" type="PERCENT" /></b>
			</c:if><label id="timer" class="pull-right"></label>
		</small>
	</h2>
</div>
<c:set var="currentTaskResult" value="${taskResults[currentTask.id]}"
	scope="page" />
<c:choose>
	<c:when test="${currentTaskResult == null}">
		<div class="descr-default">
			<p>${currentTask.description}</p>
		</div>
	</c:when>
	<c:when test="${currentTaskResult == true}">
		<div class="descr-success">
			<p>${currentTask.description}</p>
		</div>
	</c:when>
	<c:when test="${currentTaskResult == false}">
		<div class="descr-fail">
			<p>${currentTask.description}</p>
		</div>
	</c:when>
</c:choose>
<div class="row">
	<div class="col-sm-offset-1 col-sm-4" align="right">
		<h4>
			<b><spring:message code="contest.passing.timeLimit.caption" /></b>
			${currentTask.timeLimit}
		</h4>
		<h4>
			<b><spring:message code="contest.passing.memoryLimit.caption" /></b>
			${currentTask.memoryLimit}
		</h4>
	</div>
	<div class="col-sm-4" align="center">
		<h4>
			<b><spring:message code="contest.passing.inputFile.caption" /></b>
			${currentTask.inputFileName}
		</h4>
		<h4>
			<b><spring:message code="contest.passing.outputFile.caption" /></b>
			${currentTask.outputFileName}
		</h4>
	</div>
</div>
<br />
<br />
<security:authorize access="hasRole('ROLE_USER')">
	<!-- Send answer form -->
	<div class="row">
		<form:form id="task" class="form-horizontal"
			modelAttribute="currentTask" role="form">
			<form:hidden path="id" />
			<div class="form-group">
				<div class="col-sm-4">
					<h4 class="pull-right">
						<b><spring:message
								code="contest.passing.compilerSelect.caption" /></b>
					</h4>
				</div>
				<div class="col-sm-4">
					<select id="compilerId" name="compilerId" class="selectpicker">
						<c:forEach var="compiler" items="${compilers}">
							<option value="${compiler.id}">${compiler.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<h4 class="pull-right">
						<b><spring:message
								code="contest.passing.uploadSourceFile.caption" /></b>
					</h4>
				</div>
				<div class="col-sm-4">
					<span class="btn btn-block btn-file"> <span id="fileCaption">
							<spring:message code="contest.passing.uploadSourceFile.button" />
					</span> <input type="file" id="file" name="file"
						onchange="changeFileInputColor()">
					</span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-4">
					<button type="button" class="btn btn-info btn-block ladda-button"
						data-style="zoom-out" onclick="submitTask();">
						<spring:message code="contest.passing.submitButton.caption" />
					</button>
				</div>
			</div>
		</form:form>
	</div>
</security:authorize>

<!-- modal task statistic window -->
<c:if test="${displayStatistic}">
	<div id="taskStatistic" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 id="pageHeader" class="modal-title">
						<spring:message code="task.statistic.modalTitle" />
					</h4>
				</div>
				<div class="modal-body">
					<table id="statisticTable" class="table"
						style="table-layout: fixed; overflow-word: break-word;"
						data-toggle="table" data-striped="true"
						data-url="TaskStatisticTable.do?taskId=${currentTask.id}"
						data-side-pagination="server" data-pagination="true"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
						data-clear-search="true">
						<thead>
							<tr>
								<th data-field="user" data-align="center"
									data-formatter="usernameFormatter" data-sortable="true"
									class="col-md-1"><spring:message
										code="task.statistic.usernameCaption" /></th>
								<th data-field="compiler" data-align="center"
									data-formatter="compilerFormatter" data-sortable="true"
									class="col-md-1"><spring:message
										code="task.statistic.compilerCaption" /></th>
								<th data-field="passedTests" data-align="center"
									data-sortable="false" class="col-md-1"><spring:message
										code="task.statistic.testsPassedCaption" /></th>								
								<th data-field="passed" data-align="center"
									data-formatter="passedFormatter" data-sortable="true"
									class="col-md-1"><spring:message
										code="task.statistic.passedCaption" /></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</c:if>
</html>