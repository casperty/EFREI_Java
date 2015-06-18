package view;

import java.io.IOException;
import java.util.ArrayList;

import model.Client;
import model.Compte;
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class OpenListExternAccounts {
	private OpenFile f;

	/**
	 * @throws IOException 
	 * 
	 */
	public OpenListExternAccounts(Client client, ArrayList<Compte> comptes) throws IOException {
		ArrayList<String> listeComptesFavoris=new ArrayList<String>();
        f=new OpenFile("csv/listFav-"+client.getUser()+".csv", listeComptesFavoris); 
        for(String ligne:listeComptesFavoris){
        	System.out.println("[Debug] Account : "+ligne);
        }

        for(int i=0;i<listeComptesFavoris.size();i++){
        	String[] items = listeComptesFavoris.get(i).split(";");
        	System.out.println(items[0]+", "+items[1]);
        	Compte compte=new Compte();
        	OpenAccount oa=new OpenAccount(items[0], compte, items[1]);
        	comptes.add(compte);
        }
	}

}
