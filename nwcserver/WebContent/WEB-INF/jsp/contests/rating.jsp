<%--
  Created by IntelliJ IDEA.
  User: Станіслав
  Date: 03.03.2015
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<head>
    <jsp:include page="../fragments/staticFiles.jsp"/>
</head>
<body>
    <div class="wrapper container">
        <!--including bodyHead -->
        <!-- send name of current page-->
        <jsp:include page="../fragments/bodyHeader.jsp">
            <jsp:param name="pageName" value="rating"/>
        </jsp:include>

        <div class="form-group col-sm-12" style="margin:auto">
            <ul class="col-sm-offset-2 col-sm-8 ">
                <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center; font-size: large">
                    <spring:message code="rating.caption"/>
                </li>
                <c:forEach var="archived" items="${archivedContests}" varStatus="row">
                    <c:url var="results" value="/results.do?id=${archived.id}"/>
                    <li class="list-group-item">
                        <span class="row">
                            <span class="pull-left">
                                <b>${archived.title}</b>
                                <br/>
                                <spring:message code="contest.results.started.caption"/>
                                ${fn:substring(archived.starts,0,16)}
                                (<spring:message code="contest.results.continued.caption"/>
                                ${fn:substring(archived.duration,11,16)})
                            </span>
                            <span class="pull-right">
                                <a class="btn btn-md btn-info pull-right" href="${results}">
                                    <spring:message code="rating.openResults.button.caption"/>
                                </a>
                            </span>
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp" />
</body>
</html>