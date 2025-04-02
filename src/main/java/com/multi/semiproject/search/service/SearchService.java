package com.multi.semiproject.search.service;

import com.multi.semiproject.search.model.dao.SearchMapper;
import com.multi.semiproject.search.model.dto.SearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public List<SearchResultDTO> searchByTitle(String query, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return searchMapper.findByTitle(query, offset, pageSize);
    }

    public int getTotalResults(String query) {
        return searchMapper.countByTitle(query);
    }
}
