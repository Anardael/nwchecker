/**
 * Created by Станіслав on 01.03.2015.
 */

var place = 0;

function positionFormatter(value) {
    return '<b>' + ++place + '</b>';
}

function displayNameFormatter(value) {
    return '<span style="color:#5BC0DE;">' + value + '</span>';
}

function tasksPassedCountFormatter(value) {
    var result = '<span style="color:green;">' + value.substr(0, value.indexOf('/')) + '</span>'
                + '<span style="color:#5BC0DE;">' + value.substring(value.indexOf('/')) + '</span>';
    return result;
}

function timePenaltyFormatter(value) {
    return '<span style="color:red;">' + value + '</span>';
}