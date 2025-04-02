package com.multi.semiproject.district.service;

import com.multi.semiproject.district.model.dao.DistrictMapper;
import com.multi.semiproject.district.model.dto.TravelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        //중복 검사를 한 번 합니다.
        Optional<Integer> dupleResult = districtMapper.selectByTitle(travelDTO);
        if(dupleResult.isPresent())
            return dupleResult.get(); //해당 title의 no를 반환
        districtMapper.insertTravelInfo(travelDTO);
        return travelDTO.getNo(); // insert된 후 해당 지역의 no 반환


    }
}

