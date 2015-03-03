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

    <!-- TODO -->
</head>
<body>
    <div class="wrapper container">
        <!--including bodyHead -->
        <!-- send name of current page-->
        <jsp:include page="../fragments/bodyHeader.jsp">
            <jsp:param name="pageName" value="rating"/>
        </jsp:include>

        <div id="accordion" class="form-group col-sm-12" style="margin:auto">
            <ul class="col-sm-offset-2 col-sm-8 ">
                <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center">
                    <spring:message code="rating.caption"/>
                </li>
                <c:forEach var="archived" items="${archivedContests}" varStatus="row">
                    <a class="list-group-item " data-toggle="collapse" data-parent="#accordion"
                        href="#collapse${row.index}">
                        <span class="row">
                            <b class="pull-left">
                                ${archived.title}
                            </b>
                            <span class="pull-right">
                                <spring:message code="contest.results.started.caption"/>
                                <b>${fn:substring(archived.starts,0,16)}</b>
                                (<spring:message code="contest.results.continued.caption"/>
                                <b>${fn:substring(archived.duration,11,16)}</b>)
                            </span>
                        </span>
                    </a>
                    <div id="collapse${row.index}" class="panel-collapse collapse">
                        <li class="list-group-item list-group-item-info">
                            <div class="panel-body">
                                <div class="edit col-sm-12">
                                    <c:url var="results" value="/results.do?id=${archived.id}"/>
                                    <a class="btn btn-sm btn-info pull-right" href="${results}">
                                        <spring:message code="rating.openResults.button.caption"/>
                                    </a>
                                </div>
                                <div class="col-sm-12">
                                    ${archived.description}
                                </div>
                            </div>
                        </li>
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp" />
</body>
</html>