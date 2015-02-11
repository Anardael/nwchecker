/**
 * Created by ReaktorDTR on 07.02.2015.
 */

$(document).ready(function () {
    $('#contestsData').on('click', 'tbody tr', function (event) {
        var contestId = $(this).find('td:nth-child(1)').html();
        var id = document.getElementById("id");
        id.value = contestId;
        $('#ContestUserTable').bootstrapTable('destroy');
        $.get('getContestUsersList.do?contestId=' + contestId, function (data) {
            $('#ContestUserTable').bootstrapTable({
                data: data
            });
        });
        tryToShowUserList();
    });

    $('body').on("click", "#submitUserListButton", function () {
        sendContestUsers();
    });
});

function checkUserList(data) {
    if (data.userIds.length == 0) {
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_WARNING,
            title: emptyUserListHeader,
            message: emptyUserListBody,
            buttons: [{
                label: btnClose,
                action: function (dialogItself) {
                    dialogItself.close();
                }
            }, {
                label: btnSubmit,
                cssClass: 'btn-primary',
                action: function (dialogItself) {
                    data["userIds[]"] = "-1";
                    ajaxUserList(data);
                    dialogItself.close();
                }
            }
            ]
        });
        return false;
    } else {
        return true;
    }
}

function ajaxUserList(data) {
    $.ajax({
        'type': 'POST',
        'url': 'setContestUsers.do',
        'data': data,
        success: function (response) {
            if (response.status == "SUCCESS") {
                $('#userListModal').modal('hide');
                BootstrapDialog.show({
                    title: successCaption,
                    type: BootstrapDialog.TYPE_SUCCESS,
                    message: contestUserListSuccess,
                    onhidden: function (dialogRef) {
                        $('#contestsData').bootstrapTable('refresh');
                    }
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

function tryToShowUserList() {
    if ($('#id').val() == '0') {
        sendJsonContest().success(function (data) {
            if (data.status == "SUCCESS") {
                $('#id').val(data.result);
                $('#userListModal').modal();
            }
        });
    } else {
        $('#userListModal').modal();
    }
}