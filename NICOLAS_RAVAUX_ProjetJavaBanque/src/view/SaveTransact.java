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
public class SaveTransact {

	/**
	 * @throws IOException 
	 * 
	 */
	OpenFile f;
	public SaveTransact(Client c, Calendar cal, String numDebiteur, String numCrediteur, double soldeDebiteur, double soldeCrediteur, double montant, String intituleDebit, String intituleCredit) throws IOException {
		/* Pour le fichier historique */
		String historiqueDebit=cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR)+";"+numDebiteur+";"+numCrediteur+";"+intituleDebit+";"+montant;
		String historiqueCredit=cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR)+";"+numCrediteur+";"+numDebiteur+";"+intituleCredit+";"+montant;
		
		//ArrayList<String> historique=new ArrayList<String>();
		String pathHistoric="csv/historique-"+c.getUser()+".csv";
       // f=new OpenFile(pathHistoric, historique); 
        /*historique.add(historiqueDebit);
        historique.add(historiqueCredit);*/
        
        FileWriter writer = null;
        try{
             writer = new FileWriter(pathHistoric, true);
             writer.write("\n",0,"\n".length());
             writer.write(historiqueDebit,0,historiqueDebit.length());
             writer.write("\n",0,"\n".length());
             writer.write(historiqueCredit,0,historiqueCredit.length());
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
          if(writer != null){
             writer.close();
          }
        }
        

	}

}
