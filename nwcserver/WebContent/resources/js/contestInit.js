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

    $('.digits').keypress(function(e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            return false;
        }
    });
}

function submitContest() {
    sendJsonContest().success(function(data) {
        if (data.status == "FAIL") {
            contestAjaxFailed(errorCaption, emptyContestfields, data);
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
