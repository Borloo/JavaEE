package com.biblio.forms;

import java.util.HashMap;
import java.util.Map;

import com.biblio.beans.Livre;
import com.biblio.dao.DaoException;
import com.biblio.dao.LivreDao;

import jakarta.servlet.http.HttpServletRequest;

public class CreationLivreForm {
    private static final String CHAMP_TITRE       = "titreLivre";
    private static final String CHAMP_NB_PAGES    = "nbPages";
    private static final String CHAMP_CATEGORIE     = "categorie";

    private String              resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private LivreDao            livreDao;

    public CreationLivreForm( LivreDao livreDao ) {
        this.livreDao = livreDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Livre creerLivre( HttpServletRequest request ) {
        String titre       = getParameterOrNull( request, CHAMP_TITRE );
        String nbpages    = getParameterOrNull( request, CHAMP_NB_PAGES); 
        String categorie = getParameterOrNull( request, CHAMP_CATEGORIE );
        

        Livre livre = new Livre();

        // ---------------------
        // validation des champs
        
        // titre : obligatoire et > à 2 caractères
        if ( titre != null ) {
            if ( titre.length() < 2 ) {
                erreurs.put( CHAMP_TITRE, "Le titre du livre doit contenir au moins 2 caractères." );
            }
        } else {
        	erreurs.put( CHAMP_TITRE, "Merci d'entrer le titre du livre." );
        }
        // nbpapges : null ou  > à 0 caractères
        if ( nbpages != null && nbpages.length() < 0) {
        	erreurs.put( CHAMP_NB_PAGES, "Le nombre de pages doit contenir au moins 1 caractère." );
        }
        // categorie
        if ( categorie != null ) {
            if ( categorie.length() < 4 ) {
            	erreurs.put( CHAMP_CATEGORIE, "Le numéro de téléphone doit contenir au moins 4 chiffres." );
            }
        } else {
        	erreurs.put( CHAMP_CATEGORIE, "Merci d'entrer une catégorie." );
        }
        // ---------------------
        // affectation des champs dans le JavaBean
        // dans notre cas erreur ou pas, le livre prend toutes les valeurs saisies qu'elles soient validées ou non
        // dans d'autres cas, l'affectation pourrait être conditionnelle à la validation
        livre.setTitre(titre);
        livre.setNbPages(Integer.parseInt(nbpages));;
        livre.setCategorie(categorie);

        // ---------------------
        // Enregistrement en BDD du JavaBean (uniquement si toutes les validations sont OK)
        try {
            if ( erreurs.isEmpty() ) {
                livreDao.creer( livre );
                resultat = "Succès de la création du livre.";
            } else {
                resultat = "Échec de la création du livre.";
            }
        } catch ( DaoException e ) {
        	erreurs.put( "DAO", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création du livre : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return livre;
    }
	
    /*
     * Méthode utilitaire qui retourne un champ ou null si il est vide
     */
    private static String getParameterOrNull( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
