package com.multi.semiproject.district.model.dao;


import com.multi.semiproject.district.model.dto.TravelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {


    int getTotalCountByDistrict(@Param("district") String district);

    List<TravelDTO> getTravelsByDistrict(
            @Param("district") String district,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );
}
