package com.exam.assignment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder html = new StringBuilder();
        try {
            List<Post> posts = PostsDAO.getPosts();

            html.append("<table>");
            html.append("<tr><th>ID</th><th>Title</th><th>Content</th><th>Author</th><th>Creation Date</th></tr>");

            for (Post post: posts){
                html.append("<tr>")
                        .append(String.format("<td>%d</td>", post.getId()))
                        .append(String.format("<td>%s</td>", post.getTitle()))
                        .append(String.format("<td>%s</td>", post.getContent()))
                        .append(String.format("<td>%s</td>", post.getAuthor()))
                        .append(String.format("<td>%s</td>", post.getCreationDate()))
                        .append("</tr>");
            }

            html.append("</table>");
        } catch (SQLException e) {
            throw new RuntimeException("Sql Exception: " + e.getMessage());
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(html);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        Post post = Post.of(req.getParameter("title"), req.getParameter("content"), req.getParameter("author"));

        if (post.getTitle().isEmpty() || post.getContent().isEmpty() || post.getAuthor().isEmpty()){
            try (PrintWriter out = resp.getWriter()) {
                out.println("<h1> Wrong format, fields are empty! </h1>");
            }
            return;
        }

        try {
            PostsDAO.savePost(post);
        } catch (SQLException e) {
            throw new RuntimeException("Sql Exception: " + e.getMessage());
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<h1> Post was successfully saved! </h1>");
        }
    }

}