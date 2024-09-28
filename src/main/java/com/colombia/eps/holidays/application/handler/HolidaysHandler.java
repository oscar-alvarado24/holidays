package com.colombia.eps.holidays.application.handler;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;
import com.colombia.eps.holidays.application.mapper.HolidaysMapper;
import com.colombia.eps.holidays.domain.api.IApiHolidaysServicePort;
import com.colombia.eps.holidays.domain.api.IHolidaysServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidaysHandler implements IHolidaysHandler {

    private final IHolidaysServicePort holidaysServicePort;
    private final IApiHolidaysServicePort apiHolidaysServicePort;
    private final HolidaysMapper holidaysMapper;
    /**
     * @param city where the doctor works
     * @return list of holidays
     */
    @Override
    public List<DatesResponse> getHolidays(List<String> city) {
        return holidaysMapper.toDatesResponse(holidaysServicePort.getHolidays(city));
    }

    /**
     * @param holidayRequest holiday to create
     * @return confirmation message of creation
     */
    @Override
    public String createHoliday(HolidayRequest holidayRequest) {
        return holidaysServicePort.createLocalHoliday(holidaysMapper.toHolidayModel(holidayRequest));
    }

    /**
     * @return confirmation message of creation
     */
    @Override
    public String createNationalHolidays() {
        return holidaysServicePort.createNationalHoliday(apiHolidaysServicePort.getNationalHolidays());
    }
}
