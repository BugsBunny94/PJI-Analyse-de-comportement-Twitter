package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.ComboboxActionListener;

import model.Bays_Model;
import model.Dico_Model;
import model.Knn_Model;
import model.Model;

import twitter4j.Status;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class Tweet extends JPanel {
	
	private Status originalTweet ;
	private String stringToDisplay ; 
	private String cleanedString ;
	private String annotation ; 
	private JComboBox<String> comboBox ; 
	private JLabel label ; 
	private Model model ; 
	private JTextArea ta ; 
	private int index ; 

	

	/**
	 * Create the panel.
	 */
	public Tweet(Model mdl , Status otw, String annot, int i) {
		
		this.index = i ;
		ta = new JTextArea();
		ta.setWrapStyleWord(true);
		ta.setEditable(false);
		this.model = mdl ;
		this.originalTweet = otw ; 
		this.cleanedString = this.model.cleanTweet(this.originalTweet.getText()) ; 
		this.annotation = annot ;
		this.stringToDisplay = "@" + otw.getUser().getScreenName() + ":" + otw.getText();
	//	this.label = new JLabel(this.stringToDisplay);
		ta.setText(this.stringToDisplay) ;
		ta.setLineWrap(true);
		
		if(annotation.equals("positif"))
			this.ta.setBackground(Color.decode("#B5E655"));
		
		if(annotation.equals("negatif"))
			this.ta.setBackground(Color.decode("#C03000"));
		
		this.comboBox = new JComboBox<String>();
		
		this.comboBox.addItem("neutre");
		this.comboBox.addItem("positif");
		this.comboBox.addItem("negatif");
		this.comboBox.setSelectedItem(annot);
		comboBox.addActionListener(new ComboboxActionListener(this.model));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ta, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(ta, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	
	public Status getOriginalTweet(){ return this.originalTweet ; }
	public String getCleanedTweet(){ return this.cleanedString ; }
	public String toString(){return this.stringToDisplay ; }
	public String getAnnotation(){return this.annotation ; }
	public JLabel getLabel(){return this.label ; }
	public void setAnnotation(String annot){this.annotation = annot ; } 
	public int getIndex(){return this.index ;}
	public JTextArea getTextArea(){return this.ta ; }
	
}
