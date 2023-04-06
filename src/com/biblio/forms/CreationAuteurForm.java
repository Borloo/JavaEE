package com.biblio.forms;

import java.util.HashMap;
import java.util.Map;

import com.biblio.beans.Auteur;
import com.biblio.dao.AuteurDao;
import com.biblio.dao.DaoException;

import jakarta.servlet.http.HttpServletRequest;

public class CreationAuteurForm {
    private static final String CHAMP_NOM       = "nomAuteur";
    private static final String CHAMP_PRENOM    = "prenomAuteur";
    private static final String CHAMP_TELEPHONE = "telephoneAuteur";
    private static final String CHAMP_EMAIL     = "emailAuteur";

    private String              resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private AuteurDao           auteurDao;

    public CreationAuteurForm( AuteurDao auteurDao ) {
        this.auteurDao = auteurDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Auteur creerAuteur( HttpServletRequest request ) {
        String nom       = getParameterOrNull( request, CHAMP_NOM );
        String prenom    = getParameterOrNull( request, CHAMP_PRENOM );
        String telephone = getParameterOrNull( request, CHAMP_TELEPHONE );
        String email     = getParameterOrNull( request, CHAMP_EMAIL );

        Auteur auteur = new Auteur();

        // ---------------------
        // validation des champs
        
        // nom : obligatoire et > à 2 caractères
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                erreurs.put( CHAMP_NOM, "Le nom de l'auteur doit contenir au moins 2 caractères." );
            }
        } else {
        	erreurs.put( CHAMP_NOM, "Merci d'entrer un nom de l'auteur." );
        }
        // prenom : null ou  > à 2 caractères
        if ( prenom != null && prenom.length() < 2 ) {
        	erreurs.put( CHAMP_PRENOM, "Le prénom de l'auteur doit contenir au moins 2 caractères." );
        }
        // telephone : obligatoire + au moins 4 chiffres + uniquement des chiffres
        if ( telephone != null ) {
            if ( !telephone.matches( "^\\d+$" ) ) {
            	erreurs.put( CHAMP_TELEPHONE, "Le numéro de téléphone doit uniquement contenir des chiffres." );
            } else if ( telephone.length() < 4 ) {
            	erreurs.put( CHAMP_TELEPHONE, "Le numéro de téléphone doit contenir au moins 4 chiffres." );
            }
        } else {
        	erreurs.put( CHAMP_TELEPHONE, "Merci d'entrer un numéro de téléphone." );
        }
        
        // email : null ou format valide
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
        	erreurs.put( CHAMP_EMAIL, "Merci de saisir une adresse mail valide." );
        }

        // ---------------------
        // affectation des champs dans le JavaBean
        // dans notre cas erreur ou pas, l'auteur prend toutes les valeurs saisies qu'elles soient validées ou non
        // dans d'autres cas, l'affectation pourrait être conditionnelle à la validation
        auteur.setNom(nom);
        auteur.setPrenom(prenom);
        auteur.setTelephone(telephone);
        auteur.setEmail(email);

        // ---------------------
        // Enregistrement en BDD du JavaBean (uniquement si toutes les validations sont OK)
        try {
            if ( erreurs.isEmpty() ) {
                auteurDao.creer( auteur );
                resultat = "Succès de la création de l'auteur.";
            } else {
                resultat = "Échec de la création de l'auteur.";
            }
        } catch ( DaoException e ) {
        	erreurs.put( "DAO", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création de l'auteur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return auteur;
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
