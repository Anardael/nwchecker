/**
 * Created by Станіслав on 28.02.2015.
 */

var CURRENT_TASK_SUCCESS;

var ALL_COMPLETE_TITLE;
var ALL_COMPLETE_MESSAGE;

var UPLOAD_FILE;

function disableDangerousOptions() {
    document.getElementsByClassName('ladda-button')[0].disabled = CURRENT_TASK_SUCCESS&&(DYNAMIC.toLowerCase() == 'true');
}

function allTasksComplete() {
    var tasksCount = document.getElementById("tasks").getElementsByTagName("LI").length;
    var completeTasksCount = document.getElementsByClassName("success").length;
    completeTasksCount += document.getElementsByClassName("descr-success").length;
    return (tasksCount == completeTasksCount);
}

function resolveFavCompiler(index) {
    var compilerIndex = index;
    var list = document.getElementsByClassName("dropdown-menu inner selectpicker").item(0);
    // refreshing option statuses
    list.childNodes.forEach(function(item){ dropAttrClass(item) });
    //setting selected attr to already stored as cookie compiler li
    list.childNodes.item(compilerIndex).setAttribute("class", "selected");
    var compilerName = list.childNodes.item(compilerIndex).firstElementChild
                                                          .firstElementChild
                                                          .innerHTML;
    //setting button title
    var titleBtnClass = "btn dropdown-toggle form-control selectpicker btn-default";
    document.getElementsByClassName(titleBtnClass).item(0)
                                                  .firstElementChild
                                                  .innerHTML = compilerName;
}

function dropAttrClass(entry) {
    entry.removeAttribute("class");
}

function endContest() {
    BootstrapDialog.show({
        type:    BootstrapDialog.TYPE_INFO,
        title:   ALL_COMPLETE_TITLE,
        message: ALL_COMPLETE_MESSAGE,
        onhide: function() {
            var newUrl = location.href.substring(0, location.href.lastIndexOf('/'));
            newUrl += "/getContests.do";
            location.href = newUrl;
        }
    });
}

function changeFileInputColor() {
    if ($('#file').val() != "") {
        var fileName = $('#file').val();
        if (fileName.lastIndexOf('\\') != -1)
            fileName = fileName.substring(fileName.lastIndexOf('\\') + 1); // Windows separator
        else
            fileName = fileName.substring(fileName.lastIndexOf('/')); // UNIX, LINUX separator
        $('#fileCaption').html(fileName);
        $('.btn-file')[0].style.backgroundColor = '#DEF0D8';
    } else {
        $('#fileCaption').html(UPLOAD_FILE);
        $('.btn-file')[0].style.backgroundColor = 'white';
    }
}