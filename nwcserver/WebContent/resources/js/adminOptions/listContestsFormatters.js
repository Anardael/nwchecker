/**
 * Created by ReaktorDTR on 07.02.2015.
 */

function usersFormatter(value) {
    var users = [];
    var result = "";
    for (var i = 0; i < value.length; i++) {
        if (i == 2) {
            result += "...";
            result += "<br>";
            break;
        }
        result += value[i];
        result += "<br>"
    }
    return result;
}

function dateFormatter(value) {
    var date = new Date(value);
    var result = "";
    if (date != "Invalid Date") {
        result += twoDigitsDateTime(date.getDate()) + "/";
        result += twoDigitsDateTime(date.getMonth()) + "/";
        result += date.getFullYear() + " ";
        result += twoDigitsDateTime(date.getHours()) + ":";
        result += twoDigitsDateTime(date.getMinutes()) + ":";
        result += twoDigitsDateTime(date.getSeconds());
    }
    return result;
}

function twoDigitsDateTime(value) {
    value += "";
    if (value.length != 2) {
        value = "0" + value;
    }
    return value;
}

function usersSorter(a, b) {
    if (a=='' && b=='') {
        return -1;
    }
    if (a=='') {
        return 1;
    }
    if (b=='') {
        return -1;
    }
    if (a>b) return -1;
    if (a<b) return 1;
    return 0;
}

function dateSorter(a, b) {
    if (!a && !b) {
        return -1;
    }
    if (!a) {
        return 1;
    } else {
        var data1 = new Date(a);
    }
    if (!b) {
        return -1;
    } else {
        var data2 = new Date(b);
    }
    if (data1.getTime() > data2.getTime()) return 1;
    if (data1.getTime() < data2.getTime()) return -1;
    return 0;
}
