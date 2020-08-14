package ru.job4j.store;

//import ru.job4j.condition.Driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement("insert into post(name, text, link, created)" 
                + " values(?, ?, ?, ?)")) {
            ps.setString(1, post.getName());
            ps.setString(1, post.getText());
            ps.setString(1, post.getLink());
            ps.setString(1, post.getDate().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement("select * from post")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(new Post(rs.getString("name"),
                            rs.getString("text"), rs.getString("link"),
                            rs.getString("created")));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public Post findById(String id) {
        Post result = null;
        try (PreparedStatement st = cnn.prepareStatement("select * from post where id=?")) {
            st.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    result = new Post(new Post(rs.getString("name"),
                            rs.getString("text"), rs.getString("link"),
                            rs.getString("created"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}
