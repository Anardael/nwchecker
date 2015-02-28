/**
 * Created by Станіслав on 28.02.2015.
 */

var CURRENT_TASK_SUCCESS;

var ALL_COMPLETE_TITLE;
var ALL_COMPLETE_MESSAGE;

function disableDangerousOptions() {
    document.getElementsByClassName('ladda-button')[0].disabled = CURRENT_TASK_SUCCESS;
}

function allTasksComplete() {
    var tasksCount = document.getElementById("tasks").getElementsByTagName("LI").length;
    var completeTasksCount = document.getElementsByClassName("success").length;
    completeTasksCount += document.getElementsByClassName("descr-success").length;
    return (tasksCount == completeTasksCount);
}

function endContest() {
    BootstrapDialog.show({
        type:    BootstrapDialog.TYPE_WARNING,
        title:   ALL_COMPLETE_TITLE,
        message: ALL_COMPLETE_MESSAGE,
        onhide: function() {
            var newUrl = location.href.substring(0, location.href.lastIndexOf('/'));
            newUrl += "/getContests.do";
            location.href = newUrl;
        }
    });
}