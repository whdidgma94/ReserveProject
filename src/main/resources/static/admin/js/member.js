$(document).ready(function () {
    $('#deleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.attr('id');
        localStorage.setItem('selectedId', id);
        var modal = $(this);
        modal.find('.modal-footer button.btn-danger').on('click', function () {
            var selectedId = localStorage.getItem('selectedId'); // 저장된 id 값을 가져옴
            $.ajax({
                url: '/admin/memberDelete',
                type: 'POST',
                data: {id: selectedId},
                success: function (response) {
                    swal('삭제 완료', '회원 정보를 삭제했습니다.', 'success');
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
            url: "/admin/memberInfo",
            type: "GET",
            data: {id: id},
            success: function (result) {
                console.log(result);
                $('#id').text(result.id);
                $('#loginId').text(result.loginId);
                $('#name').text(result.name);
                $('#birthday').text(result.regNum1);
                $('#gender').text(result.gender);
                $('#email').text(result.email);
                $('#phone').text(result.phone);
                let address = result.roadAddress+ ", " + result.detailAddress;
                $('#address').text(address);
                // 모달 보이기
                $('#detailModal').modal('show');
            },
            error: function (error) {
                swal("에러", "회원 정보를 가져오는 중 에러가 발생하였습니다.", "error");
            }
        });
    });
});

