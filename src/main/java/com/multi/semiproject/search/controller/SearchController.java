package com.multi.semiproject.search.controller;

import com.multi.semiproject.district.model.dto.TravelDTO;
import com.multi.semiproject.search.model.dto.SearchResultDTO;
import com.multi.semiproject.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String searchPage(@RequestParam(value = "query", required = false) String query, Model model) {

        //searchError수정
        if (query != null && !query.matches("^[a-zA-Z0-9가-힣 ]*$")) {
            model.addAttribute("query", query); // 검색어 그대로 전달
            return "search/searchError";
        }

        model.addAttribute("query", query);
        return "search/search";
    }

    @GetMapping(value = "/results", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> searchResults(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "검색어가 필요합니다."));
        }

        int pageSize = 10;
        List<SearchResultDTO> searchResults = searchService.searchByTitle(query, page, pageSize);
        int totalResults = searchService.getTotalResults(query);
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);

        if (searchResults.isEmpty()) {
            return ResponseEntity.ok(Map.of("success", false, "message", "검색 결과가 없습니다."));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("results", searchResults);
        response.put("totalPages", totalPages);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchError")
    public String searchErrorPage(Model model) {
        List<String> districts = Arrays.asList("강원권", "경상권", "수도권", "전라권", "제주권", "충청권"); // 원하는 지역 추가 가능
        List<TravelDTO> randomTravels = new ArrayList<>();

        for (String district : districts) {
            List<SearchResultDTO> travels = searchService.searchByTitle(district, 1, 100);
            if (!travels.isEmpty()) {
                Collections.shuffle(travels); // 랜덤하게 섞기
                SearchResultDTO selectedTravel = travels.get(0); // 랜덤으로 선택
            }
        }

        // 권역명(district) 기준으로 오름차순 정렬
        randomTravels.sort(Comparator.comparing(TravelDTO::getDistrict));

        model.addAttribute("randomTravels", randomTravels); // 뷰에 데이터 전달

        return "search/searchError";
    }
}
