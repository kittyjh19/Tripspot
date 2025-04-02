package com.multi.semiproject.search.model.dao;

import com.multi.semiproject.search.model.dto.SearchResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<SearchResultDTO> findByTitle(@Param("query") String query, @Param("offset") int offset, @Param("limit") int limit);

    int countByTitle(@Param("query") String query);
}

