package it.unisa.controller;

import it.unisa.model.prodotto.ProdottoBean;
import it.unisa.model.prodotto.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchServelt extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search =  req.getParameter("q");
        List<ProdottoBean> prodotto = new ArrayList<>();
        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            prodotto = prodottoDAO.searchProduct(search);
        } catch (Exception e) {
            resp.getWriter().println("{\"status\":\"error\"}");
            return;
        }
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("{\"status\":\"success\"");
        responseBuilder.append(",\"products\":{");
        for(int i = 0; i < prodotto.size(); i++) {
            responseBuilder.append("\""+prodotto.get(i).getNome()+"\" : " + prodotto.get(i).getProdottoId());
            if (i != prodotto.size() - 1) {
                responseBuilder.append(",");
            }
        }
        responseBuilder.append("}}");
        resp.getWriter().println(responseBuilder.toString());
    }
}
