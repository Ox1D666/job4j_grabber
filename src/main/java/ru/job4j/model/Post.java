package ru.job4j.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String link;
    private String text;
    private Timestamp date;

    public Post(String name, String link, String text, Timestamp date) {
        this.name = name;
        this.link = link;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && name.equals(post.name)
                && Objects.equals(text, post.text)
                && Objects.equals(link, post.link)
                && date.equals(post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String toString() {
        return "id= " + id + ", name= " + name + ", url= " + link + ", date= " + date;
    }
}

