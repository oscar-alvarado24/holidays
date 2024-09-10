package com.colombia.eps.holidays.application.handler;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;

public interface IHolidaysHandler {
    DatesResponse getHolidays(String city);
    String createHoliday(HolidayRequest holidayRequest);
    String createNationalHolidays();
}
