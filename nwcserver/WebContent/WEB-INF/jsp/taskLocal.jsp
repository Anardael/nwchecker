<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <style>
        #taskLocal{
            width:600px;
            margin:auto;
            text-align: justify;
        }
        h1{
            text-align: center;
        }
        h4{
            text-align: center;
        }
        #theoryLinks p{
            margin:2px 0px;
        }

    </style>
    <!--including head -->
    <jsp:include page="fragments/staticFiles.jsp" />
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="task"/>
            </jsp:include>
            <section>
                <div id="taskLocal">
                    <h1>${task.title}</h1>
                    <h4>
                        <c:if test="${task.timeLimit!=0 or task.memoryLimit!=0}">
                            Обмеження: 
                        </c:if>
                        <c:if test="${task.timeLimit!=0}">
                            Час: ${task.timeLimit}с.
                        </c:if>
                        <c:if test="${task.memoryLimit!=0}">
                            Пам'ять: ${task.memoryLimit}кб.
                        </c:if>
                    </h4>
                    <h4>
                        <c:if test="${task.complexity!=0}">
                            Складність: ${task.complexity}% 
                        </c:if>
                        Оцінюється: ${task.rate}б.
                    </h4>
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
                                <p><a href="${link.link}">Посилання</a></p>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </section>
        </div>
    </body>
</html>
