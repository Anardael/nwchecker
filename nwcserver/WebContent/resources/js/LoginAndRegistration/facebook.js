(function(d, s, id) {
    console.log('function(d, s, id) {}(document, script, facebook-jssdk))');
    console.log('parametrs: ' + d + ' ' + s + ' ' + id);
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
        cookie     : true,
        xfbml      : true,
        version    : 'v2.3'
    });
};

function checkLoginState() {
    FB.logout(function(response) {});
    FB.getLoginStatus(function(response) {
        console.log(response);
        statusChangeCallback(response);
    });
}

function statusChangeCallback(response) {
    console.log('statusChangeCallback()');
    console.log(response);
    if (response.status === 'connected') {
        testAPI();
    }
}

function testAPI() {
    console.log('testAPI()');
    FB.api('/me', function(response) {
        console.log('Name: ' + response.name + '. ' + ' Email: ' + response['email']);
        location.href = 'loginFacebookUser.do?email=' + response['email'] + '&nickname=' + response['name'];
    });
}

