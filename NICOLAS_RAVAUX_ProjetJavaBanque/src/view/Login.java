package view;
import java.util.*;

import javax.swing.*;

import controller.LoginController;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author Jerome Nicolas; Alexandre Ravaux
 *
 */
public class Login extends JDialog{
	
	private JTextField user, mdp;
	private JButton confirm, effacer;
	private String userId="";
	private String userMdp="";
	private ActionListener listener;
	
	public Login(){
		setTitle("Login");
		setSize(400,390);
		setResizable(false); // empeche de redimensionner la fenetre
		setLocationRelativeTo(null); // centre la fenetre
		
		listener = new LoginController(this);
		
		JPanel MainPanel = new JPanel(); 
		JPanel panelTextFields = new JPanel();
		JPanel panelNumPad = new JPanel();
		JPanel panelConfirmation = new JPanel();
		
		panelTextFields.setLayout(null);	
		
		JLabel numUserLabel = new JLabel("Numero de compte :");
		numUserLabel.setBounds(10, 10, 180, 22);
		
		
		JLabel mdpUserLabel = new JLabel("Mot de passe :");
		mdpUserLabel.setBounds(10, 60, 180, 22);
		
		
		user = new JTextField();
		user.setBounds(150, 10, 95, 22);
		user.addActionListener(listener);
		
		mdp = new JPasswordField();
		mdp.setBounds(150, 60, 95, 22);
		mdp.addActionListener(listener);
		
		FocusListener highlighter = new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                e.getComponent().setBackground(Color.LIGHT_GRAY);
                //e.getComponent().setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                e.getComponent().setBackground(UIManager.getColor("TextField.background"));
                //e.getComponent().setForeground(Color.BLACK);
            }
        };

        user.addFocusListener(highlighter);
        mdp.addFocusListener(highlighter);
		mdp.setEditable(false);
		
		panelTextFields.add(numUserLabel);
		panelTextFields.add(user);
		panelTextFields.add(mdpUserLabel);
		panelTextFields.add(mdp);
		
		panelNumPad.setLayout(new GridLayout(5, 5));
		ArrayList<JButton> listButton=new ArrayList<JButton>();
		for (int index = 0; index < 25; index++) {
			listButton.add(index<=9?createButton(index):new JButton());
        }
		
		Collections.shuffle(listButton);
		for (int index = 0; index < 25; index++) {
			panelNumPad.add(listButton.get(index));
			
        }
		panelConfirmation.setLayout(new FlowLayout());
		confirm=new JButton("Valider");
		confirm.addActionListener(listener);
		
		effacer=new JButton("Effacer");
		effacer.addActionListener(listener);
		
		panelConfirmation.add(confirm);
		panelConfirmation.add(effacer);
		
		MainPanel.setLayout(new GridLayout(3, 1));
		MainPanel.add(panelTextFields);
		MainPanel.add(panelNumPad);
		MainPanel.add(panelConfirmation);
        this.setContentPane(MainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * @return the user
	 */
	public JTextField getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		userId=this.user.getText();
		userId+=user;
		this.user.setText(userId);
	}

	/**
	 * @return the mdp
	 */
	public JTextField getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		userMdp=this.mdp.getText();
		userMdp+=mdp;
		this.mdp.setText(userMdp);
	}
	
	public void eraseMdp() {
		this.mdp.setText("");
	}


	/**
	 * @return the confirm
	 */
	public JButton getConfirm() {
		return confirm;
	}

	/**
	 * @param confirm the confirm to set
	 */
	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}

	/**
	 * @return the cancel
	 */
	public JButton getEffacer() {
		return effacer;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public void setEffacer(JButton effacer) {
		this.effacer = effacer;
	}
	
	/**
	 * Permet de creer les boutons du NumPad
	 * @param index
	 * @return
	 */
	protected JButton createButton(int index) {
        JButton btn = new JButton(String.valueOf(index));
        btn.setFocusable(false);
        btn.addActionListener(listener);
        return btn;
    }

}
