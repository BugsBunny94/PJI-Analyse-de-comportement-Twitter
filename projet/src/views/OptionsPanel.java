package views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;

import Controller.ClassificationComboboxActionListener;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;



public class OptionsPanel extends JPanel {
	

	private JComboBox<String> mdlcomboBox;
	private JLabel lblClassifieur;
	private JComboBox algocomboBox;
	private JLabel lblAlgorithmeBayesien;
	private JLabel lblPar;
	private JComboBox<String> parcomboBox;
	private JComboBox<String> kcomboBox ;


	/**
	 * Create the panel.
	 */
	public OptionsPanel(Model mdl) {
		
		mdlcomboBox = new JComboBox<String>();
		mdlcomboBox.setBounds(118, 5, 114, 24);
		
		this.mdlcomboBox.addItem("Dictionnaire");
		this.mdlcomboBox.addItem("Knn") ; 
		this.mdlcomboBox.addItem("Bayes");
		setLayout(null);
		
		lblClassifieur = new JLabel("Classifieur : ");
		lblClassifieur.setBounds(12, 10, 88, 15);
		add(lblClassifieur);
		add(mdlcomboBox);
		mdlcomboBox.addActionListener(new ClassificationComboboxActionListener(mdl)); 
		//mdl.setOptionsCombobox(comboBox);
		
		JLabel lblK = new JLabel("k :");
		lblK.setBounds(250, 10, 17, 15);
		add(lblK);
		
		
		lblAlgorithmeBayesien = new JLabel("Algorithme bayesien :");
		lblAlgorithmeBayesien.setBounds(352, 10, 155, 15);
		add(lblAlgorithmeBayesien);
		
		algocomboBox = new JComboBox();
		algocomboBox.setBounds(525, 5, 108, 24);
		this.algocomboBox.addItem("Unigramme");
		this.algocomboBox.addItem("Bigramme");
		this.algocomboBox.addItem("Uni+Bi");
		algocomboBox.setEnabled(false);
		add(algocomboBox);
		
		lblPar = new JLabel("Par : ");
		lblPar.setBounds(651, 10, 37, 15);
		add(lblPar);
		
		parcomboBox = new JComboBox();
		parcomboBox.setBounds(706, 5, 130, 24);
		this.parcomboBox.addItem("Presence");
		this.parcomboBox.addItem("Frequence");
		parcomboBox.setEnabled(false);
		add(parcomboBox);
		//mdl.setalgocombobox(this.algocomboBox);
		//mdl.setparcombobox(this.parcomboBox);
		
		 this.kcomboBox = new JComboBox();
		 kcomboBox.setEnabled(false);
		kcomboBox.setBounds(285, 5, 49, 24);
		this.kcomboBox.addItem("1");
		this.kcomboBox.addItem("2");
		this.kcomboBox.addItem("3");
		this.kcomboBox.addItem("4");
		this.kcomboBox.addItem("5");
		this.kcomboBox.addItem("6");
		this.kcomboBox.addItem("7");
		this.kcomboBox.addItem("8");
		this.kcomboBox.addItem("9");
		this.kcomboBox.addItem("10");
		add(kcomboBox);
		

	}
	
	public JComboBox<String> getmdlCombobox(){return this.mdlcomboBox;}
	public JComboBox<String> getAlgoCombobox(){return this.algocomboBox;}
	public JComboBox<String> getParCombobox(){return this.parcomboBox;}
	public JComboBox<String> getKcombobox(){return this.kcomboBox;}

}
