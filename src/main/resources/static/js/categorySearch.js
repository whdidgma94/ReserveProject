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
                try {
                    var campList = $.parseJSON(result);
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

                            sbrsCl: campList[i].sbrsCl,
                            themaEnvrnCl: campList[i].themaEnvrnCl,
                        });
                    }
                    let campListBoxHtml = "";
                    for (let i = 0; i < 5; i++) {
                        campListBoxHtml += '<div><div class="tempCampBox" onClick="details(' + campList[i].contentId + ')">';
                        campListBoxHtml += '<div class="campBoxTop"><div class="campBoxLeft">';
                        campListBoxHtml += '<img style="height: 98%" src="' + campList[i].firstImageUrl + '"/></div>';
                        campListBoxHtml += '<div class="campBoxRight"><div class="campText">[ <span>' + campList[i].doNm + '</span><span>' + campList[i].sigunguNm + '</span> ]</div>';
                        campListBoxHtml += '<div class="campText">' + campList[i].facltNm + '</div>';
                        campListBoxHtml += '<div class="campText">' + campList[i].lineIntro + '</div>';
                        campListBoxHtml += '<div class="campSbrsClBox"><div class="campSbrsCl">';
                        campListBoxHtml += '<div class="campSbrsClItem">';

                        let sbrsClList = campList[i].sbrsCl.split(",");
                        for (let j = 0; sbrsClList.length; j++) {
                            if (sbrsClList[j] == "전기") {
                                campListBoxHtml += '<i class="fa-solid fa-bolt"></i>'
                            } else if (sbrsClList[j] == "무선인터넷") {
                                campListBoxHtml += '<i class="fa-solid fa-wifi"></i>'
                            } else if (sbrsClList[j] == "장작판매") {
                                campListBoxHtml += '<i class="fa-sharp fa-solid fa-fire"></i>'
                            } else if (sbrsClList[j] == "온수") {
                                campListBoxHtml += '<i class="fa-solid fa-thermometer-full"></i>'
                            } else if (sbrsClList[j] == "트렘폴린") {
                                campListBoxHtml += '<i class="fa-solid fa-person-arrow-up-from-line"></i>'
                            } else if (sbrsClList[j] == "물놀이장") {
                                campListBoxHtml += '<i class="fa-solid fa-swimmer"></i>'
                            } else if (sbrsClList[j] == "놀이터") {
                                campListBoxHtml += '<i class="fa-solid fa-rocket"></i>'
                            } else if (sbrsClList[j] == "산책로") {
                                campListBoxHtml += '<i class="fa-solid fa-hiking"></i>'
                            } else if (sbrsClList[j] == "운동시설") {
                                campListBoxHtml += '<i class="fa-solid fa-dumbbell"></i>'
                            } else if (sbrsClList[j] == "마트.편의점") {
                                campListBoxHtml += '<i class="fa-solid fa-shopping-cart"></i>'
                            } else if (sbrsClList[j] == "운동장") {
                                campListBoxHtml += '<i class="fa-solid fa-baseball-ball"></i>'
                            }
                        }
                        // let themaList;
                        // if (themaEnvrnCl) {
                        //     themaList = themaEnvrnCl[i].split(",");
                        // } else {
                        //     themaList = ["즐거운캠핑"];
                        // }
                        campListBoxHtml += '</div></div></div></div></div></div><hr></div>';
                    }

                    $('#campListBox').html(campListBoxHtml);
                } catch (e) {
                    console.log(e);
                    alert("오류가 발생했습니다.");
                }


            },
            error: function (xhr, status, error) {
            }
        });
    });
});
