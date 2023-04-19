$(function () {
    $("#deleteModal .btn-danger").click(function () {
        let pw = $("#deleteModal #password").val();
        $.ajax({
            type: "POST",
            url: "/member/delete",
            data: {
                pw: pw,
            },
            success: function (data) {
                if (data === "true") {
                    swal('탈퇴 완료', '이용해 주셔서 감사합니다.', 'success').then(function () {
                        window.location.href = "/pc/main";
                    });
                } else if (data === "false") {
                    swal("비밀번호 오류", "비밀번호를 확인해주세요", "error")
                }
            }
            ,
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
    });
});