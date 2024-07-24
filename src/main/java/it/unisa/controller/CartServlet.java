package it.unisa.controller;

import it.unisa.model.cartItem.CartItemBean;
import it.unisa.model.cartItem.CartItemDAO;
import it.unisa.model.prodotto.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = (String)req.getAttribute("type");
        String type3 = req.getParameter("type");

        if (type == null) return;


        switch (type){
            case "addToCart": {
                addToCart(req);
            }
        }
    }

    public void addToCart(HttpServletRequest request){
        Integer prodottoId = Integer.parseInt((String) request.getAttribute("prodottoId"));
        CartItemBean cartItemBean = new CartItemBean();
        cartItemBean.setProdottoId(Integer.parseInt((String) request.getAttribute("prodottoId")));
        cartItemBean.setQuantita(1);

        List<CartItemBean> carrello = (List<CartItemBean>)request.getSession().getAttribute("cart");
        carrello.add(cartItemBean);

        request.getSession().setAttribute("cart", carrello);
    }
}
