package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import twitter4j.Status;
import twitter4j.TwitterException;

import model.Bays_Model;
import model.Dico_Model;
import model.Knn_Model;
import model.Model;

public class TweetsPanel extends JPanel implements Observer {

	

	private Dico_Model dicomodel ; 
	private Knn_Model knnmodel;
	private Bays_Model baysmodel;
	private Model model  ;
	private ArrayList<Tweet> list ;
	private String chosenmodel ;
	private JComboBox<String> kcomboBox ;
	private JComboBox algocomboBox;
	private JComboBox<String> parcomboBox;
	private JComboBox<String> OptionscomboBox;

	
	/**
	 * Create the panel.
	 */
	public TweetsPanel(Model mdl) {
		
		this.model = mdl ;
		
		mdl.addObserver(this);
		
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
	}

	

	public void setchosenmodel(String mdl){this.chosenmodel = mdl;}
	public void setkcombobox(JComboBox<String> k){this.kcomboBox = k ;}
	public void setalgocombobox(JComboBox box){this.algocomboBox = box;}
	public void setparcombobox(JComboBox<String> box){this.parcomboBox = box; }
	public void setOptionsCombobox(JComboBox<String> combo){this.OptionscomboBox = combo; }

	@Override
	public void update(Observable o, Object arg) {
		
		list = new ArrayList<Tweet>();
		int i = 0 ;
		
		for (Status status : this.model.getResult().getTweets()) {
			if(this.chosenmodel.equals("Dictionnaire")){
				this.dicomodel = new Dico_Model();
				list.add(new Tweet(this.model, status, this.dicomodel.getAnnotation(status.getText()),i));
			}
			
			if(this.chosenmodel.equals("Knn")){
				
				this.knnmodel = new Knn_Model(Integer.parseInt((String)this.kcomboBox.getSelectedItem()));
				//System.out.println(this.knnmodel.knn(status.getText(), "cleaned.csv", 3));
				list.add(new Tweet(this.model, status, this.knnmodel.knn(this.model.cleanTweet(status.getText()), "cleaned.csv"),i)); //k a changer !!!!
			}
			
			if(this.chosenmodel.equals("Bayes")){
				if(this.algocomboBox.getSelectedItem().equals("Unigramme"))
					if(this.parcomboBox.getSelectedItem().equals("Presence"))
						this.baysmodel = new Bays_Model(1,"Presence");
					else this.baysmodel = new Bays_Model(1,"Frequence");
				if(this.algocomboBox.getSelectedItem().equals("Bigramme"))
					if(this.parcomboBox.getSelectedItem().equals("Presence"))
						this.baysmodel = new Bays_Model(2,"Presence");
					else this.baysmodel = new Bays_Model(2,"Frequence");
				if(this.algocomboBox.getSelectedItem().equals("Uni+Bi"))
					if(this.parcomboBox.getSelectedItem().equals("Presence"))
						this.baysmodel = new Bays_Model("Presence");
					else this.baysmodel = new Bays_Model("Frequence");
				
				list.add(new Tweet(this.model, status, this.baysmodel.classeMostProbable("cleaned.csv", this.model.cleanTweet(status.getText())),i));
			}
			
			
			 
		}
		
		this.removeAll();
		
		for(Tweet tw : list)
			this.add(tw) ;
		
		this.model.setTwList(list); 
		
		revalidate();
		repaint();
		
	}





	
}
