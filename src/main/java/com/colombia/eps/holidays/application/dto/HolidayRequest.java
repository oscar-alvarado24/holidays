package com.colombia.eps.holidays.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayRequest {
    private LocalDate date;
    private String city;
    private String name;
}
