$(function()
{
    $(document).on('click', '.btn-add', function(e)
    {
        e.preventDefault();

        var controlForm = $('.theoryAdd .controls:first'),
                currentEntry = $(this).parents('.controls:first'),
                newEntry = $(currentEntry.clone()).appendTo('.theoryAdd');
        newEntry.find('input').val('');
        $('.theoryAdd').find('.entry:not(:last) .btn-add')
                .removeClass('btn-add').addClass('btn-remove')
                .removeClass('btn-success').addClass('btn-danger')
                .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-remove', function(e)
    {
        $(this).parents('.controls:first').remove();

        e.preventDefault();
        return false;
    });
});
