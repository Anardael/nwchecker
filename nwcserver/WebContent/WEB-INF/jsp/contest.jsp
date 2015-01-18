<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %> 
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <head>
        <link href="${resources}css/taskModalView.css" rel="stylesheet"/>
        <jsp:include page="fragments/staticFiles.jsp" />    
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
                    <ul class="col-sm-offset-2 col-sm-8 ">
                        <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center">Contests</li>
                            <c:forEach items="${contests}" var="contest" varStatus="row">
                            <a class="list-group-item " data-toggle="collapse" data-parent="#accordion" href="#collapse${row.index}">
                                <span>${contest.title}</span>
                            </a>
                            <div id="collapse${row.index}" class="panel-collapse collapse">
                                <li class="list-group-item list-group-item-info">
                                    <div class="panel-body">
                                        <div class="edit col-sm-12">
                                            <span class="pull-right">
                                                <button class="btnEditContest btn btn-sm btn-info form-group" 
                                                        onclick="location.href = 'editContest.do?id=${contest.id}';">Edit</button>
                                            </span>
                                        </div>
                                        <div class="description col-sm-12">
                                            <span>${contest.description}</span>
                                        </div>
                                        <div class="dateDuration col-sm-12 form-group">
                                            <span class="pull-left"><spring:message code="contest.table.duration" />:
                                                <c:if test="${not empty contest.duration }">
                                                    ${contest.duration.hours} hours ${contest.duration.minutes} minutes 
                                                </c:if>
                                            </span>
                                            <span class="pull-right"><spring:message code="contest.table.starts" />:
                                                <c:if test="${not empty contest.starts }">
                                                    ${1900+contest.starts.year}-${1+contest.starts.month}-${contest.starts.date}
                                                    ${contest.starts.hours}:${contest.starts.minutes}
                                                </c:if>
                                            </span>
                                        </div>
                                        <div class="tasks form-group">
                                            <label class="col-sm-2 control-label">Task list:</label>
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
                    <div class="col-sm-6 col-sm-offset-3">
                        <form action="addContest.do" style="text-align: center; margin-top: 20px">
                            <button type="submit" class="btn btn-primary btn-sm" value="Submit"><spring:message code="contest.createButton.caption" /></button>
                        </form>
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>