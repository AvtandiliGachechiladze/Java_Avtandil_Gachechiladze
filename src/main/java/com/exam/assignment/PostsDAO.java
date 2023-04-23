package com.exam.assignment;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostsDAO {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String INSERT_POST_QUERY = "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, NOW())";
    private static final String SELECT_POST_QUERY = "SELECT * FROM %s";
    private static final String TABLE_POSTS = "posts";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_CREATION_DATE = "creation_date";


    public static void savePost(Post post) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparePostInsertStatement(connection, post.getTitle(), post.getContent(), post.getAuthor())) {
            statement.executeUpdate();
        }
    }

    public static List<Post> getPosts() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = getSelectPreparedStatement(conn, TABLE_POSTS);
        ResultSet results = statement.executeQuery();
        List<Post> posts = new ArrayList<>();

        while(results.next()) {
            int id = results.getInt("id");
            String title = results.getString("title");
            String content = results.getString("content");
            String author = results.getString("author");
            LocalDate creationDate = LocalDate.parse(results.getString("creation_date"), DATE_FORMAT);

            posts.add(Post.of(id, title, content, author, creationDate));
        }

        results.close();
        statement.close();
        conn.close();

        return posts;
    }

    public static PreparedStatement getSelectPreparedStatement(Connection conn, String tableName) throws SQLException {
        String query = String.format(SELECT_POST_QUERY, tableName);
        return conn.prepareStatement(query);
    }


    private static PreparedStatement preparePostInsertStatement(Connection connection, String title, String content, String author) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format(INSERT_POST_QUERY, TABLE_POSTS, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_AUTHOR, COLUMN_CREATION_DATE));
        statement.setString(1, title);
        statement.setString(2, content);
        statement.setString(3, author);
        return statement;
    }
}