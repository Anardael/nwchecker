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

function checkUserList(data) {
    if (data.userIds.length == 0) {
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_WARNING,
            title: emptyUserListHeader,
            message: emptyUserListBody,
            buttons: [{
                    label: btnClose,
                    action: function(dialogItself) {
                        dialogItself.close();
                    }
                }, {
                    label: btnSubmit,
                    cssClass: 'btn-primary',
                    action: function(dialogItself) {
                        data["userIds[]"] = "-1";
                        ajaxUserList(data);
                        dialogItself.close();
                        window.location.href = "index.do";
                    }
                }
            ]
        });
        return false;
    } else {
        return true;
    }
}

function sendContestUsers() {
    //collect data:
    var fields = $('#userListModal tbody tr');
    var data = collectUserListData(fields);
    //check if even one user avaible in List:
    if (checkUserList(data) == true) {
        //send data:
        ajaxUserList(data);
    }
}
function ajaxUserList(data) {
    $.ajax({
        'type': 'POST',
        'url': 'setContestUsers.do',
        'data': data,
        success: function(response) {
            if (response.status == "SUCCESS") {
                $('#userListModal').modal('hide');
                BootstrapDialog.show({
                    title: successCaption,
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: contestUserListSuccess,
                });
            }
            if (response.status == "FAIL") {
                if (response.errorMessageList[0].fieldName == "denied") {
                    showAccessDeniedModal();
                }
            }
        }
    });
}

function showAccessDeniedModal() {
    BootstrapDialog.show({
        title: errorLabel,
        type: BootstrapDialog.TYPE_DANGER,
        message: contestAccessDenied+".",
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