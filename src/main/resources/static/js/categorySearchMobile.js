let 현재페이지 = 1;
let 총리스트의길이;
let 한페이지에보여줄게시글수 = 10;
let 한번에보여줄페이지단위 = 10;
let 총페이지수 ;
let 현재페이지인덱스 = 1;

function details(contentId) {
    location.href = "/mobile/detailCamp?contentId=" + contentId;
}

window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('Id');
    const checkbox = document.querySelector(`input[type=checkbox][id='${id}']`);
    if (checkbox) {
        checkbox.checked = true;
        paging(1);
    }
    현재페이지 = 1;
    // makePageNum();
    const title01 = document.querySelector(".title01");
    const check01 = document.querySelector(".check01");
    const title02 = document.querySelector(".title02");
    const check02 = document.querySelector(".check02");
    const title03 = document.querySelector(".title03");
    const check03 = document.querySelector(".check03");
    const title04 = document.querySelector(".title04");
    const check04 = document.querySelector(".check04");
    const title05 = document.querySelector(".title05");
    const check05 = document.querySelector(".check05");
    title01.addEventListener('click',()=>{
        if(title01.classList.contains('viewCheck')){
            check01.style.display='none';
            title01.classList.remove('viewCheck');
        }else{
            check01.style.display='block';
            title01.classList.add('viewCheck');
        }
    })
    title02.addEventListener('click',()=>{
        if(title02.classList.contains('viewCheck')){
            check02.style.display='none';
            title02.classList.remove('viewCheck');
        }else{
            check02.style.display='block';
            title02.classList.add('viewCheck');
        }
    })
    title03.addEventListener('click',()=>{
        if(title03.classList.contains('viewCheck')){
            check03.style.display='none';
            title03.classList.remove('viewCheck');
        }else{
            check03.style.display='block';
            title03.classList.add('viewCheck');
        }
    })
    title04.addEventListener('click',()=>{
        if(title04.classList.contains('viewCheck')){
            check04.style.display='none';
            title04.classList.remove('viewCheck');
        }else{
            check04.style.display='block';
            title04.classList.add('viewCheck');
        }
    })
    title05.addEventListener('click',()=>{
        if(title05.classList.contains('viewCheck')){
            check05.style.display='none';
            title05.classList.remove('viewCheck');
        }else{
            check05.style.display='block';
            title05.classList.add('viewCheck');
        }
    })
};

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

                    let campListBoxHtml = '<div class="row">';
                    for (let i = 0; i < campList.length; i++) {
                        campListBoxHtml += '<div class="campLikeBox"><div> 추천수 : ' + campList[i].recommendCnt + '</div>'
                        campListBoxHtml += '<div class="btn memberLikeBtn" for="memberLike" id="'+campList[i].contentId+' onclick="addLike(this)">♡</div></div>'
                        campListBoxHtml += '<div class="tempCampBox" onclick="location.href=\'../detailCamp?contentId= ' + campList[i].contentId + ' \'">';
                        campListBoxHtml += '<div class="campBoxTop">';
                        campListBoxHtml += '<div class="campBoxLeft">';
                        campListBoxHtml += '<img src="' + campList[i].firstImageUrl + '" onerror="this.src=\'../../img/어서와양_사진없음.png\'"/>';
                        campListBoxHtml += '</div>';
                        campListBoxHtml += '<div class="campBoxRight">';
                        campListBoxHtml += '<div>';
                        campListBoxHtml += '<h4 class="campText">' + campList[i].facltNm + '</h4>';
                        campListBoxHtml += '<div class="campText">[ ' + campList[i].doNm + ' ' + campList[i].sigunguNm + ' ]</div>';
                        campListBoxHtml += '</div>';
                        campListBoxHtml += '<div class="campText">' + campList[i].lineIntro + '</div>';
                        campListBoxHtml += '</div></div></div>';
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
    };

function makePageNum() {
    $('#paging').html('');
    let pageBtn = '';
    for (let i = 1; i <= 한번에보여줄페이지단위; i++) {
        if (i == 1 && 현재페이지인덱스 != 1) {
            pageBtn += '<button class="paging-btn" onclick="previousPageIndex()">이전</button>'
        }
        if(한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i == 현재페이지){pageBtn += '<a class="curPageNum" href="#" onclick="paging(' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ')">' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + '</a>'}
        else{pageBtn += '<a href="#" onclick="paging(' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + ')">' + (한번에보여줄페이지단위 * (현재페이지인덱스 - 1) + i) + '</a>'}
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

function addLike(element){
    if(element.innerHTML === '♡'){
        $.ajax({
            url: "/addLikes",
            type:"post",
            data:{contentId : element.id},
            success:function (result){
                swal(element.id,"목록에 추가되었습니다","success");
                element.innerHTML = '♥';
            }
        })


    } else {
        $.ajax({
            url: "/deleteLikes",
            type:"post",
            data:{contentId : element.id},
            success:function (result){
                element.innerHTML = '♡';
                swal(element.id,"목록에서 제외되었습니다","success");
            }
        })

    }
}


