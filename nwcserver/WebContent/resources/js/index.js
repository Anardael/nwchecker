$(document).ready(function () {
    if(window.isFirstLogin === 'true'){
        var headerText = $('#title-text').text();
        var bodyText = $('#alert').text();
        $('#title-text').text(headerText + window.nickname);
        $('#alert').text(window.nickname + bodyText + window.email) + '.';
        $('#facebookModal').modal();
    }
});
