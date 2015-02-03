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

function setRequestsWantRoleTeacher(){
    var request = "WANT_ROLE_TEACHER";
    $.ajax({
        url:"addUserRequest.do",
        type:"POST",
        data: {request:request},
        success:function(data){
            $('#userRequestTeacher').hide();
        },
        error: function() {
            console.log('fail');
            console.log(typeof data);
        }
    });
}