package com.multi.semiproject.district.controller;

import com.multi.semiproject.district.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    //테스트용
    @GetMapping("/test")
    @ResponseBody
    public String districtTest(){
        int result = districtService.districtTest();
        return "district 테스트 결과 = "+result;

    }
}
