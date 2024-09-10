package com.colombia.eps.holidays.infrastructure.output.dynamo.adapter;

import com.colombia.eps.holidays.common.util.Constants;
import com.colombia.eps.holidays.domain.model.HolidayModel;
import com.colombia.eps.holidays.domain.spi.IHolidaysPersistencePort;
import com.colombia.eps.holidays.infrastructure.exception.DontCreateLocalHolidayException;
import com.colombia.eps.holidays.infrastructure.exception.DontCreateNationalHolidayException;
import com.colombia.eps.holidays.infrastructure.exception.DontGetHolidaysException;
import com.colombia.eps.holidays.infrastructure.output.dynamo.config.DynamoDbManager;
import com.colombia.eps.holidays.infrastructure.output.dynamo.entity.Holidays;
import com.colombia.eps.holidays.infrastructure.output.dynamo.mapper.IDynamoMapper;
import com.colombia.eps.holidays.infrastructure.output.dynamo.repository.DynamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DynamoAdapter implements IHolidaysPersistencePort {

    private final DynamoRepository dynamoRepository;
    private final IDynamoMapper dynamoMapper;

    /**
     * @param city where the doctor works
     * @return list of holidays
     */
    @Override
    public List<String> getHolidays(String city) {
        try (DynamoDbManager manager = new DynamoDbManager()) {
            DynamoDbTable<Holidays> table = manager.createTable();
            List<Holidays> holidaysList = new ArrayList<>(dynamoRepository.getItemByIndex(Constants.ORDER_INDEX, Constants.NATIONAL, table));
            holidaysList.addAll(dynamoRepository.getItemByIndex(Constants.CITY_INDEX, city, table));


            return holidaysList.stream()
                    .map(Holidays::getDate)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new DontGetHolidaysException();
        }
    }

    /**
     * @param holidayModel that represent holiday to create
     * @return confirmation message of creation
     */
    @Override
    public String createLocalHoliday(HolidayModel holidayModel) {
        try (DynamoDbManager manager = new DynamoDbManager()) {
            return dynamoRepository.saveHoliday(dynamoMapper.toHolidays(holidayModel), manager.createTable());
        }catch (Exception exception) {
            log.error(exception.getMessage());
            throw new DontCreateLocalHolidayException();
        }
    }

    /**
     * @param holidayModels that represent national holidays obtained converted to HolidayModel
     * @return confirmation message of creation
     */
    @Override
    public String createNationalHoliday(List<HolidayModel> holidayModels) {
        try (DynamoDbManager manager = new DynamoDbManager()) {
            return dynamoRepository.saveHolidays(dynamoMapper.toHolidays(holidayModels), manager.getEnhancedClient(), manager.createTable());
        }catch (Exception exception) {
            log.error(exception.getMessage());
            throw new DontCreateNationalHolidayException();
        }
    }
}
