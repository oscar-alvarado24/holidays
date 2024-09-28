package com.colombia.eps.holidays.domain.api;

import com.colombia.eps.holidays.domain.model.HolidayModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IHolidaysServicePort {
    Map<String,List<LocalDate>> getHolidays(List<String> city);
    String createLocalHoliday(HolidayModel holidayModel);
    String createNationalHoliday(List<HolidayModel> holidayModel);
}
