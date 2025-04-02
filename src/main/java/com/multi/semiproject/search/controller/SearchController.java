package com.multi.semiproject.search.controller;

import com.multi.semiproject.search.model.dto.SearchResultDTO;
import com.multi.semiproject.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    /**
     * 사용자의 검색 요청을 처리하는 메서드입니다.
     * - 검색어가 travel_db에 존재하지 않으면 검색 오류 페이지(searchError.html)로 이동합니다.
     * - 검색어가 존재하면 해당 지역의 관광지 목록 중 랜덤 6개를 선택하여 검색 결과 페이지(search.html)로 이동합니다.
     *
     * @param query 사용자가 입력한 검색어 (쿼리 파라미터)
     * @param model 뷰(View)로 데이터를 전달하기 위한 객체
     * @return 검색 결과 페이지("search/search") 또는 검색 오류 페이지("search/searchError")
     */
    @GetMapping("/search")
    public String searchResult(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query == null || query.isEmpty()) {
            return "redirect:/searchError"; // 검색어가 없으면 오류 페이지로 이동
        }

        // 검색어가 DB에 존재하는지 확인
        boolean exists = searchService.existsInDatabase(query);
        if (!exists) {
            return "redirect:/searchError";
        }

        query = URLDecoder.decode(query, StandardCharsets.UTF_8);

        // 검색어가 존재하면 해당 지역의 관광지 목록을 조회
        List<SearchResultDTO> searchResults = searchService.searchByDistrict(query);
        List<SearchResultDTO> randomResults = searchService.getRandomResults(searchResults); // 랜덤 6개 추출

        model.addAttribute("query", query);
        model.addAttribute("randomResults", randomResults);

        return "search/search"; // 검색 결과 페이지 반환
    }

    /**
     * 유효하지 않은 검색어 시 검색 오류 페이지로 이동
     */
    @GetMapping("/searchError")
    public String searchError() {
        return "search/searchError";
    }
}