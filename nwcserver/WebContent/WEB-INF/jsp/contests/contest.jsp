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
    <link href="${resources}css/contests/contests-style.css" rel="stylesheet"/>

    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestSignUp.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestListView.js"></script>


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
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="contest"/>
    </jsp:include>
    <div class="main-block">
        <div id="accordion">
            <c:forEach items="${contests}" var="contest" varStatus="row">
                <a class="list-group-item" data-toggle="collapse" data-parent="#accordion"
                   href="#collapse${row.index}">
                    <div class="link-block">
                        <div class="start_date-block">
                                ${fn:substring(contest.starts,0,16)}
                        </div>
                        <div class="title-block">
                            <span class="h5"><b>${contest.title}</b></span>
                        </div>
                        <div class="status-block">
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
                        </div>
                    </div>
                </a>

                <div id="collapse${row.index}" class="panel-collapse collapse drop-block">
                    <li class="list-group-item list-group-item-info">
                        <div class="panel-body">
                            <div class="dateDuration">
                                <c:if test="${not empty contest.starts }">
                                                <div class="drop-block-element"><b><spring:message code="contest.table.starts"/>:</b>
                                                    ${fn:substring(contest.starts,0,16)}
                                                </div>
                                </c:if>
                                <c:if test="${not empty contest.duration }">
                                                <div class="drop-block-element"><b><spring:message code="contest.table.duration"/>:</b>
                                                    ${fn:substring(contest.duration,11,16)}
                                                </div>
                                </c:if>
                                <c:if test="${not empty contest.typeContest.name }">
                                                <div class="drop-block-element"><b><spring:message code="contest.table.type"/>:</b>
                                                    ${contest.typeContest.name}
                                                </div>
                                </c:if>
                            </div>

                            <div class="drop-block-element" >
                                <div class="text-center" style="margin: 1px">
                                    <b><spring:message code="contest.table.description"/></b>
                                </div>
                                <div>${contest.description}</div>
                            </div>

                            <c:if test="${contest.status=='GOING'}">
                                <div class="tasks drop-block-element">
                                    <div class="text-center">
                                        <b><spring:message code="contest.taskList"/></b>
                                    </div>
                                    <div class="tasks-block">
                                        <c:forEach items="${contest.tasks}" var="task" varStatus="taskRow">
                                            <contest:taskView taskId="${taskRow.index}"
                                                              contestId="${row.index}"
                                                              task="${contest.tasks[taskRow.index]}"/>
                                            <a class="list-group-item " data-toggle="modal"
                                               data-target="#taskView_${row.index}_${taskRow.index}" href="#">
                                                <span>${task.title}</span>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>

                            <div class="edit text-center drop-block-element" style="margin-top: 10px">
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
                                                onclick="startContest(${contest.tasks[0].id})">
                                            <spring:message code="contest.startButton"/>
                                        </button>
                                    </c:if>
                                    <c:if test="${(contest.status=='ARCHIVE')}">
                                        <button class="btn btn-sm btn-info form-group"
                                                style="font-weight: 600"
                                                onclick="startContest(${contest.tasks[0].id})">
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
                            </div>
                        </div>
                    </li>
                </div>
            </c:forEach>
            <security:authorize access="hasRole('ROLE_TEACHER')">
                <div class="col-sm-6 col-sm-offset-3" style="text-align: center; margin-top: 20px">
                    <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'">
                        <spring:message code="contest.createButton.caption"/></button>
                </div>
            </security:authorize>
        </div>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>