package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.Model;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private MainPanel panel1 ; 
	private AnalysePanel panel2 ;
	private Model model = new Model();
	private JLabel lblBlblalbal;
	private JButton btnGoBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panel2 = new AnalysePanel(this.model);
		contentPane.add(panel2, "Analyse");
		
		lblBlblalbal = new JLabel("blblalbal");
		panel2.add(lblBlblalbal);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton goback = (JButton) e.getSource();
				MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(goback.getParent());
				mainFrame.getPanel1().setVisible(true);
				mainFrame.getPanel2().setVisible(false) ;
				
			}});
		panel2.add(btnGoBack);
		panel2.setVisible(false);
		panel1 = new MainPanel(this.model);
		contentPane.add(panel1, "recherche");
		panel1.setVisible(true);
		
		
		

		
	}
	
	public MainPanel getPanel1(){return this.panel1;}
	public AnalysePanel getPanel2(){return this.panel2 ;}

}
