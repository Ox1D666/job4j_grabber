package ru.job4j.store;

import ru.job4j.model.Post;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface Store {
    void save(Post post);

    Post findById(String id);

    List<Post> getAll() throws SQLException;

    Timestamp getDateLastPost() throws SQLException;
}
