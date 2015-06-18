package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Client;
import model.Compte;
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class ViewAccounts extends JPanel {
	protected JList list;
	protected JLabel labelTitulaire;
	protected boolean isToUpdate=false;

	public ViewAccounts(Client client, final ArrayList<Compte> comptes) {
		checkUpdate(client,isToUpdate);
		if(isToUpdate){
			//doUpdate();
		}
		
		list = new JList(comptes.toArray());
		list.setVisibleRowCount(3); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				String selectedValue=list.getSelectedValue().toString();
				String[] selectedValueSplitted=selectedValue.split(",", 2);
				for(Compte cpt:comptes){
					if(cpt.getNumCompte()==Integer.parseInt(selectedValueSplitted[0])){
						labelTitulaire.setText("<html><br>Titulaire : "+ cpt.getTitulaire()+"<br>Solde : "+ cpt.getSolde()+"<br>Decouvert autorise : "+cpt.getDecouvert()+"</html>");
					}
				}
			}
		}); 
		JScrollPane scroll= new JScrollPane(list);
		Border b = BorderFactory.createTitledBorder("Comptes");
		scroll.setBorder(b);
		setLayout(new GridLayout(1,2));
		add(scroll);
		
		labelTitulaire = new JLabel(""); 
		JPanel panelRight=new JPanel();
		panelRight.setLayout(new BorderLayout());
		panelRight.add(labelTitulaire, BorderLayout.NORTH);
		add(panelRight);
	}
	public void openUpdatesFile(){
		
	}
	public void checkUpdate(Client c,boolean isToUpdate){
		ArrayList <String> updates=new ArrayList<String>();
		try {
			OpenFile openedFile=new OpenFile("csv/updates.csv", updates);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String update:updates){
			if(update.startsWith(c.getUser())){
				isToUpdate=true;
				break;
			}
		}
	}
	public void doUpdate(ArrayList<Compte> comptes){
		ArrayList <String> updates=new ArrayList<String>();
		try {
			OpenFile openedFile=new OpenFile("csv/updates.csv", updates);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Compte compte:comptes){
			//if(compte.getNumCompte())
		}
		
	}

}
