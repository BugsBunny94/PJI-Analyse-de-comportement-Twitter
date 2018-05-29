package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Analyse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import views.AnalysePanel;
import views.MainFrame;
import views.MainPanel;

public class ChartAnalyseActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton btn = (JButton) e.getSource();
		MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(btn.getParent());
		MainPanel mainPanel = mainFrame.getPanel1();
		
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


		
		
		DefaultPieDataset pieDataSet = new DefaultPieDataset();
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Presence, Unigramme")){
			analyseUni1.getTauxErreur(10);
			//System.out.println("voilaaa"+analyseUni1.getTauxPos());
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseUni1.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseUni1.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseUni1.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Unigramme par presence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Frequence, Unigramme")){
			analyseUni2.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseUni2.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseUni2.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseUni2.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Unigramme par frequence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Presence, Bigramme")){
			analyseBi1.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseBi1.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseBi1.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseBi1.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Bigramme par presence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Frequence, Bigramme")){
			analyseBi2.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseBi2.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseBi2.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseBi2.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Bigramme par frequence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Presence, Unigramme+Bigramme")){
			analyseuUb1.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseuUb1.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseuUb1.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseuUb1.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Unigramme+Bigramme par presence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Frequence, Unigramme+Bigramme")){
			analyseuUb2.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(analyseuUb2.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(analyseuUb2.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(analyseuUb2.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification Unigramme+Bigramme par frequence en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Knn avec k = 3")){
			knn3.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(knn3.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(knn3.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(knn3.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification knn k=3 en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Knn avec k = 5")){
			knn5.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(knn5.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(knn5.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(knn5.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification knn k=5 en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Knn avec k = 7")){
			knn7.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(knn5.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(knn5.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(knn5.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification knn k=7 en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Knn avec k = 10")){
			knn10.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(knn10.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(knn10.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(knn10.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification knn k=10 en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}
		if(mainFrame.getPanel2().getCharcombobox().getSelectedItem().equals("Dictionnaire")){
			dictionnaire.getTauxErreur(10);
			pieDataSet.setValue("positifs", new Integer(Math.round(dictionnaire.getTauxPos())));
			pieDataSet.setValue("negatifs", new Integer(Math.round(dictionnaire.getTauxNeg())));
			pieDataSet.setValue("neutres", new Integer(Math.round(dictionnaire.getTauxNtr())));
			JFreeChart chart = ChartFactory.createPieChart("PieChart",pieDataSet,true,true,true);
			chart.setTitle("resultats classification par dictionnaire en % : ");
			PiePlot P = (PiePlot) chart.getPlot();
		
	
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(300,350);
		}


	}

}
