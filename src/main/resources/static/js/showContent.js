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
function deleteComment(){


    alert(no);
    $.ajax({
        url: '/pc/comments/deleteComment',
        type: 'POST',
        data: {no: no},
        success: function (data) {
            alert('삭제성공');
            window.location.href = '/pc/board/showContent?no='+boardNo; // 해당 URL로 이동

        },
        error: function () {
            alert('삭제실패.');
        }
    });
}
function updateComment(boardNo){
    var no=boardNo;
    location.href="/../pc/comments/updateComment?no="+no;
}
// function updateComment(commentNo,boardNo){
//     var no=commentNo;
//     $.ajax({
//         url: '/pc/board/updateComment',
//         type: 'POST',
//         data: {no:no},
//
//         success: function (data) {
//             alert('댓글수정완료.');
//             window.location.href = '/pc/board/showContent?no='+boardNo;
//         },
//         error: function () {
//             alert('삭제실패.');
//         }
//     });
// }