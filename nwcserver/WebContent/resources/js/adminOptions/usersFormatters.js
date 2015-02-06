var ROLE_ADMIN;
var ROLE_TEACHER;
var ROLE_USER;
var UNDEFINED;
var WANT_ROLE_TEACHER;

function usernameFormatter(value) {
	return '<a href="userEdit.do?Username=' + value + '">' + value + '</a>';
}

function rolesFormatter(value) {
	if (value == '') {
		return "<label style='font-size:90%;color:#D3574D'>" + UNDEFINED + "</label>"
	}
	var roles = [];
	for (var i = 0; i < value.length; i++) {
		roles[i] = value[i]['role'];
	}
	var result = "";
	if (roles.indexOf("ROLE_ADMIN") > -1) {
		result += "<label style='font-size:90%;color:#F3AD4B'>" + ROLE_ADMIN + "</label><br/>";
	}
	if (roles.indexOf("ROLE_TEACHER") > -1) {
		result += "<label style='font-size:90%;color:#5ABB5A'>" + ROLE_TEACHER + "</label><br/>";
	}
	if (roles.indexOf("ROLE_USER") > -1) {
		result += "<label style='font-size:90%;color:#5AC2DF'>" + ROLE_USER + "</label>";
	}
	return result;
}

function requestsFormatter(value) {
	var requests = [];
	for (var i = 0; i < value.length; i++) {
		requests[i] = value[i]['request'];
	}
	var result = "";
	if (requests.indexOf("WANT_ROLE_ADMIN") > -1) {
		result += "<label style='font-size:90%;color:#F3AD4B'>" + ROLE_ADMIN + "</label><br/>";
	}
	if (requests.indexOf("WANT_ROLE_TEACHER") > -1) {
		result += "<label style='font-size:90%;color:#5ABB5A'>" + ROLE_TEACHER + "</label><br/>";
	}
	if (requests.indexOf("WANT_ROLE_USER") > -1) {
		result += "<label style='font-size:90%;color:#5AC2DF'>" + ROLE_USER + "</label>";
	}
	return result;
}

function infoFormatter(value) {
	if (value == null) {
		return "";
	}
	return value;
}
