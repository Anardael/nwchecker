/**
 * Created by Reaktor on 01.03.2015.
 */
var incomingTime;
var offset;

$.get("getServerTime.do", function (data) {
    setIncomingTime(data);
});

function setIncomingTime(data) {
    incomingTime = new Date(data);
    $('#currentTime').html(incomingTime.toLocaleTimeString());
    //document.getElementById('currentTime').innerHTML = incomingTime.toLocaleTimeString();
    offset = new Date() - incomingTime;
}

setInterval(function () {
    myTimer()
}, 1000);

function myTimer() {
    var clientTime = new Date();
    var serverTime = new Date(clientTime - offset);
    $('#currentTime').html(serverTime.toLocaleTimeString());
}

