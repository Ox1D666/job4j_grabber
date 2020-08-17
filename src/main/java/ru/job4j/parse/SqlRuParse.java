package ru.job4j.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.model.Post;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

public class SqlRuParse implements Parse {
    private final FormatMonth fm = new FormatMonth();

    @Override
    public List<Post> list(String link) throws ParseException, IOException {
        List<Post> posts = new ArrayList<>();
        for (int page = 1; page <= 1; page++) {
            Document doc = Jsoup.connect(link + page).get();
            Elements row = doc.selectFirst(".forumTable").select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                String url = href.attr("href");
                String name = href.text();
                String dateText = td.lastElementSibling().text();
                posts.add(new Post(name, url, "", (Timestamp) fm.format(dateText)));
            }
        }
        return posts;
    }

    @Override
    public Post detail(String link) throws IOException, ParseException {
        Document doc = Jsoup.connect(link).get();
        Elements el = doc.select(".msgTable");
        String name = el.first().select(".messageHeader").text();
        String info = el.select(".msgBody").get(1).text();
        String dateText = el.first().select(".msgFooter").text().split("\\[")[0];
        return new Post(name, link, info, (Timestamp) fm.format(dateText));
    }
}
