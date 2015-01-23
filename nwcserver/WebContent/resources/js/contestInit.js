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

function sendContestUsers() {
    //collect data:
    var fields = $('#userListModal tbody tr');
    var data = collectUserListData(fields);
    //send data:
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': '/NWCServer/setContestUsers.do',
        'data': JSON.stringify(data),
        success: function(response) {
            if (response.status == "SUCCESS") {
                $('#userListModal').modal('hide');
                BootstrapDialog.show({
                    title: successCaption,
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: contestUserListSuccess,
                });
            }
        }
    });
}

function collectUserListData(fields) {
    var data = {};
    var data1 = [];
    //set contest Id:
    data["contestId"] = $('#id').val();
    //get data from tbody:
    $('tbody tr').each(function() {
        if ($(this).hasClass('selected')) {
            data1.push($(this).find('.idField').html());
        }
    });
    data["userIds"] = data1;
    return data;
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

function tryToShowUserList() {
    if ($('#id').val() == '0') {
        sendJsonContest().success(function(data) {
            if (data.status == "FAIL") {
                contestAjaxFailed(errorCaption, emptyContestUsers, data);
            }
            if (data.status == "SUCCESS") {
                $('#id').val(data.result);
                $('#userListModal').modal();
            }
        });
    } else {
        $('#userListModal').modal();
    }
}