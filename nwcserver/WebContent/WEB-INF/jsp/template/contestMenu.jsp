<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
</html>