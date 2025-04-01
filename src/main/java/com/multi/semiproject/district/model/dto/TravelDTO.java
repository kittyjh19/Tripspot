package com.multi.semiproject.district.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelDTO {

    private String district;
    private String title;
    private String address;
    private String phone;


    public TravelDTO(String district, String title, String address, String phone) {
        this.district = district;
        this.title = title;
        this.address = address;
        this.phone = phone;
    }
}
