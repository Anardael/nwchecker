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
	$('#registrationForm').validate({
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
				equalsTo:'#password'
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
				equalsTo:'#password'
			}
		}
	});
})