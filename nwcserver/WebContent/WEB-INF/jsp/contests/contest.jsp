<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<!--including head -->
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
    <link href="${resources}css/taskModalView.css" rel="stylesheet"/>
    <link href="${resources}css/bootstrap-dialog.css" rel="stylesheet"/>
    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestSignUp.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestListView.js"></script>
    <style>
        .contestsStatus {
            margin-right: 10px;
            padding: 1px 3px 3px 3px;
            font-size: 12px;
            font-weight: normal;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    var successCaption = "<spring:message code="success.caption"/>";
    var successSignUpContest = "<spring:message code="contest.signUpBody"/>";
    var alreadySignUp = "<spring:message code="contest.alreadySubscribed"/>";
    var errorLabel = "<spring:message code="error.caption" />";
    var nowEditingBody = "<spring:message code="contest.editing.now.body"/>";
    var nowEditingUser = "<spring:message code="contest.editing.now.username"/>";

</script>
<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="contest"/>
    </jsp:include>
    <section>
        <div id="accordion" class="form-group col-sm-12" style="margin:auto">
            <security:authorize access="hasRole('ROLE_TEACHER')">
                <c:if test="${fn:length(contests) gt 8}">
                    <div class="col-sm-6 col-sm-offset-3" style="text-align: center; margin-bottom: 20px">
                        <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'">
                            <spring:message code="contest.createButton.caption"/></button>
                    </div>
                </c:if>
            </security:authorize>
            <ul class="col-sm-offset-2 col-sm-8 ">
                <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center; font-size: large">
                    <spring:message code="contest.caption"/></li>
                <c:forEach items="${contests}" var="contest" varStatus="row">
                    <a class="list-group-item " data-toggle="collapse" data-parent="#accordion"
                       href="#collapse${row.index}">
                        <c:if test="${contest.status=='GOING'}">
                            <label class="label label-success contestsStatus">
                                <spring:message code="contest.going.label"/></label>
                        </c:if>
                        <c:if test="${contest.status=='PREPARING'}">
                            <label class="label label-info contestsStatus">
                                <spring:message code="contest.preparing.label"/>
                            </label>
                        </c:if>
                        <c:if test="${contest.status=='RELEASE'}">
                            <label class="label label-info contestsStatus">
                                <spring:message code="contest.release.label"/>
                            </label>
                        </c:if>
                        <c:if test="${contest.hidden==true}">
                            <label class="label label-default contestsStatus"><spring:message
                                    code="contest.hidden.label"/></label>
                        </c:if>
                        <span>${contest.title}</span>
                    </a>

                    <div id="collapse${row.index}" class="panel-collapse collapse">
                        <li class="list-group-item list-group-item-info">
                            <div class="panel-body">
                                <div class="edit col-sm-12">
                                            <span class="pull-right">
                                                <security:authorize access="hasRole('ROLE_TEACHER')">
                                                    <c:set var="user" value="${nowContestEdits[contest.id]}"/>
                                                    <c:if test="${not empty user}">
                                                        <label class="label label-warning contestsStatus">
                                                            <spring:message
                                                                    code="contest.editing.now.label"/>: ${user}</label>
                                                    </c:if>

                                                    <c:set value="index${contest.id}index" var="contestIndex"/>
                                                    <c:if test="${fn:contains(editContestIndexes,contestIndex)}">
                                                        <button class="btnEditContest btn btn-sm btn-info form-group"
                                                                onclick="edited(${contest.id})">
                                                            <spring:message code="btn.edit"/></button>
                                                    </c:if>
                                                </security:authorize>
                                                <security:authorize access="hasRole('ROLE_USER')">
                                                    <c:if test="${(contest.status=='GOING')}">
                                                        <button class="btn btn-sm btn-info form-group"
                                                                style="font-weight: 600"
                                                                onclick="contestStart(${contest.tasks[0].id});">
                                                            <spring:message code="contest.startButton"/>
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${(contest.status=='ARCHIVE')}">
                                                        <button class="btn btn-sm btn-info form-group"
                                                                style="font-weight: 600"
                                                                onclick="contestStart(${contest.tasks[0].id});">
                                                            <spring:message code="contest.startButton"/>
                                                        </button>
                                                    </c:if>
                                                </security:authorize>
                                                <security:authorize access="!isAuthenticated()">
                                                    <h4>
                                                        <label class="label label-info label">
                                                            <spring:message code="contest.unauthenticated"/>
                                                        </label>
                                                    </h4>
                                                </security:authorize>
                                            </span>
                                </div>
                                <div class="description col-sm-12">
                                    <span>${contest.description}</span>
                                </div>
                                <div class="dateDuration col-sm-12 form-group">
                                    <c:if test="${not empty contest.duration }">
                                                <span class="pull-left"><spring:message code="contest.table.duration"/>:
                                                    ${fn:substring(contest.duration,11,16)}
                                                </span>
                                    </c:if>
                                    <c:if test="${not empty contest.starts }">
                                                <span class="pull-right"><spring:message code="contest.table.starts"/>:
                                                    ${fn:substring(contest.starts,0,16)}
                                                </span>
                                    </c:if>
                                </div>
                                <c:if test="${contest.status=='GOING'}">
                                    <div class="tasks form-group">
                                        <label class="col-sm-2 control-label"><spring:message
                                                code="contest.taskList"/>:</label>
                                        <ul class="col-sm-8 ">
                                            <c:forEach items="${contest.tasks}" var="task" varStatus="taskRow">
                                                <contest:taskView taskId="${taskRow.index}"
                                                                  contestId="${row.index}"
                                                                  task="${contest.tasks[taskRow.index]}"/>
                                                <a class="list-group-item " data-toggle="modal"
                                                   data-target="#taskView_${row.index}_${taskRow.index}" href="#">
                                                    <span>${task.title}</span>
                                                </a>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </li>
                    </div>
                </c:forEach>
            </ul>
            <security:authorize access="hasRole('ROLE_TEACHER')">
                <div class="col-sm-6 col-sm-offset-3" style="text-align: center; margin-top: 20px">
                    <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'">
                        <spring:message code="contest.createButton.caption"/></button>
                </div>
            </security:authorize>
        </div>
    </section>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>