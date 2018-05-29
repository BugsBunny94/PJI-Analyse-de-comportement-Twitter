package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Analyse {
	
	private Bays_Model baysUniFrequence ;
	private Bays_Model baysUniPresence ;
	private Bays_Model baysBiFrequence ;
	private Bays_Model baysBiPresence ;
	private Bays_Model baysuUbFrequence;
	private Bays_Model baysuUbPresence;
	private Knn_Model knnmodel;
	private Dico_Model dictionnaire ;
	private Model model ; 
	private ArrayList<String> tweets ; 
	private String algo ; 
	private String type ;
	private boolean dicobool ;
	private boolean knnbool ;
	private boolean baysbool ;
	private int cptPos; 
	private int cptNeg ;
	private int cptNtr ;
	
	
	public Analyse(Model mdl , String base, int k){
		this.model = new Model();
		this.knnmodel = new Knn_Model(k);
		tweets = this.model.getAllTweets();
		this.knnbool = true ; 
	}
	
	public Analyse(Model mdl , String base){
		this.model = new Model();
		this.dictionnaire = new Dico_Model();
		tweets = this.model.getAllTweets();
		this.dicobool = true ; 
	}
	
	public Analyse(Model mdl , String base,String algo, String type){ //type frequence ou presence
		this.model = new Model();
		this.algo = algo ; 
		this.type = type ;
		
		if(algo.equals("uni"))
			if(type.equals("Presence"))
				this.baysUniPresence = new Bays_Model(1,"Presence");
			else 
				this.baysUniFrequence = new Bays_Model(1,"Frequence");
		
		
		if(algo.equals("bi"))
			if(type.equals("Presence"))
				this.baysBiPresence = new Bays_Model(2,"Presence") ; 
			else 
				this.baysBiFrequence = new Bays_Model(2,"Frequence") ; 

		
		if(algo.equals("baysuUb"))
			if(type.equals("Presence"))
				this.baysuUbPresence = new Bays_Model("Presence");
			else 
				this.baysuUbFrequence = new Bays_Model("Frequence");

	
		//on cree les listes qui vont contenir les sous ensembles
		tweets = this.model.getAllTweets();
		this.baysbool = true ;
		
	}
	
	public float getTauxPos(){ return  ((float)this.cptPos/(float)this.model.getAllTweets().size())*(float)100;}
	public float getTauxNeg(){return ((float)this.cptNeg/(float)this.model.getAllTweets().size())*(float)100;}
	public float getTauxNtr(){return ((float)this.cptNtr/(float)this.model.getAllTweets().size())*(float)100;}

	public ArrayList<String> ensembleApp(SousEnsemble[] sousensembles, int toClassify){
		ArrayList<String> res = new ArrayList<String>();
		
		for(int i=0;i<sousensembles.length;i++)
			if(i!=toClassify)
				for(int j=sousensembles[i].getStart();j<=sousensembles[i].getEnd();j++)
					res.add(tweets.get(j));
		
		
		return res ; 
	}
	
	public ArrayList<String> getEnsembleToClassify(SousEnsemble[] sousensembles, int toClassify){
		ArrayList<String> res = new ArrayList<String>();
		for(int i=sousensembles[toClassify].getStart();i<sousensembles[toClassify].getEnd();i++)
			res.add(tweets.get(i)) ;
		return res ;
	}
	
	
	
	public float getTauxErreur(int k){
		
		ArrayList<Float> tauxErreurs = new ArrayList<Float>();
		SousEnsemble[] sousensembles = this.getSousEnsembles("cleaned.csv", k);
		for(int i=0;i<sousensembles.length;i++){
			ArrayList<String> ensembleApp = this.ensembleApp(sousensembles, i) ; 
			this.createCsvEnsembleApp(ensembleApp,"ensembleApp"+new Integer(i).toString()+".csv");
			ArrayList<String> resClassification = this.classifier("ensembleApp"+new Integer(i).toString()+".csv", this.getEnsembleToClassify(sousensembles, i)); 
			float tauxErreur = (float)this.tauxErreur(this.getEnsembleToClassify(sousensembles, i), resClassification) ;
			System.out.println("taux erreur de dictionnaire ......."+tauxErreur);
			tauxErreurs.add(tauxErreur);
		}
		
		float somme =0 ; 
		for(int i=0;i<tauxErreurs.size();i++)
			somme += (float)tauxErreurs.get(i);
		
		return (float)((float)somme/(float)k)*(float)100 ; 
		
			
	}
	
	public SousEnsemble[] getSousEnsembles(String base, int k){
		
		//chaque case contient l'indice de fin du ieme sous ensemble
		SousEnsemble[] sousensembles = new SousEnsemble[k]; 
		
		//la on a l'indice de fin de chaque sous ensemble
		for(int i=1;i<=k;i++){
			int end = (this.model.countNbTweets(base)/k)*i ; 
			sousensembles[i-1] = new SousEnsemble(end-(this.model.countNbTweets(base)/k),end-1) ; 
		}
    	
		return sousensembles ;

	}
	
	
	
	public void createCsvEnsembleApp(ArrayList<String> ensembleApp, String filename){
		
		this.model.createBase(filename);
		//System.out.println("la base est normalement cree :)");
		
		try(FileWriter fw = new FileWriter(filename,true);
			PrintWriter out = new PrintWriter(fw)){
			
			for(int i=0;i<ensembleApp.size();i++)
				out.println(ensembleApp.get(i));
			fw.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
    	return ;
	}
	


	public ArrayList<String> classifier(String base, ArrayList<String> tweets){
		ArrayList<String> res = new ArrayList<String>();
		if(this.baysbool){
			if(this.algo.equals("uni")){
				if(this.type.equals("Presence"))
					for(int i=0;i<tweets.size();i++)
						res.add(this.baysUniPresence.classeMostProbable(base, tweets.get(i)));
				else for(int i=0;i<tweets.size();i++)
					res.add(this.baysUniFrequence.classeMostProbable(base, tweets.get(i)));
			}
			if(this.algo.equals("bi")){
				if(this.type.equals("Presence"))
					for(int i=0;i<tweets.size();i++)
						res.add(this.baysBiPresence.classeMostProbable(base, tweets.get(i)));
				else for(int i=0;i<tweets.size();i++)
					res.add(this.baysBiFrequence.classeMostProbable(base, tweets.get(i)));
			}
		
			if(this.algo.equals("baysuUb")){
				if(this.type.equals("Presence"))
					for(int i=0;i<tweets.size();i++)
						res.add(this.baysuUbPresence.classeMostProbable(base, tweets.get(i)));
				else for(int i=0;i<tweets.size();i++)
					res.add(this.baysuUbFrequence.classeMostProbable(base, tweets.get(i)));
			}
		}
		else {
			if(this.dicobool)
				for(int i=0;i<tweets.size();i++)
					res.add(this.dictionnaire.getAnnotation(tweets.get(i)));
			if(this.knnbool)
				for(int i=0;i<tweets.size();i++)
					res.add(this.knnmodel.knn(tweets.get(i), "cleaned.csv"));
		}	
		
		for(int i=0;i<res.size();i++){
			//System.out.println("icicicici...."+cptPos);
			if(res.get(i).equals("positif")){
				this.cptPos++;
				
			}
			else if(res.get(i).equals("negatif")){
				this.cptNeg++;
				
			}
			else { this.cptNtr++; }}
			
		return res ;
	}
	
	
	
	public float tauxErreur(ArrayList<String> tweets, ArrayList<String> resClassification){
		int cpt = 0 ; 
		for(int i=0;i<tweets.size();i++)
			if(!tweets.get(i).split(",")[4].equals(resClassification.get(i)))
				cpt++ ; 
		return (float)cpt/(float)tweets.size() ; 
	}
	
	
}
