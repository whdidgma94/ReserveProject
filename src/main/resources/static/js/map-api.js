// navigator.geolocation.getCurrentPosition
// (function(pos) {
//     latitude = pos.coords.latitude;
//     longitude = pos.coords.longitude;
//
//
// var HOME_PATH = window.HOME_PATH || '.';
// var home = new naver.maps.LatLng(latitude, longitude),
//     map = new naver.maps.Map('map', {
//         center: bookstore.destinationPoint(latitude, longitude),
//         zoom: 17
//     }),
//     marker = new naver.maps.Marker({
//         map: map,
//         position: home
//     });
// var contentString = [
//     '<div class="iw_inner">',
//     '<h3>우리집</h3>',
//     '<p>우리집</p>',
//
//     ,
//     '</div>'
// ].join('');
// var infowindow = new naver.maps.InfoWindow({
//     content: contentString
// });
// naver.maps.Event.addListener(marker, "click", function(e) {
//     if (infowindow.getMap()) {
//         infowindow.close();
//     } else {
//         infowindow.open(map, marker);
//     }
// });
// infowindow.open(map, marker);