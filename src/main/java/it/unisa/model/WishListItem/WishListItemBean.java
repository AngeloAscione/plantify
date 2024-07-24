package it.unisa.model.WishListItem;

import it.unisa.model.WishList.WishListBean;

import java.util.Collection;

public class WishListItemBean{
    private int wishListItemId;
    private int prodottoId;

    public int getWishListItemId() {
        return wishListItemId;
    }

    public void setWishListItemId(int wishListItemId) {
        this.wishListItemId = wishListItemId;
    }

    public int getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(int prodottoId) {
        this.prodottoId = prodottoId;
    }

    public static void updateWishListID(int id, Collection<WishListItemBean> wishList){
        for (WishListItemBean i : wishList){
        }
    }
}
