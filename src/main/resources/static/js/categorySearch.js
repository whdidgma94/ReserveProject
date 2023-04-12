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

                            sbrsCl: campList[i].sbrsCl,
                            themaEnvrnCl: campList[i].themaEnvrnCl,
                        });
                    }
                    const sbrsClList = sbrsCl.split(",");
                    let themaList;
                    if (themaEnvrnCl) {
                        themaList = themaEnvrnCl.split(",");
                    } else {
                        themaList = ["즐거운캠핑"];
                    }

                    // let campListBoxHtml = "";
                    // for (let i = 0; i < 5; i++) {
                    //     campListBoxHtml += '<div><div class="tempCampBox" onClick="details(' + campList[i].contentId + ')">';
                    //     campListBoxHtml += '<div className="campBoxTop"><div className="campBoxLeft">';
                    //     campListBoxHtml += '<img style="height: 98%" src="'+campList[i].firstImageUrl+'"/></div>';
                    //     campListBoxHtml += ' <div className="campBoxRight"><div className="campText">[ <span>'+campList[i].doNm+'</span><span>'+campList[i].sigunguNm+'</span> ]</div>';
                    //     campListBoxHtml += ;
                    //     campListBoxHtml += ;
                    //     campListBoxHtml += ;
                    //     campListBoxHtml += ;
                    //     campListBoxHtml += ;
                    // }
                    //                 <div className="campText">(주)디노담양힐링파크 지점</div>
                    //                 <div className="campText">담양 힐링파크에서 일상 속 쌓인 스트레스를 풀어보자</div>
                    //                 <div className="campSbrsClBox">
                    //                     <div className="campSbrsCl">
                    //                         <div className="campSbrsClItem">
                    //                             <i className="fa-solid fa-bolt"></i>
                    //                         </div>
                    //                     </div>
                    //                 </div>
                    //             </div>
                    //         </div>
                    //     </div>
                    //     <hr>
                    // </div>


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
