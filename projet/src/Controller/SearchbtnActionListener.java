package Controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

import model.Model;

import views.MainPanel;
import views.OptionsPanel;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import views.SearchPanel;

public class SearchbtnActionListener implements ActionListener {

	private Model model ; 
	
	public SearchbtnActionListener(Model model){
		this.model = model ; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton searchButton = (JButton) e.getSource();
		
		SearchPanel searchPanel = (SearchPanel) searchButton.getParent();
		
		MainPanel mainPanel = (MainPanel) searchButton.getParent().getParent();
		
		
		
		try {
			mainPanel.getResultPanel().getTweetsPanel().setkcombobox(mainPanel.getOptionsPanel().getKcombobox());
			mainPanel.getResultPanel().getTweetsPanel().setchosenmodel(mainPanel.getOptionsPanel().getmdlCombobox().getSelectedItem().toString());
			mainPanel.getResultPanel().getTweetsPanel().setalgocombobox(mainPanel.getOptionsPanel().getAlgoCombobox());
			mainPanel.getResultPanel().getTweetsPanel().setparcombobox(mainPanel.getOptionsPanel().getParCombobox());
			mainPanel.getResultPanel().getTweetsPanel().setOptionsCombobox(mainPanel.getOptionsPanel().getmdlCombobox());
			
			this.model.run(mainPanel.getKeyWord());
		
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
