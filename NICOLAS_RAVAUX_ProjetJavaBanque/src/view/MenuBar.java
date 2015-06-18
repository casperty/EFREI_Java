package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import model.Client;
import model.Compte;
import controller.MenuListener;
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class MenuBar extends JMenuBar{
	private String os_name = System.getProperty("os.name").toLowerCase();
	private ActionListener menuListener;

	public MenuBar(MainFrame m,Client c, ArrayList<Compte> comptes) {
		/* les evenements, raccourcis clavier et tooltips */
        menuListener = new MenuListener(m,c,comptes);

		JMenu file = new JMenu("Fichier");
        add(file);
        
        JMenuItem deconnect = new JMenuItem ("Se deconnecter");
        file.add(deconnect);
        deconnect.addActionListener(menuListener);
        
        JMenuItem quit = new JMenuItem ("Quitter");
        file.add(quit);
        
        JMenu Edit = new JMenu("Edition");
        add(Edit);
        
        JMenuItem Reset = new JMenuItem ("Actualiser liste");
        Edit.add(Reset);
        
        
      
        if (os_name.startsWith("mac")) {
        	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.META_MASK));
        	Reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.META_MASK));
        }else{
        	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
            Reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
        	
        }

        quit.addActionListener(menuListener);
        Reset.addActionListener(menuListener);
        
        quit.setMnemonic('Q');
        Reset.setMnemonic('R');
	}

}
