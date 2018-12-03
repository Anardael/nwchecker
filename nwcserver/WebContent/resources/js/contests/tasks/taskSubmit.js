var FILE_IS_SAVED_UNCHECKED_TITLE;
var FILE_IS_SAVED_UNCHECKED_MESSAGE;

var FILE_NOT_SELECTED_TITLE;
var FILE_NOT_SELECTED_MESSAGE;

var FILE_TOO_LARGE_TITLE;
var FILE_TOO_LARGE_MESSAGE;

var TASK_SUBMIT_ERROR_TITLE;
var TASK_SUBMIT_ERROR_MESSAGE;

var ACCESS_DENIED_TITLE;
var ACCESS_DENIED_MESSAGE;

var RESULT_SUCCESS_TITLE;
var RESULT_SUCCESS_MESSAGE;

var RESULT_FAIL_TITLE;

var RESULT_TIME;
var RESULT_MEMORY;
var RESULT_ERROR_MESSAGE;

var RESULT_SUCCESSFUL;
var RESULT_TOTAL;
var DYNAMIC;
var DYNAMIC_MESSAGE;


function submitTask() {
	if ($('#file').val() == "") {
		showErrorDialog(FILE_NOT_SELECTED_TITLE, FILE_NOT_SELECTED_MESSAGE);

	} else {
		if ($('#file').prop('files')[0].size > 20971520) {
			showErrorDialog(FILE_TOO_LARGE_TITLE, FILE_TOO_LARGE_MESSAGE)
		} else {
			var formData = new FormData();
			formData.append("id", $('#id').val());
			formData.append("compilerId", $('#compilerId').val());
			formData.append("file", $('#file').prop('files')[0]);
			submitTaskAjax(formData);
		}
	}
}


function showErrorDialog(dialogTitle, dialogMessage) {
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_DANGER,
		title : dialogTitle,
		message : dialogMessage
	});
}

function submitTaskAjax(formData) {
	var animatedBtn = Ladda.create(document
			.getElementsByClassName('ladda-button')[0]);
	animatedBtn.start();
	$.ajax(
			{
				url : "submitTask.do",
				data : formData,
				dataType : 'JSON',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(result) {
					showSubmitResult(result);
				},
				error : function() {
					showErrorDialog(TASK_SUBMIT_ERROR_TITLE,
							TASK_SUBMIT_ERROR_MESSAGE);
				}
			}).always(function() {
		animatedBtn.stop();
	});
}

function showSubmitResult(result) {
	if (result["accessDenied"]) {
		showErrorDialog(ACCESS_DENIED_TITLE, ACCESS_DENIED_MESSAGE);
		return;
	}
	if(result["fileTooLarge"]){
		showErrorDialog(FILE_TOO_LARGE_TITLE, FILE_TOO_LARGE_MESSAGE);
		return;
	}
	if (result["saveInfo"]) {
        showResultSuccess(FILE_IS_SAVED_UNCHECKED_MESSAGE + result["saveInfo"]);
        return;
	}
	var message = RESULT_SUCCESSFUL +' <b>' + result['successful'] + '</b><br/>' + RESULT_TOTAL + ' <b>'+ result['total']+'</b>';
	if (DYNAMIC.toLowerCase() == "true"){
		if (result['passed']) {
			showResultSuccess(message);
		} else {		
			showResultFail(message);
		}
	} else {
		showResultSuccess(DYNAMIC_MESSAGE);
	}
}

function showResultSuccess(message) {
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_SUCCESS,
		title : RESULT_SUCCESS_TITLE,
		message : message,
		onhide : function() {
			location.reload();
		}
	});
}

function showResultFail(message) {
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_DANGER,
		title : RESULT_FAIL_TITLE,
		message : message,
		onhide : function() {
			location.reload();
		}
	});
}