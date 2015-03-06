/**
 * Created by Станіслав on 01.03.2015.
 */

function positionFormatter(value) {
    return '<b>' + value + '</b>';
}

function displayNameFormatter(value) {
    return '<span>' + value + '</span>';
}

function tasksPassedCountFormatter(value) {
    var result = '<span style="color:#00B600;">' + value.substr(0, value.indexOf('/')) + '</span>'
                + " " + value.substr(value.indexOf('/'), 1) + " " + value.substring(value.indexOf('/') + 1);
    return result;
}

function timePenaltyFormatter(value) {
    return '<span style="color:red;">' + value + '</span>';
}