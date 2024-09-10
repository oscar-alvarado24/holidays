package com.colombia.eps.holidays.domain.api;

import com.colombia.eps.holidays.domain.model.HolidayModel;

import java.util.List;

public interface IApiHolidaysServicePort {
    List<HolidayModel> getNationalHolidays();
}
