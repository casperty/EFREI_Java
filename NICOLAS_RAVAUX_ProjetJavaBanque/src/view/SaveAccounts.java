/**
 * 
 */
package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Client;
import model.Compte;

/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class SaveAccounts {

	/**
	 * @throws IOException 
	 * 
	 */
	OpenFile f;
	public SaveAccounts(Client c, ArrayList<Compte> comptes){
		System.out.println("[Debug] Procedure de sauvegarde des comptes");
		String path="csv/"+c.getUser()+".csv";
		try{
		    FileWriter fw = new FileWriter (path);
		    fw.write("numero;type;libelle;solde;decouvert;titulaire");
	    	fw.write ("\r\n");
		    for (Compte compte : comptes){
		    	/* Numero compte;Type compte; Libelle du compte; Solde; Decouvert autorise; Titulaire */
		    	fw.write(compte.getNumCompte()+";"+compte.getTypeCompte()+";"+compte.getLibelle()+";"+compte.getSolde()+";"+compte.getDecouvert()+";"+compte.getTitulaire());
		        //fw.write (String.valueOf (d));
		        fw.write ("\r\n");
		    }
		 
		    fw.close();
		}catch (IOException exception){
			
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

}
