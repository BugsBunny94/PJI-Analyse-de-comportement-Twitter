package views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;

import Controller.ClassificationComboboxActionListener;
import Controller.SearchbtnActionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;

public class SearchPanel extends JPanel {
	
	private JLabel lblNewLbel ; 
	private JTextField textField;
	private JButton btnNewButton;
	private JComboBox<String> comboBox;
	private JLabel lblClassifieur;


	/**
	 * Create the panel.
	 */
	public SearchPanel(Model mdl) {
				
		btnNewButton = new JButton("Searh");
		btnNewButton.addActionListener(new SearchbtnActionListener(mdl));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 10));
		
		//Barre de recherche
		lblNewLbel = new JLabel("Key Word : ");
		
		textField = new JTextField();
		textField.setColumns(10);
		

		
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(lblNewLbel);
		add(textField);
		add(btnNewButton);
		
	}
	
	
	public String getTextField(){
		return this.textField.getText();
	}
	


}
