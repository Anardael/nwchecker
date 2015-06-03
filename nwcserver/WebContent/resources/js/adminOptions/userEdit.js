var DELETE_DIALOG_TITLE;
var DELETE_DIALOG_MESSAGE;
var CANCEL_BUTTON;
var CONFIRM_BUTTON;

var USERNAME_REQUIRED;
var USERNAME_SIZE;
var DISPLAYNAME_REQUIRED;
var DISPLAYNAME_SIZE;
var EMAIL_REQUIRED;
var EMAIL_BAD;
var PASSWORD_REQUIRED;
var PASSWORD_SIZE;
var CONFIRM_PASSWORD_REQUIRED;
var PASSWORD_EQUALS;
$(document).ready(function() {
	console.log(PASSWORD_EQUALS);
	$('#userEditForm').validate({
		rules : {
			username:{
				required:true,
				minlength:3,
				maxlength:16
			},
			displayName:{
				required:true,
				minlength:3,
				maxlength:16
			},
			email:{
				required:true,
				email:true
			},
			password:{
				required:true,
				minlength:6,
				maxlength:32
			},
			confirmPassword:{
				required:true,
				minlength:6,
				maxlength:32,				
				equalTo:'#password'
			}
		},
		messages:{
			username:{
				required:USERNAME_REQUIRED,
				minlength:USERNAME_SIZE,
				maxlength:USERNAME_SIZE
			},
			displayName:{
				required:DISPLAYNAME_REQUIRED,
				minlength:DISPLAYNAME_SIZE,
				maxlength:DISPLAYNAME_SIZE
			},
			email:{
				required:EMAIL_REQUIRED,
				email:EMAIL_BAD
			},
			password:{
				required:PASSWORD_REQUIRED,
				minlength:PASSWORD_SIZE,
				maxlength:PASSWORD_SIZE
			},
			confirmPassword:{
				required:CONFIRM_PASSWORD_REQUIRED,
				minlength:PASSWORD_SIZE,
				maxlength:PASSWORD_SIZE,				
				equalTo:PASSWORD_EQUALS
			}
		}
	});
})

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
		desc += "ROLE_ADMIN;";
	}
	if ($('#teacher').prop('checked')) {
		desc += "ROLE_TEACHER;";
	}
	if (!($('#admin').prop('checked')) && !($('#teacher').prop('checked'))) {
		$('#user').prop('checked', true);
	}
	if ($('#user').prop('checked')) {
		desc += "ROLE_USER;";
	}
	$('#rolesDesc').val(desc);
}