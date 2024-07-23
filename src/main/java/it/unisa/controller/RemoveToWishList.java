package it.unisa.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import com.mysql.cj.Session;
import it.unisa.model.WishList.WishListBean;
import it.unisa.model.WishList.WishListDAO;
import it.unisa.model.WishListItem.WishListItemBean;
import it.unisa.model.WishListItem.WishListItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PipedReader;
import java.sql.SQLException;
@WebServlet("/removeFromWishlistServlet")

public class RemoveToWishList extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        int utenteId = (int) session.getAttribute("utenteId");
        int prodottoId = (int) session.getAttribute("prodottoId");

        try {
            WishListDAO wishListDAO = new WishListDAO();
            WishListItemDAO wishListItemDAO = new WishListItemDAO();

            int wishListId = wishListDAO.doRetrieveByUser(utenteId).getWishListId();
            wishListItemDAO.doDelete(wishListId,prodottoId);


        } catch (Exception e) {
        throw new ServletException("Errore nel rimuovere il prodotto dalla wishlist", e);}

        resp.sendRedirect(req.getContextPath() + "/wishList.jsp");
    }
}