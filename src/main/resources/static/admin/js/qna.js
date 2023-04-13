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
    $('.answer').on('click', function () {
        var id = $(this).attr('id');
        $.ajax({
            url: "/qnAAnswer",
            type: "GET",
            data: {id: id},
            success: function (result) {
                $('#answerCategory').text(result.category);
                $('#answerSender').text(result.sender);
                $('#answerDate').text(result.date);
                $('#answerContext').text(result.context);
                $('#detailModal').modal('show');
            },
            error: function (error) {
            }
        });
    });

});
