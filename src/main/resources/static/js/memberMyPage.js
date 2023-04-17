function deleteMemberPc(){
    let loginId = $("#loginId").text();
    console.log(loginId)
    $.ajax({
        url: '/member/delete',
        type: 'POST',
        data: {loginId: loginId},
        success: function (response) {
            swal('탈퇴 완료', '이용해 주셔서 감사합니다.', 'success').then(function () {
                location.href = '/pc/main';
            });

        },
        error: function (error) {
            console.log(error);
        }
    });
}
function deleteMemberMobile(){
    let loginId = $("#loginId").text();
    console.log(loginId)
    $.ajax({
        url: '/member/delete',
        type: 'POST',
        data: {loginId: loginId},
        success: function (response) {
            swal('탈퇴 완료', '이용해 주셔서 감사합니다.', 'success').then(function () {
                location.href = '/mobile/main';
            });

        },
        error: function (error) {
            console.log(error);
        }
    });
}