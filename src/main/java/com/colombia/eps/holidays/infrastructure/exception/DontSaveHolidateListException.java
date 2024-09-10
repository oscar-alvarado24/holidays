package com.colombia.eps.holidays.infrastructure.exception;

public class DontSaveHolidateListException extends RuntimeException {
    public DontSaveHolidateListException(String message) {
        super(message);
    }
}
