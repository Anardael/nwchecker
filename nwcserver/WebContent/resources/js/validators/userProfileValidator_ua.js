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
				required : "Це поле необхідно заповнити.",
			},
			newPassword : {
				required : "Це поле необхідно заповнити.",
			},
			confirmPassword : {
				required : "Це поле необхідно заповнити.",
				equalTo: "Пароль та підтверження пароля не збігаються!",
			},
		},
	});
	jQuery.validator.addMethod("pass", function(value, element) {
	    return this.optional(element) || /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,32}/.test(value);
	}, "Пароль має містити від 6 до 32 символів, літери у верхньому або нижньому регістрі, а також цифри.");
});


