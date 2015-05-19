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
			<c:forEach var="rating" items="${ratingContests}" varStatus="row">
				<c:url var="results" value="/results.do?id=${rating.id}" />
				<li class="list-group-item"><span class="row"> <span
						class="pull-left"> <b><a href="${results}">${rating.title}</a></b>
                                <br/> <br /> <spring:message
								code="contest.results.started.caption" />
							${fn:substring(rating.starts,0,16)} (<spring:message
								code="contest.results.continued.caption" />
							${fn:substring(rating.duration,11,16)})
                            <span class="pull-right">
                                <label class="label label-danger contestsStatus"> <spring:message
										code="contest.archive.label" />
								</label>
                            </span>
                        </span>
				</span></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>