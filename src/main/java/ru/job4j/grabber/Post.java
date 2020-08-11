package ru.job4j.grabber;

import java.util.Calendar;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String text;
    private String url;
    private Calendar date;

    public Post(int id, String name, String text, String url, Calendar date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
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
                && Objects.equals(url, post.url)
                && date.equals(post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "id= " + id + ", name= " + name + ", url= " + url + ", date= " + date;
    }
}
