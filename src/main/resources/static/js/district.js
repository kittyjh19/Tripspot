document.addEventListener("DOMContentLoaded", function () {
    const districtTableBody = document.querySelector(".district-table tbody");
    const paginationContainer = document.querySelector(".pagination");

    // URL에서 districtName 가져오기
    const urlParts = window.location.pathname.split('/');
    const districtName = decodeURIComponent(urlParts[urlParts.length - 1]); // URL 인코딩된 값을 디코딩, 마지막 값이 district

    if (!districtName) { // districtName이 없으면 콘솔에 오류 출력 후 함수 종
        console.error("districtName이 설정되지 않았습니다.");
        return;
    }

    document.getElementById("districtName").value = districtName;
    document.getElementById("districtTitle").textContent = `${districtName} 관광지 목록`; // <h1> 제목을 "권역별 관광지 목록" 형태로 변경.

    // 관광지 목록 불러오기
    // fetch()이용해 데이터 요청
    // /district/권역명?page=페이지번호 → 해당 권역의 관광지 목록을 요청
    function fetchDistrictData(page = 1) {
        fetch(`/district/${encodeURIComponent(districtName)}?page=${page}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`서버 응답 실패: ${response.status}`);
                }
                return response.json();
            })
            .then(data => { // JSON 데이터(data.travels)를 받아서 테이블과 페이지네이션을 렌더링
                console.log("서버 응답 데이터:", data);
                renderTable(data.travels);
                renderPagination(data.currentPage, data.totalPages);
            })
            .catch(error => console.error("데이터 불러오기 실패:", error));
    }

    function renderTable(travels) {
        districtTableBody.innerHTML = "";

        if (!travels || travels.length === 0) {
            districtTableBody.innerHTML = `<tr><td colspan="4">관광지가 없습니다.</td></tr>`;
            return;
        }

        // document.createDocumentFragment() → 성능 최적화
        const fragment = document.createDocumentFragment();

        travels.forEach(travel => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${travel.district}</td>
                <td><a href="/district/detail/${travel.no}">${travel.title}</a></td>
                <td>${travel.address}</td>
                <td>${travel.phone}</td>
            `;
            fragment.appendChild(row);
        });

        districtTableBody.appendChild(fragment);
    }

    function renderPagination(currentPage, totalPages) {
        paginationContainer.innerHTML = "";

        const createPageButton = (page, label, disabled = false) => {
            const button = document.createElement("a");
            button.href = "#";
            button.dataset.page = page;
            button.textContent = label;
            if (disabled) {
                button.classList.add("disabled");
                button.removeAttribute("href");
            }
            return button;
        };

        paginationContainer.appendChild(
            currentPage > 1 ? createPageButton(currentPage - 1, "〈") : createPageButton(1, "〈", true)
        );

        for (let i = 1; i <= totalPages; i++) {
            const button = createPageButton(i, i);
            if (i === currentPage) {
                button.classList.add("active");
            }
            paginationContainer.appendChild(button);
        }

        paginationContainer.appendChild(
            currentPage < totalPages ? createPageButton(currentPage + 1, "〉") : createPageButton(totalPages, "〉", true)
        );
    }

    // 페이지 버튼 클릭 시 데이터 갱신
    paginationContainer.addEventListener("click", function (event) {
        if (event.target.tagName === "A" && event.target.dataset.page) {
            event.preventDefault();
            fetchDistrictData(Number(event.target.dataset.page));
        }
    });

    fetchDistrictData(); // 관광지 데이터 불러오기
});
