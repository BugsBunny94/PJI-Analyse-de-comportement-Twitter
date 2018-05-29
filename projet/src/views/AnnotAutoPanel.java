package views;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Model;

import Controller.AnalyseActionListener;
import Controller.AnalyserActionListener;
import Controller.BtnAddToBaseActionListener;
import Controller.BtnAnnotateActionListener;
import Controller.BtnCleanActionListener;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class AnnotAutoPanel extends JPanel {
	
	private JButton lastanalysebtn;
	
	/**
	 * Create the panel.
	 */
	public AnnotAutoPanel(Model mdl) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnClean = new JButton("clean");
		btnClean.addActionListener(new BtnCleanActionListener(mdl));
		add(btnClean);
		
		JButton btnAddToBase = new JButton("add");
		btnAddToBase.addActionListener(new BtnAddToBaseActionListener(mdl));
		
		btnAddToBase.setFont(new Font("Dialog", Font.BOLD, 12));
		add(btnAddToBase);
		
		JButton analysebtn = new JButton("new analysis");
		analysebtn.addActionListener(new AnalyserActionListener());
		add(analysebtn);
		
		lastanalysebtn = new JButton("last analysis");
		lastanalysebtn.setEnabled(false);
		lastanalysebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MainPanel mainPanel = (MainPanel) btn.getParent().getParent();
				MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(btn.getParent());
				mainFrame.getPanel2().setVisible(true);
				mainFrame.getPanel1().setVisible(false);
			}
		});
		add(lastanalysebtn);
		
		

	}

	public JButton getLastAnalysisbtn(){
		return this.lastanalysebtn;
	}
}
