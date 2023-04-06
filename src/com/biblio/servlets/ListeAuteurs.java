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
 * Servlet implementation class ListeAuteur
 */
public class ListeAuteurs extends HttpServlet {

	private static final long serialVersionUID = -1902392141500008387L;

    public static final String ATT_AUTEURS = "auteurs";

    public static final String VUE        = "/WEB-INF/listerAuteurs.jsp";

	private AuteurDao auteurDao;

	@Override
	public void init() throws ServletException {
		this.auteurDao   = DaoFactory.getInstance().getAuteurDao();
	}
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	try {
    		request.setAttribute( ATT_AUTEURS, auteurDao.lister() );
    	} catch (DaoException e) {
    		// TODO : erreur DAO non remontée à l'utilisateur
    		e.printStackTrace();
    	}

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
