
    function databaseUpdate() {
    $.ajax({
    type: 'POST',
    url: '/updateDatabase',
    success: function (response) {
    swal('갱신 완료', '데이터베이스가 갱신되었습니다', 'success');
},
    error: function () {
    swal('갱신 실패', '데이터베이스 정보를 갱신할 수 없습니다', 'error');
}
});
}