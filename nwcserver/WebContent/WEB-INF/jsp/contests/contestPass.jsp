<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<!--including head -->
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
    <link rel="stylesheet" href="${resources}css/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${resources}css/contests/contestPass.css"/>
    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-select.js"></script>
</head>
<body>
<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="contest"/>
    </jsp:include>
    <!-- Shows tasks navigation list and marks every task status -->
    <div id="tasks" class="col-md-3">
        <c:url var="taskURL" value="/passTask.do?id=" scope="page"/>
        <c:set var="count" value="0" scope="page"/>
        <c:forEach var="taskId" items="${tasksIdList}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <c:set var="taskResult" value="${taskResults.key[taskId]}" scope="page"/>
            <c:choose>
                <c:when test="${taskId eq currentTask.id}">
                    <div class="col-sm-2 active-tab">
                        <a href="${taskURL}${taskId}">${count}</a>
                    </div>
                </c:when>
                <c:when test="${taskResult == null}">
                    <div class="col-sm-2 default-tab">
                        <a href="${taskURL}${taskId}">${count}</a>
                    </div>
                </c:when>
                <c:when test="${taskResult == true}">
                    <div class="col-sm-2 success-tab">
                        <a href="${taskURL}${taskId}">${count}</a>
                    </div>
                </c:when>
                <c:when test="${taskResult == false}">
                    <div class="col-sm-2 fail-tab">
                        <a href="${taskURL}${taskId}">${count}</a>
                    </div>
                </c:when>
            </c:choose>
        </c:forEach>
    </div>
    <!-- Current Task information -->
    <div class="col-md-9">
        <div class="page-header">
            <h2>${currentTask.title}</h2>
        </div>
        <c:set var="currentTaskResult" value="${taskResults.key[currentTask.id]}" scope="page"/>
        <c:choose>
            <c:when test="${currentTaskResult == null}">
                <div class="descr-default"><p>${currentTask.description}</p></div>
            </c:when>
            <c:when test="${currentTaskResult == true}">
                <div class="descr-success"><p>${currentTask.description}</p></div>
            </c:when>
            <c:when test="${currentTaskResult == false}">
                <div class="descr-fail"><p>${currentTask.description}</p></div>
            </c:when>
        </c:choose>
        <h4>Time limit: <b>${currentTask.timeLimit}</b></h4>
        <h4>Memory limit: <b>${currentTask.memoryLimit}</b></h4>
        <br/><br/>
        <!-- Send answer form -->
        <form:form class="form-horizontal" method="post" action="submitTask.do" modelAttribute="currentTask"
                   enctype="multipart/form-data" role="form">
            <form:hidden path="id"/>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <select id="compilerId" name="compilerId" class="selectpicker">
                        <option value="1">Compiler1</option>
                        <option value="2">Compiler2</option>
                        <option value="3">Compiler3</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
            				<span class="btn btn-block btn-file">
            					<spring:message code="contest.passing.uploadSourceFile.caption"/>
            					<input type="file" id="file" name="file">
            				</span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-info btn-block">
                        <spring:message code="contest.passing.sendCode.caption"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>