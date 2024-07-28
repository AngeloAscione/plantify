package it.unisa.controller;

import it.unisa.model.cartItem.CartItemBean;
import it.unisa.model.cartItem.CartItemDAO;
import it.unisa.model.prodotto.ProdottoDAO;
import it.unisa.utils.CartHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (type == null) return;

        switch (type){
            case "addToCart" -> {
                String response = CartHelper.addToCart(req);
                out.println(response);
            }
            case "removeFromCart" -> {
                String response = CartHelper.removeFromCart(req);
                out.println(response);
            }
            case "updateQta" -> {
                String response = CartHelper.updateQta(req);
                out.println(response);
            }
            case "totalPrice" -> {
                String response = CartHelper.totalPrice(req);
                out.println(response);
            }
        }
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
