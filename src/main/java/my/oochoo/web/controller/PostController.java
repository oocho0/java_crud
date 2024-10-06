package my.oochoo.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.oochoo.jdbc.ApplicationFactory;
import my.oochoo.web.service.PostService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/")
public class PostController extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(PostController.class.getName());
    private PostService postService = ApplicationFactory.getPostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showPostList(HttpServletRequest req, HttpServletResponse resp) {

    }
}
