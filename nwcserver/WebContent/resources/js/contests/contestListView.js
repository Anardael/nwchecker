/**
 * Created by Роман on 21.02.2015.
 */
function edited(contestId) {
    //1. Send ajax to check if somebody currently edit contest:
    $.get("checkContestEdit.do?id=" + contestId)
        .success(function (data) {
            if (data == "OK") {
                location.href = 'editContest.do?id=' + contestId;
            } else {
                BootstrapDialog.show({
                    title: errorLabel,
                    type: BootstrapDialog.TYPE_DANGER,
                    message: nowEditingBody + "\n" + nowEditingUser + ": " + data
                });
            }
        }
    );
}