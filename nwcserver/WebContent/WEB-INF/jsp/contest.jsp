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
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <div class="row">
                <aside class="col-md-3">
                    <ul class="list-group submenu">
                        <li class="list-group-item active"><a href="addContest.do"><spring:message code="contest.createButton.caption" /></a></li>
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
                                <td id="title">${contest.title}</td>
                                <td id="starts">${contest.starts.day}/${1+contest.starts.month}/${1900+contest.starts.year}
                                    ${contest.starts.hours}:${contest.starts.minutes}</td>
                                <td id="duration">${contest.duration}</td>
                                <td id="registered">0</td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>