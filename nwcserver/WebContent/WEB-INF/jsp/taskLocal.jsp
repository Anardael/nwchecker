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
                <jsp:param name="pageName" value="taskLocal"/>
            </jsp:include>
            <section>
                <div id="taskLocal">
                    <h1>${task.title}</h1>
                    <h4>Обмеження: (Час: ${task.timeLimit}с. Пам'ять: ${task.memoryLimit}кб.)</h4>
                    <h4>Складність: ${task.difficulty}% Оцінюється: ${task.maxMark}б.</h4>
                    <p>${task.description}<p>

                        <!-- if forum link consist-->
                        <c:if test="${not empty task.forumLink}">
                            <a href="${task.forumLink}">Посилання теми на форумі</a>
                        </c:if>
                        <!-- if theory links consist-->
                        <c:if test="${not empty task.theoryLinks}">
                        <div id="theoryLinks">
                            <p>Посилання на інформаційні матеріали:</p>
                            <c:forEach items="${task.theoryLinks}" var="link">
                                <c:choose>
                                    <c:when test="${not empty link.name}">
                                        <p><a href="${link.link}">${link.name}</a></p>
                                        </c:when>
                                        <c:otherwise>
                                        <p><a href="${link.link}">Посилання</a></p>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                        </div>
                    </c:if>
                </div>
            </section>
        </div>
    </body>
</html>
