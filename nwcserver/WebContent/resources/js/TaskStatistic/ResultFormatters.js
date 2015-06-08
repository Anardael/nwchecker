var PASSED = 'true';
var FAILED = 'false';

/*function usernameFormatter(value){
	return value['name'];
}

function compilerFormatter(value){
	return value['compiler'];
}*/

function passedFormatter(value){
	if (value){
		return "<label style='font-size:90%;color:#5ABB5A'>" + PASSED + "</label><br/>";
	}
	else return "<label style='font-size:90%;color:#F3AD4B'>" + FAILED + "</label><br/>"
}