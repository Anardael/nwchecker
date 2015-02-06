<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="contest" uri="/tlds/ContestTags"%>

<div class="tests" avaible="${fn:length(taskData)}">
    <%-- pattern for future files: --%>
    <contest:taskTestFiles taskId="${taskId}" row="pattern" hidden="hidden=\"true\""/>
    <%-- go through all current taskData in task --%>
    <c:forEach items="${taskData}" var="data" varStatus="row">
        <contest:downloadTaskTestFiles taskId="${taskId}" row="${row.index}" contestId="${contestId}" testId="${data.id}"/>
    </c:forEach>
</div> 