<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <jsp:include page="fragments/staticFiles.jsp" />
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="olympiad"/>
            </jsp:include>
            <div class="row">
                <aside class="col-md-3">
                    <ul class="list-group submenu">
                        <li class="list-group-item active"><a href="contestCreating.do"><spring:message code="contest.createButton.caption" /></a></li>
                    </ul>
                </aside>
                <section>
                    <table id="taskTable">
                        <tr id="firstRow">
                            <td id="title">
                                <spring:message code="contest.table.title" />
                            </td>
                            <td id="starts">
                                <spring:message code="contest.table.starts" />
                            </td>
                            <td id="duration">
                                <spring:message code="contest.table.duration" />
                            </td>
                            <td id="registered">
                                <spring:message code="contest.table.registered" />
                            </td>
                        </tr>
                        <c:forEach items="${contests}" var="contest">
                            <tr>
                                <td id="title"><a href="getContestByID.do?id=${contest.id}">${contest.title}</a></td>
                                <td id="complexity">${contest.starts}</td>
                                <td id="tried">${contest.duration}</td>
                                <td id="success">0</td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>