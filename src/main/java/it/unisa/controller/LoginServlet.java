package it.unisa.controller;

import it.unisa.model.WishList.WishListBean;
import it.unisa.model.WishList.WishListDAO;
import it.unisa.model.carrello.CarrelloBean;
import it.unisa.model.carrello.CarrelloDAO;
import it.unisa.model.cartItem.CartItemBean;
import it.unisa.model.cartItem.CartItemDAO;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteDAO;
import it.unisa.utils.CartHelper;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                    CarrelloBean carrelloBean = carrelloDAO.doRetrieveByUserId(ub.getUtenteId());

                    WishListDAO wishListDAO = new WishListDAO();
                    WishListBean wishListBean = wishListDAO.doRetrieveByUserId(ub.getUtenteId());

                    HttpSession session = req.getSession(true);
                    synchronized (session) {
                        session.setAttribute("userInfo", ub);
                        session.setAttribute("logged", true);
                        session.setAttribute("carrelloId", carrelloBean.getCarrelloId());
                        session.setAttribute("wishListId", carrelloBean.getCarrelloId());
                        Set<CartItemBean> sessionCart = (Set<CartItemBean>) session.getAttribute("cart");
                        CartItemDAO cartItemDAO = new CartItemDAO();
                        session.setAttribute("cart", CartHelper.mergeCarts(carrelloBean.getCarrelloId(), cartItemDAO.doRetrieveAllByCartId(carrelloBean.getCarrelloId()), sessionCart));
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

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(address);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
