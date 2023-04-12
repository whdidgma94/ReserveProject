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
                if (campList == null || campList.length == 0) {
                    alert("검색결과가 존재하지 않습니다.");

                    return;
                }
                areaArr = new Array();
                for (let i = 0; i < campList.length; i++) {
                    areaArr.push({
                        contentId: campList[i].contentId,
                        firstImageUrl: campList[i].firstImageUrl,
                        doNm: campList[i].doNm,
                        sigunguNm: campList[i].sigunguNm,
                        facltNm: campList[i].facltNm,
                        lineIntro: campList[i].lineIntro,

                        lineInt: campList[i].lineIntro,
                        int: campList[i].intro,
                        phone: campList[i].tel,
                        facil: campList[i].sbrsCl,
                        id: campList[i].contentId

                    });
                }


            },
            error: function (xhr, status, error) {
            }
        });
    });
});
