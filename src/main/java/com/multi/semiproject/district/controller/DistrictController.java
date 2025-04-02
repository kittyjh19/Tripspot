package com.multi.semiproject.district.controller;

import com.multi.semiproject.common.ResponseDTO;
import com.multi.semiproject.district.model.dto.TravelDTO;
import com.multi.semiproject.district.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    // JSON과 HTML을 분리하여 API 설계

    // HTML 반환
    @GetMapping(value = "/{district}", produces = "text/html")
    public String showDistrictPage(@PathVariable("district") String district, Model model) { // Model 객체를 사용하여 district 값을 View에 전달
        model.addAttribute("district", district);
        return "district/district";  // district.html 반환
    }

    // JSON 데이터 반환 (produces = "application/json" 사용)
    @GetMapping(value = "/{district}", produces = "application/json")
    @ResponseBody // @ResponseBody를 사용하여 데이터를 JSON 형식으로 변환
    public Map<String, Object> getTravelsByDistrict(
            @PathVariable("district") String district,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        district = URLDecoder.decode(district, StandardCharsets.UTF_8);

        List<TravelDTO> travels = districtService.getTravelsByDistrict(district, page, pageSize);
        int total = districtService.getTotalCountByDistrict(district);
        int totalPages = (int) Math.ceil((double) total / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("district", district);
        response.put("travels", travels);
        response.put("total", total);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response; // JSON 데이터 반환
    }

    // no 기준으로 상세조회
    @GetMapping("/detail/{no}")
    public String getTravelDetail(@PathVariable("no") int no, Model model) {
        TravelDTO travel = districtService.getTravelByNo(no);
        model.addAttribute("travel", travel);
        return "district/detail";
    }

    @GetMapping("/map")
    public String goMap(@RequestParam int no, Model model){
        TravelDTO travel = districtService.getTravelByNo(no);
        model.addAttribute("travel", travel);
        return "map/map";
    }

    @GetMapping("/duplicate")
    public String duplicateCheck(@RequestParam String title){
        System.out.println("DistrictController.test");
        return null;
    }

    @PostMapping(value = "/regist", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseDTO> registDistrict(@RequestBody TravelDTO travelDTO){
        int result = districtService.insertTravelInfo(travelDTO);
        if(result>0){

            return ResponseEntity.created(URI.create("/district/detail/"+travelDTO.getNo())).build();
        }else{ //이미 저장된 지역도 없고, insert된 지역도 없는 경우
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/district/"+travelDTO.getDistrict());
            return ResponseEntity.status(303).headers(headers).build();
        }

     }
}

