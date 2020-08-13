package ru.job4j.grabber;

import ru.job4j.model.Post;

import java.util.List;

public interface Store {
    void save(Post post);

    Post get();

    List<Post> getAll();
}
