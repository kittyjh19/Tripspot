package com.multi.semiproject.district.model.dao;


import com.multi.semiproject.board.model.dto.BoardDTO;
import com.multi.semiproject.district.model.dto.TravelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictMapper {

    int getTotalCountByDistrict(@Param("district") String district);

    List<TravelDTO> getTravelsByDistrict(
            @Param("district") String district,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );

    TravelDTO getTravelByNo(@Param("no") int no);

    int insertTravelInfo(TravelDTO travelDTO);

    Optional<Integer> selectByTitle(TravelDTO travelDTO);
    int updateBoard(BoardDTO board);
}
