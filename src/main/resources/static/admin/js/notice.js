$(document).ready(function () {
    $('#deleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.attr('id');
        localStorage.setItem('selectedId', id);
        var modal = $(this);
        modal.find('.modal-footer button.btn-danger').on('click', function () {
            var selectedId = localStorage.getItem('selectedId'); // 저장된 id 값을 가져옴
            $.ajax({
                url: '/admin/deleteNotice',
                type: 'POST',
                data: {id: selectedId},
                success: function (response) {
                    swal('삭제 완료', '공지를 삭제했습니다.', 'success');
                    history.go(0);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
    $('.btn-info').on('click', function () {
        var id = $(this).attr('id');
        $.ajax({
            url: "/noticeInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                console.log(result);
                $('#id').text(result.id);
                $('#subject').text(result.subject);
                $('#date').text(result.date);
                $('#context').text(result.context);
                $('#detailModal').modal('show');
            },
            error: function (error) {
                swal("에러", "공지를 가져오는 중 에러가 발생하였습니다.", "error");
            }
        });
    });
});

