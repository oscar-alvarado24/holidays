package com.colombia.eps.holidays.infrastructure.output.dynamo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class Holidays {
    private String date;
    private String city;
    private String order;
    @Getter
    private String name;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("date")
    public String getDate() {
        return date;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "city-index")
    @DynamoDbAttribute("city")
    public String getCity() {
        return city;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "order-index")
    @DynamoDbAttribute("order")
    public String getOrder() {
        return order;
    }
}
