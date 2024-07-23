package it.unisa.controller;

import it.unisa.model.carrello.CarrelloDAO;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteDAO;
import it.unisa.utils.PasswordTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtenteDAO utenteDAO = new UtenteDAO();
        String loginEmail = req.getParameter("email");
        String address;
        try {
            UtenteBean ub = utenteDAO.doRetrieveByEmail(loginEmail);
            if (ub != null){
                String loginPassword = req.getParameter("password");
                if (PasswordTool.cipherPassword(loginPassword).equals(ub.getPassword())){
                    address = "homepage.jsp";

                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    carrelloDAO.doRetrieveByUserId(ub.getUtenteId());

                    HttpSession session = req.getSession(true);
                    synchronized (session) {
                        session.setAttribute("userInfo", ub);
                        session.setAttribute("logged", true);
                    }
                } else {
                    address = "login.jsp";
                    req.setAttribute("passwordNotValid", 1);
                }
            } else {
                address = "login.jsp";
                req.setAttribute("emailNotValid", 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("homepage.jsp");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(address);
//        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
