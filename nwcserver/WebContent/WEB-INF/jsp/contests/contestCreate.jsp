<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<style>
    label.btn-primary {
        width: 120px;
    }
</style>
<!--including head -->
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
    <%-- ladda button
    <link href="resources/js/laddaBtnLoad/ladda-themeless.min.css" rel="stylesheet"/>
    --%>
    <link rel="stylesheet" href="resources/js/laddaBtnLoad/ladda-themeless.min.css"/>

    <script type="text/javascript" src="resources/js/laddaBtnLoad/spin.min.js"></script>
    <script type="text/javascript" src="resources/js/laddaBtnLoad/ladda.min.js"></script>
    <%-- ladda button --%>
    <link href="${resources}css/taskModalView.css" rel="stylesheet"/>
    <link href="${resources}css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link href="${resources}css/bootstrap-dialog.css" rel="stylesheet"/>
    <link href="${resources}js/bootstrapTables/bootstrap-table.min.css" rel="stylesheet"/>

    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="${resources}js/maskInput.js"></script>
    <script type="text/javascript" src="${resources}js/moment.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${resources}js/includedJS/bootstrap-filestyle.min.js"></script>

    <script type="text/javascript" src="${resources}js/contests/tasks/taskCreateAddLink.js"></script>
    <script type="text/javascript" src="${resources}js/contests/tasks/taskEditing.js"></script>
    <script type="text/javascript" src="${resources}js/contests/tasks/taskAjax.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestAjax.js"></script>
    <script type="text/javascript" src="${resources}js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${resources}js/ckeditor/ckeditorInit.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestInit.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestUsers.js"></script>
</head>
<body>
<script type="text/javascript">
    //variables for dynamically created Modal forms:
    //task successfully added modal:
    var contestAccessDenied = "<spring:message code="contest.accessDenied" />";
    var errorLabel = "<spring:message code="error.caption" />";
    var uploadSize = "<spring:message code="task.wrongUploadSize" />";
    var taskEditResultHeader = "<spring:message code="taskCreate.header"/>";
    var taskEditResultSuccess = "<spring:message code="taskCreate.result.success"/>";
    //Can't add task modal:
    var errorCaption = "<spring:message code="error.caption"/>";
    var successCaption = "<spring:message code="success.caption"/>";
    var emptyContest = "<spring:message code="contest.EmptyFields.caption"/>";
    var emptyContestUsers = "<spring:message code="contest.EmptyFields.Users.caption"/>";
    var emptyContestfields = "<spring:message code="contest.emptyFields.submit"/>";
    //delete task:
    var taskDeleteHeader = "<spring:message code="task.delete.header"/>";
    var taskDeleteQuestion = "<spring:message code="task.delete.question"/>";
    var taskDeleteSuccess = "<spring:message code="task.delete.successful"/>";
    //buttons:
    var btnSubmit = "<spring:message code="btn.submit"/>";
    var btnClose = "<spring:message code="btn.close"/>";
    //success modal:
    var successContestSave = "<spring:message code="contest.successSave"/>";
    var contestUserListSuccess = "<spring:message code="contest.success.userList.save"/>";
    //contest submit finish form:
    var contestFinishHeader = "<spring:message code="contest.submit.header"/>";
    var contestFinishBody = "<spring:message code="contest.submit.body"/>";
    var contestReleaset = "<spring:message code="contest.release.body"/>";
    var contestReleaseFailDueDate = "<spring:message code="contest.failReleaseDueDate"/>";
    var emptyStart = "<spring:message code="contest.startDateEmpty"/>";
    var emptyTask = "<spring:message code="contest.release.taskEmpty"/>";
    //contestUserAccessListWarning:
    var emptyUserListHeader = "<spring:message code="contest.userList.EmptyListHeader"/>";
    var emptyUserListBody = "<spring:message code="contest.userList.EmptyListBody"/>";
    var uploadTestFileButton = "<spring:message code="taskCreate.uploadFileButton"/>";
    //taskTestAddButton:
    var newTest = "<spring:message code="taskCreate.newTest" />";
    var taskAddTestButton = "<spring:message code="task.tests.header"/>";
    //task test files deleting:
    var testDeletedHeader = "<spring:message code="taskCreate.testDeleteHeader"/>";
    var testDeletedBody = "<spring:message code="taskCreate.testDeleteBody"/>";
    var testDeleteQuestion = "<spring:message code="taskCreate.testDeleteQuestion" />";
    //length of recieved contest.tasks List:
    var TaskListSize = ${fn:length(contestModelForm.tasks)};
    //bind sendTaskJsonButton in modal:
    $('body').on("click", ".sendTaskJsonButton", function () {
        //get id of Modal
        var id = $(this).attr("data-modalId");
        var idInt = parseInt(id);
        //call doPostJson method from taskEditing.js:
        preparePostTaskJson(idInt);
    });
    //bind deleteButtonEvent:
    $('body').on("click", ".buttonDeleteTask", function () {
        //get index of Deleting task:
        var taskId = $(this).attr("data-taskId");
        var idInt = parseInt(taskId);
        //call deleteTask method from taskEditing.js:
        prepareDeleteTask(idInt);
    });
    //add New Task:
    $('body').on("click", "#addnewTaskButton", function () {
        tryToAddTask('${pageContext.response.locale}');
    });
    $('body').on('click', "#submitContest", function () {
        submitContest();
    });
    $('body').on('click', "#finishContest", function () {
        finishContest();
    });
    jQuery(function () {
        Ladda.bind('.ladda-button');
    });
    jQuery(function ($) {
        // CKEditor initialization
        $('.ckEdit').each(function () {
            initializeCKEdior($(this).attr('id'), '${pageContext.response.locale}');
        });
    });
    $('body').on("click", "#showUserList", function () {
        tryToShowUserList();
    });
    $('body').on("click", "#submitUserListButton", function () {
        sendContestUsers();
    });
    $(function () {
        setMask();
    });

    //start long polling:
    $(function () {
        if ($('#id') != 0) {
            contestLongPolling();
        }
    });
