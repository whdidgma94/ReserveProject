
    let 현재페이지;
    let 총리스트의길이;
    let 한페이지에보여줄게시글수=10;
    let 한번에보여줄페이지단위=10;
    let 총페이지수;
    let 현재페이지인덱스;


    let sido;
    let sigoon;
    const replaceList = [

    ["서울특별시", "서울시"],
    ["부산광역시", "부산시"],
    ["대구광역시", "대구시"],
    ["인천광역시", "인천시"],
    ["광주광역시", "광주시"],
    ["대전광역시", "대전시"],
    ["울산광역시", "울산시"],
    ["세종특별자치시", "세종시"],
    ["제주특별자치도", "제주도"]



    // ... 추가 조건
    ];
    let key='F3DA23BB-17C0-3F0B-87F2-FEC846489FDC';
    $.support.cors = true;

    $(function(){
    $.ajax({
        type: "get",
        url: "https://api.vworld.kr/req/data?key=F3DA23BB-17C0-3F0B-87F2-FEC846489FDC&domain=http://localhost:8080&service=data&version=2.0&request=getfeature&format=json&size=1000&page=1&geometry=false&attribute=true&crs=EPSG:3857&geomfilter=BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)&data=LT_C_ADSIDO_INFO",
        async: false,
        dataType: 'jsonp',
        success: function(data) {
            let html = "<option>전체</option>";

            data.response.result.featureCollection.features.forEach(function(f){
                console.log(f.properties)
                let locationCd = f.properties.ctprvn_cd;
                let locationNm = f.properties.ctp_kor_nm;
                for(let i=0;i<replaceList.length;i++){
                    if(locationNm==replaceList[i][0]){
                        locationNm=replaceList[i][1];
                    }
                }

                html +=`<option value="${locationCd}">${locationNm}</option>`

            })

            $('#sido_code').html(html);

        },
        error: function(xhr, stat, err) {}
    });


    $(document).on("change","#sido_code",function(){
    let thisVal = $(this).val();

    $.ajax({
    type: "get",
    url: "https://api.vworld.kr/req/data?key=F3DA23BB-17C0-3F0B-87F2-FEC846489FDC&domain=http://localhost:8080&service=data&version=2.0&request=getfeature&format=json&size=1000&page=1&geometry=false&attribute=true&crs=EPSG:3857&geomfilter=BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)&data=LT_C_ADSIGG_INFO",
    data : {attrfilter : 'sig_cd:like:'+thisVal},
    async: false,
    dataType: 'jsonp',
    success: function(data) {
    let html = "<option>전체</option>";
    let addedValues = {}; // 중복 검사를 위한 객체

    data.response.result.featureCollection.features.forEach(function(f){
    console.log(f.properties)
    let locationCd = f.properties.sig_cd;
    let locationNm = f.properties.sig_kor_nm;
    locationNm = locationNm.replace(/\s+.*/, '');

    // 중복 검사 수행
    if (!addedValues[locationNm]) {
    html +=`<option value="${locationCd}">${locationNm}</option>`
    addedValues[locationNm] = true; // 중복 검사를 위한 객체에 값 추가
}





})
    $('#sigoon_code').html(html);
    $('#dong_code').html(html);


},
    error: function(xhr, stat, err) {}
});
});

})
    function search() {
    sido = $('#sido_code option:selected').text();
    sigoon = $('#sigoon_code option:selected').text();

    $.ajax({

    url: '/search/addressSearchTest',
    type: 'GET',
    data: {
    sido: sido, sigoon: sigoon, pageNum: 1,pageRequest: 한페이지에보여줄게시글수

},

    success: function(result) {

    try {
    var response = $.parseJSON(result);
    var campList = response.campList;
    총리스트의길이 = response.length;

    if(campList == null || campList.length == 0) {
    alert("검색결과가 존재하지 않습니다.");
    총리스트의길이=0;
    return;
}




    $('#sample').html('');
    현재페이지=1;
    현재페이지인덱스=1;
    let goPage='<input id="inputPageNum" type="number" class="form-control me-2" style="max-width: 100px;">\n' +
    '    <button onclick="insertPageNum($(\'#inputPageNum\').val())" class="btn btn-primary">이동</button>';
    $('#pagingInsert').html('');
    $('#pagingInsert').html(goPage);

    if(총리스트의길이%10==0){
    총페이지수=총리스트의길이/한페이지에보여줄게시글수;
}
    else{
    총페이지수=Math.ceil(총리스트의길이/한페이지에보여줄게시글수);
}
        let resultSize='<h1>총&nbsp;'+총리스트의길이+'&nbsp;개의 캠핑장이 검색되었습니다.<br> 현재페이지&nbsp;&nbsp;'+현재페이지+'&nbsp;/&nbsp;'+총페이지수+'</h1>';
        $('#resultSize').html('');
        $('#resultSize').html(resultSize);
    areaArr = new Array();

    for(let i = 0; i < campList.length; i++) {
    areaArr.push({
    name: campList[i].facltNm,
    sido: campList[i].doNm,
    sigoon: campList[i].sigunguNm,
    address: campList[i].addr1,
    img: campList[i].firstImageUrl,
    theme: campList[i].themaEnvrnCl,
    lineInt: campList[i].lineIntro,
    int: campList[i].intro,
    phone: campList[i].tel,
    facil: campList[i].sbrsCl,
    id: campList[i].contentId

});
}

    let campListHtml = '<div class="row">';
    for(let i = 0; i < areaArr.length; i++) {
    campListHtml += '<div class="col-md-6 col-lg-4">';
    campListHtml += '<div class="card">';
    campListHtml += '<img style="width:300px;height:150px;" src="' + (areaArr[i].img || '../img/어서와영_사진없음.png') + '" alt="">';
    campListHtml += '<div class="card-body">';
    campListHtml += '<h5 class="card-title"><a href="../pc/detailCamp?contentId='+areaArr[i].id +'">'+areaArr[i].name + '</a> </h5>';
    campListHtml += '<p class="card-text">' + areaArr[i].sido+ '&nbsp;&nbsp;&nbsp;'+areaArr[i].sigoon+ '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].address + '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].lineInt+'</p>';
    campListHtml += '<p class="card-text">' + (areaArr[i].int.length > 30 ? areaArr[i].int.slice(0, 30) + '..' : areaArr[i].int) + '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].phone+'</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].facil+'</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].theme + '</p>';
    campListHtml += '</div></div></div>';
}
    campListHtml += '</div>';
    $('#campList').html(campListHtml);
    makePageNum();

} catch (e) {
    console.log(e);
}
},

});

}
    function paging(i){

    현재페이지=i;
    $.ajax({

    url: '/search/addressSearchTestTest',
    type: 'GET',
    data: {
    sido: sido, sigoon: sigoon, pageNum: i,pageRequest: 한페이지에보여줄게시글수

},

    success: function(result) {

    try {
    var response = $.parseJSON(result);
    var campList = response.campList;


    if(campList == null || campList.length == 0) {
    alert("검색결과가 존재하지 않습니다.");
    총리스트의길이=0;
    return;
}




    areaArr = new Array();

    for(let i = 0; i < campList.length; i++) {
    areaArr.push({
    name: campList[i].facltNm,
    sido: campList[i].doNm,
    sigoon: campList[i].sigunguNm,
    address: campList[i].addr1,
    img: campList[i].firstImageUrl,
    theme: campList[i].themaEnvrnCl,
    lineInt: campList[i].lineIntro,
    int: campList[i].intro,
    phone: campList[i].tel,
    facil: campList[i].sbrsCl,
    id: campList[i].contentId

});
}

    let campListHtml = '<div class="row">';
    for(let i = 0; i < areaArr.length; i++) {
    campListHtml += '<div class="col-md-6 col-lg-4">';
    campListHtml += '<div class="card">';
    campListHtml += '<img style="width:300px;height:150px;" src="' + (areaArr[i].img || '../img/어서와영_사진없음.png') + '" alt="">';
    campListHtml += '<div class="card-body">';
    campListHtml += '<h5 class="card-title"><a href="../pc/detailCamp?contentId='+areaArr[i].id +'">'+areaArr[i].name + '</a> </h5>';
    campListHtml += '<p class="card-text">' + areaArr[i].sido+ '&nbsp;&nbsp;&nbsp;'+areaArr[i].sigoon+ '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].address + '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].lineInt+'</p>';
    campListHtml += '<p class="card-text">' + (areaArr[i].int.length > 30 ? areaArr[i].int.slice(0, 30) + '..' : areaArr[i].int) + '</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].phone+'</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].facil+'</p>';
    campListHtml += '<p class="card-text">' + areaArr[i].theme + '</p>';
    campListHtml += '</div></div></div>';
}
    campListHtml += '</div>';
    $('#campList').html('');
    $('#campList').html(campListHtml);




} catch (e) {
    console.log(e);
}
},

});
        let resultSize='<h1>총&nbsp;'+총리스트의길이+'&nbsp;개의 캠핑장이 검색되었습니다.<br> 현재페이지&nbsp;&nbsp;'+현재페이지+'&nbsp;/&nbsp;'+총페이지수+'</h1>';
        $('#resultSize').html('');
        $('#resultSize').html(resultSize);
}
    function makePageNum(){
    $('#paging').html('');

    let pageBtn='';
    for(let i=1;i<=한번에보여줄페이지단위;i++){

    if(i==1&&현재페이지인덱스!=1){
    pageBtn+='<button onclick="previousPageIndex()">이전</button>'
}
    pageBtn +='<a href="#" onclick="paging('+(한번에보여줄페이지단위*(현재페이지인덱스-1)+i)+')">['+(한번에보여줄페이지단위*(현재페이지인덱스-1)+i)+']</a>'
    if(i==한번에보여줄페이지단위&&(한번에보여줄페이지단위*(현재페이지인덱스-1)+i)<총페이지수){
    pageBtn+='<button onclick="nextPageIndex()">다음</button>'
}
    if((한번에보여줄페이지단위*(현재페이지인덱스-1)+i)==총페이지수){
    break;
}


}
    $('#paging').html(pageBtn);
}
    function nextPageIndex(){
    현재페이지인덱스++;

    makePageNum();
    paging((현재페이지인덱스-1)*한번에보여줄페이지단위+1);
}
    function previousPageIndex(){
    현재페이지인덱스--;
    makePageNum();
    paging((현재페이지인덱스-1)*한번에보여줄페이지단위+1);
}
    function insertPageNum(i){
        if(i<1){alert("1이상의 자연수만 입력 가능합니다");return;}
    현재페이지인덱스=Math.ceil($('#inputPageNum').val()/10);
    makePageNum();
    paging(i);
}


