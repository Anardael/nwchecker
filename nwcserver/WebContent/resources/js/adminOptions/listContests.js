/**
 * Created by ReaktorDTR on 07.02.2015.
 */

$(document).ready(function () {
    $('#contestsData').on('click', 'tbody tr', function (event) {
        var contestId = $(this).find('td:nth-child(1)').html();
        var id = document.getElementById("id");
        id.value = contestId;
        $('#userListModal').removeData();

        $.get('getContestUsersList.do?contestId=' + contestId, function(data) {
            $('#userListModal').bootstrapTable({
                data: data
            });
        });
        $('#fixed-table-container').hide();
        tryToShowUserList();

    });
});

