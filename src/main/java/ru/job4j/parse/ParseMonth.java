package ru.job4j.parse;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ParseMonth {
    private Locale language = new Locale("ru");
    private SimpleDateFormat dateFormatAll = new SimpleDateFormat("dd MMM yy, HH:mm", language);
    private SimpleDateFormat todayAndYesterday = new SimpleDateFormat("dd MMM yy", language);

    public ParseMonth() {
        DateFormatSymbols instance = DateFormatSymbols.getInstance(language);
        instance.setShortMonths(new String[]{"янв", "февр", "мар", "апр", "май", "июн", "июл",
                "авг", "сент", "окт", "нояб", "дек"});
        dateFormatAll.setDateFormatSymbols(instance);
        todayAndYesterday.setDateFormatSymbols(instance);
    }
}
