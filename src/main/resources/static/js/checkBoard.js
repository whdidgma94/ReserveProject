function validateFormBoard() {
    var title = document.getElementById("title").value.trim();
    var content = document.getElementById("content").value.trim();

    if (title == "" || content == "") {
        alert("제목과 내용은 필수 입력사항입니다.");
        return false;
    }
}