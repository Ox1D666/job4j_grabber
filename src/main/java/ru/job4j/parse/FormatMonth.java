package ru.job4j.parse;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormatMonth {
    private final Locale language = new Locale("ru");
    private final SimpleDateFormat dateFormatAll = new SimpleDateFormat("dd MMM yy, HH:mm", language);
    private final SimpleDateFormat todayAndYesterday = new SimpleDateFormat("dd MMM yy", language);


    public FormatMonth() {
        DateFormatSymbols instance = DateFormatSymbols.getInstance(language);
        instance.setShortMonths(new String[]{"янв", "февр", "мар", "апр", "май", "июн", "июл",
                "авг", "сент", "окт", "нояб", "дек"});
        dateFormatAll.setDateFormatSymbols(instance);
        todayAndYesterday.setDateFormatSymbols(instance);
    }

    public Date format(String dateText) throws ParseException {
        Calendar date = Calendar.getInstance();
        if (dateText.contains("сегодня")) {
            String time = dateText.split(" ")[1];
            String s = todayAndYesterday.format(date.getTime()) + ", " + time;
            return dateFormatAll.parse(s);
        } else if (dateText.contains("вчера")) {
            String time = dateText.split(" ")[1];
            date.add(Calendar.DATE, -1);
            String s = todayAndYesterday.format(date.getTime()) + ", " + time;
            return dateFormatAll.parse(s);
        } else {
            return dateFormatAll.parse(dateText);
        }
    }
}