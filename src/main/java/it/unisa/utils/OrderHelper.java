package it.unisa.utils;

import it.unisa.model.cartItem.CartItemBean;
import it.unisa.model.cartItem.CartItemDAO;
import it.unisa.model.orderItem.OrderItemBean;
import it.unisa.model.orderItem.OrderItemDAO;
import it.unisa.model.ordine.OrdineBean;
import it.unisa.model.ordine.OrdineDAO;
import it.unisa.model.prodotto.ProdottoDAO;
import it.unisa.model.utente.UtenteBean;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderHelper {

    public static boolean saveOrder(HttpServletRequest request){

        OrdineBean ordineBean = new OrdineBean();
        UtenteBean utenteBean = (UtenteBean) request.getSession().getAttribute("userInfo");
        CartItemDAO cartItemDAO = new CartItemDAO();
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        ordineBean.setUtenteId(utenteBean.getUtenteId());
        ordineBean.setTotale(0);
        ordineBean.setDataOrdine(LocalDateTime.now());
        ordineBean.setStatoOrdine(OrdineBean.StatoOrdine.PAGATO);

        OrdineDAO ordineDAO = new OrdineDAO();
        int newKey = -1;
        try {
            newKey = ordineDAO.doSave(ordineBean, true);
            ordineBean = ordineDAO.doRetrieveByKey(newKey);
        } catch (SQLException e) {
            request.setAttribute("orderError", true);
            return false;
        }

        Set<CartItemBean> carrello = (Set<CartItemBean>) request.getSession().getAttribute("cart");

        try {
            for (CartItemBean i : carrello) {
                OrderItemBean temp = new OrderItemBean();
                temp.setQuantita(i.getQuantita());
                if (temp.getQuantita() > prodottoDAO.doRetrieveByKey(i.getProdottoId()).getQta() || temp.getQuantita() <= 0){
                    request.setAttribute("orderError", true);
                    return false;
                }
                temp.setProdottoId(i.getProdottoId());
                temp.setOrdineId(newKey);
                temp.setPrezzo(prodottoDAO.doRetrieveByKey(i.getProdottoId()).getPrezzo() * temp.getQuantita());
                ordineBean.setTotale(ordineBean.getTotale() + temp.getPrezzo());
                orderItemDAO.doSave(temp);
            }


            ordineDAO.doUpdate(ordineBean);
            cartItemDAO.doRemoveByCartId((Integer) request.getSession().getAttribute("carrelloId"));
            request.getSession().setAttribute("cart", null);

        } catch (SQLException ex){
            request.setAttribute("orderError", true);
            return false;
        }

        return true;
    }


    public static void getOrders(HttpServletRequest request) {

        if (request.getSession().getAttribute("logged") == null){
            return;
        }

        OrdineDAO ordineDAO = new OrdineDAO();
        try {
            Set<OrdineBean> ordini = ordineDAO.doRetrieveByUserId(((UtenteBean)request.getSession().getAttribute("userInfo")).getUtenteId());
            request.setAttribute("ordini", ordini);
        } catch (SQLException e) {
            return;
        }

    }
}
