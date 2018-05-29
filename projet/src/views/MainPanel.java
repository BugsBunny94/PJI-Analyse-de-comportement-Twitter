package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import model.Model;
import java.awt.Panel;

public class MainPanel extends JPanel {
	
	private SearchPanel searchPanel ;
	private ResultPanel resultPanel ; 
	
	private AnnotAutoPanel annotAutoPanel ;
	private OptionsPanel optionsPanel ; 
	
	private Model model ; 
	
	public SearchPanel getSearchPanel(){return this.searchPanel;}
	public ResultPanel getResultPanel(){return this.resultPanel;}
	public AnnotAutoPanel getAnnotAutoPanel(){return this.annotAutoPanel;}
	public OptionsPanel getOptionsPanel(){return this.optionsPanel;}

	
	/**
	 * Create the panel.
	 */
	public MainPanel(Model mdl) {
		this.model = mdl ; 
		this.searchPanel = new SearchPanel(mdl);
		this.resultPanel = new ResultPanel(mdl);
		this.optionsPanel=  new OptionsPanel(mdl) ;
	
		
		
		this.annotAutoPanel= new AnnotAutoPanel(mdl);
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(resultPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(annotAutoPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
						.addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(resultPanel, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(annotAutoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		
		
	}

	
	public String getKeyWord(){	return this.searchPanel.getTextField();}
	public Model getModel(){return this.model;}
}