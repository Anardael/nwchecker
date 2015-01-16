function usernameFormatter(value) {
	return '<a href="userEdit.do?Username=' + value + '">' + value + '</a>';
}
function rolesFormatter(value) {
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