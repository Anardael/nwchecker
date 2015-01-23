<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ tag description="TaskModalForm" pageEncoding="UTF-8"%>
<spring:url value="/resources/" var="resources"/> 
<%-- The list of normal or fragment attributes can be specified here: --%>

<%@ attribute name="taskModelName" required="true"%>
<%@ attribute name="taskId" required="true"%>
<%@ attribute name="formUrl" required="true" %>
<spring:url value="${formUrl}" var="processedFormUrl" />

<%-- any content can be specified here e.g.: --%>
<form:form id="taskModalForm_${taskId}" modelAttribute="${taskModelName}" action="${processedFormUrl}" class="form-horizontal">
    <div id="taskModal_${taskId}" class="modal fade "  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                        <%--<div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputData" />:</label>
                            <div class="col-sm-2">
                                <span class="btn btn-default btn-file">
                                    Browse <input type="file" name="inputFile">
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputData" />:</label>
                            <div class="col-sm-2">
                                <span class="btn btn-default btn-file">
                                    Browse <input type="file" name="outputFile">
                                </span>
                            </div>
                        </div>--%>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.close" /></button>
                    <button  type="button" class="btn btn-primary sendTaskJsonButton" data-modalId="${taskId}"><spring:message code="btn.save" /></button>
                </div>
            </div>
        </div>
    </div>
</form:form>