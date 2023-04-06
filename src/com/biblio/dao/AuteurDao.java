package com.biblio.dao;

import java.util.List;

import com.biblio.beans.Auteur;

public interface AuteurDao {

	void         creer( Auteur auteur ) throws DaoException;

    Auteur       trouver( long id ) throws DaoException;

    List<Auteur> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

}
