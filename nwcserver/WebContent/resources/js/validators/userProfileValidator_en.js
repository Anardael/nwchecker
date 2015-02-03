$().ready(function() {
	$("#changePassword").validate({
		rules : {
			oldPassword : {
				required : true,
				pass : true,
			},
			newPassword : {
				required : true,
				pass : true,
			},
			confirmPassword : {
				required : true,
				pass : true,
				equalTo : "#newPassword"
			}
		},
		messages : {
			oldPassword : {
				required : "This field is required",
			},
			newPassword : {
				required : "This field is required",
			},
			confirmPassword : {
				required : "This field is required",
				equalTo : "Password and confirm password do not match!",
			},
		},
	});
	jQuery.validator.addMethod("pass", function(value, element) {
	    return this.optional(element) || /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,32}/.test(value);
	}, "Password must be contain for 6 to 32 symbols, including uppercase, lowercase letters and numeric characters.");
});