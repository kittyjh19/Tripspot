document.addEventListener("DOMContentLoaded", function () {
    var address = document.getElementById('address').innerText.trim();
    var geocoder = new kakao.maps.services.Geocoder();
    var mapContainer = document.getElementById('map');
    var roadviewContainer = document.getElementById('roadview');

    geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 지도 표시
            var map = new kakao.maps.Map(mapContainer, { center: coords, level: 3 });
            var marker = new kakao.maps.Marker({ map: map, position: coords });
            map.setCenter(coords);

            // 로드뷰 표시
            var roadview = new kakao.maps.Roadview(roadviewContainer);
            var roadviewClient = new kakao.maps.RoadviewClient();

            roadviewClient.getNearestPanoId(coords, 50, function(panoId) {
                if (panoId) {
                    roadview.setPanoId(panoId, coords);
                } else {
                    roadviewContainer.innerHTML = "<p>로드뷰가 제공되지 않는 지역입니다.</p>";
                }
            });
        } else {
            mapContainer.innerHTML = "<p>주소를 찾을 수 없습니다.</p>";
            roadviewContainer.innerHTML = "<p>로드뷰를 사용할 수 없습니다.</p>";
        }
    });
});