function setMask() {
    $('#datetimepicker1').datetimepicker({
        language: 'uk'
    });
    $('#datetimepicker4').datetimepicker({
        pickDate: false,
        language: 'uk'
    });
    $("#datetimepicker4").val("00:00");
    $("#starts").mask("9999-99-99 99:99");
    $("#duration").mask("99:99");

    $('.digits').keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            return false;
        }
    });
}

function submitContest() {
    sendJsonContest().success(function (data) {
        if (data.status == "FAIL") {
            if (data.errorMessageList[0].fieldName == "denied") {
                showAccessDeniedModal();
            } else {
                contestAjaxFailed(errorCaption, emptyContestfields, data);
            }
        }
        if (data.status == "SUCCESS") {
            BootstrapDialog.show({
                title: successCaption,
                type: BootstrapDialog.TYPE_SUCCESS,
                message: successContestSave
            });
        }
        //set recieved id:
        if (data.result != null) {
            $('#id').val(data.result);
        }
    });
}

function finishContest() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_WARNING,
        title: contestFinishHeader,
        message: contestFinishBody,
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
                sendFinishContest();
            }
        }
        ]
    });
}

function sendFinishContest() {
    //first of all- send ajax to save current contest:
    sendJsonContest().success(function (data) {
        if (data.status == "FAIL") {
            if (data.errorMessageList[0].fieldName == "denied") {
                showAccessDeniedModal();
            } else {
                contestAjaxFailed(errorCaption, emptyContestfields, data);
            }
        }
        if (data.status == "SUCCESS") {
            if (data.result != null) {
                $('#id').val(data.result);
            }
            //if contest successfully saved- send ajax to finish contest preparing:
            $.ajax({
                url: "stopContestPrepare.do",
                type: 'GET',
                data: "id=" + ($('#id').val())
            }).success(function (data) {
                if (data.status == "FAIL_STARTS") {
                    BootstrapDialog.show({
                        title: errorCaption,
                        type: BootstrapDialog.TYPE_DANGER,
                        message: contestReleaseFailDueDate
                    });
                }
                if (data.status == "FAIL_EMPTY") {
                    BootstrapDialog.show({
                        title: errorCaption,
                        type: BootstrapDialog.TYPE_DANGER,
                        message: emptyStart
                    });
                }
                if (data.status == "TASK_SIZE") {
                    BootstrapDialog.show({
                        title: errorCaption,
                        type: BootstrapDialog.TYPE_DANGER,
                        message: emptyTask
                    });
                }

                if (data.status == "SUCCESS") {
                    BootstrapDialog.show({
                        title: successCaption,
                        type: BootstrapDialog.TYPE_SUCCESS,
                        message: contestReleaset
                    });
                }
            });
        }
    });
}
