$('body').on('click', "#atata", function() {
                alert('a');
                var data = $('#taskModalForm_0');
                $.ajax({
                    url: '/NWCServer/newTaskJson.do',
                    type: 'POST',
                    data: new FormData($('#taskModalForm_0')),
                    processData: false,
                    contentType: false
                });
            });
            $("form").submit(function() {
                alert('submit');
            });