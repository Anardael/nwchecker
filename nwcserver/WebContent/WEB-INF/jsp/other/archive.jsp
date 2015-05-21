<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<form action="<c:url value="/etiam.do"></c:url>">
		<div class="navbar-form">
			<input type="text" id="filterText" class="form-control"
				name="filterText"
				placeholder="<spring:message code="pagination.search.field" />"
				value="${filterText}" />
			<button type="submit" id="filter" class="btn btn-default">
				<spring:message code="pagination.search.button" />
			</button>
		</div>
		<c:choose>
			<c:when test="${not empty tasks}">
				<!-- Pagination STARTO -->
				<div class="text-center">
					<ul class="pagination">
						<!-- Display first page -->
						<c:choose>
							<c:when test="${currentPage - 3 ge 1}">
								<li><a
									href="<c:url value="/etiam.do">
										<c:param name="page" value="1"/>										
										<c:if test="${not empty filterText}">
											<c:param name="filterText" value="${filterText}"/>
										</c:if>
										</c:url>">&#60;&#60;
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a>&#60;&#60;</a></li>
							</c:otherwise>
						</c:choose>

						<!-- "Previous" button -->
						<c:choose>
							<c:when test="${currentPage gt 1}">
								<li><a
									href="<c:url value="/etiam.do">
										<c:if test="${not empty filterText}">
											<c:param name="filterText" value="${filterText}"/>
										</c:if>
										<c:param name="page" value="${currentPage - 1}"/>
									  </c:url>"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
							</c:otherwise>
						</c:choose>

						<!-- Display 2 closest pages -->
						<c:forEach begin="1" end="5" var="loop">
							<c:choose>
								<c:when test="${loop eq 3}">
									<li class="active"><a><c:out value="${currentPage}" /></a></li>
								</c:when>
								<c:when
									test="${(currentPage + loop - 3 gt 0)and(currentPage + loop - 3 lt pageCount + 1)}">
									<li><a
										href="<c:url value="/etiam.do">
										<c:if test="${not empty filterText}">
											<c:param name="filterText" value="${filterText}"/>
										</c:if>
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- "Next" button -->
						<c:choose>
							<c:when test="${currentPage lt pageCount}">
								<li><a
									href="<c:url value="/etiam.do">
										<c:if test="${not empty filterText}">
											<c:param name="filterText" value="${filterText}"/>
										</c:if>
										<c:param name="page" value="${currentPage + 1}"/>
									  </c:url>"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
							</c:otherwise>
						</c:choose>
						
						<!-- Display last page -->
						<c:choose>
						<c:when test="${currentPage + 3 le pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:if test="${not empty filterText}">
											<c:param name="filterText" value="${filterText}"/>
										</c:if>
										<c:param name="page" value="${pageCount}"/>
										</c:url>">
									>> </a></li>
						</c:when>
						<c:otherwise><li class="disabled"><a>>></a></li></c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- Pagination FINISH -->
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