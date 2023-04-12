$(document).ready(function () {
    $('.details').on('click', function () {
        var id = $(this).attr('id');
        $.ajax({
            url: "/noticeInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                $('#context').text(result.context);
                $('#detailModal').modal('show');
            },
            error: function (error) {
                swal("에러", "공지를 가져오는 중 에러가 발생하였습니다.", "error");
            }
        });
    });
});

