package it.unisa.controller;

import it.unisa.utils.UserHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        switch (type) {
            case "updateUserInfo" -> {
                UserHelper.updateUserInfo(req);
            }
        }

        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }
}
