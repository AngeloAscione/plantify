package it.unisa.controller;

import it.unisa.model.WishListItem.WishListItemBean;
import it.unisa.model.cartItem.CartItemBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@WebServlet("/index.html")
public class MainServlet extends HttpServlet {
    /*
    * Questa servlet accoglie le richieste su index.html ed effettua un forward su homepage.*/


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        synchronized (session) {
            if (session.getAttribute("cart") == null) {
                session.setAttribute("cart", new HashSet<CartItemBean>());
            }
            if (session.getAttribute("wishList") == null){
                session.setAttribute("wishList", new ArrayList<CartItemBean>());
            }
        }

        req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
    }
}

