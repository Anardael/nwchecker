<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<spring:url value="/resources/" var="resources" />
<script type="text/javascript">
	$(function() {
		$(".dropdown-menu > li > a.trigger").on("click", function(e) {
			e.stopPropagation();
		});
	});
</script>
<link href="${resources}css/fragments/bodyHeader.css" rel="stylesheet" />
<header>
	<!-- navigating toolbar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<ul class="nav navbar-nav">
				<!-- home -->
				<c:choose>
					<c:when test="${pageName=='home'}">
						<li class="active"><a><spring:message code="home.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="index.do"><spring:message
									code="home.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- news -->
				<c:choose>
					<c:when test="${pageName=='news'}">
						<li class="active"><a href="news.do"><spring:message
									code="news.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="news.do"><spring:message code="news.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- olympiad -->
				<c:choose>
					<c:when test="${pageName=='contest'}">
						<li class="active"><a><spring:message
									code="contest.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="getContests.do"><spring:message
									code="contest.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- rating -->
				<c:choose>
					<c:when test="${pageName=='rating'}">
						<li class="active"><a><spring:message
									code="rating.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="rating.do"><spring:message
									code="rating.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- rules -->
				<c:choose>
					<c:when test="${pageName=='rules'}">
						<li class="active"><a href="rules.do"><spring:message
									code="home.rules.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="rules.do"><spring:message
									code="home.rules.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- archive -->
				<c:choose>
					<c:when test="${pageName=='archive'}">
						<li class="active"><a><spring:message
									code="home.archive.caption" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="etiam.do"><spring:message
									code="home.archive.caption" /></a></li>
					</c:otherwise>
				</c:choose>
				<!-- login -->
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="!isAuthenticated()">
					<c:choose>
						<c:when test="${pageName=='login'}">
							<li class="active"><a><spring:message
										code="login.caption" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="login.do"><spring:message
										code="login.caption" /></a></li>
						</c:otherwise>
					</c:choose>
				</security:authorize>
				<!-- logout/profile -->
				<security:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"> <security:authentication
								property="principal.username" /> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu multi-level" role="menu"
							aria-labelledby="dropdownMenu">
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="dropdown-submenu pull-left admin-subMenu"><a
									class="trigger"> <spring:message code="admin.caption" /></a>
									<ul class="dropdown-menu">
										<li><a href="admin.do"><spring:message
													code="adminPanel.users.caption" /></a></li>
										<li class="divider"></li>
										<li><a href="userRequests.do"><spring:message
													code="userRequests.caption" /></a></li>
										<li class="divider"></li>
										<li><a href="listContests.do"><spring:message
													code="listContests.caption" /></a></li>
									</ul></li>
								<li class="divider"></li>
							</security:authorize>
							<li><a href="profile.do"><spring:message
										code="profile.caption" /></a></li>
							<li class="divider"></li>
							<li><a href="logout.do"><spring:message
										code="logout.caption" /></a></li>
						</ul></li>
				</security:authorize>
				<!-- localization -->
				<c:if test="${not empty param['id']}">
					<li><a href="?id=${param['id']}&locale=ua"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukraineFlag.png" width="36" height="36"
							alt="ua"></a></li>
					<li><a href="?id=${param['id']}&locale=en"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukFlag.png" width="36" height="36"
							alt="en"></a></li>
				</c:if>

				<c:if test="${not empty param['Username']}">
					<li><a href="?Username=${param['Username']}&locale=ua"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukraineFlag.png" width="36" height="36"
							alt="ua"></a> <a
						href="?Username=${param['Username']}&locale=en"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukFlag.png" width="36" height="36"
							alt="en"></a></li>
				</c:if>

				<c:if test="${not empty param['status']}">
					<li><a href="?status=${param['status']}&locale=ua"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukraineFlag.png" width="36" height="36"
							alt="ua"></a></li>
					<li><a href="?status=${param['status']}&locale=en"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukFlag.png" width="36" height="36"
							alt="en"></a></li>
				</c:if>

				<c:if
					test="${(empty param['id']) && (empty param['Username']) && (empty param['status'])}">
					<li><a href="?locale=ua"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukraineFlag.png" width="36" height="36"
							alt="ua"></a></li>
					<li><a
						href="${requestScope['javax.servlet.forward.request_uri']}?locale=en"
						style="padding-left: 2px; padding-right: 2px;"><img
							src="${resources}images/ukFlag.png" width="36" height="36"
							alt="en"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<c:if test="${not empty sessionScope.currentBreadCrumb}">
		<div class="container">
			<ol class="breadcrumb" style="font-size: large;">
				<c:forEach var="entry" items="${sessionScope.currentBreadCrumb}">
					<c:choose>
						<c:when test="${entry.currentPage == true}">
							<li class="active"><spring:message code="${entry.label}" /></li>
						</c:when>
						<c:otherwise>
							<li><a href="${entry.url}"><spring:message
										code="${entry.label}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</ol>
		</div>
	</c:if>
</header>
