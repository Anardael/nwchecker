<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<jsp:useBean id="filterText" class="java.lang.String" scope="session" />
	<form action="<c:url value="/etiam.do"></c:url>">
		<div class="navbar-form">
			<input type="text" id="filterText" name="filterText"
				class="form-control"
				placeholder="<spring:message code="pagination.search.field" />">
			<button type="submit" id="filter" class="btn btn-default">
				<spring:message code="pagination.search.button" />
			</button>
		</div>
		<c:choose>
			<c:when test="${not empty tasks}">
				<c:set var="loop"></c:set>
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${currentPage gt 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage - 1}"/>
									  </c:url>"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:forEach begin="1" end="${pageCount}" var="loop">
							<c:choose>
								<c:when test="${loop != currentPage}">
									<li><a href="<c:url value="/etiam.do">
										<c:param name="page" value="${loop}"/>
									  </c:url>">
											<c:out value="${loop}" />
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="active disabled"><a><c:out value="${loop}" /></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${currentPage lt numberOfPages}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage + 1}"/>
									  </c:url>"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</div>
				<c:forEach items="${tasks}" var="task">
					<table class="table">
						<tr>
							<td class="list-group-item list-group-item-heading" width="40%">${task.title}</td>
							<td class="list-group-item list-group-item-heading" width="15%"><spring:message
									code="archive.taskComplexity" />:${task.complexity}</td>
							<td class="list-group-item list-group-item-heading" width="20%">Discussion</td>
						</tr>
						<tr>
							<td class="list-group-item list-group-item-info" colspan="3">${task.description}</td>
						</tr>
					</table>
				</c:forEach>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach begin="0" end="5" var="loop">
							<c:if test="${currentPage + loop - 3>0}">
								<c:if test="${currentPage + loop - 3<pageCount + 1}">
									<li><a
										href="<c:url value="/etiam.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign"
						aria-hidden="true"></span> <span class="sr-only">Error:</span> No
					records found.
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</body>
</html>