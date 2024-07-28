package it.unisa.utils;

import it.unisa.model.cartItem.CartItemBean;
import it.unisa.model.cartItem.CartItemDAO;
import it.unisa.model.prodotto.ProdottoBean;
import it.unisa.model.prodotto.ProdottoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.*;

public class CartHelper {

    public static Set<CartItemBean> mergeCarts(int cartId, Set<CartItemBean> dbCart, Set<CartItemBean> sessionCart){
        Set<CartItemBean> merged = new HashSet<>();
        Map<Integer, CartItemBean> dbCartMap = new HashMap<>();
        for (CartItemBean item : dbCart) {
            dbCartMap.put(item.getProdottoId(), item);
        }
        for (CartItemBean sessionItem : sessionCart) {
            CartItemBean dbItem = dbCartMap.get(sessionItem.getProdottoId());
            if (dbItem != null) {
                dbItem.setQuantita(dbItem.getQuantita() + sessionItem.getQuantita());
                merged.add(dbItem);
            } else {
                sessionItem.setCarrelloId(cartId);
                merged.add(sessionItem);
            }
        }
        for (CartItemBean dbItem : dbCart) {
            if (!merged.contains(dbItem)) {
                merged.add(dbItem);
            }
        }

        return merged;
    }

    public static String addToCart(HttpServletRequest request){
        Integer prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
        if (prodottoId == null) return "{\"status\":\"error\"}";
        CartItemBean cartItemBean = new CartItemBean();
        cartItemBean.setProdottoId(prodottoId);
        cartItemBean.setQuantita(1);

        CartItemBean in = null;
        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
        if (carrello == null) return "{\"status\":\"error\"}";
        if (carrello.contains(cartItemBean)){
            Optional<CartItemBean> temp = carrello.stream().filter((v) -> v.equals(cartItemBean)).findFirst();
            if (temp.isPresent()){
                in = temp.get();
            }
        }

        if (in != null)
            in.setQuantita(in.getQuantita() + cartItemBean.getQuantita());
        else
            carrello.add(cartItemBean);

        return "{\"status\":\"success\"}";
    }

    public static String removeFromCart(HttpServletRequest request){
        Integer prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
        if (prodottoId == null) return "{\"status\":\"error\"}";

        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
        if (carrello == null) return "{\"status\":\"error\"}";

        CartItemBean toRemove = new CartItemBean();
        toRemove.setProdottoId(prodottoId);
        carrello.remove(toRemove);
        return "{\"status\":\"success\", \"dimCarrello\":\"" + carrello.size() +"\"}";
    }

    public static String updateQta(HttpServletRequest request) {
        Integer prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
        if (prodottoId == null) return "{\"status\":\"error\"}";
        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
        if (carrello == null) return "{\"status\":\"error\"}";
        Integer newQta = Integer.parseInt(request.getParameter("newQta"));
        if (newQta == null) return "{\"status\":\"error\"}";
        String response;

        try {
            ProdottoBean prodottoFromDb = new ProdottoDAO().doRetrieveByKey(prodottoId);

            CartItemBean temp = new CartItemBean();
            temp.setProdottoId(prodottoId);
            temp.setQuantita(prodottoFromDb.getQta());
            if (carrello.contains(temp)){
                CartItemBean finalTemp = temp;
                Optional<CartItemBean> p = carrello.stream().filter((v) -> v.equals(finalTemp)).findFirst();
                if (p.isPresent()){
                    temp = p.get();
                }
            } else {
                carrello.add(temp);
            }

            if (newQta > prodottoFromDb.getQta()){
                temp.setQuantita(prodottoFromDb.getQta());
                response = "{\"status\":\"maxOverflow\", \"maxNum\":\"" + prodottoFromDb.getQta() + "\", \"dimCarrello\":\"" + carrello.stream().mapToInt((v) -> v.getQuantita()).sum() + "\"}";
            } else {
                temp.setQuantita(newQta);
                response = "{\"status\":\"success\", \"dimCarrello\":\"" + carrello.stream().mapToInt((v) -> v.getQuantita()).sum() + "\"}";
            }

        } catch (SQLException e) {
            return "{\"status\":\"error\"}";
        }
        return response;
    }

    public static String totalPrice(HttpServletRequest request) {

        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
        if (carrello == null) return "{\"status\":\"error\"}";

        Double totalPrice = carrello.stream().mapToDouble((v) -> {
            try {
                return new ProdottoDAO().doRetrieveByKey(v.getProdottoId()).getPrezzo() * v.getQuantita();
            } catch (SQLException e) {
                return 0.0;
            }
        }).sum();

        return "{\"status\":\"success\", \"totalPrice\":\""+ String.format("%.2f",totalPrice) +"\"}";
    }

    public static void saveCart(HttpServletRequest request) {
        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
        if (carrello == null) return;

        CartItemDAO cartItemDAO = new CartItemDAO();

        HttpSession session = request.getSession();

        for (CartItemBean i : carrello){
            i.setCartItemId((Integer) session.getAttribute("carrelloId"));
            try {
                cartItemDAO.doSave(i);
            } catch (SQLException e) {
                return;
            }
        }

    }
}
