package com.colombia.eps.holidays.domain.spi;

import com.colombia.eps.holidays.domain.model.HolidayModel;

import java.util.List;

public interface IApiHolidaysPersistencePort {
    List<HolidayModel> getNationalHolidays();
}
