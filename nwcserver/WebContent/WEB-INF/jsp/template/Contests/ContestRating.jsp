<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
<body>
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
                <br>
                <br>
                <c:forEach var="type2" items="${type2Contests}" varStatus="row">
                    <c:url var="results" value="/results.do?id=${type2.id}"/>
                    <li class="list-group-item">
                        <span class="row">
                            <span class="pull-left">
                                <b>${type2.title}</b>
                                <br/>
                                <spring:message code="contest.results.started.caption"/>
                                ${fn:substring(type2.starts,0,16)}
                                (<spring:message code="contest.results.continued.caption"/>
                                ${fn:substring(type2.duration,11,16)})
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
</body>
</html>