package ru.job4j.parse;

import ru.job4j.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Parse {
    List<Post> list(String link) throws ParseException, IOException;

    Post detail(String link);
}