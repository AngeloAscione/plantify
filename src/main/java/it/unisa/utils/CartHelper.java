package it.unisa.utils;

import it.unisa.model.cartItem.CartItemBean;
import jakarta.servlet.http.HttpServletRequest;

import javax.swing.text.html.Option;
import java.util.*;

public class CartHelper {

    public static Set<CartItemBean> mergeCarts(int cartId, List<CartItemBean> dbCart, Set<CartItemBean> sessionCart){
        Set<CartItemBean> merged = new HashSet<>();
        boolean found;
        for (CartItemBean i : sessionCart){
            found = false;
            for (CartItemBean j : dbCart) {
                if (j.getProdottoId() == i.getProdottoId()){
                    j.setQuantita(j.getQuantita() + i.getQuantita());
                    merged.add(j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                i.setCarrelloId(cartId);
                merged.add(i);
            }
        }
        return merged;
    }

    public static void addToCart(HttpServletRequest request){
        Integer prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
        CartItemBean cartItemBean = new CartItemBean();
        cartItemBean.setProdottoId(prodottoId);
        cartItemBean.setQuantita(1);

        CartItemBean in = null;
        Set<CartItemBean> carrello = (Set<CartItemBean>)request.getSession().getAttribute("cart");
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
    }

}
