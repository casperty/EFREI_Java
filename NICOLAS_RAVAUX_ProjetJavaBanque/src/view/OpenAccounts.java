/**
 * 
 */
package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Client;
import model.Compte;

/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class OpenAccounts {
	private OpenFile f;

	/**
	 * @throws IOException 
	 * 
	 */
	public OpenAccounts(Client client, ArrayList<Compte> comptes) throws IOException {
		ArrayList<String> userAccount=new ArrayList<String>();
        f=new OpenFile("csv/"+client.getUser()+".csv", userAccount); 
        for(String test:userAccount){
        	System.out.println("[Debug] Account : "+test);
        }
        for(int i=1;i<userAccount.size();i++){
        	String[] items = userAccount.get(i).split(";");
        	System.out.println(items[0]);
        	// Compte(numCompte,TypeCompte,libelle,solde,decouvert,titulaire)
        	comptes.add(new Compte( Integer.parseInt(items[0]),Integer.parseInt(items[1]),items[2],Double.parseDouble(items[3]),Double.parseDouble(items[4]),items[5]));
        }
	}
}
