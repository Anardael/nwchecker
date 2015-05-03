<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- Importing head --%>
<head>
<link rel="stylesheet" href="${resources}css/contests/contestPass.css"/>
<jsp:include page="../fragments/staticFiles.jsp" />
</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="contest" />
		</jsp:include>
		<div class="row">
		<%-- Side panel --%>		
		<div id="tasks" class="col-md-3">
			<ul class="nav nav-pills nav-stacked">			
				<c:url var="taskURL" value="/passTask.do?id=" scope="page" />
				<c:set var="count" value="0" scope="page" />
				
				<c:forEach var="taskInfo" items="${taskTitles}">
					<c:set var="count" value="${count + 1}" scope="page"/>
                    <c:set var="taskId" value="${taskInfo.key}"/>
                    <c:set var="taskTitle" value="${taskInfo.value}"
                           scope="page" />
					<c:choose>
						<c:when test="${taskId eq currentTask.id}">
							<li class="active">
								<a href="${taskURL}${taskId}">
                                    <b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="default">
								<a href="${taskURL}${taskId}">
									<b>${count}. </b><c:out value="${taskTitle}"/>
								</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<section class="col-md-9"> 
			<%-- Table of statistic --%>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th>#Id</th>
							<th>Username</th>
							<th>Compiler</th>
							<th>Execution time</th>
							<th>Memory Used</th>
							<th>Attempts</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${taskPassList}" var="taskPass">
							<tr>
								<td>${taskPass.taskPassId}</td>
								<td>${taskPass.userName}</td>
								<td>${taskPass.compiler.name}</td>
								<td>${taskPass.executionTime}</td>
								<td>${taskPass.memoryUsed}</td>
								<td>${taskPass.numberOfAttempts}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach begin="0" end="5" var="loop">
							<c:if test="${currentPage + loop - 3>0}">
							<c:if test="${currentPage + loop - 3<=lastPage}">
								<li><a href="TaskStatistic.do?id=${taskId}&page=${currentPage + loop - 3}"> 
								<c:out value="${currentPage + loop - 3}" /></a></li>
							</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			</section>
		</div>
	</div>
</body>
</html>
