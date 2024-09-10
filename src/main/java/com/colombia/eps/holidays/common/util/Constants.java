package com.colombia.eps.holidays.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String LOCAL = "local";
    public static final String NATIONAL = "nacional";
    public static final String TABLE_NAME = "holidays";
    public static final String SAVE_HOLIDAYS_SUCCESSFULLY = "Se crearon los festivos satisfactoriamente";
    public static final String ROLE_SESSION_NAME_DYNAMO = "dynamo-conn";
    public static final String SAVE_HOLIDAY_SUCCESSFULLY = "Se creo el festivo correctamente";
    public static final String SAVE_HOLIDAYS_FAILED = "Fallo el proceso de guardado de la lista de festivos";
    public static final String ORDER_INDEX = "order-index";
    public static final String CITY_INDEX = "city-index";
    public static final String COUNTRY = "CO";
    public static final String YEAR = "2024";
    public static final String DYNAMO_ROL = "DYNAMO_ROL";
}
