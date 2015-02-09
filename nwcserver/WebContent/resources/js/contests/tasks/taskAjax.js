function preparePostTaskJson(taskId) {
    cleanTaskFieldsFromErrors(taskId);
    var avaibleTests = $('#taskModalForm_' + taskId + ' .tests').attr("avaible");
    //check files data:
    var errrosIndex = {};
    var avaibleTestsInt = parseInt(avaibleTests);
    var index = 0;
    $('#taskModalForm_' + taskId + ' .newTest:not([hidden=true])').each(function() {
        var inLength = $(this).find("input[name^='in']").val().length;
        var outLength = $(this).find("input[name^='out']").val().length;
        if ((inLength != 0 && outLength == 0) || (inLength == 0 && outLength != 0)) {
            errrosIndex[index] = avaibleTestsInt;
            index++;
        }
        avaibleTestsInt++;
    });
    avaibleTestsInt = parseInt(avaibleTests);
    if (index == 0) {
        //if files correctly uploaded:
        doPostJson(taskId);
    } else {
        //set error messages:
        var head = $('#taskModal_' + taskId).find(".modal-header");
        head.removeClass("modal-header-info");
        head.addClass("modal-header-danger");
        for (var i = 0; i < index; i++) {
            var $controlGroup = $('#test_' + taskId + '_' + errrosIndex[i]);
            $controlGroup.addClass('has-error');
            $controlGroup.find('.help-inline').html("Each input file should have appropriate output file.");
        }
    }
}

function cleanTaskFieldsFromErrors(index) {
    //change header:
    var head = $('#taskModal_' + index).find(".modal-header");
    head.removeClass("modal-header-danger");
    head.addClass("modal-header-info");
    //delete all error messages:
    //form:
    var $form = $('#taskModal_' + index);
    $form.find('.field').removeClass('has-error');
    $form.find('.help-inline').empty();
}
function taskAjaxFail(response, index) {
    //set modal header error:
    var head = $('#taskModal_' + index).find(".modal-header");
    head.removeClass("modal-header-info");
    head.addClass("modal-header-danger");
    //for each field:
    for (var i = 0; i < response.errorMessageList.length; i++) {
        //get Error error items:
        var item = response.errorMessageList[i];
        var $controlGroup = $('#taskModal_' + index + ' .' + item.fieldName);
        $controlGroup.addClass('has-error');
        $controlGroup.find('.help-inline').html(item.message);
    }
}
function doPostJson(index) {
    //update CKEditors data:
    updateCKEditors();
    //get taskModalForm accordingly to Id:
    var $form = $('#taskModal_' + index);
    //find all input & textarea elements:
    var $inputs = $form.find("input[type='hidden'],input[type='text'], textarea");
    //collect data from input & textarea fields:
    var data = collectFormData(index, $inputs);
    //collect data from TestFiles:
    //data = collectTestData(data);
    //do Post:
    $.ajax({
        url: 'newTaskJson.do',
        data: data,
        dataType: 'JSON',
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(response) {
            Ladda.stopAll();
            //prepare:
            cleanTaskFieldsFromErrors(index);
            if (response.status == 'FAIL') {
                var va=0;
                if (response.errorMessageList[0].fieldName == "denied") {
                    va=1;
                }    
                if (response.errorMessageList[0].fieldName == "uploadSize") {
                    va=2;
                }
                if(va==1){
                    showAccessDeniedModal();
                }
                if(va==2){
                     BootstrapDialog.show({
                        title: errorLabel,
                        type: BootstrapDialog.TYPE_DANGER,
                        message: uploadSize,
                    });
                }
                if(va==0){
                    taskAjaxFail(response, index);
                    
                }
            } else {
                //hide modal:
                $('#taskModal_' + index).modal('hide');
                //show success message:
                BootstrapDialog.show({
                    title: taskEditResultHeader,
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: taskEditResultSuccess
                });
                $form.unbind('submit');
                $form.submit();
                //change title in buttons:
                $('#taskView_' + index + ' .viewTitle').html($('#taskModal_' + index + ' .title input').val());
                //set input new inputId:
                $form.find('.idTask input').val(response.result);
                //get current task test view:
                //get contestId:
                var contestId = $('#id').val();
                var taskId = $('#taskModal_' + index + ' .idTask :input').val();
                var localTaskId = index;
                replaceTaskTestFiles(contestId, taskId, localTaskId);
            }
        }
    });
    return false;
}
function collectFormData(taskId, fields) {
    //get all input & textarea fields values:
    var data = new FormData();
    for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        var nameField = $item.attr('name');
        //if contains String indicates Parent model:
        //preapare Json (delete all trash):
        if (nameField.indexOf(".") > -1) {
            //deleting " tasks[i]."
            nameField = nameField.substring(nameField.indexOf(".") + 1);
        }
        if ($item.hasClass('digits') && $item.val() == "") {
            data.append(nameField, 0);
        } else {
            data.append(nameField, $item.val());
        }
    }
    //get current contest Id:
    data.append("contestId", $('#id').val());
    //collect data files:
    var avaibleTests = $('#taskModalForm_' + taskId + ' .tests').attr("avaible");
    var avaibleTestsInt = parseInt(avaibleTests);
    var testFilesCount = $('#taskModalForm_' + taskId + ' .newTest:not([hidden=true])').size();
    for (var i = 0; i < avaibleTestsInt + testFilesCount; i++) {
        //check if exist:
        var asd=$('#test_' + taskId + '_' + i).length;
        if (asd==0){
            testFilesCount++;
        }
        //check if path != null:
        //check if contains input:
        var inp= $('#test_' + taskId + '_' + i +' input').length;
        if (asd==true && inp!=0 && $('#input_' + taskId + '_' + i).val() != "" && $('#output_' + taskId + '_' + i).val() != "") {
            data.append('input_' + taskId + '_' + i, $('#input_' + taskId + '_' + i).prop('files')[0]);
            data.append('output_' + taskId + '_' + i, $('#output_' + taskId + '_' + i).prop('files')[0]);
        }
        if (i==50){
            break;
        }
    }
    return data;
}