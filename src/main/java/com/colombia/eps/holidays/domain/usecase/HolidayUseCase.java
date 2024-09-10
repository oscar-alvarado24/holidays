package com.colombia.eps.holidays.domain.usecase;

import com.colombia.eps.holidays.domain.api.IHolidaysServicePort;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.domain.spi.IHolidaysPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class HolidayUseCase implements IHolidaysServicePort {
    private final IHolidaysPersistencePort holidaysPersistencePort;

    public HolidayUseCase(IHolidaysPersistencePort holidaysPersistencePort) {
        this.holidaysPersistencePort = holidaysPersistencePort;
    }

    /**
     * @param city where the doctor works
     * @return list of holidays
     */
    @Override
    public List<String> getHolidays(String city) {
        return holidaysPersistencePort.getHolidays(city);
    }

    /**
     * @param holidayModel that represent holiday to create
     * @return confirmation message of creation
     */
    @Override
    public String createLocalHoliday(HolidayModel holidayModel) {
        return holidaysPersistencePort.createLocalHoliday(holidayModel);
    }

    /**
     * @param nationalHolidays that represent national holidays obtained
     * @return confirmation message of creation
     */
    @Override
    public String createNationalHoliday(List<HolidayModel> nationalHolidays) {
        return holidaysPersistencePort.createNationalHoliday(nationalHolidays);
    }
}
