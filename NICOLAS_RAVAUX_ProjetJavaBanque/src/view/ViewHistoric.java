/**
 * Panel Historique des transactions
 */
package view;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Client;

/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class ViewHistoric extends JPanel{
	private OpenFile h;

	/**
	 * Constructeur 
	 */
	public ViewHistoric(Client c) {
		setLayout(new GridLayout(1,1));

		ArrayList<String> historiqueTransactions=new ArrayList<String>();
		
		/* On essaie d'ouvrir le fichier historique-[ID Utilisateur].csv*/
		try {
			h= new OpenFile("csv/historique-"+c.getUser()+".csv", historiqueTransactions);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Il semblerait qu'il n'y ait pas d'historique !", "Erreur", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		/* Definition de la JTable */
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Compte debiteur");
        model.addColumn("Compte crediteur");
        model.addColumn("Intitule");
        model.addColumn("Montant");
        JTable tableau = new JTable(model);
        tableau.setEnabled(false);
		for(int i=0;i<historiqueTransactions.size();i++){
			/* 
			   Pour chaque ligne du fichier, on decompose la ligne grace au separateur ; 
			   et on ajoute cela dans le tableau tiems[] 
			 */
        	String[] items = historiqueTransactions.get(i).split(";");
        	model = (DefaultTableModel) tableau.getModel();
        	/* Les elements de la ligne sont ajoutes alors a une ligne du tableau JTable */
            model.addRow(new Object[]{items[0],items[1],items[2],items[3],items[4]});
		}
		/* 
		  Ajout du tableau au panel 
		  + JScrollPane qui permet d'avoir un scroll si les elements depassent le tableau
		 */
		add(new JScrollPane(tableau));
	}

}
