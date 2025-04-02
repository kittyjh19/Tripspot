package com.multi.semiproject.search.model.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {
    private int no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;
}
