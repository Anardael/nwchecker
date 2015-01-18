<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <head>
        <jsp:include page="fragments/staticFiles.jsp" />
    </head>
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="home"/>
            </jsp:include>

            <div class="row">
                <aside class="col-md-3">
                    <ul class="list-group submenu">
                        <li class="list-group-item active"><spring:message code="home.info.caption" /></li>
                        <li class="list-group-item"><a href="/donec/"><spring:message code="home.rules.caption" /></a></li>
                        <li class="list-group-item"><a href="/vestibulum/"><spring:message code="home.contacts.caption" /></a></li>
                        <li class="list-group-item"><a href="/etiam/"><spring:message code="home.archive.caption" /></a></li>
                        <li class="list-group-item"><a href="/phasellus/"><spring:message code="home.forum.caption" /></a></li>
                    </ul>
                </aside>
                <section class="col-md-9">
                    <div class="jumbotron">
                        <blockquote>
                            <p>
                                "The greatest pleasure in life is doing what people say you cannot do"
                            </p>
                            <small>Walter Bagehot</small>
                        </blockquote>
                    </div>
                    <div class="info">
                        <p>
                            Добро пожаловать на сайт проекта "Школа программиста"!
                        </p>
                        <p>
                            Данный проект создан для повышения у школьников Красноярского края уровня программирования и способностей, направленных на решение олимпиадных задач. Надеемся, что наш проект найдет массу заинтересованных в этой области людей, желающих оказать поддержку в развитии олимпиадного и спортивного программирования в Красноярском крае.
                        </p>
                        <p>
                            Сайт содержит архив задач по олимпиадному программированию со встроенной проверяющей системой. Для участия в системе достаточно зарегистрироваться и перейти в раздел "Архив задач", где на текущий момент Вам будет предложено решить 700 задач различной сложности. Сложность задач определяется числом от 1 до 100, из этих значений сложности формируется рейтинг, отражаемый в разделе "Рейтинг".
                        </p>
                    </div>
                </section>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>