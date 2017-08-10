package ru.itpark.dto;

/**
 * Created by Student21 on 05.08.2017.
 */
public class PhoneRecordDto {
    private String name;
    private String phone;

    public PhoneRecordDto() {
    }

    public PhoneRecordDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
