package com.travix.medusa.busyflights.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String convertFormat(String date, DateTimeFormatter from, DateTimeFormatter to) {
        return LocalDateTime.parse(date, from).format(to);
    }

}
