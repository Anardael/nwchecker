<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/resources/" var="resources"/>
<html>
<!--including head -->
<script type="text/javascript" src="${resources}js/serverTime.js"></script>
<body onload="setIncomingTime()">
    <input id="incomingServerTime" value="${serverTime}" hidden="true">
    <label id="labelServerTime"></label>
</body>
</html>
