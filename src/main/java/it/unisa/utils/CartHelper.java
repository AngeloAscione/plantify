package it.unisa.utils;

import it.unisa.model.cartItem.CartItemBean;

import java.util.ArrayList;
import java.util.List;

public class CartHelper {

    public static List<CartItemBean> mergeCarts(int cartId, List<CartItemBean> dbCart, List<CartItemBean> sessionCart){
        List<CartItemBean> merged = new ArrayList<>();
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

}
