function getDistrictName(address){
    let district;

    if(!address){
        district ="지역 정보 미제공";
    }else if(address.includes("서울")||address.includes("경기")||address.includes("인천"))
        district =  "수도권";
    else if(address.includes("충청"))
        district = "충청권";
    else if(address.includes("전라"))
        district ="전라권";
    else if(address.includes("강원"))
        district = "강원권";
    else if(address.includes("경상"))
        district = "경상권";
    else
        district = "지역 정보 미제공";
    return district;
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

function getCoords(address) {
    return new Promise((resolve, reject) => {
        //console.log(geocoder.addressSearch.constructor.name); // 출력 결과: function
        geocoder.addressSearch(address, function (result, status) { //geocoder.addressSearch가 비동기 작업

            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                //console.log(kakao.maps.LatLng.constructor.name) 출력 결과: function
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                resolve({x:coords.La, y:coords.Ma});
            } else {
                reject(new Error("해당 지역의 좌표를 알 수 없습니다."))
            }
        })
    })
}

function getDescription(description){
    if(!(!!description) || description=="-")
        return "상세정보 미제공";
    else
        return description;
}

function getAddress(address){
    if(!(!!address) || address=="-")
        return "상세정보 미제공";
    else
        return address;
}

function getPhone(phone){
    if(!(!!phone) || phone=="-")
        return "상세정보 미제공";
    else
        return phone;
}

function getHomepage(homepage){
    if(!(!!homepage) || homepage=="-")
        return "상세정보 미제공";
    else
        return homepage;
}



function displayInfo(place, savePlace){
    return ()=>{
        savePlace.saveObject = place;
        var positionkakao = new kakao.maps.LatLng(place.info.mapy, place.info.mapx);

        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
        roadviewClient.getNearestPanoId(positionkakao, 500, function(panoId) {
            roadview.setPanoId(panoId, positionkakao); //panoId와 중심좌표를 통해 로드뷰 실행
        });



        //district, title, description, tel li tage
        const titleBox = document.querySelector("#title");
        const descriptionBox = document.querySelector("#desc");
        const addressBox = document.querySelector("#addr");
        const districtBox = document.querySelector("#district");
        const phoneBox = document.querySelector("#phone");
        const homepageBox = document.querySelector("#homepage");


        //console.log(place.info.title)
        //console.log(place);

        titleBox.innerHTML = place.info.title;
        descriptionBox.innerHTML = "Description: "+place.info.description;
        addressBox.innerHTML ="Address: "+ place.info.address;
        districtBox.innerHTML ="Disctict: "+ place.info.district;
        phoneBox.innerHTML = "Phone: "+place.info.phone;
        homepageBox.innerHTML = place.info.homepage;





    }

}





