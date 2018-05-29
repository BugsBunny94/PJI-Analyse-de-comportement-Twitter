package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import views.MainPanel;

import model.Model;

import views.Tweet;

public class ComboboxActionListener implements ActionListener{

	private Model model ; 
	
	public ComboboxActionListener(Model mdl){
		this.model = mdl ; 
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<String> box = (JComboBox<String>) e.getSource();
		Tweet panel = (Tweet) box.getParent();

		if(box.getSelectedItem().equals("neutre")){
			this.model.getTwList().get(panel.getIndex()).setAnnotation("neutre"); 
			panel.getTextArea().setBackground(Color.WHITE);
		}
		else if(box.getSelectedItem().equals("positif")){
			this.model.getTwList().get(panel.getIndex()).setAnnotation("positif"); 
			panel.getTextArea().setBackground(Color.decode("#B5E655"));
		}
		else {
			this.model.getTwList().get(panel.getIndex()).setAnnotation("negatif"); 
			panel.getTextArea().setBackground(Color.decode("#C03000"));
		}
	}
	

}
