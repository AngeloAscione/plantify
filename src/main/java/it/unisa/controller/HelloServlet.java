package it.unisa.controller;

import java.io.*;

import it.unisa.model.DBConnector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/init")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DBConnector.getInstance().setParameter("jdbc:mysql://bfpoyklzrtfcmj1mapbm-mysql.services.clever-cloud.com:3306/bfpoyklzrtfcmj1mapbm", "uy80mmnvr1uxpplr", "9vlC7RSQinwr6XkkMMdi");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}