<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<form action="<c:url value="/etiam.do"></c:url>">
		<div class="navbar-form">
			<input type="text" id="filterText" name="filterText"
				class="form-control"
				placeholder="<spring:message code="pagination.search.field" />" >
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
						<c:if test="${currentPage - 3 ge 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="1"/>
										</c:url>">&#60;&#60;
							</a></li>
						</c:if>

						<!-- "Previous" button -->
						<c:if test="${currentPage gt 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage - 1}"/>
									  </c:url>"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>


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
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- "Next" button -->
						<c:if test="${currentPage lt pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage + 1}"/>
									  </c:url>"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
						<!-- Display last page -->
						<c:if test="${currentPage + 3 le pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${pageCount}"/>
										</c:url>">
									>> </a></li>
						</c:if>
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
				<!-- Pagination STARTO -->
				<div class="text-center">
					<ul class="pagination">
						<!-- Display first page -->
						<c:if test="${currentPage - 3 gt 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="1"/>
										</c:url>">
									<c:out value="1" />
							</a></li>
						</c:if>
						<!-- "Previous" button -->
						<c:if test="${currentPage gt 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage - 1}"/>
									  </c:url>"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

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
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- Display there're more pages before last page -->
						<c:if test="${currentPage + 2 lt pageCount}">
							<li class="disabled"><a>...</a></li>
						</c:if>
						<!-- "Next" button -->
						<c:if test="${currentPage lt pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage + 1}"/>
									  </c:url>"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
						<!-- Display last page -->
						<c:if test="${currentPage + 3 lt pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${pageCount}"/>
										</c:url>">
									&#62;&#62; </a></li>
						</c:if>
					</ul>
				</div>
				<!-- Pagination FINISH -->
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