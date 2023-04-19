$(function(){
    $("#deleteModal .btn-danger").click(function(){
        let pw = $("#deleteModal #password").val();
        $.ajax({
            type: "POST",
            url: "/member/delete",
            data: {
                pw: pw,
            },
            success: function(data){
                if(data==="true"){
                swal({
                    title: "회원 탈퇴",
                    text: "회원 탈퇴가 완료되었습니다.",
                    type: "success"
                }, function(){
                    window.location.href = "/";
                });}
                else if(data === "false"){
                    swal({
                        title: "회원 탈퇴 실패",
                        text: "비밀번호를 확인해주세요.",
                        type: "error"
                    });
                }
            },
            error: function(jqXHR, textStatus, errorThrown){

            }
        });
    });
});


function deleteMemberMobile(){
    let loginId = $("#loginId").text();
    console.log(loginId)
    $.ajax({
        url: '/member/delete?type=mobile',
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