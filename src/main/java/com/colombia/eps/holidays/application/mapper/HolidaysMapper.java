package com.colombia.eps.holidays.application.mapper;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HolidaysMapper {

    HolidaysMapper INSTANCE = Mappers.getMapper(HolidaysMapper.class);
    default List<DatesResponse> toDatesResponse(Map<String,List<LocalDate>>holidays){
        List<DatesResponse> datesResponses = new ArrayList<>();
        holidays.forEach((k,v)->{
            datesResponses.add(new DatesResponse(k,v));
        });
        return datesResponses;
    }

    HolidayModel toHolidayModel(HolidayRequest holidayRequest);

}
