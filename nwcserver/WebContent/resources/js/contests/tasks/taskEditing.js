function tryToAddTask(locale) {
    if ($('#id').val() == '0') {
        sendJsonContest().success(function (data) {
            if (data.status == "FAIL") {
                contestAjaxFailed(errorCaption, emptyContest, data);
            }
            if (data.status == "SUCCESS") {
                $('#id').val(data.result);
                createNewTask(locale);
                contestLongPolling();
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
    $.get("newTaskForm.do?taskId=" + TaskListSize + "&contestId=" + $('#id').val(), 0, function (newMod) {
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
        $('#taskModal_' + TaskListSize + ' .ckEdit').each(function () {
            initializeCKEdior($(this).attr('id'), locale);
        });

        //set digits only:
        $('#taskModal_' + TaskListSize + ' .digits').keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                return false;
            }
        });
        //set ladda:
        Ladda.bind('.ladda-button');
        //set upload buttons:
        $('#taskModal_' + TaskListSize + ' .newTest:not([hidden=true])').filestyle({
            input: false,
            buttonText: uploadTestFileButton
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
            action: function (dialogItself) {
                dialogItself.close();
            }
        }, {
            label: btnSubmit,
            cssClass: 'btn-primary',
            action: function (dialogItself) {
                dialogItself.close();
                sendAjaxDeleteTask(index, taskActualId);
            }
        }
        ]
    });
}
function sendAjaxDeleteTask(view, index) {
    var contId = $('#id').val();
    $.get('deleteTaskJson.do?taskId=' + index + "&contestId=" + contId, 0, function (response) {
        if (response.status == "SUCCESS") {
            deleteTask(view);
            BootstrapDialog.show({
                title: taskDeleteHeader,
                type: BootstrapDialog.TYPE_SUCCESS,
                message: taskDeleteSuccess,
            });

        }
        if (response.status == "FAIL") {
            if (response.errorMessageList[0].fieldName == "denied") {
                showAccessDeniedModal();
            }
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
            form.find('input').each(function () {
                this.id = "tasks" + iDecremented + this.id.substring(this.id.indexOf("."));
                this.name = "tasks[" + iDecremented + "]" + this.name.substring(this.name.indexOf("."));
            });
            //change all textarea elements id & name:
            form.find('textarea').each(function () {
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
function addNewTestCouple(taskId) {
    //get pattern clone:
    var clone = $('#test_0_pattern').clone();
    //get last test files couple:
    var last = $('#taskModalForm_' + taskId + ' .tests .test').last();
    //get last index:
    var lastId = last.attr('id');
    var newId;
    if (lastId.indexOf('pattern') != -1) {
        newId = 0;
    } else {
        lastId = lastId.substring(lastId.indexOf('_') + 1, lastId.length);
        lastId = lastId.substring(lastId.indexOf('_') + 1, lastId.length);
        newId = parseInt(lastId);
        //set new Id:
        newId++;
    }
    //change fields:
    clone.attr('id', 'test_' + taskId + '_' + newId);
    clone.removeAttr('hidden');
    clone.find('[id^=input]').attr('id', 'input_' + taskId + '_' + newId);
    clone.find('[for^=input]').attr('for', 'input_' + taskId + '_' + newId);
    clone.find('[id^=output]').attr('id', 'output_' + taskId + '_' + newId);
    clone.find('[for^=output]').attr('for', 'output_' + taskId + '_' + newId);
    //set label:
    clone.find('.testTitle').html(newTest + ':');
    //append to Tests:
    clone.appendTo('#taskModalForm_' + taskId + ' .tests');
    //set buttons js:
    $('#test_' + taskId + '_' + newId + ' :file').filestyle({
        input: false,
        icon: false,
        buttonName: "btn-primary",
        buttonText: uploadTestFileButton
    });
}
function replaceTaskTestFiles(contestId, taskId, localTaskId) {
//get current tst files from server:
    $.ajax({
        url: 'getAvaibleTests.do?contestId=' + contestId + "&taskId=" + taskId + "&localTaskId=" + localTaskId,
        processData: false,
        contentType: false,
        type: 'GET',
        success: function (response) {
            $('#taskModal_' + localTaskId + ' .tests').replaceWith(response);
            //set filestyle:
            $('.newTest:not([hidden]) :file').filestyle({
                input: false,
                icon: false,
                buttonName: "btn-primary",
                buttonText: uploadTestFileButton
            });
        }
    });
}

function deleteNewTest(button) {
    var parent = button.parentNode.parentNode;
    document.getElementById(parent.id).remove();
}

function deleteAvaibleTest(button, testCurrentId) {
    //first of all: ajax to server:
    var contestId = $('#id').val();
    $.ajax({
        url: 'deleteTaskTestFile.do?contestId=' + contestId + "&taskTestId=" + testCurrentId,
        processData: false,
        contentType: false,
        type: 'GET',
        success: function (response) {
            if (response.status == "SUCCESS") {
                BootstrapDialog.show({
                    title: testDeletedHeader,
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: testDeletedBody,
                });
                //now remove div:
                var parent = button.parentNode.parentNode;
                document.getElementById(parent.id).remove();
            }
            if (response.status == "FAIL") {
                if (response.errorMessageList[0].fieldName == "denied") {
                    showAccessDeniedModal();
                }
            }

        }
    });

}

function prepateDeleteAvaibleTest(button, testCurrentId) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_WARNING,
        title: testDeletedHeader,
        message: testDeleteQuestion,
        buttons: [{
            label: btnClose,
            action: function (dialogItself) {
                dialogItself.close();
            }
        }, {
            label: btnSubmit,
            cssClass: 'btn-primary',
            action: function (dialogItself) {
                dialogItself.close();
                deleteAvaibleTest(button, testCurrentId);
            }
        }
        ]
    });
}