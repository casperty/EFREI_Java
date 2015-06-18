package view;

import java.io.IOException;
import java.util.ArrayList;

import model.Client;
import model.Compte;
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class OpenAccount {
	private OpenFile f;
	public OpenAccount(String numClient,Compte compte, String numCompte){
		// TODO Auto-generated constructor stub
		ArrayList<String> userAccounts=new ArrayList<String>();
        try {
			f=new OpenFile("csv/"+numClient+".csv", userAccounts);
	        for(int i=1;i<userAccounts.size();i++){
	        	String[] items = userAccounts.get(i).split(";");
	        	if(items[0].equals(numCompte)){
	        		compte.setNumCompte(Integer.parseInt(items[0]));
	        		compte.setTypeCompte(Integer.parseInt(items[1]));
	        		compte.setLibelle(items[2]);
	        		compte.setSolde(Double.parseDouble(items[3]));
	        		compte.setDecouvert(Double.parseDouble(items[4]));
	        		compte.setTitulaire(items[5]);
	        	}
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
