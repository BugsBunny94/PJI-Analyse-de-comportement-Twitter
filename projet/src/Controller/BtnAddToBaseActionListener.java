package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

import views.MainPanel;
import twitter4j.Status;

import model.Model;

public class BtnAddToBaseActionListener implements ActionListener{

	private Model model ; 
	
	public BtnAddToBaseActionListener(Model mdl){
		this.model = mdl ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.model.createBase("tweetter.csv");
		
		for(int i= 0 ; i <this.model.getTwList().size(); i++){
			
			Status status = this.model.getTwList().get(i).getOriginalTweet() ;
			if(!this.model.exists(status)){                               
				
				try {
					
					this.model.addToBase(new FileWriter("tweetter.csv",true) , status , this.model.getTwList().get(i).getAnnotation()) ;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
		}
		
	}

}
