<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	FILE_NOT_SELECTED_TITLE = '<spring:message code="contest.passing.fileNotSelectedDialog.title"/>';
	FILE_NOT_SELECTED_MESSAGE = '<spring:message code="contest.passing.fileNotSelectedDialog.message"/>';

	TASK_SUBMIT_ERROR_TITLE = '<spring:message code="contest.passing.taskSubmitErrorDialog.title"/>';
	TASK_SUBMIT_ERROR_MESSAGE = '<spring:message code="contest.passing.taskSubmitErrorDialog.message"/>';

	ACCESS_DENIED_TITLE = '<spring:message code="contest.passing.accessDeniedDialog.title"/>';
	ACCESS_DENIED_MESSAGE = '<spring:message code="contest.passing.accessDeniedDialog.message"/>';

	RESULT_SUCCESS_TITLE = '<spring:message code="contest.passing.resultSuccessDialog.title"/>';
	RESULT_SUCCESS_MESSAGE = '<spring:message code="contest.passing.resultSuccessDialog.message"/>';

	RESULT_FAIL_TITLE = '<spring:message code="contest.passing.resultFailDialog.title"/>';

	RESULT_TIME = '<spring:message code="contest.passing.resultDialog.time"/>';
	RESULT_MEMORY = '<spring:message code="contest.passing.resultDialog.memory"/>';
	RESULT_ERROR_MESSAGE = '<spring:message code="contest.passing.resultDialog.message"/>';

	TIME_END_TITLE = '<spring:message code="contest.passing.timeIsOverDialog.title"/>';
	TIME_END_MESSAGE = '<spring:message code="contest.passing.timeIsOverDialog.message"/>';

	ALL_COMPLETE_TITLE = '<spring:message code="contest.passing.allCompleteDialog.title"/>';
	ALL_COMPLETE_MESSAGE = '<spring:message code="contest.passing.allCompleteDialog.message"/>';

	UPLOAD_FILE = '<spring:message code="contest.passing.uploadSourceFile.button"/>';

	<c:if test="${not empty taskResults[currentTask.id]}">
	CURRENT_TASK_SUCCESS = $
	{
		taskResults[currentTask.id]
	};
	</c:if>
	END_TIME_GTM_MILLISECONDS = $
	{
		contestEndTimeGTM
	};
	var isArchive = $
	{
		isArchive
	};
	$(document).ready(function() {
		if (allTasksComplete())
			endContest();
		disableDangerousOptions();
		if (!isArchive)
			startTimer();
	});
</script>

<!-- Current Task information -->
<div class="page-header">
	<h2>
		<a href="TaskStatistic.do?id=${currentTask.id}">${currentTask.title}</a>
		<small> (<spring:message code="contest.passing.rate.caption" />
			<b>${currentTask.rate}</b>) <c:if test="${not empty taskSuccessRate}"><b><fmt:formatNumber
					value="${taskSuccessRate}" maxFractionDigits="2"
					minIntegerDigits="2" type="PERCENT" /></b> </c:if><label id="timer"
			class="pull-right"></label>
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
			<spring:message code="contest.passing.timeLimit.caption" />
			<b>${currentTask.timeLimit}</b>
		</h4>
		<h4>
			<spring:message code="contest.passing.memoryLimit.caption" />
			<b>${currentTask.memoryLimit}</b>
		</h4>
	</div>
	<div class="col-sm-4" align="right">
		<h4>
			<spring:message code="contest.passing.inputFile.caption" />
			<b>${currentTask.inputFileName}</b>
		</h4>
		<h4>
			<spring:message code="contest.passing.outputFile.caption" />
			<b>${currentTask.outputFileName}</b>
		</h4>
	</div>
</div>
<br />
<br />
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
</html>