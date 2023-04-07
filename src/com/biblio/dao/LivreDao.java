package com.biblio.dao;

import java.util.List;

import com.biblio.beans.Livre;

public interface LivreDao {

	void         creer( Livre livre ) throws DaoException;

    Livre       trouver( long id ) throws DaoException;

    List<Livre> lister() throws DaoException;
    

    void         supprimer( long id ) throws DaoException;

}
