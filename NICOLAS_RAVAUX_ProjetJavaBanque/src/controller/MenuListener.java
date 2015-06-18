package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import view.Login;
import view.MainFrame;
import view.SaveAccounts;
import model.Client;
import model.Compte;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class MenuListener implements ActionListener{
	private MainFrame m;
	private Client c;
	private ArrayList<Compte> comptes;
	SaveAccounts saveAccounts;
	public MenuListener(MainFrame m, Client c, ArrayList<Compte> comptes) {
		// TODO Auto-generated constructor stub
		this.m=m;
		this.c=c;
		this.comptes=comptes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Se deconnecter")){
			/* Enregistrer les comptes */
			saveAccounts=new SaveAccounts(this.c, this.getComptes());
			System.out.println("[Success] Accounts saved");
			/* Ouvrir la fenetre de connexion */
			Login l = new Login();	
			/* Et fermer la fenetre*/
			m.dispose();
		}else if(e.getActionCommand().equals("Quitter")){
			/* Enregistrer les comptes */
			saveAccounts=new SaveAccounts(this.c, this.getComptes());
			/* Et fermer la fenetre*/
			m.dispose();
		}
		
	}
	
	/**
	 * @return the c
	 */
	public Client getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Client c) {
		this.c = c;
	}
	/**
	 * @return the comptes
	 */
	public ArrayList<Compte> getComptes() {
		return comptes;
	}
	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(ArrayList<Compte> comptes) {
		this.comptes = comptes;
	}

}
