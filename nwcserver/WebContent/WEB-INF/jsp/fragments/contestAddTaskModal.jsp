<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="taskModal_${param.index}" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 85%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Task editing</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.title" />:*</label>
                    <div class="col-sm-10">
                        <form:input path="tasks[${param.index}].title" class="roman form-control"/>
                        <form:errors path="tasks[${param.index}].title" Class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.memoryLimit" />:</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].memoryLimit" class="roman form-control" />
                        <form:errors path="tasks[${param.index}].memoryLimit" Class="error"/>
                    </div>
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.rate" />:</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].rate" class="form-control"/>
                        <form:errors path="tasks[${param.index}].rate" Class="error"/>
                    </div>
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputFileName" />:*</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].inputFileName" class="form-control"/>
                        <form:errors path="tasks[${param.index}].inputFileName" Class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.timeLimit" />:</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].timeLimit" class="form-control" />
                        <form:errors path="tasks[${param.index}].timeLimit" Class="error"/>
                    </div>
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.complexity" />:</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].complexity" class="form-control"/>
                        <form:errors path="tasks[${param.index}].complexity" Class="error"/>
                    </div>
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputFileName" />:*</label>
                    <div class="col-sm-2">
                        <form:input path="tasks[${param.index}].outputFileName" class="form-control"/>
                        <form:errors path="tasks[${param.index}].outputFileName" Class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.description" />:*</label>
                    <div class="col-sm-10">
                        <form:textarea style="resize:none" path="tasks[${param.index}].description" class="form-control" rows="7"/>
                        <form:errors path="tasks[${param.index}].description" Class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.verificationScript" />:</label>
                    <div class="col-sm-10">
                        <form:textarea path="tasks[${param.index}].scriptForVerification" style="resize:none" class="form-control" rows="7"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputData" />:</label>
                    <div class="col-sm-2">
                        <input type="file" name="inputFile">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputData" />:</label>
                    <div class="col-sm-2">
                        <input type="file" name="outputFile">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="taskCreate.forumLink" />:</label>
                    <div class="col-sm-4">
                        <input class="form-control" name="forumLink">
                    </div>
                </div>
                <div class="theoryAdd">
                    <!-- Task have theory links already??? -->
                    <!-- if not null- go throw all links in task:-->
                    <c:forEach items="${contestAddForm.tasks[param.index].theoryLinks}" var="theory" varStatus="gridRow">
                        <div class="controls form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.addTheoryLink" />:</label>
                            <div class="entry input-group col-sm-4" style="padding-left: 15px">
                                <form:input path="tasks[${param.index}].theoryLinks[${gridRow.index}].link" class="form-control" autocomplete="off"/>
                                <span class="input-group-btn">
                                    <c:choose>
                                        <c:when test="${fn:length(contestAddForm.tasks[param.index].theoryLinks)==gridRow.index+1}">
                                            <button class="btn btn-success btn-add" type="button">
                                                <span class="glyphicon glyphicon-plus"></span>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-remove btn-danger" type="button">
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteModal(${param.index});"><spring:message code="contestCreate.modal.remove" /></button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="contestCreate.modal.save" /></button>
            </div>
        </div>
    </div>
</div>