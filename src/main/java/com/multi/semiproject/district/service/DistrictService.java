package com.multi.semiproject.district.service;

import com.multi.semiproject.district.model.dao.DistrictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictMapper districtMapper;

    //테스트용
    public int districtTest() {
        return districtMapper.districtTest();
    }
}
