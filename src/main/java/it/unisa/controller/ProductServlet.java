package it.unisa.controller;

import it.unisa.model.prodotto.ProdottoBean;
import it.unisa.model.prodotto.ProdottoDAO;
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

        String type = req.getParameter("requestType");
        UtenteDAO utenteDAO = new UtenteDAO();
        String address = "products.jsp";
        try {
            switch (type) {
                case "addProduct":
                case "modifyProduct":
                case "deleteProduct":
                    try {
                        if (utenteDAO.isAdmin(Integer.parseInt(req.getParameter("utenteId")))) {
                            ProductServlet.class.getMethod(type, new Class[]{HttpServletRequest.class}).invoke(req);
                            req.setAttribute(type, 0);
                        }
                    }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex){
                        ;
                    }
                    break;
                case "getProductDetails":
                    address = getProductDetails(req);
                    break;
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
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        prodottoDAO.doDelete(prodottoBean.getProdottoId());
    }

    private String getProductDetails(HttpServletRequest req){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        String address = "productDetails.jsp";
        try {
            ProdottoBean pb = prodottoDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("prodottoId")));
            req.setAttribute("getProductsDetails", 0);
            req.setAttribute("productDetails", pb);
        } catch (SQLException ex){
            req.setAttribute("getProductDetails", 1);
            req.setAttribute("errorMessage", ex.getMessage());
            address = "products.jsp";
        }
        return address;
    }

    public static ProdottoBean createProdottoBeanFromRequest(HttpServletRequest request) {
        ProdottoBean prodotto = new ProdottoBean();

        // Ottieni i parametri dalla request e imposta i campi del bean
        int prodottoId = Integer.parseInt(request.getParameter("prodottoId") != null ? request.getParameter("prodottoId") : "-1");
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId") != null ? request.getParameter("categoriaId") : "-1");
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        Double prezzoStr = Double.parseDouble(request.getParameter("prezzo") != null ? request.getParameter("prezzo") : "0.0");
        int qta = Integer.parseInt(request.getParameter("qta") != null ? request.getParameter("qta") : "0");
        String foto = request.getParameter("foto");

        // Converti i parametri in tipi adeguati e imposta i campi del bean
        prodotto.setProdottoId(prodottoId);
        prodotto.setCategoriaId(categoriaId);
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setPrezzo(prezzoStr);
        prodotto.setQta(qta);
        prodotto.setFoto(foto);

        return prodotto;
    }
}
