<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="task" required="true" type="com.nwchecker.server.model.Task"%>
<%@ attribute name="contestId" required="true"%>
<%@ attribute name="taskId" required="true"%>

<form:form  id="taskViewForm_${contestId}_${taskId}" modelAttribute="${task}" action="nothing" class="form-horizontal">
    <div id="taskView_${contestId}_${taskId}" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg " style="width: 85%">
            <div class="modal-content ">
                <div class="modal-header modal-header-info">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">${task.title}</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="taskDescription col-sm-12" >
                            ${task.description}
                        </div>
                    </div>
                    <div class="form-group col-sm-12">
                        <spring:message code="taskCreate.memoryLimit" var="memoryLimit"/>
                        <label class="col-sm-2 control-label">${memoryLimit}: </label>
                        <div class="col-sm-2">
                            <c:if test="${task.memoryLimit==0}">
                                <label class="control-label">-</label>
                            </c:if>
                            <c:if test="${task.memoryLimit!=0}">
                                <label class="control-label">${task.memoryLimit}</label>
                            </c:if>
                        </div>
                        <spring:message code="taskCreate.complexity" var="complexity"/>
                        <label class="col-sm-2 control-label">${complexity}: </label>
                        <div class="col-sm-2">
                            <c:if test="${task.complexity==0}">
                                <label class="control-label">-</label>
                            </c:if>
                            <c:if test="${task.complexity!=0}">
                                <label class="control-label">${task.complexity}</label>
                            </c:if>
                        </div>
                        <spring:message code="taskCreate.inputFileName" var="inputFileName"/>
                        <label class="col-sm-2 control-label">${inputFileName}: </label>
                        <div class="col-sm-2">
                            <label class="control-label">${task.inputFileName}</label>
                        </div>
                    </div>
                    <div class="form-group col-sm-12">
                        <spring:message code="taskCreate.timeLimit" var="timeLimit"/>
                        <label class="col-sm-2 control-label">${timeLimit}: </label>
                        <div class="col-sm-2">
                            <c:if test="${task.timeLimit==0}">
                                <label class="control-label">-</label>
                            </c:if>
                            <c:if test="${task.timeLimit!=0}">
                                <label class="control-label">${task.timeLimit}</label>
                            </c:if>
                        </div>
                        <spring:message code="taskCreate.rate" var="rate"/>
                        <label class="col-sm-2 control-label">${rate}: </label>
                        <div class="col-sm-2">
                            <c:if test="${task.rate==0}">
                                <label class="control-label">-</label>
                            </c:if>
                            <c:if test="${task.rate!=0}">
                                <label class="control-label">${task.rate}</label>
                            </c:if>
                        </div>
                        <spring:message code="taskCreate.outputFileName" var="outputFileName"/>
                        <label class="col-sm-2 control-label">${outputFileName}: </label>
                        <div class="col-sm-2">
                            <label class="control-label">${task.outputFileName}</label>
                        </div>
                    </div>
                    <div class="row">
                    </div>
                </div>
                <div class="modal-footer ">
                    <div class="buttons col-sm-12 ">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.close" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
