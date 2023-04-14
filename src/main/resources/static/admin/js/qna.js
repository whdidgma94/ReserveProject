$(document).ready(function () {
    $('.details').on('click', function () {
        var id = $(this).attr('id');
        $.ajax({
            url: "/qnAInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                $('#category').text(result.category);
                $('#sender').text(result.sender);
                $('#date').text(result.date);
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
                $('#answerModal').modal('show');
            },
            error: function (error) {
            }
        });
    });

});
