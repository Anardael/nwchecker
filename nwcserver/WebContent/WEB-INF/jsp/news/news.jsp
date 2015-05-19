<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<head>
<jsp:include page="../fragments/staticFiles.jsp" />
</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="news" />
		</jsp:include>

		<div class="form-group col-sm-12" style="margin: auto">
		
			<c:url var="news" value="/news.do" />
			<ul class="col-sm-offset-2 col-sm-8 ">
				<li
					class="list-group-item list-group-item-heading list-group-item-info"
					style="text-align: left; font-size: large"><spring:message
						code="news.contests" /></li>
					<li class="list-group-item">${contest.title}<br> <spring:message
						code="news.contests.date" /> ${contest.starts}
				</li>
			</ul>
			<br>
			<ul class="col-sm-offset-2 col-sm-8 ">
				<li
					class="list-group-item list-group-item-heading list-group-item-info"
					style="text-align: left; font-size: large"><spring:message
						code="news.result" />
						<li class="list-group-item "> ${title}</li>
					<li class="list-group-item "
						style="text-align: left; font-size: large">
						<table class="table table-bordered">
							<tr>
							<th style="text-align: center"><spring:message
									code="contest.results.tableHeader.place" /></th>
							<th style="text-align: center"><spring:message
									code="contest.results.tableHeader.displayName" /></th>
							<th style="text-align: center"><spring:message
									code="contest.results.tableHeader.tasksPassedCount" /></th>
							<th style="text-align: center"><spring:message
									code="contest.results.tableHeader.timePenalty" /></th>
						</tr>
						<c:forEach items="${result}" var="contest">
							<tr>
								<td align="center">${contest.rank}</td>
								<td align="center">${contest.displayName}</td>
								<td align="center">${contest.tasksPassedCount}</td>
								<td align="center">${contest.timePenalty}</td>
							</tr>
						</c:forEach>
					</table>
				</li>
			</ul>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>