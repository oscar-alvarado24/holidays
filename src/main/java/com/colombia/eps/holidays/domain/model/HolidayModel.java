package com.colombia.eps.holidays.domain.model;

import com.colombia.eps.holidays.common.util.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HolidayModel {
    private String date;
    private String city;
    private String order;
    private String name;

    public HolidayModel() {
        this.order =  Constants.LOCAL;
    }

    public HolidayModel(String date, String city, String name) {
        this.date = date;
        this.city = city;
        this.name = name;
        this.order = Constants.LOCAL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}