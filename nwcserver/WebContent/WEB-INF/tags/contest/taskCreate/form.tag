<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ tag description="TaskModalForm" pageEncoding="UTF-8"%>
<spring:url value="/resources/" var="resources"/> 
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="taskModelName" required="true" %>
<%@ attribute name="taskId" required="true"%>
<%@ attribute name="formUrl" required="true" %>
<%@ attribute name="contestId" required="true" %>
<spring:url value="${formUrl}" var="processedFormUrl" />

<%-- any content can be specified here e.g.: --%>
<form:form id="taskModalForm_${taskId}" modelAttribute="${taskModelName}" action="${processedFormUrl}" class="form-horizontal" enctype="multipart/form-data" acceptcharset="UTF-8">
    <div id="taskModal_${taskId}" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg " style="width: 85%">
            <div class="modal-content ">
                <div class="modal-header modal-header-info">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="taskCreate.header"/></h4>
                </div>
                <div  class="modal-body">
                    <fieldset>
                        <div class='idTask'>
                            <form:hidden path="id" />
                        </div>
                        <div class="form-group">
                            <spring:message code="taskCreate.title" var="titleLabel"/>
                            <contest:taskModalFormInput element="title" label="${titleLabel}:*" 
                                                        inputDivClass="col-sm-10"/>
                        </div>
                        <div class="form-group">
                            <spring:message code="taskCreate.memoryLimit" var="memoryLimit"/>
                            <contest:taskModalFormInput element="memoryLimit" label="${memoryLimit}:" 
                                                        inputDivClass="col-sm-2" inputClass="digits"/>

                            <spring:message code="taskCreate.rate" var="rate"/>
                            <contest:taskModalFormInput element="rate" label="${rate}:" 
                                                        inputDivClass="col-sm-2" inputClass="digits"/>

                            <spring:message code="taskCreate.inputFileName" var="inputFileName"/>
                            <contest:taskModalFormInput element="inputFileName" label="${inputFileName}:*" 
                                                        inputDivClass="col-sm-2"/>
                        </div>
                        <div class="form-group">
                            <spring:message code="taskCreate.timeLimit" var="timeLimit"/>
                            <contest:taskModalFormInput element="timeLimit" label="${timeLimit}:" 
                                                        inputDivClass="col-sm-2" inputClass="digits"/>

                            <spring:message code="taskCreate.complexity" var="complexity"/>
                            <contest:taskModalFormInput element="complexity" label="${complexity}:" 
                                                        inputDivClass="col-sm-2" inputClass="digits"/>

                            <spring:message code="taskCreate.outputFileName" var="outputFileName"/>
                            <contest:taskModalFormInput element="outputFileName" label="${outputFileName}:*" 
                                                        inputDivClass="col-sm-2"/>
                        </div>
                        <div class="field form-group description">
                            <spring:message code="taskCreate.description" var="description"/>
                            <label class="col-sm-2 control-label">${description}:*</label>
                            <div class="col-sm-10">
                                <form:textarea path="description" style="resize:none" 
                                               class="form-control ckEdit" rows="7"/>
                                <span class="help-inline control-label"></span>
                            </div>
                        </div>
                        <div class="form-group scriptForVerification">
                            <spring:message code="taskCreate.verificationScript" var="verificationScript"/>
                            <label class="col-sm-2 control-label">${verificationScript}:</label>
                            <div class="col-sm-10">
                                <form:textarea path="scriptForVerification" style="resize:none" class="form-control" rows="7"/>
                                <span class="help-inline control-label"></span>
                            </div>
                        </div>
                        <div class="form-group fileHeaders">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.verificationData"/>:</label>
                            <div class="col-sm-4 col-sm-offset-2">
                                <label class="control-label">
                                    <spring:message code="taskCreate.inputData" />
                                </label>
                            </div>
                            <div class="col-sm-4">
                                <label class="control-label">
                                    <spring:message code="taskCreate.outputData" />
                                </label>
                            </div>
                        </div>
                        <div class="tests" avaible="${fn:length(contestModelForm.tasks[taskId].inOutData)}">
                            <%-- pattern for future files: --%>
                            <contest:taskTestFiles taskId="${taskId}" row="pattern" hidden="hidden=\"true\""/>
                            <%-- go through all current taskData in task --%>
                            <c:forEach items="${contestModelForm.tasks[taskId].inOutData}" var="data" varStatus="row">
                                <contest:downloadTaskTestFiles taskId="${taskId}" row="${row.index}" contestId="${contestId}" testId="${data.id}"/>
                            </c:forEach>
                        </div>
                        <div class="col-sm-offset-2"  style="text-align: center; margin-bottom: 20px; ">
                            <button type="button" class="btn btn-primary" style="margin-right: 30px" onclick="addNewTestCouple(${taskId})" ><spring:message code="task.tests.addButton"/></button>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.close" /></button>
                    <button id="taskSubmitButton" type="button" class="btn btn-primary sendTaskJsonButton ladda-button" data-style="expand-right" data-modalId="${taskId}"><span class="ladda-label"><spring:message code="btn.save" /></span></button>
                </div>
            </div>
        </div>
    </div>
</form:form>