async function getCoords(address) {
    await new Promise((resolve, reject) => {
        //console.log(geocoder.addressSearch.constructor.name); // 출력 결과: function
        geocoder.addressSearch(address, function (result, status) { //geocoder.addressSearch가 비동기 작업

            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                //console.log(kakao.maps.LatLng.constructor.name) 출력 결과: function
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                position[0].placeInfo.mapx = coords.La;
                position[0].placeInfo.mapy = coords.Ma;

                resolve(coords)
            } else {
                reject(new Error("해당 지역의 주소를 제공하지 않습니다."))
            }
        })
    })
}