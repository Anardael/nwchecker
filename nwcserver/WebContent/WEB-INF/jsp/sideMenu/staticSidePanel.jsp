<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<ul class="list-group submenu">
	<c:choose>
		<c:when test="${pageName=='news'}">
			<li class="list-group-item active"><a href="news.do"><spring:message
						code="news.caption" /></a></li>
		</c:when>
		<c:otherwise>
			<li class="list-group-item"><a href="news.do"><spring:message
						code="news.caption" /></a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${pageName=='rules'}">
			<li class="list-group-item active"><a href="rules.do"><spring:message
						code="home.rules.caption" /></a></li>
		</c:when>
		<c:otherwise>
			<li class="list-group-item"><a href="rules.do"><spring:message
						code="home.rules.caption" /></a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${pageName=='contacts'}">
			<li class="list-group-item active"><spring:message
					code="home.contacts.caption" /></li>
		</c:when>
		<c:otherwise>
			<li class="list-group-item"><a href="vestibulum.do"><spring:message
						code="home.contacts.caption" /></a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${pageName=='forum'}">
			<li class="list-group-item active"><spring:message
					code="home.forum.caption" /></li>
		</c:when>
		<c:otherwise>
			<li class="list-group-item"><a href="phasellus.do"><spring:message
						code="home.forum.caption" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>
</html>