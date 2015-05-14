<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/" var="resources" />
<html>
<!-- Including head -->
<head>
<jsp:include page="../fragments/staticFiles.jsp" />

<link href="${resources}js/bootstrapTables/bootstrap-table.min.css"
	rel="stylesheet" />
<link
	href="${resources}js/bootstrapTables/bootstrap-table-heightFix.css"
	rel="stylesheet" />

<script type="text/javascript"
	src="${resources}js/bootstrapTables/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${resources}js/bootstrapTables/locale/bootstrap-table-${pageContext.response.locale}.min.js"></script>
<script type="text/javascript"
	src="${resources}js/adminOptions/usersFormatters.js"></script>
</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="home" />
		</jsp:include>
		<div class="row">
			<aside class="col-md-3">
				<ul class="list-group submenu">
					<li class="list-group-item active"><spring:message
							code="home.info.caption" /></li>
					<li class="list-group-item"><a href="donec.do"><spring:message
								code="home.rules.caption" /></a></li>
					<li class="list-group-item"><a href="vestibulum.do"><spring:message
								code="home.contacts.caption" /></a></li>
					<li class="list-group-item"><a href="etiam.do"><spring:message
								code="home.archive.caption" /></a></li>
					<li class="list-group-item"><a href="phasellus.do"><spring:message
								code="home.forum.caption" /></a></li>
				</ul>
			</aside>
			<div class="col-md-9">
				<div class="text-center">
					<ul class="pagination">
						<c:forEach begin="0" end="5" var="loop">
							<c:if test="${currentPage + loop - 3>0}">
								<c:if test="${currentPage + loop - 3<pageCount + 1}">
									<li><a
										href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<c:forEach items="${tasks}" var="task">
					<table class="table">
						<tr>
							<td class="list-group-item list-group-item-heading" width="40%">${task.title}</td>
							<td class="list-group-item list-group-item-heading" width="15%">Difficulty:${task.complexity}</td>
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

			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>