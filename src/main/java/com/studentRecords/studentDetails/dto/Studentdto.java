package com.studentRecords.studentDetails.dto;

public class Studentdto {

    private Long id;
    private String name;
    private String birthDate;
    private String address;
    private Long regNo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getRegNo() {
        return regNo;
    }
    public void setRegNo(Long regNo) {
        this.regNo = regNo;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
