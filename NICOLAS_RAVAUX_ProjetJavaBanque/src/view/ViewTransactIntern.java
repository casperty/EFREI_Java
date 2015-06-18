package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Client;
import model.Compte;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class ViewTransactIntern extends JPanel{
	protected JList listDebiteur, listCrediteur;
	JTextField dateExec, montant, intituleDebit, intituleCredit;
	SaveTransact saveIt;

	public ViewTransactIntern(final Client c,final ArrayList<Compte> comptes){
		// TODO Auto-generated constructor stub
		//int hauteurJFrame=(int) this.getSize().getHeight();
		int hauteurJFrame=200;
    	final String [] comptesTransaction = new String [2];
    	
		JPanel panelCenter=new JPanel();
		JPanel panelLeft=new JPanel();
		JPanel panelRight=new JPanel();
		JPanel form=new JPanel();
		JPanel boutons=new JPanel();
		
		listDebiteur = new JList(comptes.toArray());
		listDebiteur.setVisibleRowCount(3); 
		listDebiteur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		listDebiteur.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				String[] selectedValueSplitted=listDebiteur.getSelectedValue().toString().split(",");
				comptesTransaction[0]=selectedValueSplitted[0];
				System.out.println("[Debug] Compte debiteur:"+comptesTransaction[0]+" Comte crediteur:"+comptesTransaction[1]);
			}
		});
		
		listCrediteur = new JList(comptes.toArray());
		listCrediteur.setVisibleRowCount(3); 
		listCrediteur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		listCrediteur.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				String[] selectedValueSplitted=listCrediteur.getSelectedValue().toString().split(",");
				comptesTransaction[1]=selectedValueSplitted[0];
				System.out.println("[Debug] Compte debiteur:"+comptesTransaction[0]+" Comte crediteur:"+comptesTransaction[1]);
			}
		}); 
		
		panelCenter.setLayout(new GridLayout(2,1));
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		boutons.setLayout(new FlowLayout());
		
		form.add(new JLabel("<html>Virement entre mes comptes<br></html>"));
		JLabel dateExecLabel=new JLabel("Date d'execution (jj-mm-aaaa):");
		form.add(dateExecLabel);
		dateExec=new JTextField();
		dateExec.setPreferredSize(new Dimension(5, 10));
		form.add(dateExec);
		
		JLabel montantLabel=new JLabel("Montant:");
		form.add(montantLabel);
		montant=new JTextField();
		montant.setPreferredSize(new Dimension(5, 10));
		form.add(montant);
		
		JLabel intituleDebitLabel=new JLabel("Intitule pour le compte debiteur:");
		form.add(intituleDebitLabel);
		intituleDebit=new JTextField();
		intituleDebit.setPreferredSize(new Dimension(5, 10));
		form.add(intituleDebit);
		
		JLabel intituleCreditLabel=new JLabel("Intitule pour le compte crediteur:");
		form.add(intituleCreditLabel);
		intituleCredit=new JTextField();
		intituleCredit.setPreferredSize(new Dimension(5, 10));
		form.add(intituleCredit);
		
		
		JButton validTransact=new JButton("Valider");		
		/* Actions generees lorsqu'on appuie sur le bouton Valider */
		validTransact.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				/* Expression reguliere afin de verifier si le format date est respecte*/
				Pattern r = Pattern.compile("^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4}|[0-9]{2})$");
				Matcher m = r.matcher(dateExec.getText());
				if(comptesTransaction[0]==null ||comptesTransaction[1]==null){
					JOptionPane.showMessageDialog(null, "Vous n'avez pas selectionne de comptes", "Erreur selection", JOptionPane.ERROR_MESSAGE);
					throw new IllegalArgumentException("La variable Objet ne doit pas être null");
            	}else if(comptesTransaction[0].equals(comptesTransaction[1])){
            		JOptionPane.showMessageDialog(null, "Vous ne pouvez pas selectionner deux fois le meme compte", "Attention", JOptionPane.WARNING_MESSAGE);
            	}else{
            		if(m.find()){
                		System.out.println("[Debug] Format date correcte");
                		/* Procedure pour separer la date */
                		String date [] = dateExec.getText().split("-");
	            		int jour= Integer.parseInt(date[0]);
						int mois= Integer.parseInt(date[1]);
						int annee= Integer.parseInt(date[2]);
				
						Calendar calChosen = GregorianCalendar.getInstance();
						calChosen.set(annee, mois-1, jour); // Janvier vaut 0 dans ce calendrier
						Date dateChoisie= calChosen.getTime();
						Date dateCourante=new Date();
						Calendar calCurrent = GregorianCalendar.getInstance();
						calCurrent.setTime(dateCourante);
						System.out.println("[Debug] Date choisie : "+dateChoisie.toString());
						System.out.println("[Debug] Date choisie : "+calChosen.toString());
						System.out.println("[Debug] Date courante : "+dateCourante.toString());
						System.out.println("[Debug] Date courante : "+calCurrent.toString());
						
						try {
						    double montantADebiter=Double.parseDouble(montant.getText());
						    boolean isValidAccount=false;
							double solde=0.0;
							double nouveauSoldeDebiteur = 0.0,nouveauSoldeCrediteur=0.0;
							
							/* Parcours de la liste des comptes */
							for(Compte compte:comptes){
								if(compte.getNumCompte()==Integer.parseInt(comptesTransaction[0])){
									if(montantADebiter <= (compte.getSolde()+compte.getDecouvert())){
										solde=compte.getSolde();
										isValidAccount=true; // le compte est valide on pourra effectuer une transaction, youpi !
										break;
									}
								}
							}
							
							if(isValidAccount){
								System.out.println("[Debug] Solde acceptable, decouvert acceptable");
								for(Compte compte:comptes){
									if(compte.getNumCompte()==Integer.parseInt(comptesTransaction[0])){
										nouveauSoldeDebiteur=compte.getSolde()-montantADebiter;
									}else if(compte.getNumCompte()==Double.parseDouble(comptesTransaction[1])){
										nouveauSoldeCrediteur=compte.getSolde()+montantADebiter;
									}
								}
							}else{
								System.out.println("[Debug] Solde inacceptable, decouvert inacceptable");
								JOptionPane.showMessageDialog(null, "La somme a virer est superieure au solde du compte debiteur", "Erreur somme trop importante", JOptionPane.ERROR_MESSAGE);
							}
							/* On va regarder la date pour savoir s'il s'agit d'un virement immediat ou non */
							if((calChosen.get(Calendar.YEAR)==calCurrent.get(Calendar.YEAR) && calChosen.get(Calendar.MONTH)==calCurrent.get(Calendar.MONTH) && calChosen.get(Calendar.DAY_OF_MONTH)==calCurrent.get(Calendar.DAY_OF_MONTH))&& isValidAccount){
								System.out.println("[Info] Virement immediat");
								for(Compte compte:comptes){
									if(compte.getNumCompte()==Integer.parseInt(comptesTransaction[0])){
										compte.setSolde(nouveauSoldeDebiteur);
									}else if(compte.getNumCompte()==Double.parseDouble(comptesTransaction[1])){
										compte.setSolde(nouveauSoldeCrediteur);
									}
								}
								try {
									saveIt=new SaveTransact(c,calChosen,comptesTransaction[0], comptesTransaction[1], nouveauSoldeDebiteur, nouveauSoldeCrediteur, montantADebiter,intituleDebit.getText(), intituleCredit.getText());
									JOptionPane.showMessageDialog(null, "Le virement a bien ete pris en compte, il sera effectif dans tres peu de temps", "Succes", JOptionPane.INFORMATION_MESSAGE);
									eraseData();
								} catch (IOException e1) {
									System.out.println("[Error] Problem catched :\n");
									e1.printStackTrace();
								}
								
								
							}else if(calChosen.get(Calendar.YEAR)<calCurrent.get(Calendar.YEAR)){
								/* Cas ou la date est anterieure a la date d'aujourd'hui*/
								JOptionPane.showMessageDialog(null, "La date est inferieure a la date d'aujourd'hui", "Attention", JOptionPane.WARNING_MESSAGE);
							}else if((calChosen.get(Calendar.YEAR)>calCurrent.get(Calendar.YEAR) || calChosen.get(Calendar.MONTH)>calCurrent.get(Calendar.MONTH) || calChosen.get(Calendar.DAY_OF_MONTH)>calCurrent.get(Calendar.DAY_OF_MONTH))&& isValidAccount){
								/* Virement en differe */
								JOptionPane.showMessageDialog(null, "Le virement differe a bien ete pris en compte, il sera effectif en temps voulu ", "Succes", JOptionPane.INFORMATION_MESSAGE);
								/* Sauvegarde de la transaction dans un fichier contenant les differentes transactions futures*/
								FileWriter writer = null;
								String line=c.getUser()+";"+comptesTransaction[0]+";"+comptesTransaction[1]+";"+intituleDebit.getText()+";"+montant.getText();
						        try{
						             writer = new FileWriter("csv/updates.csv", true);
						             writer.write("\n",0,"\n".length());
						             writer.write(line,0,line.length());
						        }catch(IOException ex){
						            ex.printStackTrace();
						        }finally{
						          if(writer != null){
						             try {
										writer.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
						          }
						        }
							}else{
								/* Well, we've got a problem, Houston */
								System.out.println("[Error] Unknown problem");
								JOptionPane.showMessageDialog(null, "Le logiciel a rencontre une erreur", "Erreur inconnue", JOptionPane.ERROR_MESSAGE);
							}
							 
						} catch (NumberFormatException nfe) {
						    JOptionPane.showMessageDialog(null, "Le montant precise n'est pas valide", "Attention", JOptionPane.WARNING_MESSAGE);
						}						
                	}else{
                		JOptionPane.showMessageDialog(null, "La date entree n'est pas une date valide", "Attention", JOptionPane.WARNING_MESSAGE);
                	}
            	}
            	

            }
        });
		
		JButton cancelTransact=new JButton("Annuler");
		cancelTransact.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eraseData();
			}
		});
		boutons.add(validTransact);
		boutons.add(cancelTransact);
			
		panelCenter.add(form);
		panelCenter.add(boutons);
		
		panelLeft.setLayout(new GridLayout(1,1));
		panelLeft.setPreferredSize(new Dimension(200,hauteurJFrame));
		panelLeft.add(listDebiteur);
		
		panelRight.setLayout(new GridLayout(1,1));
		panelRight.setPreferredSize(new Dimension(200,hauteurJFrame));
		panelRight.add(listCrediteur);

		setLayout(new GridLayout(1,3));
		add(panelLeft);
		add(panelCenter);
		add(panelRight);
	}
	public void eraseData(){
		dateExec.setText("");
		montant.setText("");
		intituleDebit.setText("");
		intituleCredit.setText("");	
	}

}
