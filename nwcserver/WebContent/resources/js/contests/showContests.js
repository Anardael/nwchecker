$(document).ready(function () {
    $('#contestTable').bootstrapTable({
    }).on('click-row.bs.table', function (e, row, $element) {
        console.log(JSON.stringify(row));

        window.contestId = row['id'];

        $('#title-text').text(row['title']);
        $('#start_date').text(row['starts']);
        $('#duration').text(row['duration']/3600000);
        $('#type').text(row['type']);
        $('#description').text(row['description']);

        switch (row['status']){
            case 'GOING': {
                $('.open-btn').show();
                $('#archive-btn').hide();
                $('#edit-btn').hide();
            } break;
            case 'ARCHIVE': {
                $('#archive-btn').show();
                $('.open-btn').hide();
                $('#edit-btn').hide();
            } break;
            case 'PREPARING': {
                $('#archive-btn').hide();
                $('.open-btn').hide();
                $('#edit-btn').show();
            } break;
            case 'RELEASE': {
                $('#archive-btn').hide();
                $('.open-btn').hide();
                $('#edit-btn').hide();
            } break;
        }

        $('#contestModal').modal();
    });
});

function edited(contestId) {
    //1. Send ajax to check if somebody currently edit contest:
    $.get("checkContestEdit.do?id=" + contestId)
        .success(function (data) {
            if (data == "OK") {
                location.href = 'editContest.do?id=' + contestId;
            } else {
                location.href = 'editContest.do?id=' + contestId;
                /*BootstrapDialog.show({
                 title: errorLabel,
                 type: BootstrapDialog.TYPE_DANGER,
                 message: nowEditingBody + "\n" + nowEditingUser + ": " + data
                 });*/
            }
        }
    );
}

function openContest(contestId) {
    location.href = 'passContest.do?id=' + contestId;
}
function archive(){
    location.href='etiam.do';
}

