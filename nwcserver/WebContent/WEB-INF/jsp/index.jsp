<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<!--including head -->
<head>
<jsp:include page="fragments/staticFiles.jsp" />
</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="fragments/bodyHeader.jsp">
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
			<section class="col-md-9">
				<div class="jumbotron">
					<blockquote>
						<p>
							"
							<spring:message code="index.quote" />
							"
						</p>
						<small><spring:message code="index.quoteAuthor" /></small>
					</blockquote>
				</div>
				<div class="info">
					<p>
						<b>NWCServer</b> -
						<spring:message code="index.content.header" />
					</p>
					<p>
						<b><spring:message code="index.content.goal" />:</b>
					</p>
					<p>
						<spring:message code="index.content.goalBody" />
					</p>
			</section>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>