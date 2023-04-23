package kucse.introductoryproject.b01;

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
        DateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            Date date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
