function checkLoginPc(log) {
    var log = log;
    if (log == "") {
        location.href = "/../pc/member/login";
    } else {
        location.href = "/../pc/board/addBoard";
    }
}
function checkLoginMobile(log) {
    var log = log;
    if (log == "") {
        location.href = "/../mobile/member/login";
    } else {
        location.href = "/../mobile/board/addBoard";
    }
}
