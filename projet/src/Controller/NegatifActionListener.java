package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class NegatifActionListener implements ActionListener {
	
	private Model model ; 
	
	public NegatifActionListener(Model mdl){
		this.model = mdl ; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.model.setAnnotationMem("negatif") ; 
	}

}
