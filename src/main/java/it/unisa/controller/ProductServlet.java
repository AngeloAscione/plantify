package it.unisa.controller;

import it.unisa.model.prodotto.ProdottoBean;
import it.unisa.model.prodotto.ProdottoDAO;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        if (type == null)
            type = (String)req.getAttribute("type");

        UtenteDAO utenteDAO = new UtenteDAO();
        String address = "products.jsp";
        try {
            switch (type) {
                case "addProduct" -> {
                    if(utenteDAO.isAdmin(((UtenteBean)req.getSession().getAttribute("userInfo")).getUtenteId()))
                        addProduct(req);
                }
                case "modifyProduct" -> {
                    if(utenteDAO.isAdmin(((UtenteBean)req.getSession().getAttribute("userInfo")).getUtenteId()))
                        modifyProduct(req);
                }
                case "deleteProduct" -> {
                    if (utenteDAO.isAdmin(((UtenteBean)req.getSession().getAttribute("userInfo")).getUtenteId()))
                        deleteProduct(req);
                }
                case "getProductDetails" -> {
                    address = getProductDetails(req);
                }
            }
        }catch (SQLException ex){
            req.setAttribute(type, 1);
            req.setAttribute("errorMessage", ex.getMessage());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(address);
        requestDispatcher.forward(req, resp);
    }

    private void addProduct(HttpServletRequest req) throws SQLException {
        ProdottoBean prodottoBean = createProdottoBeanFromRequest(req);
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        prodottoDAO.doSave(prodottoBean);
    }

    private void modifyProduct(HttpServletRequest req) throws SQLException {
        ProdottoBean prodottoBean = createProdottoBeanFromRequest(req);
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        prodottoDAO.doUpdate(prodottoBean);
    }

    private void deleteProduct(HttpServletRequest req) throws SQLException {
        ProdottoBean prodottoBean = createProdottoBeanFromRequest(req);
        prodottoBean.setQta(-1);
        new ProdottoDAO().doUpdate(prodottoBean);
    }

    private String getProductDetails(HttpServletRequest req){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        String address = "productDetails.jsp";
        try {
            Integer prodottoId = Integer.parseInt(req.getParameter("prodottoId"));
            if (prodottoId == null){
                prodottoId = Integer.parseInt((String)req.getAttribute("prodottoId"));
            }
            ProdottoBean pb = prodottoDAO.doRetrieveByKey(prodottoId);
            req.setAttribute("getProductsDetails", 0);
            req.setAttribute("productDetails", pb);
        } catch (SQLException ex){
            req.setAttribute("getProductDetails", 1);
            req.setAttribute("errorMessage", ex.getMessage());
            address = "products.jsp";
        }
        return address;
    }

    public static ProdottoBean createProdottoBeanFromRequest(HttpServletRequest request) throws SQLException {
        return new ProdottoDAO().doRetrieveByKey(Integer.parseInt(request.getParameter("prodottoId")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodottoId = req.getParameter("prodottoId");
        req.setAttribute("prodottoId", prodottoId);
        req.setAttribute("type", "getProductDetails");
        doPost(req, resp);
    }
}
