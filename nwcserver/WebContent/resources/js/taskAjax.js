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
    var $inputs = $form.find("input, textarea");
    //collect data from input & textarea fields:
    var data = collectFormData($inputs);
    //do Post:
    $.post('/NWCServer/newTaskJson.do', data, function(response) {
        //prepare:
        cleanTaskFieldsFromErrors(index);
        //if responsed error:
        if (response.status == 'FAIL') {
            taskAjaxFail(response, index);
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
        }
    }, 'json');
    return false;
}
function collectFormData(fields) {
    //get all input & textarea fields values:
    var data = {};
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
            data[nameField] = 0;
        } else {
            data[nameField] = $item.val();
        }
    }
    //get current contest Id:
    data["contestId"] = $('#id').val();
    return data;
}