package com.colombia.eps.holidays.infrastructure.configuration;

import com.colombia.eps.holidays.domain.api.IApiHolidaysServicePort;
import com.colombia.eps.holidays.domain.api.IHolidaysServicePort;
import com.colombia.eps.holidays.domain.spi.IApiHolidaysPersistencePort;
import com.colombia.eps.holidays.domain.spi.IHolidaysPersistencePort;
import com.colombia.eps.holidays.domain.usecase.ApiHolidaysUseCase;
import com.colombia.eps.holidays.domain.usecase.HolidayUseCase;
import com.colombia.eps.holidays.infrastructure.output.dynamo.adapter.DynamoAdapter;
import com.colombia.eps.holidays.infrastructure.output.dynamo.mapper.IDynamoMapper;
import com.colombia.eps.holidays.infrastructure.output.dynamo.repository.DynamoRepository;
import com.colombia.eps.holidays.infrastructure.output.feing.adapter.ApiHolidaysAdapter;
import com.colombia.eps.holidays.infrastructure.output.feing.client.IApiHolidaysClient;
import com.colombia.eps.holidays.infrastructure.output.feing.mapper.IApiHolidaysMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    private final IApiHolidaysClient apiHolidaysClient;
    private final IApiHolidaysMapper apiHolidaysMapper;
    private final DynamoRepository dynamoRepository;
    private final IDynamoMapper dynamoMapper;

    @Bean
    public IApiHolidaysPersistencePort apiHolidaysPersistencePort() {
        return new ApiHolidaysAdapter(apiHolidaysClient,apiHolidaysMapper);
    }

    @Bean
    public IApiHolidaysServicePort apiHolidaysServicePort() {
        return new ApiHolidaysUseCase(apiHolidaysPersistencePort());
    }
    @Bean
    public IHolidaysPersistencePort holidaysPersistencePort(){
        return new DynamoAdapter(dynamoRepository,dynamoMapper);
    }
    @Bean
    public IHolidaysServicePort holidaysServicePort(){
        return new HolidayUseCase(holidaysPersistencePort());
    }
}
