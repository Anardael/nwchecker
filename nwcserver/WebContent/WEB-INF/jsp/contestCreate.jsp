<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- set path to resources folder -->
                    <spring:url value="/resources/" var="resources"/> <html>
    <style>
        /*make red style in error forms*/
        .error{
            color: #ff0000;
        }
    </style>
            <!--including head -->
            <jsp:include page="fragments/staticFiles.jsp" />
    <body>
            <script type="text/javascript">
                    var TaskListSize = ${fn:length(contestAddForm.tasks)}; function showModal(index) {                         $('#taskModal_' + index).modal('toggle'); }
                    function addNewTask() {
            //get Modal pattern example:
                    var patternModal = $("#patternMod"); ////crete new Modal clone:
                    var newCloneModal = patternModal.clone();
                    //set unique id for Modal:
                    newCloneModal.attr("id", "taskModal_" + TaskListSize);
                    //paste new Modal clone to document:
                    $('.taskList').append(newCloneModal);
                    ////set disable=false for input & set name accordingly to Id:                         
                    $('#taskModal_' + TaskListSize).find('input').each(function() {
            this.name = "tasks[" + TaskListSize + this.name.substring(7);
                    this.disabled = false;
            });
                    //for all textareas set disabled=false:
                    $('#taskModal_' + TaskListSize).find('textarea').each(function() {
            this.name = "tasks[" + TaskListSize + this.name.substring(7);
                    this.disabled = false;
            });
                    //add taskModalButton:
                    var taskModalButton = document.createElement('div');
                    taskModalButton.id = 'taskModalButton_' + TaskListSize;
                    taskModalButton.className = 'col-sm-6 taskModalButton form-group';
                    taskModalButton.innerHTML =
                    '<!-- view title-->\n\
                        <a href="#" onclick="showModal(' + TaskListSize + ');">Edit</a>\n\
                            ' + TaskListSize + '. New Task';
                $('.taskModalButtons').append(taskModalButton);

                //increment TaskListSize:
                TaskListSize++;
            }
            function deleteModal(index){
                $('#taskModal_' + index).find('input').each(function() {
                    this.disabled = true;
                });
                $('#taskModal_' + index).find('textarea').each(function() {
                    this.disabled = true;
                });
                //delete buttons view for modal:
                $('#taskModalButton_'+index).remove();
            }
        </script>

        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <section>
                <form:form modelAttribute="contestAddForm" class="form-horizontal" 
                           action="addContest.do" method="post" role="form">
                    <form:hidden  path="id" />
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.title" />: *</label>
                        <div class="col-sm-10">
                            <form:input path="title" class="form-control"/>
                            <form:errors path="title" Class="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.description" />: *</label>
                        <div class="col-sm-10">
                            <form:textarea style="resize:none" path="description" class="form-control" rows="7"></form:textarea>
                            <form:errors path="description" Class="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.starts" />:</label>
                        <div class="col-sm-2">
                            <form:input path="starts" class="form-control" name="starts"/>
                            <form:errors path="starts" Class="error"/>
                        </div>
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.duration" />:</label>
                        <div class="col-sm-2">
                            <form:input path="duration" class="form-control"/>
                            <form:errors path="duration" Class="error"/>
                        </div>
                    </div>
                    <div class="taskList" >
                        <!-- patternMod using for creating new Tasks-->
                        <div id="patternMod" class="taskModal modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg" style="width: 80%">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Task editing</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.title" />:*</label>
                                            <div class="col-sm-10">
                                                <input name="tasks[0].title" class="roman form-control" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.memoryLimit" />:</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].memoryLimit" class="roman form-control" value="0" disabled/>
                                            </div>
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.rate" />:*</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].rate" class="form-control" value="0" disabled/>
                                            </div>
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputFileName" />:*</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].inputFileName" class="form-control" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.timeLimit" />:</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].timeLimit" class="form-control" value="0" disabled/>
                                            </div>
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.complexity" />:</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].complexity" class="form-control" value="0" disabled/>
                                            </div>
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputFileName" />:*</label>
                                            <div class="col-sm-2">
                                                <input name="tasks[0].outputFileName" class="form-control" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.description" />:*</label>
                                            <div class="col-sm-10">
                                                <textarea style="resize:none" name="tasks[0].description" class="form-control" rows="7" disabled></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.verificationScript" />:</label>
                                            <div class="col-sm-10">
                                                <textarea name="tasks[0].scriptForVerification" style="resize:none" class="form-control" rows="7" disabled></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputData" />:</label>
                                            <div class="col-sm-2">
                                                <input type="file" name="inputFile" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputData" />:</label>
                                            <div class="col-sm-2">
                                                <input type="file" name="outputFile" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.forumLink" />:</label>
                                            <div class="col-sm-4">
                                                <input class="form-control" name="forumLink" disabled/>
                                            </div>
                                        </div>
                                        <div class="theoryAdd">
                                            <!-- Task have theory links already??? -->
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteModal(1);">Remove</button>
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- go throw all avaible tasks in contest:-->
                        <!-- include taskModal; send argument with ID-->
                        <c:forEach items="${contestAddForm.tasks}" var="task"
                                   varStatus="gridRow">
                            <jsp:include page="fragments/contestAddTaskModal.jsp">
                                <jsp:param name="index" value="${gridRow.index}"/>
                            </jsp:include>
                        </c:forEach>
                    </div>

                    <div class="form-group" >
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.tasks.caption" />:</label>
                        <!-- go throw all avaible tasks in contest:-->
                        <div class="taskModalButtons col-sm-10">
                            <c:forEach items="${contestAddForm.tasks}" var="task"
                                       varStatus="gridRow">
                                <div id="taskModalButton_${gridRow.index}" class="col-sm-6 taskModalButton form-group">
                                    <a href="#" onclick="showModal(${gridRow.index});">Edit</a>
                                    ${gridRow.index}. ${task.title} 
                                    <form:errors path="tasks[${gridRow.index}]" Class="error"/>
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <!-- add task script -->
                                <a href="#" onclick="addNewTask();"><spring:message code="contestCreate.addNewTask" /></a>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-default" value="Submit"><spring:message code="contestCreate.submitButton.caption" /></button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>