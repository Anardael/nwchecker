function showUserRoles(roles) {
    for (var i = 0; i < roles.length; i++) {
    	switch (roles[i]) {
    		case "ROLE_ADMIN":
    			$('#admin').prop('checked', true);
    			break;
			case "ROLE_TEACHER":
				$('#teacher').prop('checked', true);
    			break;
			case "ROLE_USER":
				$('#user').prop('checked', true);
    			break;
    	}
    }
}
function disableDeleteBtn() {
	$('#deleteBtn').prop('disabled', true);
}
function resetRolesDesc() {
	var desc = "";
	if ($('#admin').prop('checked')) {
		desc += "ROLE_ADMIN;";
	}
	if ($('#teacher').prop('checked')) {
		desc += "ROLE_TEACHER;";
	}
	if (!($('#admin').prop('checked')) && !($('#teacher').prop('checked'))) {
		$('#user').prop('checked', true);
	}
	if ($('#user').prop('checked')) {
		desc += "ROLE_USER";
	}
	$('#rolesDesc').val(desc);
}