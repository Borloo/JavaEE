package com.biblio.servlets;

import java.io.IOException;

import com.biblio.dao.AuteurDao;
import com.biblio.dao.DaoException;
import com.biblio.dao.DaoFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;	

/**
 * Servlet implementation class SupprimerAuteur
 */
public class SuppressionAuteur extends HttpServlet {

	private static final long serialVersionUID = 8962589637477858336L;

	public static final String PARAM_ID_AUTEUR  = "idAuteur";

    public static final String VUE              = "/listeAuteurs";
       
	private AuteurDao auteurDao;

	@Override
	public void init() throws ServletException {
		this.auteurDao   = DaoFactory.getInstance().getAuteurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération du paramètre */
        String idAuteur = request.getParameter( PARAM_ID_AUTEUR );

        try {
            Long id = Long.parseLong( idAuteur );
            auteurDao.supprimer( id );
        } catch ( NumberFormatException nfe) {
        	// mauvais parametre : pas d'action
        }catch ( DaoException e ) {
        	// #TODO : erreur DAO non remontée à l'utilisateur
            e.printStackTrace();
        }

        /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + VUE );
	}


}