</script>

<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="contest"/>
    </jsp:include>
    <section>
        <!-- add usersList Modal -->
        <contest:usersList contestId="${contestModelForm.id}"/>
        <div class="accessList pull-right">
            <button id="showUserList" class="btn btn-warning btn-sm" type="button"><spring:message
                    code="btn.userAccessList"/></button>
        </div>
        <form:form id="contestForm" modelAttribute="contestModelForm" class="form-horizontal"
                   action="addContest.do" method="post">
            <form:hidden path="id"/>
            <form:hidden path="status"/>
            <div class="form-group">
                <spring:message code="contestCreate.title" var="contestTitle"/>
                <contest:taskModalFormInput element="title" inputDivClass="col-sm-8" label="${contestTitle}: *"/>
            </div>
            <div class="field description form-group">
                <label class="col-sm-2 control-label"><spring:message code="contestCreate.description"/>: *</label>

                <div class="col-sm-10">
                    <form:textarea style="resize:none" path="description" class="form-control ckEdit"
                                   rows="7"></form:textarea>
                    <form:errors path="description" cssClass="error"/>
                    <span class="help-inline control-label"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="field starts">
                    <spring:message code="contestCreate.starts" var="contestStarts"/>
                    <label class="col-sm-2 control-label">${contestStarts}:</label>

                    <div class="col-sm-3">
                        <div class="">
                            <div class='input-group date' id='datetimepicker1'><%-- --%>
                                <form:input path="starts" type='text' class="form-control"
                                            data-date-format="YYYY-MM-DD HH:mm"/>
                                        <span class="input-group-addon"><span
                                                class="glyphicon glyphicon-calendar"></span>
                                        </span>
                            </div>
                        </div>
                        <span class="help-inline control-label"></span>
                    </div>
                </div>
                <div class="field duration">
                    <spring:message code="contestCreate.duration" var="contestDuration"/>
                    <label class="col-sm-2 control-label">${contestDuration}:</label>

                    <div class='col-sm-2'>
                        <div class='input-group date' id='datetimepicker4'>
                            <form:input path="duration" type='text' class="form-control"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                                    </span>
                        </div>
                        <span class="help-inline control-label"></span>
                    </div>
                </div>
                <div class="field col-sm-2">
                    <label class="control-label ">
                        <form:checkbox path="hidden" title="Display only for accessed teachers"/> Hidden
                    </label>
                </div>
            </div>
        </form:form>
        <div class="taskList">
            <c:forEach items="${contestModelForm.tasks}" var="task"
                       varStatus="gridRow">
                <contest:taskModalForm taskModelName="contestModelForm.tasks[${gridRow.index}]"
                                       taskId="${gridRow.index}" formUrl="/NWCServer/newTaskJson.do"
                                       contestId="${contestModelForm.id}"/>
            </c:forEach>
        </div>
        <div id="liTaskViewPattern" hidden="true">
            <a class="list-group-item">
                        <span class="viewTitle">
                        </span>
                        <span class="pull-right">
                            <button class="btnEdit btn btn-xs btn-info" data-toggle="modal"
                                    data-target="#taskModal_0"><spring:message code="btn.edit"/></button>
                            <button class="buttonDeleteTask btn btn-xs btn-warning"
                                    data-taskId="0"><spring:message code="btn.delete"/></button>
                        </span>
            </a>
        </div>
        <div class="tasksControls form-horizontal">
            <div class="form-group">
                <ul class="col-sm-offset-4 col-sm-6">
                    <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center">
                        <spring:message code="contestCreate.tasks.caption"/></li>
                    <!-- go throw all avaible tasks in contest:-->
                    <c:forEach items="${contestModelForm.tasks}" var="task" varStatus="gridRow">
                        <a class="list-group-item" id="taskView_${gridRow.index}">
                                    <span class="viewTitle">
                                            ${task.title}
                                    </span>
                                    <span class="pull-right">
                                        <button class="btnEdit btn btn-xs btn-info" data-toggle="modal"
                                                data-target="#taskModal_${gridRow.index}"><spring:message
                                                code="btn.edit"/></button>
                                        <button class="buttonDeleteTask btn btn-xs btn-warning"
                                                data-taskId="${gridRow.index}"><spring:message
                                                code="btn.delete"/></button>
                                    </span>
                        </a>
                    </c:forEach>
                </ul>
                <button id="addnewTaskButton" class="col-sm-offset-6 col-sm-2
                                btn btn-primary btn-sm"><spring:message code="contestCreate.addNewTask"/>
                </button>
            </div>
        </div>
        <div class="pull-right">
            <button id="submitContest" type="submit" class="btn btn-primary btn-sm " value="Submit">
                <spring:message code="btn.save"/></button>
            <button id="finishContest" class="btn btn-warning btn-sm" value="Submit">
                <spring:message code="contest.finish.button"/> </button>
        </div>
    </section>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>