package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Client;
import view.Login;
import view.MainFrame;
import view.OpenFile;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class LoginController implements ActionListener, KeyListener{
	private String userId="";
	private String userMdp="";
	private Login l;
	private OpenFile f;
	private JTextField tmp;
	private int cpt=0;
	String value="";

	/**
	 * 
	 */
	public LoginController(Login l) {
		this.l=l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == l.getConfirm() || e.getActionCommand() == l.getConfirm().getText()) {
			ArrayList<String> usersBDD=new ArrayList<String>();
			try {
				f=new OpenFile("csv/users.csv", usersBDD);
				boolean doNotExist=false;
				
				for(int i=1;i<usersBDD.size();i++){
					String[] identifiants = usersBDD.get(i).split(";");
					System.out.println("[Debug] Identifiants : "+identifiants[0]+" Mot de passe : "+identifiants[1]);					
					if(identifiants[0].equals(l.getUser().getText()) && decrypt(identifiants[1],"ProjetJavaBanque").equals(l.getMdp().getText())){
						doNotExist=false;
						break;
					}else{
						doNotExist=true;
					}
				}
				
				if(doNotExist==true){
					System.out.println("[Fail] IDs not matched ("+doNotExist+")");
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe non valide", "Erreur identifiants", JOptionPane.ERROR_MESSAGE);
				}else{
					System.out.println("[Success] IDs matched ("+doNotExist+")");
					Client client=new Client(l.getUser().getText());
					MainFrame m = new MainFrame(client);
					l.dispose();
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO : Lancer process de verification dans la base de donnees .CSV
		}else if(e.getSource() == l.getEffacer() || e.getActionCommand() == l.getEffacer().getText()) {
			l.eraseMdp();
			//l.dispose();
		}else{
			
			if(e.getActionCommand().toString()!=null){
				if(l.getFocusOwner() == l.getUser() ) {
				    l.setUser(e.getActionCommand().toString());
				}else if(l.getFocusOwner() == l.getMdp() ) {
				    l.setMdp(e.getActionCommand().toString());
				}else{
					/* pas utile, mais permet une interface user-friendly */
					cpt++;
					if(cpt==2){
						JOptionPane.showMessageDialog(null, "Attention, aucun champ de texte n'est selectionne", "Attention", JOptionPane.INFORMATION_MESSAGE);
						cpt=0;
					}
				}
			}
		}
		System.out.println("[Debug] User : "+l.getUser().getText()+" Mdp : "+l.getMdp().getText());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Attention il faut changer les parametes d'encodage d'eclipse. Preferences > General > Workspace > Text file encoding > ISO-8859-1
	 * */
	public String decrypt(String password,String key){
		try{
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE,clef);
			System.out.println("test1 "+new String(cipher.doFinal(password.getBytes())));
			return new String(cipher.doFinal(password.getBytes()));
		}catch (Exception e){
			System.out.println(e);
			return null;
		}
	}

}
