let name;
$(document).ready(function () {
    $('#upload-button').click(function () {
        var formData = new FormData();
        formData.append('file', $('#file-input')[0].files[0]);

        $.ajax({
            url: '/pc/board/postImg',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                name=String(data);
                alert(data);
                $('input[name=img]').val(data);
                setTimeout(function() {
                    $('#image-preview').html('<img src="../../img/'+name+'">');
                }, 7000);
            },
            error: function () {
                alert('이미지 업로드에 실패하였습니다.');
            }
        });
    });
});