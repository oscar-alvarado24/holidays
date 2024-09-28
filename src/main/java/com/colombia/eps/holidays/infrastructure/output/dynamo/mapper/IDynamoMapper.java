package com.colombia.eps.holidays.infrastructure.output.dynamo.mapper;

import com.colombia.eps.holidays.common.util.Constants;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.infrastructure.output.dynamo.entity.Holidays;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDynamoMapper {
    IDynamoMapper INSTANCE = Mappers.getMapper(IDynamoMapper.class);
    Holidays toHolidays(HolidayModel holidayModel);
    List<Holidays> toHolidays(List<HolidayModel> holidayModel);
    default LocalDate toLocalDate(Holidays holidays){
        String [] date = holidays.getDate().split(Constants.SPACE);
        return LocalDate.parse(date[0].trim());
    }
    List<LocalDate> toLocalDateList(List<Holidays> holidays);
}
