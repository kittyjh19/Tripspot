document.addEventListener('DOMContentLoaded', function() {
    const searchQuery = document.getElementById('searchQuery').innerText; // 검색어 가져오기

    function loadSearchResults(page) {
        fetch(`/search/results?query=${encodeURIComponent(searchQuery)}&page=${page}`)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('searchResultsTable');
                tableBody.innerHTML = ''; // 기존 내용 비우기

                if (data.success) {
                    data.results.forEach(travel => {
                        const row = `
                            <tr>
                                <td>${travel.district}</td>
                                <td><a href="/district/detail/${travel.no}">${travel.title}</a></td>
                                <td>${travel.address}</td>
                                <td>${travel.phone}</td>
                            </tr>
                        `;
                        tableBody.innerHTML += row;
                    });

                    // 페이지네이션 링크 생성
                    const paginationDiv = document.getElementById('pagination');
                    paginationDiv.innerHTML = ''; // 기존 링크 비우기
                    const totalPages = data.totalPages;

                    for (let i = 1; i <= totalPages; i++) {
                        const link = document.createElement('a');
                        link.href = '#';
                        link.innerText = i;
                        link.addEventListener('click', function(event) {
                            event.preventDefault();
                            loadSearchResults(i);
                        });
                        paginationDiv.appendChild(link);
                    }
                } else {
                    tableBody.innerHTML = '<tr><td colspan="4">검색 결과가 없습니다.</td></tr>';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                const tableBody = document.getElementById('searchResultsTable');
                tableBody.innerHTML = '<tr><td colspan="4">검색 중 오류가 발생했습니다.</td></tr>';
            });
    }

    // 초기 검색 결과 로드 (1페이지)
    loadSearchResults(1);
});