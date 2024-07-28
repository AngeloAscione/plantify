package it.unisa.utils;

import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class UserHelper {

    public static void updateUserInfo(HttpServletRequest request){

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String via = request.getParameter("via");
        int civico = Integer.parseInt(request.getParameter("civico"));
        int cap = Integer.parseInt(request.getParameter("cap"));

        UtenteBean utente = (UtenteBean) request.getSession().getAttribute("userInfo");
        UtenteDAO utenteDAO = new UtenteDAO();

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setVia(via);
        utente.setCivico(civico);
        utente.setCap(cap);

        try{
            utenteDAO.doUpdate(utente);
        } catch (SQLException ex){
            ;
        }
    }

}
