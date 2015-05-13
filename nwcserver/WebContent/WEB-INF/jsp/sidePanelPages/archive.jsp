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
		<%-- 	<aside class="col-md-3">
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
			</aside> --%>
			<section class="col-md-15">
				<%--			<table id="table">
						<c:forEach items="${tasks}" var="task">
							<tr>
								<td class="list-group-item ">${task.title}(${task.complexity})</td>
							</tr>
							<tr>
								<td class="list-group-item list-group-item-info" colspan="2">
									${task.description} ${task.description}${task.description}
									${task.description}${task.description}
									${task.description}${task.description}
									${task.description}${task.description}
									${task.description}${task.description} ${task.description}</td>
							</tr>
						</c:forEach>
					</table>
--%>
				<c:url var="dataUrl" value="/archivedTasks.do" />
				<table id="bootstrap-table" data-toggle="table" data-url="${dataUrl}"
					data-method="get" data-cache="false" data-pagination="true"
					data-show-pagination-switch="true" data-search="true"
					data-clear-search="true">
					<thead>
						<tr>
							<th data-field="complexity" data-align="center" class="col-md-1"><spring:message
									code="archive.taskComplexity" /></th>
							<th data-field="title" data-align="center" class="col-md-3"><spring:message
									code="archive.taskTitle" /></th>

							<th data-field="description" data-align="center" class="col-md-11"><spring:message
									code="archive.taskDescription" /></th>
						</tr>
					</thead>
				</table>
			</section>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>