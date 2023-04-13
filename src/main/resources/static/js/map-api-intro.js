let latitude =0;
let longitude =0;
let myKey='4cafcd3f6ac34355b1f99ce7d42f1068';
window.onload = function(){

    navigator.geolocation.getCurrentPosition(function(pos) {
        //현재위치: 위도 경도 (살짝 오차 있음)
        latitude = pos.coords.latitude;
        longitude = pos.coords.longitude;
        //정확한 학원 위치
        // latitude=37.499806;
        // longitude=127.028312;


        getMap();
    });

}
function getMap() {
    var myLocation=new naver.maps.LatLng(latitude,longitude),
        map= new naver.maps.Map('map',{
            center:myLocation.destinationPoint(latitude,longitude),
            zoom:17
        }),
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
    var infowindow = new naver.maps.InfoWindow({
        content: contentString
    });
    naver.maps.Event.addListener(marker, "click", function(e) {
        if (infowindow.getMap()) {
            infowindow.close();
        } else {
            infowindow.open(map, marker);
        }
    });
    infowindow.open(map, marker);



}