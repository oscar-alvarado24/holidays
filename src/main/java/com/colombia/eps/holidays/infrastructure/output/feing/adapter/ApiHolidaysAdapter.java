package com.colombia.eps.holidays.infrastructure.output.feing.adapter;

import com.colombia.eps.holidays.common.util.Constants;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.domain.spi.IApiHolidaysPersistencePort;
import com.colombia.eps.holidays.infrastructure.output.feing.client.IApiHolidaysClient;
import com.colombia.eps.holidays.infrastructure.output.feing.mapper.IApiHolidaysMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApiHolidaysAdapter implements IApiHolidaysPersistencePort {

    private final IApiHolidaysClient apiHolidaysClient;
    private final IApiHolidaysMapper apiHolidaysMapper;
    /**
     * @return list of national holidays
     */
    @Override
    public List<HolidayModel> getNationalHolidays() {
        return apiHolidaysMapper.toHolidayModelList(apiHolidaysClient.getHolidays(Constants.COUNTRY, Constants.YEAR));
    }
}
