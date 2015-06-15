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
    FB.logout();
    FB.getLoginStatus(function(response) {
        if (response.status === 'connected') {
            FB.api('/me', function(response) {
                if(response){
                    location.href = 'loginFacebookUser.do?email=' + response['email'] + '&nickname=' + response['name'];
                } else {
                    console.log("NO RESPONSE!")
                    checkLoginState();
                }
            });
        }
    });
}


