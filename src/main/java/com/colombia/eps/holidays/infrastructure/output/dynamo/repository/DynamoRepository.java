package com.colombia.eps.holidays.infrastructure.output.dynamo.repository;

import com.colombia.eps.holidays.common.util.Constants;
import com.colombia.eps.holidays.infrastructure.exception.DontSaveHolidateListException;
import com.colombia.eps.holidays.infrastructure.output.dynamo.entity.Holidays;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class DynamoRepository {

    public List<Holidays> getItemByIndex(String index, String valueSearch, DynamoDbTable<Holidays> table){
        DynamoDbIndex<Holidays> dbIndex = table.index(index);

        // Perform the query for the attribute
        QueryConditional queryConditionalA = QueryConditional.keyEqualTo(Key.builder()
                .partitionValue(valueSearch)
                .build());

        SdkIterable<Page<Holidays>> queryResult = dbIndex.query(r -> r.queryConditional(queryConditionalA));
        return queryResult.stream()
                .flatMap(page -> page.items().stream())
                .collect(Collectors.toList());
    }

    public String saveHolidays(List<Holidays> holidays,DynamoDbEnhancedClient enhancedClient, DynamoDbTable<Holidays> table){
        try {
            WriteBatch.Builder<Holidays> writeBatchBuilder = WriteBatch.builder(Holidays.class)
                    .mappedTableResource(table);

            for (Holidays holiday : holidays) {
                writeBatchBuilder.addPutItem(holiday);
            }

            BatchWriteItemEnhancedRequest batchWriteItemEnhancedRequest = BatchWriteItemEnhancedRequest.builder()
                    .writeBatches(writeBatchBuilder.build())
                    .build();
            enhancedClient.batchWriteItem(batchWriteItemEnhancedRequest);
            return Constants.SAVE_HOLIDAYS_SUCCESSFULLY;
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new DontSaveHolidateListException(Constants.SAVE_HOLIDAYS_FAILED);
        }
    }

    public String saveHoliday(Holidays holidays, DynamoDbTable<Holidays> table){
        table.putItem(holidays);
        return Constants.SAVE_HOLIDAY_SUCCESSFULLY;
    }
}