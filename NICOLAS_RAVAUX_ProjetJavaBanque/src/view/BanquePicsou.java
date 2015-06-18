/**
 * Main; permet de lancer l'application
 */
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
package view;

import javax.swing.SwingUtilities;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class BanquePicsou {
	public static void main(String[] args) {
		System.setProperty("file.encoding","ISO-8859-1");
		SwingUtilities.invokeLater(new Runnable(){ 
			public void run(){
				/* Lance la fenetre de login */
				Login login = new Login();	
			}
		});
	}	
}
