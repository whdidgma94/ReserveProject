let 현재페이지 = 1;
let 한페이지에보여줄게시글수 = 10;
let 한번에보여줄페이지단위 = 10;
let 총페이지수 ;
let 현재페이지인덱스 = 1;

function details(contentId) {
    location.href = "/pc/detailCamp?contentId=" + contentId;
}

$(function () {
    $('#search-icon').click(function () {
        var selectedCategories = $('.categoryCheckBox input[type=checkbox]:checked, input[type=radio]:checked')
            .map(function () {
                return $(this).attr('id');
            }).get();
        console.log(selectedCategories)
        $.ajax({
            url: '/search/categoryCheck',
            method: 'GET',
            data: {
                categories: selectedCategories.join(","),
                pageNum: 현재페이지인덱스
            },
            success: function (result) {
                try {
                    var data = JSON.parse(result)
                    var campCount = data.count;
                    var campList = data.list;
                    if (campList == null || campList.length == 0) {
                        alert("검색결과가 존재하지 않습니다.");
                        location.reload();
                        return;
                    }
                    areaArr = new Array();
                    for (let i = 0; i < 한페이지에보여줄게시글수; i++) {
                        console.log("ajax시작");
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
                    console.log("ajax반복시작");
                    let campListBoxHtml = "";
                    for (let i = 0; i < 한페이지에보여줄게시글수; i++) {
                        console.log("ajax반복중");
                        campListBoxHtml += '<div><div class="tempCampBox" onClick="details(' + campList[i].contentId + ')">';
                        campListBoxHtml += '<div class="campBoxTop"><div class="campBoxLeft">';
                        campListBoxHtml += '<img style="height: 98%" src="' + (campList[i].firstImageUrl || '../img/어서와영_사진없음.png') + '"/></div>';
                        campListBoxHtml += '<div class="campBoxRight"><div class="campText">[ <span>' + campList[i].doNm + '</span><span>' + campList[i].sigunguNm + '</span> ]</div>';
                        campListBoxHtml += '<div class="campText">' + campList[i].facltNm + '</div>';
                        campListBoxHtml += '<div class="campText">' + campList[i].lineIntro + '</div>';
                        campListBoxHtml += '<div class="campSbrsClBox">';
                        let sbrsClList = campList[i].sbrsCl.split(",");
                        for (let j = 0; j < sbrsClList.length; j++) {
                            campListBoxHtml += '<div class="campSbrsCl"><div class="campSbrsClItem">';
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
                            campListBoxHtml += '</div></div>'
                        }
                        // let themaList;
                        // if (themaEnvrnCl) {
                        //     themaList = themaEnvrnCl[i].split(",");
                        // } else {
                        //     themaList = ["즐거운캠핑"];
                        // }
                        campListBoxHtml += '</div></div></div></div></div><hr></div>';
                    }
                    $('#campListBox').html(campListBoxHtml);
                    let campCountHTML = "총 " + campCount + " 개의 캠핑장이 검색되었습니다."
                    $('#camplistCount').html(campCountHTML);
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

function makePageNum() {
    $('#paging').html('');
    let pageBtn = '';
    for (let i = 1; i <= 한번에보여줄페이지단위; i++) {
        if (i == 1 && 현재페이지인덱스 != 1) {
            pageBtn += '<button onclick="previousPageIndex()">이전</button>'
        }
        pageBtn += '<a href="#" onclick="paging(' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ')">[' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ']</a>'
        if (i == 한번에보여줄페이지단위 && (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) < 총페이지수) {
            pageBtn += '<button onclick="nextPageIndex()">다음</button>'
        }
        if ((한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) == 총페이지수) {
            break;
        }
    }
    $('#paging').html(pageBtn);
}
function nextPageIndex() {
    현재페이지인덱스++;
    makePageNum();
    paging((현재페이지인덱스 - 1) * 한번에보여줄페이지단위 + 1);
}
function previousPageIndex() {
    현재페이지인덱스--;
    makePageNum();
    paging((현재페이지인덱스 - 1) * 한번에보여줄페이지단위 + 1);
}
function insertPageNum(i) {
    현재페이지인덱스 = Math.ceil($('#inputPageNum').val() / 10);
    makePageNum();
    paging(i);
}

