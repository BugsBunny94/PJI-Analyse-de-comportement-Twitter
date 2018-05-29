package views;

import static org.junit.Assert.*;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import model.Analyse;
import model.Bays_Model;
import model.Model;
import model.SousEnsemble;


public class Test {

	@org.junit.Test
	public void test() {
		
		//FrameTest main = new FrameTest() ; 
		
		/*assertEquals(main.existsIn("negative.txt", "pas très bon"),true) ; 
		
		assertEquals(main.nombreMotsNeg("ils ne supportent pas qu'on se permette de mépriser les gens"),2) ;
		
		assertEquals(main.nombreMotsPos("ils ne supportent pas qu'on se permette de mépriser les gens, de la vigueur intellectuelle est vraiment bien"),2) ;
		
		assertEquals(main.nombreMotsNeg("ils ne supportent pas qu'on se permette de mépriser les gens, de la vigueur intellectuelle est vraiment bien"),2) ;
		
		assertEquals(main.getAnnotation("ils ne supportent pas qu'on se permette de mépriser les gens, merde de la vigueur intellectuelle est vraiment bien"),"negatif") ;
		
		assertEquals(main.getAnnotation("ils ne supportent pas qu'on se permette de mépriser les gens, de la vigueur intellectuelle est vraiment bien"),"neutre") ;
		
		assertEquals(main.getAnnotation("ils ne supportent pas qu'on se permette de mépriser les gens, top de la vigueur intellectuelle est vraiment bien"),"positif") ;
		
		
		System.out.println(main.getAnnotation("Prévisions Méteo pour le Lundi 07 Novembre 2016 A la mijournée le temps sera très nuageux à Coutances"));

		System.out.println(main.getAnnotation("roubaix La carte des stations  VLille  transpoleactu est disponible sur le portail OpenData de Roubaix  http…")); 
		
		System.out.println("a  b".split(" ")[2]);
		
		System.out.println(main.cleanTweet("RT @kdqchi Prévisions #Méteo pour le Lundi 07 Novembre 2016 A la mi-journée le temps sera très nuageux à Coutances,... https://t.co/DzZ1gzNqBU"));
		
		assertEquals(main.nbMotsCommun("bonjour je suis  Aymane", "bonjour je suis Younes"),3);
		assertEquals(main.nbTotalMots("bonjour       je suis Aymane","bonjour je suis Younes"),8);
		assertEquals(main.getRidOfSpaces("a   b"),"a  b");
		System.out.println(main.getRidOfSpaces("a                               b     c     d"));
		
		
		for(int i=0;i<main.getKfirstTweets("cleaned.csv", 5).size();i++)
			System.out.println(main.getKfirstTweets("cleaned.csv", 5).get(i));
		
		int[][] matrix = main.confusionMatrix("cleaned.csv", 8) ; 
		
		System.out.println("      Pos"+"        "+"Neg"+"        "+"Ntr");
		System.out.println("Pos"+"    "+matrix[0][0]+".........."+matrix[0][1]+".........."+matrix[0][2]);
		System.out.println("Neg"+"    "+matrix[1][0]+".........."+matrix[1][1]+".........."+matrix[1][2]);
		System.out.println("Ntr"+"    "+matrix[2][0]+".........."+matrix[2][1]+".........."+matrix[2][2]);
		*/
		
		//System.out.println(main.propTweets("cleaned.csv", "positif"));
		
		Bays_Model bays = new Bays_Model(2,"Presence");
		String str = "797446744485326848 , Paritix1 , aujourd'hui il fait tres beau, le ciel est bleu , neutre , positif" ;
		
		System.out.println(bays.classeMostProbable("cleaned.csv", str));

		assertEquals(bays.nbOccurenceMotDansTweet("aymane nabila", "aymane nabila blala blal aymane nabila bla"), 2);
		
		
		String str2 = "ceci est un test pour se debarasser des mots tres cours" ; 
		assertEquals(bays.getRidOfSmallWords(str2),"ceci test pour debarasser mots tres cours");
		
		String[] list = bays.buildNgrammeSplit("ceci est est un un test pour se debarasser des mots tres cours") ; 
		System.out.println("\n\n liste n grammes : \n");
		
		/*for(int i=0;i<list.length;i++)
			System.out.println(list[i]);*/
		
		String[] list2 = bays.uniUbigramme(str2) ;
		/*for(int i=0;i<list2.length;i++)
			System.out.println(list2[i]);
		assertEquals(bays.getuUb(),false);
		bays = new Bays_Model();
		System.out.println(bays.classeMostProbable("cleaned.csv", str));
		
		Model mdl = new Model();
		Analyse analyse = new Analyse(mdl ,"cleaned.csv");
		
		System.out.println("indices de debut et fin de chaque sous ensemble dans la base :");
		SousEnsemble[] sousensembles = analyse.getSousEnsembles("cleaned.csv", 3) ; 
		for(int i=0;i<sousensembles.length;i++)
			System.out.println(sousensembles[i].getStart()+"..."+sousensembles[i].getEnd());
		
		System.out.println("ensemble d'apprentissage (union des sous ensembles qu'on ne classfie pas ) :");
		ArrayList<String> ensembleApp = analyse.ensembleApp(sousensembles, 0);
		for(int i=0;i<ensembleApp.size();i++)
			System.out.println(ensembleApp.get(i).split(" , ")[0]);
		
		analyse.createCsvEnsembleApp(ensembleApp, "a1.csv");
		
		System.out.println("\n ensemble a classifier : \n");
		
		ArrayList<String> ensembletoClassify = analyse.getEnsembleToClassify(sousensembles, 0);
		
		for(int i=0;i<ensembletoClassify.size();i++)
			System.out.println(ensembletoClassify.get(i).split(" , ")[0]);
		
		System.out.println("resultats de la classification :");
		
		ArrayList<String> classes = analyse.classifier("a1.csv", ensembletoClassify,"bi");
		for(int i=0;i<classes.size();i++)
			System.out.println(classes.get(i));
		
		System.out.println("taux d'erreur unigramme : "+ analyse.getTauxErreur("uni", 3));
		System.out.println("taux d'erreur bigramme : "+ analyse.getTauxErreur("bi", 3));
		System.out.println("taux d'erreur unigramme : "+ analyse.getTauxErreur("baysuUb", 3));*/
	}

		
		
		
		
		
		
				
}