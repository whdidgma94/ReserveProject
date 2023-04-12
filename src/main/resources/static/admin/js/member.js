
$(document).ready(function() {
    $('#deleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // 버튼 객체
        var id = button.attr('id'); // 클릭한 버튼의 id 값
        localStorage.setItem('selectedId', id); // id 값을 로컬 스토리지에 저장

        var modal = $(this);
        modal.find('.modal-footer button.btn-danger').on('click', function() {
            var selectedId = localStorage.getItem('selectedId'); // 저장된 id 값을 가져옴
            $.ajax({
                url: '/admin/memberDelete',
                type: 'POST',
                data: { id: selectedId },
                success: function(response) {
                    swal('삭제 완료', '회원 정보를 삭제했습니다.', 'success');
                    history.go(0);
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
    });
});

// "상세보기" 버튼 클릭 시
$('.btn-info').on('click', function () {
    // 클릭한 버튼의 id 값 가져오기
    var id = $(this).attr('id');
    // id 값을 /member/info 로 보내서 정보 가져오기
    $.ajax({
        url: "/admin/memberInfo",
        type: "GET",
        data: { id: id },
        success: function (result) {
            // 가져온 정보를 모달에 채워넣기
            $('#id').text(result.id);
            $('#loginId').text(result.loginId);
            $('#name').text(result.name);
            $('#birthday').text(result.regNum1);
            $('#gender').text(result.gender);
            $('#email').text(result.email);
            $('#phone').text(result.phone);
            $('#address').text(result.road_address+" "+detail_address);
            // 모달 보이기
            $('#detailModal').modal('show');
        },
        error: function (error) {
            swal("에러", "회원 정보를 가져오는 중 에러가 발생하였습니다.", "error");
        }
    });
});
