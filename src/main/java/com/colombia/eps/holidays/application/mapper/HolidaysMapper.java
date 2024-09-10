package com.colombia.eps.holidays.application.mapper;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HolidaysMapper {

    HolidaysMapper INSTANCE = Mappers.getMapper(HolidaysMapper.class);
    default DatesResponse toDatesResponse(List<String> holidaysList){
        return new DatesResponse(holidaysList);
    }

    HolidayModel toHolidayModel(HolidayRequest holidayRequest);

}
