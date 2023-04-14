function details(contentId) {
    location.href = "/pc/detailCamp?contentId=" + contentId;
}



let cltList = ["해변", "섬", "산", "숲", "계곡", "강", "호수", "도심"];
let index = 0;
let ranCltBox = document.getElementById("rancltCl");

window.onload = function () {
    ranCltBox = document.getElementById("rancltCl");
    if (ranCltBox !== null) {
        ranCltBox.innerHTML = cltList[index];
        changeView(cltList[index]);
    }
};

function prevWord() {
    if (index <= 0) {
        index = cltList.length - 1;
    } else {
        index--;
    }
    if (ranCltBox !== null) {
        ranCltBox.innerHTML = cltList[index];
    }
    changeView(cltList[index]);
}

function nextWord() {
    if (index >= cltList.length - 1) {
        index = 0;
    } else {
        index++;
    }
    if (ranCltBox !== null) {
        ranCltBox.innerHTML = cltList[index];
    }
    changeView(cltList[index]);
}

ranCltBox.innerHTML = cltList[index];
changeView(cltList[index]);

function changeView(lct) {
    $.ajax({
        type: "GET",
        url: "/camp/ranView",
        data: {
            lctCl: lct
        },
        success: function (result) {
            try {
                let campList = $.parseJSON(result);
                if (campList == null || campList.length == 0) {
                    alert("검색결과가 존재하지 않습니다.");
                    return;
                }
                let areaArr = new Array(); // areaArr 변수 추가
                for (let i = 0; i < 5; i++) {
                    areaArr.push({ // areaArr에 값을 push
                        firstImageUrl: campList[i].firstImageUrl,
                        contentId: campList[i].contentId,
                    });
                }
                let ranViewImageBoxHtml = "";
                for (let i = 0; i < 5; i++) {
                    ranViewImageBoxHtml += '<div class="ranViewImage" onClick="details(' + areaArr[i].contentId + ')">';
                    ranViewImageBoxHtml += '<img style="width: 200%" src="' + (areaArr[i].firstImageUrl || '../../img/어서와영_사진없음.png') + '" /></div>';
                }
                $('#ranViewImageBox').html(ranViewImageBoxHtml);
            } catch (e) {
                console.log(e);
                alert("오류가 발생했습니다.");
            }
        },
        error: function () {
            console.log("Error occurred during data fetching.");
            alert("데이터를 불러오는 중 오류가 발생했습니다.");
        }
    });
}


