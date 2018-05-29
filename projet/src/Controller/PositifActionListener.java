package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import views.MainPanel;

import model.Model;


public class PositifActionListener implements ActionListener {
	
	private Model model ; 
	
	public PositifActionListener(Model mdl){
		this.model = mdl ; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.model.setAnnotationMem("positif") ; 
	}

}
