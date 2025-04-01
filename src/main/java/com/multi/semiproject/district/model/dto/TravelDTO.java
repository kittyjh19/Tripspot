package com.multi.semiproject.district.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelDTO {

    private int no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;


    public TravelDTO(int no, String district, String title, String description, String address, String phone) {
        this.no = no;
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }
}
