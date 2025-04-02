package com.multi.semiproject.search.model.dao;

import com.multi.semiproject.search.model.dto.SearchResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

//@Mapper
public interface SearchMapper {

    /**
     * 특정 키워드에 해당하는 관광지 개수를 조회하는 메서드
     * @param keyword 검색어
     * @return 검색된 개수
     */
    int getTotalCountByKeyword(@Param("keyword") String keyword);

    /**
     * 특정 키워드로 관광지 목록을 검색하는 메서드
     * @param keyword 검색어
     * @return 검색된 관광지 목록
     */
    List<SearchResultDTO> getSearchResults(@Param("keyword") String keyword);

    /**
     * 특정 권역의 관광지 목록을 조회하는 메서드
     * @param district 권역명
     * @return 해당 권역의 관광지 목록
     */
    List<SearchResultDTO> getSearchResultsByRegion(@Param("district") String district);
}
