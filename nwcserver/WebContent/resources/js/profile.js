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
function showUserRequests(requests) {
    for (var i = 0; i < requests.length; i++) {
        switch (requests[i]) {
            case "WANT_ROLE_TEACHER":
                $('#userRequestTeacher').prop('checked', true);
                break;
        }
    }
}

function setRequests(){
    var request = "";
    if ($('#userRequestTeacher').prop('checked')) {
        request += "WANT_ROLE_TEACHER";
    }
    $('#userRequest').val(request);
}