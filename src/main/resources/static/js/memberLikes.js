function addLike(element) {
    if (log === null) {
        swal('로그인 후 이용해주세요.', '', 'error').then(function () {
            location.href = '/pc/member/login';
        });
        return;
    }
    if (element.innerHTML === '♡') {
        $.ajax({
            url: "/addLikes",
            type: "post",
            data: {contentId: element.id},
            success: function (result) {
                element.innerHTML = '♥';
                swal(element.id, "목록에 추가되었습니다", "success");
            }
        })


    } else {
        $.ajax({
            url: "/deleteLikes",
            type: "post",
            data: {contentId: element.id},
            success: function (result) {
                element.innerHTML = '♡';
                swal(element.id, "목록에서 제외되었습니다", "success");
            }
        })

    }
}

function details(element) {
    let contentId = element.id;
    location.href = "/pc/detailCamp?contentId=" + contentId;
}