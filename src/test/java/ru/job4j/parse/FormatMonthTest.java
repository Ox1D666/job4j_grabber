package ru.job4j.parse;

import org.junit.Test;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class FormatMonthTest {
    private final Locale language = new Locale("ru");
    @Test
    public void setDate() throws ParseException {
        FormatMonth fm = new FormatMonth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy, HH:mm");
        DateFormatSymbols instance = DateFormatSymbols.getInstance(language);
        instance.setShortMonths(new String[]{"янв", "февр", "мар", "апр", "май", "июн", "июл",
                "авг", "сент", "окт", "нояб", "дек"});
        dateFormat.setDateFormatSymbols(instance);
        String date = "17 окт 07, 14:42";
        Date res = fm.format(date);
        String[] str = dateFormat.format(res).split(" ");
        assertEquals("17", str[0]);
        assertEquals("окт", str[1]);
        assertEquals("07,", str[2]);
        assertEquals("14:42", str[3]);
    }
}