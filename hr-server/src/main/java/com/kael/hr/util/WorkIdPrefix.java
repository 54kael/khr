package com.kael.hr.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/10 0010
 */
public class WorkIdPrefix {
    public static String createWorkIdPrefix() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        return localDate.format(dateTimeFormatter);
    }
}
