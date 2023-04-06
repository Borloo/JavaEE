package com.biblio.servlets;

import java.io.IOException;

import com.biblio.beans.Auteur;
import com.biblio.dao.AuteurDao;
import com.biblio.dao.DaoFactory;
import com.biblio.forms.CreationAuteurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreationAuteur
 */
public class CreationAuteur extends HttpServlet {

	private static final long serialVersionUID = 4463415429016513991L;
	
    public static final String ATT_AUTEUR       = "auteur";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCES       = "/WEB-INF/afficherAuteur.jsp";
    public static final String VUE_FORM         = "/WEB-INF/creerAuteur.jsp";
	
	private AuteurDao auteurDao;

	@Override
	public void init() throws ServletException {
		this.auteurDao   = DaoFactory.getInstance().getAuteurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        CreationAuteurForm form = new CreationAuteurForm( auteurDao );

        /* Traitement de la requête et récupération du bean correspondant */
        Auteur auteur = form.creerAuteur( request );

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_AUTEUR, auteur );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            /* Si aucune erreur : Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
	}

}
