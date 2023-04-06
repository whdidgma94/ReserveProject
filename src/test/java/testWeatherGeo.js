
    let latitude =0;
    let longitude =0;
    let myKey='4cafcd3f6ac34355b1f99ce7d42f1068';
    window.onload = function(){

    navigator.geolocation.getCurrentPosition(function(pos) {
        latitude = pos.coords.latitude;
        longitude = pos.coords.longitude;
        //console.log(latitude);
        //console.log("현재 위치는 : " + latitude + ", "+ longitude);
        getWeather();
    });

}

    function getWeather(){
    console.log("현재 위치는 : " + latitude + ", "+ longitude);
    $.ajax({
    method :"get",
    url : `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${myKey}&lang=kr`,
    success: function(res){
    console.log(res);
    let weather = res;
    city = res.name;
    temp = Math.round((res.main.temp-273.15)*100)/100; // K to C
    desc = res.weather[0].description;
    icon = res.weather[0].icon;
    imgUrl = "https://openweathermap.org/img/wn/"+icon+"@2x.png";
    console.log(`${city} : ${temp}'C (${desc})`);
    // console.log('imgUrl:',imgUrl);
        const result = `<div><img src="${imgUrl}"><br><p>${city}<br>${temp}'C</p>${desc}<p></p></div>`;
        $('body').prepend(result);


}
});

}


