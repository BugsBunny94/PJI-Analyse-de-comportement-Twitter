package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import model.Model;
import java.awt.Font;
import javax.swing.JComboBox;

import Controller.ChartAnalyseActionListener;

public class AnalysePanel extends JPanel {

	private JButton btnGoBack;
	private Model model ; 
	private JTextArea restextarea ; 
	private JButton btnChartAnalysis;
	private JComboBox chartcomboBox;
	
	/**
	 * Create the panel.
	 */
	public AnalysePanel(Model mdl) {
		this.model = mdl ; 
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton goback = (JButton) e.getSource();
				MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(goback.getParent());
				mainFrame.getPanel1().setVisible(true);
				mainFrame.getPanel2().setVisible(false) ;
				
			}});
		
		this.restextarea = new JTextArea();
		restextarea.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		restextarea.setEditable(false);
		restextarea.setOpaque(false);
		
		
		JLabel lblNewLabel = new JLabel("Resultats : ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnChartAnalysis = new JButton("chart analysis");
		btnChartAnalysis.addActionListener(new ChartAnalyseActionListener());
		
		this.chartcomboBox = new JComboBox();
		this.chartcomboBox.addItem("Presence, Unigramme");
		this.chartcomboBox.addItem("Frequence, Unigramme");
		this.chartcomboBox.addItem("Presence, Bigramme");
		this.chartcomboBox.addItem("Frequence, Bigramme");
		this.chartcomboBox.addItem("Presence, Unigramme+Bigramme");
		this.chartcomboBox.addItem("Frequence, Unigramme+Bigramme");
		this.chartcomboBox.addItem("Knn avec k = 3");
		this.chartcomboBox.addItem("Knn avec k = 5");
		this.chartcomboBox.addItem("Knn avec k = 5");
		this.chartcomboBox.addItem("Knn avec k = 10");
		this.chartcomboBox.addItem("Dictionnaire");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(restextarea, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
							.addGap(238))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnGoBack)
									.addGap(18)
									.addComponent(btnChartAnalysis)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chartcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(restextarea, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(btnChartAnalysis)
						.addComponent(chartcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	
	public Model getModel(){return this.model; }
	public JTextArea getrestextarea(){return this.restextarea;}
	public JComboBox getCharcombobox(){return this.chartcomboBox;}
}
