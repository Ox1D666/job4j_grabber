package ru.job4j.parse;

import ru.job4j.model.Post;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

public interface Parse {
    List<Post> list(String link, Timestamp lastDate) throws Exception;

    Post detail(String link) throws IOException, ParseException;
}
