function deleteBoardPc(boardNo){
    var no=boardNo;
location.href="/../pc/board/deleteBoard?no="+no;
}
function updateBoardPc(boardNo){
    var no=boardNo;
    location.href="/../pc/board/updateBoard?no="+no;
}
function addSecondComment(commentNo){
    var no=commentNo;

}
function deleteBoardMobile(boardNo){
    var no=boardNo;
    location.href="/../mobile/board/deleteBoard?no="+no;
}
function updateBoardMobile(boardNo){
    var no=boardNo;
    location.href="/../mobile/board/updateBoard?no="+no;
}
function deleteComment(commentNo){
    var no=commentNo;
    $.ajax({
        url: '/pc/comments/deleteComment',
        type: 'POST',
        data: no,

        success: function (data) {
            alert('댓글삭제완료.');
        },
        error: function () {
            alert('삭제실패.');
        }
    });
}
function updateComment(commentNo){
    var no=commentNo;
    $.ajax({
        url: '/pc/board/updateComment',
        type: 'POST',
        data: no,

        success: function (data) {
            alert('댓글수정완료.');
        },
        error: function () {
            alert('삭제실패.');
        }
    });
}