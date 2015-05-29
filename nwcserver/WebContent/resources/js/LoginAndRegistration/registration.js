$(document).ready(function() {
	$('#registrationForm').validate({
		rules : {
			username:{
				required:true,
				minlength:3
			},
			displayName:{
				required:true,
				minlength:3
			},
			email:{
				required:true,
				email:true
			},
			password:{
				required:true,
				minlength:6
			},
			confirmPassword:{
				required:true,
				minlength:6,
				equalsTo:'#password'
			}
		}
	});
})