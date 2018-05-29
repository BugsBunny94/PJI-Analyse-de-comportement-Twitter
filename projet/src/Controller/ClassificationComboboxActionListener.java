package Controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import views.MainPanel;


import views.OptionsPanel;
import views.SearchPanel;
import views.Tweet;

import model.Model;

public class ClassificationComboboxActionListener implements ActionListener{
	
	private Model model ; 
	
	private JLabel lblK;
	private JTextField kvalue;
	
	public ClassificationComboboxActionListener (Model mdl){
		this.model = mdl ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<String> box = (JComboBox<String>) e.getSource();
		OptionsPanel optionsPanel = (OptionsPanel) box.getParent() ;
		
		if(box.getSelectedItem().equals("Dictionnaire")){
			optionsPanel.getKcombobox().setEnabled(false);
			optionsPanel.getAlgoCombobox().setEnabled(false);
			optionsPanel.getParCombobox().setEnabled(false);
		}
		
		if(box.getSelectedItem().equals("Knn"))			
			optionsPanel.getKcombobox().setEnabled(true);
			optionsPanel.getAlgoCombobox().setEnabled(false);
			optionsPanel.getParCombobox().setEnabled(false);	

		if(box.getSelectedItem().equals("Bayes")){
			optionsPanel.getKcombobox().setEnabled(false);
			optionsPanel.getAlgoCombobox().setEnabled(true);
			optionsPanel.getParCombobox().setEnabled(true);
			
		}
		
			
	}
	
}
