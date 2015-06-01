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

