<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ tag description="DownloadTestFiles" pageEncoding="UTF-8"%>
<%@ attribute name="taskId" required="true"%>
<%@ attribute name="row" required="true"%>
<%@ attribute name="contestId" required="true"%>
<%@ attribute name="testId" required="true"%>
<%@ attribute name="hidden" %>
<spring:url value="/resources/" var="resources"/> 
<div id="test_${taskId}_${row}" class="form-group field test" ${hidden}>
    <label class="testTitle control-label col-sm-2 col-sm-offset-2">
        <spring:message code="taskCreate.avaibleTest"/>:
    </label>
    <div class="col-sm-2">
        <button type="button" class="btn btn-default" style="width:120px;" onclick="window.open('getTaskTestData.do?contestId=${contestId}&testId=${testId}&type=in')">
            <spring:message code="taskCreate.downloadFileButton"/>
        </button>
    </div>
    <div class="col-sm-2 col-sm-offset-2">
        <button type="button" class="btn btn-default" style="width:120px;" onclick="window.open('getTaskTestData.do?contestId=${contestId}&testId=${testId}&type=out')">
            <spring:message code="taskCreate.downloadFileButton"/>
        </button>
    </div>
    <div >
        <button type="button" class="btn btn-danger btn-xs" style="margin-top: 5px" onclick="prepateDeleteAvaibleTest(this,${testId})">
            <spring:message code="btn.delete"/>
        </button>
    </div>
</div>