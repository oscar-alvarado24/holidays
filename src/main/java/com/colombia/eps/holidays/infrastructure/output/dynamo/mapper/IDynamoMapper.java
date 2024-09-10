package com.colombia.eps.holidays.infrastructure.output.dynamo.mapper;

import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.infrastructure.output.dynamo.entity.Holidays;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDynamoMapper {
    IDynamoMapper INSTANCE = Mappers.getMapper(IDynamoMapper.class);
    Holidays toHolidays(HolidayModel holidayModel);
    List<Holidays> toHolidays(List<HolidayModel> holidayModel);
}
