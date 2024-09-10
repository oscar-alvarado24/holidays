package com.colombia.eps.holidays.infrastructure.exception;

public class DynamoDbManagerException extends RuntimeException{
    public DynamoDbManagerException(String message) {
        super(message);
    }
}
