function getDistrictName(areacode){

    // 확인 방법:
    // https://apis.data.go.kr/B551011/KorService1/areaCode1?numOfRows=30&pageNo=1&MobileOS=win&MobileApp=multitravel&serviceKey=
    let areaName;
    switch(areacode){

        case "1": // 서울
        case "2": // 인천
        case "9": // 경기도
            areaName = "수도권";
            break;
        case "10": // 강원특별자치도
            areaName = "강원권";
            break;
        case "3": // 대전
        case "8": // 세종특별자치시
        case "11": // 충청북도
        case "12": // 충청남도
            areaName = "충청권";
            break;
        case "4": // 대구
        case "6": // 부산
        case "7": // 울산
        case "13": // 경상북도
        case "14": // 경상남도
            areaName = "경상권";
            break;
        case "5": // 광주
        case "15": // 전북특별자치도
        case "16": // 전라남도
            areaName = "전라권";
            break;
        case "17": // 제주도
            areaName = "제주권";
            break;
        default:
            areaName = "해당 관광지는 district를 제공하지 않습니다."
            break;
    }
    return areaName;
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

async function getCoords(address) {
    await new Promise((resolve, reject) => {
        //console.log(geocoder.addressSearch.constructor.name); // 출력 결과: function
        geocoder.addressSearch(address, function (result, status) { //geocoder.addressSearch가 비동기 작업

            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                //console.log(kakao.maps.LatLng.constructor.name) 출력 결과: function
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                positions[0].placeInfo.mapx = coords.La;
                positions[0].placeInfo.mapy = coords.Ma;

                resolve(coords)
            } else {
                reject(new Error("해당 지역의 주소가 없습니다."))
            }
        })
    })
}

function getDescription(description){
    if(description==""||description==undefined){
        return "해당 관광지는 description을 제공하지 않습니다.";
    }else{
        return description;
    }
}

function getAddress(address){
    if(address==""||address==undefined){
        return "해당 관광지는 address를 제공하지 않습니다.";
    }else{
        return address;
    }
}

function getTel(tel){
    if(tel==""||tel==undefined){
        return "해당 관광지는 tel을 제공하지 않습니다.";
    }else{
        return tel;
    }
}

function getHomepage(homepage){
    if(homepage==""||homepage==undefined) {
        return "해당 관광지는 homepage를 제공하지 않습니다.";
    }else{
        return homepage;
    }
}

function displayInfo(position){
    return ()=>{
        var positionkakao = new kakao.maps.LatLng(position.placeInfo.mapy, position.placeInfo.mapx);

        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
        roadviewClient.getNearestPanoId(positionkakao, 50, function(panoId) {
            roadview.setPanoId(panoId, positionkakao); //panoId와 중심좌표를 통해 로드뷰 실행
        });


        const infoBox = document.querySelector("#infoBox");

        //district, title, description, tel li tage
        const districtLi = document.querySelector("#districtLi");
        const titleLi = document.querySelector("#titleLi");
        const descriptionLi = document.querySelector("#descriptionLi");
        const addressLi = document.querySelector("#addressLi");
        const telLi = document.querySelector("#telLi");
        const homeplageLi = document.querySelector("#homeplage");

        districtLi.innerHTML = "district : "+getDistrictName(position.placeInfo.areacode);
        titleLi.innerHTML = "title : "+position.placeInfo.title;
        descriptionLi.innerHTML = "description : "+getDescription(position.placeInfo.description);
        addressLi.innerHTML = "address : "+getAddress(position.placeInfo.addr1);
        telLi.innerHTML = "tel : "+getTel(position.placeInfo.tel);
        homeplageLi.innerHTML = getHomepage(position.placeInfo.homepage);


    }

}

