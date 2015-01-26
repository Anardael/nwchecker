/**
 * Created by ReaktorDTR on 26.01.2015.
 */

function acceptedUserRequests(){
    var selected = [];
    $('#data-index class:selected').each(function() {
        selected.push($(this).attr('data-index'));
    });
    alert(selected);
}