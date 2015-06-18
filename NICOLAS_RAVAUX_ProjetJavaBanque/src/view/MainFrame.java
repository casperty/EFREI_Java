package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.Compte;
import controller.MenuListener;
/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class MainFrame extends JFrame{
	private JTabbedPane tabbedPane;
	private JPanel container;
	
	private ViewAccounts viewAccounts;
	private ViewTransactIntern viewTransactIntern;
	private ViewTransactExtern viewTransactExtern;
	private ViewHistoric viewHistoric;

	private Client c;
	private OpenAccounts openAccounts;
	ArrayList <Compte> comptes;
	private ActionListener menuListener;
	private MenuBar menuBar;
	private String os_name = System.getProperty("os.name").toLowerCase();
	
	public MainFrame(Client c) throws IOException{
		setTitle("Banque Picsou - Compte "+c.getUser());
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Etes vous sûr de vouloir fermer l'application ?\nToute manipulation effectuee ne sera pas sauvegardee", "Fermeture de l'application Banque Picsou", JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);
                if(i==JOptionPane.YES_NO_OPTION){
                	System.exit(0);
                }else{
                	JOptionPane.showMessageDialog(null, "Si vous souhaitez quitter correctement l'application allez dans Fichier > Se deconnecter ou Fichier > Quitter", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-600/2, dim.height/2-600/2);
        setMinimumSize(new Dimension(800,400));
        setVisible(true);
        
        /* looknfeel */
        try {    
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        /* si on travaille sous mac, le JMenuItem sera mis tout en haut */
        if(os_name.startsWith("mac")){
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Banque Picsou"); 	
        }  
        container = new JPanel();
        
        comptes=new ArrayList<Compte>();
		openAccounts=new OpenAccounts(c,comptes);
        
        /* Intialisation du menu (boutons + gestions de ces boutons) */
        menuBar=new MenuBar(this,c, comptes);
        setJMenuBar(menuBar);
        
		viewAccounts=new ViewAccounts(c,comptes);
		viewTransactIntern=new ViewTransactIntern(c,comptes);
		viewTransactExtern=new ViewTransactExtern(c,comptes);
		viewHistoric=new ViewHistoric(c);
        
		container.setLayout( new BorderLayout());
		setContentPane(container);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mes comptes", viewAccounts);
        tabbedPane.addTab("Transactions internes", viewTransactIntern);
        tabbedPane.addTab("Transactions externes", viewTransactExtern);
        tabbedPane.addTab("Historique", viewHistoric);
        
        container.add(tabbedPane, BorderLayout.CENTER );
	}
}