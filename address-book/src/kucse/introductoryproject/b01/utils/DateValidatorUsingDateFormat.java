package kucse.introductoryproject.b01.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidatorUsingDateFormat {
    private String dateFormat;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isValid(String dateStr) {
        return getDate(dateStr) != null;
    }

    public Date getDate(String dateStr) {
        DateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
        simpleDateFormat.setLenient(false);
        Date date;
        try {
             date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
}
