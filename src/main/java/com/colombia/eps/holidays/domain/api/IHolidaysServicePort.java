package com.colombia.eps.holidays.domain.api;

import com.colombia.eps.holidays.domain.model.HolidayModel;

import java.time.LocalDate;
import java.util.List;

public interface IHolidaysServicePort {
    List<String> getHolidays(String city);
    String createLocalHoliday(HolidayModel holidayModel);
    String createNationalHoliday(List<HolidayModel> holidayModel);
}
