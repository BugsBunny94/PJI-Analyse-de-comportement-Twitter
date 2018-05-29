package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import views.MainFrame;

public class AnalyseActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton analyse = (JButton) e.getSource();
		MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(analyse.getParent());
		
		mainFrame.getPanel1().setVisible(false);
		mainFrame.getPanel2().setVisible(true) ;
		
	}

}
