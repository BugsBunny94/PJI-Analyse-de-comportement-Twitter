package views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.ComboboxActionListener;

import model.Model;

public class ResultPanel extends JPanel implements Observer {

	
	private TweetsPanel tweetsPanel ;
	private Model model ; 
	/**
	 * Create the panel.
	 */
	public ResultPanel(Model mdl) {
		
		this.model = mdl; 
		mdl.addObserver(this);
		
		this.setLayout(new BorderLayout(10,10));

		
		
		this.tweetsPanel = new TweetsPanel(model);
		add(this.tweetsPanel, BorderLayout.CENTER);
		JScrollPane scroller = new JScrollPane(this.tweetsPanel);
		this.add(scroller, BorderLayout.CENTER);
		
		
		
	}
	
	public TweetsPanel getTweetsPanel(){return this.tweetsPanel;}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
