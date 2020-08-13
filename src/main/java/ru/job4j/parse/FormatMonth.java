package ru.job4j.parse;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormatMonth {
    private Locale language = new Locale("ru");
    private SimpleDateFormat dateFormatAll = new SimpleDateFormat("dd MMM yy, HH:mm", language);
    private SimpleDateFormat todayAndYesterday = new SimpleDateFormat("dd MMM yy", language);
    private final Calendar date = Calendar.getInstance();

    public FormatMonth() {
        DateFormatSymbols instance = DateFormatSymbols.getInstance(language);
        instance.setShortMonths(new String[]{"янв", "февр", "мар", "апр", "май", "июн", "июл",
                "авг", "сент", "окт", "нояб", "дек"});
        dateFormatAll.setDateFormatSymbols(instance);
        todayAndYesterday.setDateFormatSymbols(instance);
    }

    public Calendar getDate() {
        return date;
    }

    public Calendar format(String dateText) throws ParseException {
        if (dateText.contains("сегодня")) {
            String time = dateText.split(" ")[1];
            date.setTime(todayAndYesterday.parse(todayAndYesterday.format(date.getTime()) + ", " + time));
        } else if (dateText.contains("вчера")) {
            String time = dateText.split(" ")[1];
            date.add(Calendar.DATE, -1);
            date.setTime(todayAndYesterday.parse(todayAndYesterday.format(date.getTime()) + ", " + time));
        } else {
            date.setTime(dateFormatAll.parse(dateText));
        }
        return date;
    }
}
