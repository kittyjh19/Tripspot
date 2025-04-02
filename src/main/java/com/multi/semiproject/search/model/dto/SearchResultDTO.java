package com.multi.semiproject.search.model.dto;

public class SearchResultDTO {

    private String district;  // 권역 (예: 수도권, 강원권 등)
    private String title;     // 관광지 이름
    private String address;   // 주소
    private String phone;     // 전화번호

    // 기본 생성자
    public SearchResultDTO() {}

    // 모든 필드를 초기화하는 생성자
    public SearchResultDTO(String district, String title, String address, String phone) {
        this.district = district;
        this.title = title;
        this.address = address;
        this.phone = phone;
    }

    // district 필드에 대한 getter와 setter
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    // title 필드에 대한 getter와 setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // address 필드에 대한 getter와 setter
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // phone 필드에 대한 getter와 setter
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "district='" + district + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
