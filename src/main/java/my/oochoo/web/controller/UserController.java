package my.oochoo.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.oochoo.jdbc.ApplicationFactory;
import my.oochoo.web.service.UserService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private UserService userService = ApplicationFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
