
let latitude ;//학원위도
let longitude;//학원경도
var map;//네이버지도가 정보가 담기는 객체
var marker;//네이버지도의 마커의 정보가 담기는 객체
var infoWindow;//네이버지도의 마커의 정보창
let areaArr;//db 로부터 받아온 campList 의 필요한 정보가 담기는 객체
let markers;//마커들이 속한 배열(검색시 생성);
let infoWindows;//정보창들이 속한 배열(검색시 생성);


window.onload = function(){

    // navigator.geolocation.getCurrentPosition(function(pos) {
    // //현재위치: 위도 경도 (살짝 오차 있음)
    // latitude = pos.coords.latitude;
    // longitude = pos.coords.longitude;
    //
    // });

//정확한 학원 위치
    latitude=37.499806;
    longitude=127.028312;


    getMap();
}


function getMap() {
    var myLocation=new naver.maps.LatLng(latitude,longitude)
        map= new naver.maps.Map('map',{
            center:myLocation.destinationPoint(latitude,longitude),
            zoom:17
        });
        marker = new naver.maps.Marker({
            map:map,
            position:myLocation
        })
    var contentString = [
        '<div class="iw_inner">',
        '<a href="#">그린아이티아카데미</a>',
        '<p>그린아이티아카데미학원입니다.</p>',
        '<br />',
        ,
        '</div>'
    ].join('');
        infoWindow = new naver.maps.InfoWindow({
        content: contentString
    });
    naver.maps.Event.addListener(marker, "click", function(e) {
        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker);
        }
    });
    infoWindow.open(map, marker);



}




$(document).ready(function() {


    // 검색 버튼 클릭 이벤트 리스너 등록
    $('#search-icon').click(function() {

        // 검색어와 검색 타입 가져오기
        var keyword = $('#search').val();
        var type = $('select[name="type"]').val();
        var bounds = map.getBounds(),
            southWest = bounds.getSW(),
            northEast = bounds.getNE()
        var boundsObj = {
            southWest: southWest,
            northEast: northEast

        };
        // 검색 요청 보내기
        $.ajax({

            url: '/search/mapSearch',
            type: 'GET',
            data: {
                keyword: keyword,
                type: type,
                boundsObj: JSON.stringify(boundsObj)

            },

            success: function(result) {

                try {
                    var campList = $.parseJSON(result);
                    if(campList ==null || campList.length==0){
                        alert("검색결과가 존재하지 않습니다.");

                        return;
                    }

                    initMap(campList);


                } catch (e) {

                    console.log(e);
                }
            },
        });
    });
});





function initMap(campList) {



    areaArr = new Array();  // 지역을 담는 배열 ( 지역명/위도경도 )
    for (let i = 0; i < campList.length; i++) {
        areaArr.push({
            name: campList[i].facltNm,
            lat: campList[i].mapY,
            lng: campList[i].mapX,
            address: campList[i].addr1,
            img: campList[i].firstImageUrl,
            theme: campList[i].themaEnvrnCl,
            id: campList[i].contentId
        });
    }
    if(markers!=null){
        for(let i=0;i<markers.length;i++){
            markers[i].setMap(null);
        }
    }
    if(infoWindows!=null){
        for(let i=0;i<infoWindows.length;i++){
            infoWindows[i].close();
        }
    }


    marker.setMap(null);
    infoWindow.close();
    markers = new Array(); // 마커 정보를 담는 배열
    infoWindows = new Array(); // 정보창을 담는 배열

    for (var i = 0; i < areaArr.length; i++) {
        // 지역을 담은 배열의 길이만큼 for문으로 마커와 정보창을 채워주자 !


        marker = new naver.maps.Marker({
            map: map,
            title: areaArr[i].name, // 지역구 이름
            position: new naver.maps.LatLng(areaArr[i].lat , areaArr[i].lng), // 지역구의 위도 경도 넣기
            icon: {
                url: '../img/marker.png',
                size: new naver.maps.Size(25, 25),
                origin: new naver.maps.Point(0, 0),
                anchor: new naver.maps.Point(25, 26)
            }
        });
        // marker = new naver.maps.Marker({
        //     map: map,
        //     title: areaArr[i].name, // 지역구 이름
        //     position: new naver.maps.LatLng(areaArr[i].lat , areaArr[i].lng) // 지역구의 위도 경도 넣기
        // });

        /* 정보창 */
        infoWindow = new naver.maps.InfoWindow({
            content: '<div style="width:300px;text-align:center;padding:10px;"><a href="../detailCamp?contentId=' + areaArr[i].id + '">'
                + areaArr[i].name + '</a><p>'+areaArr[i].address+'</p>' +
                (areaArr[i].theme != null ? '<p>' + areaArr[i].theme + '</p>' : '') +
                '<img style="width:200px;height:70px;" src="' + (areaArr[i].img || 'path/to/alternative/image') + '" alt="/img/어서와영_사진없음.png>' +

                '</div>'

        }); // 클릭했을 때 띄워줄 정보 HTML 작성

        markers.push(marker); // 생성한 마커를 배열에 담는다.
        infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
    }
    // 이전에 생성된 지도 객체가 존재할 경우 지도 객체를 삭제합니다.












    for (var i=0, ii=markers.length; i<ii; i++) {
        console.log(markers[i] , getClickHandler(i));
        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
    }
    function getClickHandler(seq) {

        return function(e) {  // 마커를 클릭하는 부분
            var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
                infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open(map, marker); // 표출
            }
        }
    }


}







