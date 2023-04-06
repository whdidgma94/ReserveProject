

    let myKey='4cafcd3f6ac34355b1f99ce7d42f1068';
    const cities=['Seoul','Daejeon','Busan','Gwangju','Daegu','Ulsan','Incheon','Jeju'];

    window.onload = function(){
    getCitiesWeather();
}

    function getCitiesWeather(){
    cities.forEach(city => {
        $.ajax({
            method: "get",
            url: `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${myKey}&lang=kr`,
            success: function(res){
                console.log(res);
                let weather = res;
                city = res.name;
                temp = Math.round((res.main.temp-273.15)*100)/100; // K to C
                desc = res.weather[0].description;
                icon = res.weather[0].icon;
                imgUrl = "https://openweathermap.org/img/wn/"+icon+"@2x.png";
                console.log(`${city} : ${temp}'C (${desc})`);

                const result = `<div><img src="${imgUrl}"><p>${city}&nbsp;${temp}'C</p>${desc}<p></p></div>`;
                $('body').prepend(result);
            }
        });
    });
}


