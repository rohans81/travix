package com.travix.medusa.busyflights.validation;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormat {
    ISO_LOCAL_DATE (DateTimeFormatter.ISO_LOCAL_DATE);

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    private DateTimeFormatter dateTimeFormatter;
    DateTimeFormat(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
