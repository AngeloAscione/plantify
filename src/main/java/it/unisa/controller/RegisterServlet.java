package it.unisa.controller;

import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteDAO;
import it.unisa.utils.PasswordTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
            address = "homepage.jsp";
            UtenteDAO ud = new UtenteDAO();
            try {
                if (ud.doRetrieveByEmail(ub.getEmail()) == null){
                    ud.doSave(ub);
                } else {
                    address = "register.jsp";
                    req.setAttribute("emailTaken", 1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            address = "register.jsp";
            req.setAttribute("passwordNotValid", 1);
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(address);
        requestDispatcher.forward(req, resp);
    }
}
