package com.colombia.eps.holidays.infrastructure.output.feing.mapper;

import com.colombia.eps.holidays.common.util.Constants;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.infrastructure.output.feing.entity.ResponseHolidaysApi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IApiHolidaysMapper {
    IApiHolidaysMapper INSTANCE = Mappers.getMapper(IApiHolidaysMapper.class);
    default HolidayModel toHolidayModel(ResponseHolidaysApi responseHolidaysApi){
        if ( responseHolidaysApi == null ) {
            return null;
        }

        HolidayModel holidayModel = new HolidayModel();

        String [] dateArray = responseHolidaysApi.getDate().split(" ");
        holidayModel.setDate( dateArray[0] );
        holidayModel.setName( responseHolidaysApi.getName() );
        holidayModel.setOrder(Constants.NATIONAL);
        return holidayModel;

    }
    List<HolidayModel> toHolidayModelList(List<ResponseHolidaysApi> responseHolidaysApiList);
}
