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
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DynamoAdapter implements IHolidaysPersistencePort {

    private final DynamoRepository dynamoRepository;
    private final IDynamoMapper dynamoMapper;

    /**
     * @param cities where the doctor works
     * @return list of holidays
     */
    @Override
    public Map<String,List<LocalDate>> getHolidays(List<String> cities) {
        try (DynamoDbManager manager = new DynamoDbManager()) {
            DynamoDbTable<Holidays> table = manager.createTable();
            List<LocalDate> nationalHolidaysList = new ArrayList<>(dynamoMapper.toLocalDateList(dynamoRepository.getHolidaysByIndex(Constants.ORDER_INDEX, Constants.NATIONAL, table)));
            return cities.stream()
                    .collect(Collectors.toMap(
                            city -> city,  // La ciudad es la clave
                            city -> {
                                // Obtener la lista de festivos por ciudad
                                List<Holidays> cityHolidays = dynamoRepository.getHolidaysByIndex(Constants.CITY_INDEX,city, table);
                                List<LocalDate> dateCityHolidays = dynamoMapper.toLocalDateList(cityHolidays);
                                // Combinar la lista de festivos por ciudad con los festivos nacionales
                                dateCityHolidays.addAll(nationalHolidaysList);
                                return dateCityHolidays;
                            }
                    ));
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
