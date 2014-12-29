<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <jsp:param name="pageName" value="task"/>
            </jsp:include>
            <div class="row">
                <aside class="col-md-3">
                    <ul class="list-group submenu">
                        <li class="list-group-item active"><a href="taskCreating.do"><spring:message code="task.createTask.button" /></a></li>
                    </ul>
                </aside>
                <section>
                    <table id="taskTable">
                        <tr id="firstRow">
                            <td id="title">
                                <spring:message code="task.table.task" />
                            </td>
                            <td id="complexity">
                                <spring:message code="task.table.complexity" />
                            </td>
                            <td id="tried">
                                <spring:message code="task.table.tried" />
                            </td>
                            <td id="success">
                                <spring:message code="task.table.success" />
                            </td>
                        </tr>
                        <c:forEach items="${tasks}" var="task">
                            <tr>
                                <td id="title"><a href="getTaskById.do?id=${task.id}">${task.title}</a></td>
                                <td id="complexity">${task.difficulty}</td>
                                <td id="tried">0</td>
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