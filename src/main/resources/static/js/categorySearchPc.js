let 현재페이지 = 1;
let 총리스트의길이;
let 한페이지에보여줄게시글수 = 10;
let 한번에보여줄페이지단위 = 10;
let 총페이지수;
let 현재페이지인덱스 = 1;


window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('Id');
    const checkbox = document.querySelector(`input[type=checkbox][id='${id}']`);
    if (checkbox) {
        checkbox.checked = true;
        document.getElementById("search-icon").click();
    }
}

function details(contentId) {
    location.href = "/pc/detailCamp?contentId=" + contentId;
}

$(function () {
    $('#search-icon').click(function () {
        paging(1);
    });
});

function paging(i) {
    현재페이지 = i;
    let selectedCategories = $('.categoryCheckBox input[type=checkbox]:checked')
        .map(function () {
            return $(this).attr('id');
        }).get();
    console.log(selectedCategories)
    $.ajax({
        url: '/search/categoryCheck',
        method: 'GET',
        data: {
            categories: selectedCategories.join(","),
            pageNum: 현재페이지
        },
        success: function (result) {
            try {
                let data = JSON.parse(result)
                총리스트의길이 = data.count;
                let campList = data.list;
                if (campList == null || campList.length == 0) {
                    alert("검색결과가 존재하지 않습니다.");
                    location.reload();
                    return;
                }
                if (총리스트의길이 % 10 === 0) {
                    총페이지수 = 총리스트의길이 / 한페이지에보여줄게시글수;
                } else {
                    총페이지수 = Math.ceil(총리스트의길이 / 한페이지에보여줄게시글수);
                }
                console.log("ajax반복시작");
                let campCountHTML = "총 " + 총리스트의길이 + " 개의 캠핑장이 검색되었습니다."
                $('#campListCount').html(campCountHTML);

                let campListBoxHtml = "";
                for (let i = 0; i < campList.length; i++) {
                    campListBoxHtml += '<div class="campLikeBox"><div> 추천수 : ' + campList[i].recommendCnt + '</div>'
                    campListBoxHtml += '<div><label for="memberLike" id="' + campList[i].contentId + '" class="btn memberLikeBtn" style="width: 100%;height: 100%; border:red 0px solid;color: red;font-size: 40px;font-weight: bold" onclick="addLike(this)">♡</label></div></div>'
                    campListBoxHtml += '<div><div class="tempCampBox" onClick="details(' + campList[i].contentId + ')">';
                    campListBoxHtml += '<div class="campBoxTop"><div class="campBoxLeft">';
                    campListBoxHtml += '<img style="height: 98%" src="' + campList[i].firstImageUrl + '" onerror="this.src=\'../../img/어서와양_사진없음.png\'"/></div>';
                    campListBoxHtml += '<div class="campBoxRight"><div class="campText">[ <span>' + campList[i].doNm + '</span> <span>' + campList[i].sigunguNm + '</span> ]</div>';
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
                    campListBoxHtml += '</div></div></div>'
                    campListBoxHtml += '<div class="campBoxBottom"><div class="campThema">'

                    if (campList[i].themaEnvrnCl === "") {
                        campList[i].themaEnvrnCl = "즐거운캠핑";
                    } else {
                        campList[i].themaEnvrnCl += ",즐거운캠핑";
                    }
                    console.log(campList[i].themaEnvrnCl)
                    let themaList = campList[i].themaEnvrnCl.split(",");
                    console.log(themaList)
                    for (let j = 0; j < themaList.length; j++) {
                        campListBoxHtml += '#' + themaList[j] + ' '
                        console.log(themaList[j])
                    }
                    campListBoxHtml += '</div></div></div></div><hr></div>';
                }
                $('#campListBox').html(campListBoxHtml);
                console.log("ajax반복끝")
                makePageNum();
            } catch (e) {
                console.log(e);
                alert("오류가 발생했습니다.");
            }
        },
        error: function (xhr, status, error) {
        }
    });
}

function makePageNum() {
    $('#paging').html('');
    console.log("페이지만들기")
    let pageBtn = '';
    for (let i = 1; i <= 한번에보여줄페이지단위; i++) {
        if (i == 1 && 현재페이지인덱스 != 1) {
            pageBtn += '<button class="paging-btn" onclick="previousPageIndex()">이전</button>'
        }
        if (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i == 현재페이지) {
            pageBtn += '<a class="curPageNum" href="#" onclick="paging(' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ')">' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + '</a>'
        } else {
            pageBtn += '<a href="#" onclick="paging(' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ')">' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + '</a>'
        }
        if (i == 한번에보여줄페이지단위 && (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) < 총페이지수) {
            pageBtn += '<button class="paging-btn" onclick="nextPageIndex()">다음</button>'
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

function addLike(element) {
    if (log === null) {
        swal('로그인 후 이용해주세요.', '', 'error').then(function () {
            location.href = '/pc/member/login';
        });
        return;
    }
    if (element.innerHTML === '♡') {
        $.ajax({
            url: "/addLikes",
            type: "post",
            data: {contentId: element.id},
            success: function (result) {
                element.innerHTML = '♥';
                swal(element.id, "목록에 추가되었습니다", "success");
            }
        })


    } else {
        $.ajax({
            url: "/deleteLikes",
            type: "post",
            data: {contentId: element.id},
            success: function (result) {
                element.innerHTML = '♡';
                swal(element.id, "목록에서 제외되었습니다", "success");
            }
        })

    }
}

