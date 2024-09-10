package com.colombia.eps.holidays.domain.usecase;

import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.domain.api.IApiHolidaysServicePort;
import com.colombia.eps.holidays.domain.spi.IApiHolidaysPersistencePort;

import java.util.List;

public class ApiHolidaysUseCase implements IApiHolidaysServicePort {
    private final IApiHolidaysPersistencePort apiHolidaysPersistencePort;

    public ApiHolidaysUseCase(IApiHolidaysPersistencePort apiHolidaysPersistencePort) {
        this.apiHolidaysPersistencePort = apiHolidaysPersistencePort;
    }

    /**
     * @return list of national holidays
     */
    @Override
    public List<HolidayModel> getNationalHolidays() {
        return apiHolidaysPersistencePort.getNationalHolidays();
    }
}
