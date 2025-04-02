package com.multi.semiproject.search.controller;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String searchPage(@RequestParam(value = "query", required = false) String query, Model model) {
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
}
