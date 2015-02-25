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
        $.get('getContestStatus.do?contestId=' + contestId, function (contestStatus) {
            $("input[name=radioStatus]").val([contestStatus]);
        });
        tryToShowContestInfo();
    });

    $('body').on("click", "#submitUserListButton", function () {
        sendContestInfo();
    });
});

function sendContestInfo() {
    //collect users data:
    var fields = $('#userListModal tbody tr');
    var data = collectContestData(fields);
    //check if even one user avaible in List:
    if (checkUserList(data) == true) {
        //send data:
        sendContestData(data);
    }
}

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
                    sendContestData(data);
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

function sendContestData(data) {
    $.ajax({
        'type': 'POST',
        'url': 'setContestStatus.do',
        'data': data,
        success: function (response) {
            if (response.status == "SUCCESS") {
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
            if (response.status == "FAIL") {
                if (response.errorMessageList[0].fieldName == "denied") {
                    showAccessDeniedModal();
                }
            }
        }
    });
}

function collectContestData(fields) {
    var data = {};
    var data1 = [];
    //set contest Id:
    data["contestId"] = $('#id').val();
    data["contestStatus"] = $('input:radio[name=radioStatus]:checked').val();
    //get data from tbody:
    $('tbody tr').each(function () {
        if ($(this).hasClass('selected')) {
            data1.push($(this).find('.idField').html());
        }
    });
    data["userIds"] = data1;
    return data;
}

function tryToShowContestInfo() {
    if ($('#id').val() != '0') {
        $('#userListModal').modal();
    }
}