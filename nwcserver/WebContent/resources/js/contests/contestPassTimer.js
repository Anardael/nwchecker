/**
 * Created by Станіслав on 26.02.2015.
 */

var END_TIME_GTM_MILLISECONDS;

function startTimer() {
    var duration = new Date();
    duration.setTime(END_TIME_GTM_MILLISECONDS - duration.getTime());
    var hours = duration.getHours();
    var minutes = duration.getMinutes();
    var seconds = duration.getSeconds();

    if (hours < 10) hours = "0" + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    $("#timer").html(hours + ":" + minutes + ":" + seconds);
    if ((seconds <= 10) && (minutes == 0) && (hours == 0))
        $("#timer").addClass("text-danger");

    if (!((seconds == 0) && (minutes == 0) && (hours == 0)))
        setTimeout(startTimer, 1000);
    else
        timeEnd();
}

function timeEnd() {
    //TODO
    alert('Time END!');
}