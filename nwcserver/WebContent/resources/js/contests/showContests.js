$(document).ready(function () {
    $('#contestTable').bootstrapTable({
    }).on('click-row.bs.table', function (e, row, $element) {
        console.log(JSON.stringify(row));

        window.contestId = row['id'];

        $('#title-text').text(row['title']);
        $('#start_date').text(row['starts']);
        $('#duration').text(row['duration']);
        $('#type').text(row['type']);
        $('#description').text(row['description']);

        if(row['status'] != 'ARCHIVE'){
            $('#archive-btn').hide();
        }

        $('#contestModal').modal();
    });
});

