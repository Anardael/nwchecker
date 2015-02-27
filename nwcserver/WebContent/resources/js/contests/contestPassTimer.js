/**
 * Created by Станіслав on 26.02.2015.
 */

var END_TIME_MILLISECONDS;
var SERVER_TIME_ZONE; // example: GTM +2 = 2

function startTimer() {
    var duration = END_TIME_MILLISECONDS - new Date().getTime();
    duration = new Date(duration - SERVER_TIME_ZONE * 3600000);
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