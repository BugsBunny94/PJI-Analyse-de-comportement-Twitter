package Controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import model.Analyse;

import views.AnalysePanel;
import views.MainFrame;
import views.MainPanel;
import views.SearchPanel;

public class AnalyserActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton analysebtn = (JButton) e.getSource();
		
		MainPanel mainPanel = (MainPanel) analysebtn.getParent().getParent();
		
		Analyse analyseUni1 = new Analyse(mainPanel.getModel(),"cleaned.csv","uni","Presence");
		Analyse analyseUni2 = new Analyse(mainPanel.getModel(),"cleaned.csv","uni","Frequence");
		Analyse analyseBi1 = new Analyse(mainPanel.getModel(),"cleaned.csv","bi","Presence");
		Analyse analyseBi2 = new Analyse(mainPanel.getModel(),"cleaned.csv","bi","Frequence");
		Analyse analyseuUb1 = new Analyse(mainPanel.getModel(),"cleaned.csv","baysuUb","Presence");
		Analyse analyseuUb2 = new Analyse(mainPanel.getModel(),"cleaned.csv","baysuUb","Frequence");
		Analyse dictionnaire = new Analyse(mainPanel.getModel(),"cleaned.csv");
		Analyse knn3 = new Analyse(mainPanel.getModel(),"cleaned.csv",3);
		Analyse knn5 = new Analyse(mainPanel.getModel(),"cleaned.csv",5);
		Analyse knn7 = new Analyse(mainPanel.getModel(),"cleaned.csv",7);
		Analyse knn10 = new Analyse(mainPanel.getModel(),"cleaned.csv",10);
		
		MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(analysebtn.getParent());
		
		mainFrame.getPanel2().getrestextarea().setText("taux d'erreurs des differents algorithmes bayesiens : \n\n"+
														"Presence, Unigramme : "+Float.toString(analyseUni1.getTauxErreur(10))+" %\n\n"+
														"Frequence, Unigramme : "+Float.toString(analyseUni2.getTauxErreur(10))+" %\n\n"+
														"Presence, Bigramme : "+Float.toString(analyseBi1.getTauxErreur(10))+" %\n\n"+
														"Frequence, Bigramme : "+Float.toString(analyseBi2.getTauxErreur(10))+" %\n\n"+
														"Presence, Unigramme+Bigramme : "+Float.toString(analyseuUb1.getTauxErreur(10))+" %\n\n"+
														"Frequence, Unigramme+Bigramme : "+Float.toString(analyseuUb2.getTauxErreur(10))+" %\n\n"+
														"Knn avec k = 3 : "+Float.toString(knn3.getTauxErreur(10))+" %\n\n"+
														"Knn avec k = 5 : "+Float.toString(knn5.getTauxErreur(10))+" %\n\n"+
														"Knn avec k = 7 : "+Float.toString(knn7.getTauxErreur(10))+" %\n\n"+
														"Knn avec k = 10 : "+Float.toString(knn10.getTauxErreur(10))+" %\n\n"+
														/*"Dictionnaire : "+Float.toString(dictionnaire.getTauxErreur(10))+"%*/ "\n");
														
		
		mainFrame.getPanel1().getAnnotAutoPanel().getLastAnalysisbtn().setEnabled(true);
		mainFrame.getPanel1().setVisible(false);
		mainFrame.getPanel2().setVisible(true) ;
		
		
		
		
		
		
	}

}
