package it.unisa.controller;

import it.unisa.model.DBConnector;
import it.unisa.model.prodotto.ProdottoBean;
import it.unisa.model.prodotto.ProdottoDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/index.html")
public class ProductServlet extends HttpServlet {
    /*
    * Questa servlet utilizza ProdottoDAO per recuperare tutti i prodotti dal database.
I prodotti recuperati vengono impostati come attributo della richiesta con il nome "products".
La richiesta viene quindi inoltrata alla pagina JSP products.jsp per la visualizzazione.*/


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector.getInstance().setParameter("jdbc:mysql://bfpoyklzrtfcmj1mapbm-mysql.services.clever-cloud.com:3306/bfpoyklzrtfcmj1mapbm", "uy80mmnvr1uxpplr", "9vlC7RSQinwr6XkkMMdi");
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        try {
            Collection<ProdottoBean> prodotti = prodottoDAO.doRetrieveAll();
            req.setAttribute("products", prodotti);
            req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

