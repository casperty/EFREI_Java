/**
 * 
 */
package model;

import java.util.Observable;

/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class Compte extends Observable implements ICompte{
	private int numCompte;
	private int TypeCompte;
	private String libelle;
	private String titulaire;
	private double solde;
	private double decouvert;

	/**
	 * Constructeur par defaut
	 */
	public Compte() {
		this.numCompte=0;
		this.TypeCompte=0;
		this.libelle="Vide";
		this.setTitulaire("Anonyme");
		this.solde=0;
		this.decouvert=0;
	}
	/**
	 * Constructeur
	 */
	public Compte(int numCompte, int TypeCompte, String libelle, double solde, double decouvert,String titulaire) {
		this.numCompte=numCompte;
		this.TypeCompte=TypeCompte;
		this.libelle=libelle;
		this.solde=solde;
		this.decouvert=decouvert;
		this.setTitulaire(titulaire);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return numCompte + ", " + libelle;
	}
	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return the numCompte
	 */
	public int getNumCompte() {
		return numCompte;
	}

	/**
	 * @param numCompte the numCompte to set
	 */
	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	/**
	 * @return the typeCompte
	 */
	public int getTypeCompte() {
		return TypeCompte;
	}

	/**
	 * @param typeCompte the typeCompte to set
	 */
	public void setTypeCompte(int typeCompte) {
		TypeCompte = typeCompte;
	}
	
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the decouvert
	 */
	public double getDecouvert() {
		return decouvert;
	}
	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	/**
	 * @return the titulaire
	 */
	public String getTitulaire() {
		return titulaire;
	}
	/**
	 * @param titulaire the titulaire to set
	 */
	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}
	

}
