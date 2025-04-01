package com.multi.semiproject.district.controller;

import com.multi.semiproject.district.model.dto.TravelDTO;
import com.multi.semiproject.district.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping("/{district}")
    public String getTravelsByDistrict(
            @PathVariable("district") String district,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {

        district = URLDecoder.decode(district, StandardCharsets.UTF_8);

        List<TravelDTO> travels = districtService.getTravelsByDistrict(district, page, pageSize);
        int total = districtService.getTotalCountByDistrict(district);

        int totalPages = (int) Math.ceil((double) total / pageSize);


        // 이전/다음 버튼 활성화 여부만 계산
        boolean hasPrevious = page > 1;
        boolean hasNext = page < totalPages;

        model.addAttribute("district", district);
        model.addAttribute("travels", travels);
        model.addAttribute("total", total);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasPrevious", hasPrevious);
        model.addAttribute("hasNext", hasNext);

        return "district/district"; // district.html로 이동
    }

    @GetMapping("/map")
    public String goMap(){
        return "map/address";
    }
}

