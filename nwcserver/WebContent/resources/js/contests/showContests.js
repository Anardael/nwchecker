$(document).ready(function () {
    var $result = $('#mdl-body');

    $('#contestTable').bootstrapTable({
    }).on('click-row.bs.table', function (e, row, $element) {
        $result.text(JSON.stringify(row));
        window.contestId = row['id'];

        $('#contestModal').modal();
    });

    function getContestId(){
        return window.contestId;
    }
});
