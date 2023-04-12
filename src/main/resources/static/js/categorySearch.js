$(function () {
    $('.categoryCheckBox input[type=checkbox]').click(function () {
        var selectedCategories = $('.categoryCheckBox input[type=checkbox]:checked')
            .map(function () {
                return $(this).attr('id');
            }).get();

        $.ajax({
            url: '/search/categoryCheck',
            method: 'GET',
            data: {categories: selectedCategories.join(",")},
            success: function (result) {
                var response = $.parseJSON(result);
                var campList = response.campList;
                if(campList == null || campList.length == 0) {
                    alert("검색결과가 존재하지 않습니다.");
                    총리스트의길이=0;
                    return;
                }

            },
            error: function (xhr, status, error) {
            }
        });
    });
});
