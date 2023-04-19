let noticeId;
$(document).ready(function () {
    $('#deleteModal').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget);
        let id = button.attr('id');
        localStorage.setItem('selectedId', id);
        let modal = $(this);
        modal.find('.modal-footer button.btn-danger').on('click', function () {
            let selectedId = localStorage.getItem('selectedId'); // 저장된 id 값을 가져옴
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
        let id = $(this).attr('id');
        console.log(id);
        noticeId = id;
        $.ajax({
            url: "/noticeInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                console.log(result);
                $('#id').val(result.id);
                $('#subject').val(result.subject);
                $('#date').val(result.date);
                $('#context').val(result.context);
                $('#detailModal').modal('show');
            },
            error: function (error) {
                swal("에러", "공지를 가져오는 중 에러가 발생하였습니다.", "error");
            }
        });
    });
});

function update() {
    let subject = $("#subject").val();
    let context = $("#context").val();
    $.ajax({
        url: "/noticeUpdate",
        type: "post",
        data: {
            id: noticeId,
            subject: subject,
            context: context
        },

        success: function (result) {
            swal("수정 완료", "공지가 수정되었습니다", "success").then(function () {
                location.href = '/noticeList?type=admin';
            })
        },
        error: function (error) {
            swal("에러", "공지를 가져오는 중 에러가 발생하였습니다.", "error");
        }
    });
}