/*$(document).ready(function() {
    $.ajaxSetup({ cache: true });
    console.log("READY!");
    $.getScript('/jsp/LoginAndRegistration/web-sdk.js', function(){
        FB.init({
            appId: '838971806151542',
            version: 'v2.3' // or v2.0, v2.1, v2.0
        });
        $('#loginbutton,#feedbutton').removeAttr('disabled');
        FB.getLoginStatus(updateStatusCallback);
    });
    console.log("READY END!");
});*/

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
        testAPI();
    } else if (response.status === 'not_authorized') {
        /*$('status').text('Please log ' +
        'into this app.');*/
    } else {
        /*$('status').text('Please log ' +
        'into Facebook.');*/
    }
}

function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '838971806151542',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.3' // use version 2.2
    });

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "/jsp/LoginAndRegistration/web-sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function testAPI() {
    FB.api('/me', function(response) {
        console.log('Name: ' + response.name + '. ' + ' Email: ' + response['email']);
        var nickname = response.name;
        var email = response['email'];

        $.ajax({
            type: 'GET',
            url: 'checkFacebookUser.do?email=' + response['email'] + '&nickname=' + response.name,
            dataType:'text',
            success: function (response) {
                console.log(JSON.stringify(response));
                if(response === 'true'){
                    showAlertMsg(nickname, email);
                } else {
                    location.href = 'index.do';
                }

            },
            error: function() {
                console.log('Error response!');
            }
        });

        //location.href = 'j_spring_security_check?j_username=JNAME&j_password=JPASSWORD';
    });
}

function showAlertMsg(nickname, email){
    $('#alert-block').text('' + nickname + ', we send your login and password to ' + email);
    $('#facebookModal').modal();
}

function goHome() {
    location.href = 'index.do';
}