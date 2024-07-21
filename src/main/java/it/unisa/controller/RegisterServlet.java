package it.unisa.controller;

import it.unisa.model.utente.UtenteBean;
import it.unisa.utils.PasswordTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtenteBean ub = new UtenteBean();
        ub.setNome(req.getParameter("nome"));
        ub.setCognome(req.getParameter("cognome"));
        ub.setEmail(req.getParameter("email"));
        ub.setPassword(req.getParameter("password"));
        ub.setVia(req.getParameter("via"));
        ub.setCivico(Integer.parseInt(req.getParameter("civico")));
        ub.setCap(Integer.parseInt(req.getParameter("cap")));
        ub.setTelefono(req.getParameter("numero"));

        String address;
        if (PasswordTool.isValidPassword(ub.getPassword())){
            ub.setPassword(PasswordTool.cipherPassword(ub.getPassword()));
            address = "index.jsp";
        } else {
            address = "register.jsp";
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(address);
        requestDispatcher.forward(req, resp);
    }
}
