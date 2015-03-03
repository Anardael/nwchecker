<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>

    <link rel="stylesheet" href="${resources}css/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${resources}css/bootstrap-dialog.css"/>
    <link rel="stylesheet" href="${resources}css/contests/contestPass.css"/>
    <link rel="stylesheet" href="${resources}js/laddaBtnLoad/ladda-themeless.min.css"/>

    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-select.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestPass.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestPassTimer.js"></script>
    <script type="text/javascript" src="${resources}js/laddaBtnLoad/spin.min.js"></script>
    <script type="text/javascript" src="${resources}js/laddaBtnLoad/ladda.min.js"></script>
    <script type="text/javascript" src="${resources}js/contests/tasks/taskSubmit.js"></script>
</head>
<body>
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
            CURRENT_TASK_SUCCESS = ${taskResults[currentTask.id]};
        </c:if>
        END_TIME_GTM_MILLISECONDS = ${contestEndTimeGTM};
        var isArchive = ${isArchive};
        $(document).ready(function() {
            if (allTasksComplete())
                endContest();
            disableDangerousOptions();
            if (!isArchive)
                startTimer();
        });
	</script>

	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="contest" />
		</jsp:include>
		<!-- Shows tasks navigation list and marks every task status -->
		<div id="tasks" class="col-md-3">
			<ul class="nav nav-pills nav-stacked">
				<c:url var="taskURL" value="/passTask.do?id=" scope="page" />
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="taskInfo" items="${taskTitles}">
					<c:set var="count" value="${count + 1}" scope="page"/>
                    <c:set var="taskId" value="${taskInfo.key}"/>
                    <c:set var="taskTitle" value="${taskInfo.value}"
                           scope="page" />
					<c:set var="taskResult" value="${taskResults[taskId]}"
						scope="page" />
					<c:choose>
						<c:when test="${taskId eq currentTask.id}">
							<li class="active">
								<a href="${taskURL}${taskId}">
                                    <b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:when>
						<c:when test="${taskResult == null}">
							<li class="default">
								<a href="${taskURL}${taskId}">
									<b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:when>
						<c:when test="${taskResult == true}">
							<li class="success">
								<a href="${taskURL}${taskId}">
                                    <b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:when>
						<c:when test="${taskResult == false}">
							<li class="fail">
								<a href="${taskURL}${taskId}">
                                    <b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<!-- Current Task information -->
		<div class="col-md-9">
			<div class="page-header">
				<h2>
					${currentTask.title} 
					<small> 
						(<spring:message code="contest.passing.rate.caption"/>
						<b>${currentTask.rate}</b>)
                        <label id="timer" class="pull-right"></label>
					</small>
				</h2>
			</div>
			<c:set var="currentTaskResult"
				value="${taskResults[currentTask.id]}" scope="page" />
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
			<br/><br/>
			<!-- Send answer form -->
			<div class="row">
				<form:form id="task" class="form-horizontal"
					modelAttribute="currentTask" role="form">
					<form:hidden path="id" />
					<div class="form-group">
						<div class="col-sm-4">
                            <h4 class="pull-right">
                                <b><spring:message code="contest.passing.compilerSelect.caption" /></b>
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
                                <b><spring:message code="contest.passing.uploadSourceFile.caption" /></b>
                            </h4>
                        </div>
                        <div class="col-sm-4">
							<span class="btn btn-block btn-file">
                                <span id="fileCaption">
                                        <spring:message code="contest.passing.uploadSourceFile.button" />
                                </span>
                                <input type="file" id="file" name="file" onchange="changeFileInputColor()">
							</span>
						</div>
					</div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-4">
                            <button type="button" class="btn btn-info btn-block ladda-button" data-style="zoom-out"
                                    onclick="submitTask();">
                                <spring:message code="contest.passing.submitButton.caption" />
                            </button>
                        </div>
                    </div>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>