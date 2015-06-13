(function(d, s, id) {
    console.log('function(d, s, id) {}(document, script, facebook-jssdk))');
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "/jsp/LoginAndRegistration/web-sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

window.fbAsyncInit = function() {
    console.log('window.fbAsyncInit = function()');
    FB.init({
        appId      : '838971806151542',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.3' // use version 2.2
    });

    /*FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });*/
};

function statusChangeCallback(response) {
    console.log('statusChangeCallback()');
    console.log(response);
    if (response.status === 'connected') {
        testAPI();
    }
}

function checkLoginState() {
    FB.getLoginStatus(function(response) {
        console.log(response);
        statusChangeCallback(response);
    });
}



function testAPI() {
    console.log('testAPI()');
    FB.api('/me', function(response) {
        console.log('Name: ' + response.name + '. ' + ' Email: ' + response['email']);
        var nickname = response.name;
        var email = response['email'];

        location.href = 'loginFacebookUser.do?email=' + email + '&nickname=' + nickname;

        /*$.ajax({
            type: 'GET',
            url: 'checkFacebookUser.do?email=' + email + '&nickname=' + nickname,
            dataType:'text',
            success: function (response) {
                if(response === 'true'){
                    location.href = 'index.do';
                    showAlertMsg(nickname, email);
                } else {
                    location.href = 'index.do';
                }
            },
            error: function() {
                console.log('Error response!');
            }
        });*/
    });
}

