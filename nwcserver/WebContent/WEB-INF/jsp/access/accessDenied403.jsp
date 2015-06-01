<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %> 
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <body>
            <section>
                <h3 style="text-align: center; color: red"><spring:message code="contest.accessDeniedHeader"/></h3>
                <h4 style="text-align: center; color: red"><spring:message code="contest.accessDenied" />.</h4>
            </section>
    </body>
</html>
