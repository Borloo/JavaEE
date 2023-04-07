package com.biblio.beans;

public class Livre {

	private Long   id;
    private String titre;
    private int nbPages;
    private String categorie;
    

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Long getId() {
		return id;
	}
}
