<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/" var="resources"/> 
<%@ tag description="TaskModalFormInput" pageEncoding="UTF-8"%>
<%@ attribute name="element" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="inputClass" %>
<%@ attribute name="inputDivClass" %>

<div class="field ${element}">
    <label class="col-sm-2 control-label">${label}</label>
    <div class="${inputDivClass}">
        <form:input path="${element}" class="form-control ${inputClass}"/>
        <span class="help-inline control-label"></span>
    </div>
</div>
