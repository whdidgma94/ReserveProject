$(document).ready(function () {
    $('.details').on('click', function () {
        var id = $(this).attr('id');
        $.ajax({
            url: "/qnAInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                $('#context').text(result.context);
                $('#detailModal').modal('show');
            },
            error: function (error) {
            }
        });
    });
});

