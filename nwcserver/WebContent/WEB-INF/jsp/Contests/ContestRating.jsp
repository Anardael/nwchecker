<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<body>
	<div class="form-group col-sm-12" style="margin: auto">
		<ul class="col-sm-offset-2 col-sm-8 ">
			<%--<table id="ratingTable" class="table table-bordered" data-search="true"--%>
				   <%--data-clear-search="true">--%>
				<%--<tr>--%>
					<%--<th style="text-align: center"><spring:message--%>
							<%--code="contest.table.title"/></th>--%>
					<%--<th style="text-align: center"><spring:message--%>
							<%--code="contest.table.description"/></th>--%>
					<%--<th style="text-align: center"><spring:message--%>
							<%--code="contest.results.started.caption"/></th>--%>
					<%--<th style="text-align: center"><spring:message--%>
							<%--code="listContests.contests.tableHeader.status"/></th>--%>
				<%--</tr>--%>
				<%--<c:forEach items="${ratingContests}" var="contest">--%>
					<%--<c:url var="results" value="/results.do?id=${contest.id}"/>--%>
					<%--<tr>--%>
						<%--<td align="center"><a href="${results}">${contest.title}</a></td>--%>
						<%--<td align="center">${contest.description}</td>--%>
						<%--<td align="center">${contest.starts}</td>--%>
						<%--<td align="center">${contest.status}</td>--%>
					<%--</tr>--%>
				<%--</c:forEach>--%>
			<%--</table>--%>

			<c:url var="dataURL" value="/rating.do" />
			<table id="ratingContest" class="table" data-toggle="table"
				   data-striped="true" data-url="${dataURL}"
				   data-side-pagination="server" data-pagination="true"
				   data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
				   data-clear-search="true" data-sort-name="username"
				   data-sort-order="asc">
				<thead>
				<tr>
					<th data-field="title" data-align="center" data-sortable="true">Title</th>
					<th data-field="description" data-align="center" data-sortable="true">Description</th>
					<th data-field="starts" data-align="center" data-sortable="true">Starts</th>
					<th data-field="status" data-align="center" data-sortable="true">Status</th>
				</tr>
				</thead>
			</table>

		</ul>
	</div>
</body>
</html>