package com.colombia.eps.holidays.infrastructure.output.feing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHolidaysApi {
    private String date;
    private String start;
    private String end;
    private String name;
    private String type;
    private String rule;
}
