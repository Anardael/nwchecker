function usernameFormatter(value) {
	return '<a href="userEdit.do?Username=' + value + '">' + value + '</a>';
}
function rolesFormatter(value) {
	if (value == '') {
		return '<h3><label class="label label-danger">ERROR: Invalid user roles!</label></h3>';
	}
	value.sort();
	var roles = value[0]['role'];
	for (var i = 1; i < value.length; i++) {
		roles += ',\n';
		roles += value[i]['role'];
	}
	return roles;
}
function infoFormatter(value) {
	if (value == null) {
		return "";
	}
	return value;
}