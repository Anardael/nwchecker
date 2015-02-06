function sendJsonContest() {
    //update CKEditors content for accessability of textarea data:
    updateCKEditors();
    //delete errors messages and classes:
    var form = $('#contestForm');
    form.find('.field').removeClass('has-error');
    form.find('.help-inline').empty();
    //posting ContestForm to server:
    return $.ajax({
        url: "addContest.do",
        type: 'POST',
        data: $('#contestForm').serialize()
    });
}

function contestAjaxFailed(title, modalMessage, data) {
    //show modal:
    BootstrapDialog.show({
        title: title,
        type: BootstrapDialog.TYPE_DANGER,
        message: modalMessage
    });
    //set errors:
    for (var i = 0; i < data.errorMessageList.length; i++) {
        var item = data.errorMessageList[i];
        var $controlGroup = $('#contestForm .' + item.fieldName);
        $controlGroup.addClass('has-error');
        $controlGroup.find('.help-inline').html(item.message);
    }
}

function updateCKEditors() {
    $('.ckEdit').each(function() {
        var $textarea = $(this);
        $textarea.val(CKEDITOR.instances[$textarea.attr('id')].getData());
    });
}