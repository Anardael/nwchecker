function setMask() {
    $('#datetimepicker1').datetimepicker({
        language: 'uk'
    });
    $('#datetimepicker4').datetimepicker({
        pickDate: false,
        language: 'uk'
    });
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
                    title: 'Successfully updated',
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: 'Contest teacher access lsit have been successfuly updated',
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
            contestAjaxFailed("Please eneter all required fields", data);
        }
        if (data.status == "SUCCESS") {
            BootstrapDialog.show({
                title: 'Contest',
                type: BootstrapDialog.TYPE_SUCCESS,
                message: 'Contest have been sucessfully saved.'
            });
        }
        //set recieved id:
        $('#id').val(data.result);
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