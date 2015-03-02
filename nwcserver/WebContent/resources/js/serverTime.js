/**
 * Created by Reaktor on 01.03.2015.
 */
var incomingTime;
var offset;

function setIncomingTime() {
    incomingTime = new Date(document.getElementById('incomingServerTime').value);
    document.getElementById('labelServerTime').innerHTML = incomingTime.toLocaleTimeString();
    offset = new Date() - incomingTime;
}

var myVar = setInterval(function () {
    myTimer()
}, 1000);

function myTimer() {
    var clientTime = new Date();
    var serverTime = new Date(clientTime - offset);
    document.getElementById('labelServerTime').innerHTML = serverTime.toLocaleTimeString();
}



