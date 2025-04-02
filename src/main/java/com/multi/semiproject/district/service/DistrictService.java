package com.multi.semiproject.district.service;

import com.multi.semiproject.district.model.dao.DistrictMapper;
import com.multi.semiproject.district.model.dto.TravelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictMapper districtMapper;

    public List<TravelDTO> getTravelsByDistrict(String district, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return districtMapper.getTravelsByDistrict(district, offset, pageSize);
    }

    public int getTotalCountByDistrict(String district) {
        return districtMapper.getTotalCountByDistrict(district);
    }

    public TravelDTO getTravelByNo(int no) {
        return districtMapper.getTravelByNo(no);
    }

    public int insertTravelInfo(TravelDTO travelDTO) {
        return districtMapper.insertTravelInfo(travelDTO);
    }
}

