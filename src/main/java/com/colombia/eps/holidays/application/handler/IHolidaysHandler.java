package com.colombia.eps.holidays.application.handler;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;

import java.util.List;

public interface IHolidaysHandler {
    List<DatesResponse> getHolidays(List<String> city);
    String createHoliday(HolidayRequest holidayRequest);
    String createNationalHolidays();
}
