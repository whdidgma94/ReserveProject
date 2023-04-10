let latitude =0;
let longitude =0;
let camp = null;
let myKey='4cafcd3f6ac34355b1f99ce7d42f1068';
window.onload = function(){
    navigator.geolocation.getCurrentPosition(function(pos) {
        latitude=37.499806;
        longitude=127.028312;
        getMap();
    });
}
function getMap() {
    let myLocation=new naver.maps.LatLng(latitude,longitude),
        map= new naver.maps.Map('map',{
            center:myLocation.destinationPoint(latitude,longitude),
            zoom:17
        }),
        marker = new naver.maps.Marker({
            map:map,
            position:myLocation
        })
    let contentString = [
        '<div class="iw_inner">',
        '<a th:text="${camp.facltNm}" href="#"></a>',
        '<p th:text="${camp.lineIntro}"></p>',
        '<br />',
        '</div>'
    ].join('');
    let infowindow = new naver.maps.InfoWindow({
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