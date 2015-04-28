var flag = false;

$('.list-group-area').focus(function(){
    $('.rule-submit-btn').show();
});

$('.list-group-area').change(function(){
    $('.rule-submit-btn').show();
    console.log("FLAG TRUE!");
    flag = true;
});

$('.list-group-area').blur(function(){
    if(flag === false){
        $('.rule-submit-btn').hide();
    }
});
