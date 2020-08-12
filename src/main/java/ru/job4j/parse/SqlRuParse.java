package ru.job4j.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.model.Post;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SqlRuParse implements Parse {
    private Locale language = new Locale("ru");
    private SimpleDateFormat dateFormatAll = new SimpleDateFormat("dd MMM yy, HH:mm", language);
    private SimpleDateFormat todayAndYesterday = new SimpleDateFormat("dd MMM yy", language);

    public static void main(String[] args) throws Exception {
        SqlRuParse sqlRuParse = new SqlRuParse();
        sqlRuParse.list("https://www.sql.ru/forum/job-offers/");
    }

    @Override
    public List<Post> list(String link) throws ParseException, IOException {
        List<Post> posts = new ArrayList<>();
        DateFormatSymbols instance = DateFormatSymbols.getInstance(language);
        instance.setShortMonths(new String[]{"янв", "февр", "мар", "апр", "май", "июн", "июл",
                "авг", "сент", "окт", "нояб", "дек"});
        dateFormatAll.setDateFormatSymbols(instance);
        todayAndYesterday.setDateFormatSymbols(instance);
        Calendar date = Calendar.getInstance();
        for (int page = 1; page <= 5; page++) {
            Document doc = Jsoup.connect(link + page).get();
            Elements row = doc.selectFirst(".forumTable").select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                String url = href.attr("href");
                String name = href.text();
                String dateText = td.lastElementSibling().text();
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
                posts.add(new Post(name, url, "", date.getTime()));
            }
        }
        posts.forEach(System.out::println);
        return posts;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        return post;
    }
}
