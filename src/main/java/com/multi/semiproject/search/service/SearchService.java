package com.multi.semiproject.search.service;

import com.multi.semiproject.search.model.dto.SearchResultDTO;
import com.multi.semiproject.search.model.dao.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    /**
     * 검색어가 travel_db에 존재하는지 확인하는 메서드입니다.
     * @param query 사용자가 입력한 검색어
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean existsInDatabase(String query) {
        return searchMapper.getTotalCountByKeyword(query) > 0;
    }

    /**
     * 검색어에 맞는 관광지 목록을 조회하는 메서드입니다.
     * @param query 사용자가 입력한 검색어
     * @return 해당 지역에 맞는 관광지 목록 (없으면 빈 리스트 반환)
     */
    public List<SearchResultDTO> searchByDistrict(String query) {
        // 1. 검색어가 DB에 존재하는지 확인
        int totalCount = searchMapper.getTotalCountByKeyword(query);

        // 2. 검색 결과가 없으면 빈 리스트 반환 (searchError로 이동하게끔 처리)
        if (totalCount == 0) {
            return Collections.emptyList();
        }

        // 3. 검색 결과 목록을 가져옴
        List<SearchResultDTO> searchResults = searchMapper.getSearchResults(query);

        // 4. 검색 결과 중에서 랜덤하게 6개 선택
        return getRandomResults(searchResults);
    }

    /**
     * 검색 결과에서 랜덤하게 6개를 선택하는 메서드
     * @param searchResults 검색된 관광지 목록
     * @return 랜덤하게 선택된 6개의 관광지
     */
    public List<SearchResultDTO> getRandomResults(List<SearchResultDTO> searchResults) {
        Collections.shuffle(searchResults);  // 목록을 랜덤하게 섞음
        return searchResults.stream().limit(6).collect(Collectors.toList());  // 상위 6개 선택
    }
}
