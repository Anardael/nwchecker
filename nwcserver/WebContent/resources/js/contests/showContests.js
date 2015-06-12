$(document).ready(function () {
    $('#contestTable').on('click-row.bs.table', function (e, row, $element) {
        //console.log(JSON.stringify(row));

        var typeContest = row['type'];
        window.contestId = row['id'];
        var startDate = new Date(row['starts']);
        var duration = new Date(row['duration']);

        $('#title-text').text(row['title']);
        $('#start-date').text(startDate.toLocaleDateString() + " " + startDate.toLocaleTimeString().substr(0, 5));
        $('#duration-hours').text(duration.getHours());
        $('#duration-minutes').text(duration.getMinutes());
        $('#type').text(typeContest['name']);
        $('#description').html(row['description']);

        switch (row['status']){
            case 'GOING': {
                $('.open-btn').show();
                $('#archive-btn').hide();
                $('#edit-group').hide();
            } break;
            case 'ARCHIVE': {
                $('#archive-btn').show();
                $('.open-btn').hide();
                $('#edit-group').hide();
            } break;
            case 'PREPARING': {
                $('#archive-btn').hide();
                $('.open-btn').hide();
                showEditGroup(row['id']);
            } break;
            case 'RELEASE': {
                $('#archive-btn').hide();
                $('.open-btn').hide();
                $('#edit-group').hide();
            } break;
        }

        $('#contestModal').modal();
    });
});

function edited(contestId) {
    location.href = 'editContest.do?id=' + contestId;
}

function openContest(contestId) {
    location.href = 'passContest.do?id=' + contestId;
}

function archive(){
    location.href='etiam.do';
}

function statusFormatter(value, row) {
    rowStyle(row);
    switch (value){
        case 'GOING': {
            return '<span style="color:limegreen;">' + statusCaptionGoing + '</span>';
        }
        case 'ARCHIVE': {
            return '<span style="color:#ff0000;">' + statusCaptionArchive + '</span>';
        }
        case 'PREPARING': {
            return '<span style="color:cornflowerblue;">' + statusCaptionPreparing + '</span>';
        }
        case 'RELEASE': {
            return '<span style="color:blue;">' + statusCaptionRelease + '</span>';
        }
    }
}

function dateFormatter(value) {
    return new Date(value).toLocaleDateString();
}

function rowStyle(row) {
    if(row['hidden']){
        return {
            classes: 'warning'
        };
    }
    return {};
}


function updateContestsList(){
    var selectVar = $('#selectOption').val();
    var radioVar = $('input:radio[name="radioOption"]:checked').val();

    $('#contestTable').bootstrapTable('destroy');
    $('#contestTable').bootstrapTable({
        method: 'get',
        url: 'contestListJson.do?hidden=' + radioVar + '&status=' + selectVar.toUpperCase()
    });
}

function showEditGroup(contestId){
    $.ajax({
        type: 'GET',
        url: 'checkContestIsEdited.do?id=' + contestId,
        dataType:'json',
        success: function (response) {
            //console.log(JSON.stringify(response));
            updateEditGroup(response);
        },
        error: function() {
            console.log('Error response!');
        }
    });
}

function updateEditGroup(editResponse){
    $('#edit-group').show();
    if(editResponse['edit'] === true){
        $('#now-edit').show();
        $('#edit-username').text(editResponse['name']);
        $('#edit-btn').show().attr('disabled','disabled');
    } else {
        $('#now-edit').hide();
        $('#edit-btn').show().removeAttr('disabled');
    }
}

function refresh(contestId){
    showEditGroup(contestId);
}















