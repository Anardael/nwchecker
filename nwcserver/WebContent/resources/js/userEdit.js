var DELETE_DIALOG_TITLE;
var DELETE_DIALOG_MESSAGE;
var CANCEL_BUTTON;
var CONFIRM_BUTTON;

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
function disableDangerousOptions() {
	$('#deleteBtn').prop('disabled', true);
	$('#admin').prop('disabled', true);
}
function showDeleteDialog() {
	BootstrapDialog.show({
		type:    BootstrapDialog.TYPE_DANGER,
		title:   DELETE_DIALOG_TITLE,
		message: DELETE_DIALOG_MESSAGE,
		buttons: [{
			icon:   'glyphicon glyphicon-ban-circle',
			label:  CANCEL_BUTTON,
			action: function(dialogItself){
				dialogItself.close();
			}
		}, {
			icon:     'glyphicon glyphicon-trash',
			label:    CONFIRM_BUTTON,
			cssClass: 'btn-danger',
			action:   function(dialogItself){
				$('#formDeleteBtn').click();
			}
		}]
	});
}
function resetRolesDesc() {
	var desc = "";
	if ($('#admin').prop('checked')) {
		desc += "ROLE_ADMIN";
	}
	if ($('#teacher').prop('checked')) {
		desc += "ROLE_TEACHER";
	}
	if (!($('#admin').prop('checked')) && !($('#teacher').prop('checked'))) {
		$('#user').prop('checked', true);
	}
	if ($('#user').prop('checked')) {
		desc += "ROLE_USER";
	}
	$('#rolesDesc').val(desc);
}