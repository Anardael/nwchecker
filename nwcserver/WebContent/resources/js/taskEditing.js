function tryToAddTask(locale) {
    if ($('#id').val() == '0') {
        sendJsonContest().success(function(data) {
            if (data.status == "FAIL") {
                contestAjaxFailed(errorCaption, emptyContest, data);
            }
            if (data.status == "SUCCESS") {
                $('#id').val(data.result);
                createNewTask(locale);
            }
        });
    } else {
        createNewTask(locale);
    }
}

function createNewTask(locale) {
    //get taskModalForm pattern:
    //get parameter "taskId" indicates index of current "newTask".
    //TaskListSize is declared in contestCreate.jsp
    $.get("/NWCServer/newTaskForm.do?taskId=" + TaskListSize, 0, function(newMod) {
        //append TaskModalForm.html in taskList div:
        $('.taskList').append(newMod);
        //set in/out:
        $('#taskModalForm_' + TaskListSize).find('.inputFileName input').val("in");
        $('#taskModalForm_' + TaskListSize).find('.outputFileName input').val("out");
        //create editingDiv for new Modal:
        //get view pattern:
        var pattern = $('#liTaskViewPattern a').clone();
        //set unique ID:
        pattern.attr("id", "taskView_" + TaskListSize);
        //set title:
        pattern.find('.viewTitle').html('New Task');
        //set buttons:
        pattern.find('.btnEdit').attr("data-target", "#taskModal_" + TaskListSize);
        pattern.find('.buttonDeleteTask').attr("data-taskId", TaskListSize);

        $('.tasksControls ul').append(pattern);

        //open modal:
        $('#taskModal_' + TaskListSize).modal();

        //set CK:
        $('#taskModal_' + TaskListSize + ' .ckEdit').each(function() {
        	initializeCKEdior($(this).attr('id'), locale);
        });

        //set digits only:
        $('#taskModal_' + TaskListSize + ' .digits').keypress(function(e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                return false;
            }
        });
        //increment TaskListSize:
        TaskListSize++;
    });
}

function prepareDeleteTask(index) {
    var taskActualId;
    //get actual index of Task from hidden input:
    taskActualId = $('#taskModal_' + index + ' .idTask input').val();
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_WARNING,
        title: taskDeleteHeader,
        message: taskDeleteQuestion,
        buttons: [{
                label: btnClose,
                action: function(dialogItself) {
                    dialogItself.close();
                }
            }, {
                label: btnSubmit,
                cssClass: 'btn-primary',
                action: function(dialogItself) {
                    deleteTask(index);
                    dialogItself.close();
                    sendAjaxDeleteTask(taskActualId);
                }
            }
        ]
    });
}
function sendAjaxDeleteTask(index) {
    $.get('/NWCServer/deleteTaskJson.do?taskId=' + index, 0, function(response) {
        if (response.status == "SUCCESS") {
            BootstrapDialog.show({
                title: taskDeleteHeader,
                type: BootstrapDialog.TYPE_SUCCESS,
                message: taskDeleteSuccess,
            });
        }
    });
}
function deleteTask(index) {
    //delte taskModal:
    $('#taskModalForm_' + index).remove();
    //delete buttons view for modal:
    $('#taskView_' + index).remove();
    //if user deleted not last Task:
    //move all tasks indexes:
    var i = index + 1;
    if (i != TaskListSize) {
        for (i; i < TaskListSize; i++) {
            var iDecremented = i - 1;
            //find form:
            var form = $('#taskModalForm_' + i);
            //change id:
            form.attr('id', 'taskModalForm_' + iDecremented);
            //change div modal id:
            form.find('#taskModal_' + i).attr('id', 'taskModal_' + iDecremented);
            //change all input elements id and name:
            form.find('input').each(function() {
                this.id = "tasks" + iDecremented + this.id.substring(this.id.indexOf("."));
                this.name = "tasks[" + iDecremented + "]" + this.name.substring(this.name.indexOf("."));
            });
            //change all textarea elements id & name:
            form.find('textarea').each(function() {
                this.id = "tasks" + iDecremented + this.id.substring(this.id.indexOf("."));
                this.name = "tasks[" + iDecremented + "]" + this.name.substring(this.name.indexOf("."));
            });
            //change button data-modalId argument:
            form.find('.sendTaskJsonButton').attr('data-modalId', iDecremented);

            //change taskView ids:
            var modalButtons = $('#taskView_' + i);
            //change id:
            modalButtons.attr('id', 'taskView_' + iDecremented);
            //change title:
            //change edit button:
            modalButtons.find('.btnEdit').attr('data-target', '#taskModal_' + iDecremented);
            //change delete button:
            modalButtons.find('.buttonDeleteTask').attr('data-taskId', iDecremented);
        }
    }
    TaskListSize--;
}