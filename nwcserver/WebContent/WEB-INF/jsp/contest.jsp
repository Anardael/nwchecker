<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %> 
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <head>
        <jsp:include page="fragments/staticFiles.jsp" />    
        <link href="${resources}css/taskModalView.css" rel="stylesheet"/>
        <script type="text/javascript" src="${resources}js/bootstrap-dialog.js"></script>
    </head>
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <section>
                <div id="accordion" class="form-group col-sm-12" style="margin:auto">
                    <security:authorize access="hasRole('ROLE_TEACHER')">
                        <c:if test="${fn:length(contests) gt 8}">
                            <div class="col-sm-6 col-sm-offset-3"  style="text-align: center; margin-bottom: 20px">
                                <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'"><spring:message code="contest.createButton.caption" /></button>
                            </div>
                        </c:if> 
                    </security:authorize>
                    <ul class="col-sm-offset-2 col-sm-8 ">
                        <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center"><spring:message code="contest.caption" /></li>
                            <c:forEach items="${contests}" var="contest" varStatus="row">
                            <a class="list-group-item " data-toggle="collapse" data-parent="#accordion" href="#collapse${row.index}">
                                <span>${contest.title}</span>
                            </a>
                            <div id="collapse${row.index}" class="panel-collapse collapse">
                                <li class="list-group-item list-group-item-info">
                                    <div class="panel-body">
                                        <div class="edit col-sm-12">
                                            <span class="pull-right">
                                                <c:set value="index${contest.id}index" var="contestIndex"/>
                                                <c:if test="${fn:contains(editContestIndexes,contestIndex)}">
                                                    <button class="btnEditContest btn btn-sm btn-info form-group" 
                                                            onclick="location.href = 'editContest.do?id=${contest.id}';"><spring:message code="btn.edit" /></button>
                                                </c:if>
                                            </span>
                                        </div>
                                        <div class="description col-sm-12">
                                            <span>${contest.description}</span>
                                        </div>
                                        <div class="dateDuration col-sm-12 form-group">
                                            <c:if test="${not empty contest.duration }">
                                                <span class="pull-left"><spring:message code="contest.table.duration" />:
                                                    ${contest.duration.hours} <spring:message code="contest.table.duration.hours"/>
                                                    ${contest.duration.minutes}  <spring:message code="contest.table.duration.minutes"/>.
                                                </span>
                                            </c:if>
                                            <c:if test="${not empty contest.starts }">
                                                <span class="pull-right"><spring:message code="contest.table.starts" />:
                                                    ${1900+contest.starts.year}-${1+contest.starts.month}-${contest.starts.date}
                                                    ${contest.starts.hours}:${contest.starts.minutes}
                                                </span>
                                            </c:if>
                                        </div>
                                        <div class="tasks form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="contest.taskList" />:</label>
                                            <ul class="col-sm-8 ">
                                                <c:forEach items="${contest.tasks}" var="task" varStatus="taskRow">
                                                    <contest:taskView taskId="${taskRow.index}" 
                                                                      contestId="${row.index}" task="${contest.tasks[taskRow.index]}"/>
                                                    <a class="list-group-item " data-toggle="modal" 
                                                       data-target="#taskView_${row.index}_${taskRow.index}" href="#">
                                                        <span>${task.title}</span>
                                                    </a>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                            </div>
                        </c:forEach>
                    </ul>
                    <security:authorize access="hasRole('ROLE_TEACHER')">
                        <div class="col-sm-6 col-sm-offset-3"  style="text-align: center; margin-top: 20px">
                            <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'"><spring:message code="contest.createButton.caption" /></button>
                        </div>
                    </security:authorize>
                </div>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>