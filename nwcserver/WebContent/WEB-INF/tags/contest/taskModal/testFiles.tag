<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ tag description="TestFiles" pageEncoding="UTF-8"%>
<%@ attribute name="taskId" required="true"%>
<%@ attribute name="row" required="true"%>

<%@ attribute name="inputClass" %>
<%@ attribute name="hidden" %>
<div id="test_${taskId}_${row}" class="newTest form-group field test" ${hidden}>
    <label class="testTitle control-label col-sm-2 col-sm-offset-2">
        <spring:message code="taskCreate.newTest"/>:
    </label>
    <div class="col-sm-2">
        <input id="input_${taskId}_${row}" type="file" class="${inputClass}" 
               name="inputFile" data-input="false" data-icon="false" data-buttonName="btn-primary" 
               data-buttonText="<spring:message code="taskCreate.uploadFileButton"/>">
    </div>
    <div class="col-sm-2 col-sm-offset-2">
        <input id="output_${taskId}_${row}" type="file" class="${inputClass}" 
               name="outputFile" data-input="false" data-icon="false" data-buttonName="btn-primary" 
               data-buttonText="<spring:message code="taskCreate.uploadFileButton"/>">
    </div>
    <div >
        <button type="button" class="btn btn-danger btn-xs" style="margin-top: 5px" onclick="deleteNewTest(this)">
            <spring:message code="btn.delete"/>
        </button>
    </div>
    <div class="col-sm-4 col-sm-offset-4">
        <span class="help-inline control-label"></span>
    </div>
</div>