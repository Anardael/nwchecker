function contestSignUp(collapseIndex, index) {
    $.get("contestSignUp.do?id=" + index, function (data) {
        if (data == "success") {
            BootstrapDialog.show({
                title: successCaption,
                type: BootstrapDialog.TYPE_SUCCESS,
                message: successSignUpContest
            });
            //add label:
            var collapse = $('#collapse' + collapseIndex + ' .edit .pull-right');
            collapse.find('button').remove();
            collapse.append('<h4><label class="label label-info label">' + alreadySignUp + '</label></h4>');
        }
    });
}

function contestStart(indexFirstTask) {
    location.href = 'passTask.do?id=' + indexFirstTask;
}

function contestSignUpAndStart(index, firstTaskIndex) {
    //0. Sign up:
    $.get("contestSignUp.do?id=" + index, function (data) {
        if (data == "success") {
            //1. start:
            contestStart(firstTaskIndex);
        }
    });
}
