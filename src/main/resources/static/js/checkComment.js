function validateFormComment() {
    var comment=document.getElementById("comment").value.trim();


    if (comment == "" ) {
        alert("내용을 입력하세요");
        return false;
    }
}