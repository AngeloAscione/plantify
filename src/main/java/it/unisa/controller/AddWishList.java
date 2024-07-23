package it.unisa.controller;

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

@WebServlet("/addWishList")
public class AddWishList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        int utenteId = (int) session.getAttribute("utenteId");
        int prodottoId = (int) session.getAttribute("prodottoId");

        //utente non loggato non so come scriverlo
        if(utenteId < 1){
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            WishListDAO wishListDAO = new WishListDAO();
            WishListItemDAO itemDAO = new WishListItemDAO();

            WishListBean wishListBean = wishListDAO.doRetrieveByKey(utenteId);

            if (wishListBean == null) {
                // Crea se non esiste
                wishListBean = new WishListBean();
                wishListBean.setUtenteId(utenteId);
                wishListDAO.doSave(wishListBean);
            }

            //controllo se il prodotto è nella wishList
            for (WishListItemBean item : itemDAO.doRetrieveByWishlistId(wishListBean.getWishListId())) {
                if (item.getProdottoId() == prodottoId) {
                    resp.sendRedirect(req.getContextPath() + "/wihsList.jsp");
                    return;
                }
            }

            WishListItemBean wishListItemBean = new WishListItemBean();
            wishListItemBean.setWishListItemId(wishListBean.getWishListId());
            wishListItemBean.setProdottoId(prodottoId);
            itemDAO.doSave(wishListItemBean);

            resp.sendRedirect(req.getContextPath() + "/wishList.jsp");

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
