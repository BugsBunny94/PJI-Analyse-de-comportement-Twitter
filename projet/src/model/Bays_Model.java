package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bays_Model extends Model{

	//=============!!!!!!!! REMPLACER "MOT" PAR "NGRAMME" DANS TOUT LE MODELE 

	private int n ;
	
	private int nmem ; 
	
	private boolean uUb = false; 
	private String type ; 
	
	public Bays_Model(int gr, String type){ // type presence ou frequence
		this.n = gr ; 
		this.nmem = n ; 
		this.type = type ;
	}
	
	public Bays_Model(String type){
		this.n = 0 ; 
		this.uUb = true ;
		this.type = type;
	}
	
	public boolean getuUb(){return this.uUb ;}
	
	public void setN(int gr){this.n = gr ; }
	public int getN(){return this.n; }
	
	public String getTypeModel(){
		return "Bays_Model" ;
	}
	
	
	
	// P(c)
	public float propTweets(String classe){
		
		ArrayList<String> tweets = this.getAllTweets() ; 
		int cptclasse = 0 ;
		float proportion ; 
		
		
		for(int i=0;i<tweets.size();i++){
			
			String annotation = tweets.get(i).split(",")[4] ; 
			
			if(annotation.equals(classe))
				cptclasse++; 
		}
			
		proportion = ((float)cptclasse/(float)tweets.size()) ; 
		
		return proportion;
	 
	}
	
	
	
		
	
	public int nbOccurencesMotAux(String mot , String text){
		
		/*String[] str = text.split(" ") ; 
		int cpt = 0 ;
		
		for(int i=0 ; i<str.length ; i++)
			if(str[i].equals(mot))
				cpt++ ; 
		
		return cpt ;*/
		return this.nbOccurenceMotDansTweet(mot, text);
	}
	
	

	
	
	//N
	public int nbTotalMotsBase(){
		
		ArrayList<String> twlist = this.getAllTweets() ;
		int cpt = 0 ;
		
		for(int i=0;i<twlist.size();i++){
			if(!uUb)
				cpt += this.buildNgrammeSplit( twlist.get(i).split(",")[2]).length ;
			else
				cpt += this.uniUbigramme(( twlist.get(i).split(",")[2])).length ;
				
		}
			
			//cpt += twlist.get(i).split(" , ")[2].split(" ").length ; 
		
		return cpt ; 
	}
	
	
	
	
	//n(m,c)
	public int nbOccurencesMot(String mot, String classe, String tw){
		
		int res= 0 ; 
		
		if(tw.equals(classe))
			this.nbOccurencesMotAux(mot,tw) ;
		
		return res ; 
	}
	
	
	
	
	//n(c)
	public int nbTotalMotsClasse(String base, String classe){
		
		ArrayList<String> twlist = this.getAllTweets() ;
		int cpt = 0 ; 
		
		for(int i=0;i<twlist.size();i++)
			if(twlist.get(i).split(",")[4].equals(classe))
				if(!this.uUb)
					cpt += this.buildNgrammeSplit(twlist.get(i).split(",")[2]).length ;
				else 
					cpt += this.uniUbigramme(twlist.get(i).split(",")[2]).length ;
				
				//cpt += twlist.get(i).split(" , ")[2].split(" ").length ; 
		
		return cpt ;
	}
	
	
	//P(m|c)
	public float propotionMot(String mot , String classe, String base , String tweet){

		return (float)(nbOccurencesMot(mot, classe, tweet) + 1) / (float)(nbTotalMotsClasse(base, classe) + nbTotalMotsBase()) ; 
	}
	
	
	
	//P(t|c) //updated !! Math pow
	public float probabiliteDeTsachantCpresence(String base, String tweet, String classe){
		
		
		
		float res = 1 ; 
		
		//System.out.println(tweet);
		//String txt = tweet.split(" , ")[2] ;  
		String txt = tweet ; 
		String text = this.getRidOfSmallWords(txt) ;  //update for tp2
		
		if(!this.uUb){
			for(int i=0;i<this.buildNgrammeSplit(text).length;i++)
				res *= propotionMot(this.buildNgrammeSplit(text)[i] , classe, base , tweet) ; 
		}
		else{
			for(int i=0;i<this.uniUbigramme(text).length;i++)
				res *= propotionMot(this.uniUbigramme(text)[i] , classe, base , tweet); 
		}
		
		return res ; 
	}
	
	//P(t|c) //updated !! Math pow
	public float probabiliteDeTsachantCfrequence(String base, String tweet, String classe){
		
		
		
		float res = 1 ; 
		
		//System.out.println(tweet);
		//String txt = tweet.split(" , ")[2] ;  
		String txt = tweet ; 
		String text = this.getRidOfSmallWords(txt) ;  //update for tp2
		
		if(!this.uUb){
			for(int i=0;i<this.buildNgrammeSplit(text).length;i++)
				res *= Math.pow(propotionMot(this.buildNgrammeSplit(text)[i] , classe, base , tweet), this.nbOccurenceMotDansTweet(this.buildNgrammeSplit(text)[i], tweet)) ; 
		}
		else{
			for(int i=0;i<this.uniUbigramme(text).length;i++)
				res *= Math.pow(propotionMot(this.uniUbigramme(text)[i] , classe, base , tweet), this.nbOccurenceMotDansTweet(this.uniUbigramme(text)[i], tweet)) ; 
		}
		
		return res ; 
	}
	
	
	
	
	//P(c|t)
	public float probabiliteClasseTweet(String base , String tweet, String classe){ // type == presence ou frequence 
		
		if(this.type.equals("Presence"))
			return (float)this.probabiliteDeTsachantCpresence(base, tweet, classe) * (float)this.propTweets(classe) ; 
		else
			return (float)this.probabiliteDeTsachantCfrequence(base, tweet, classe) * (float)this.propTweets(classe) ; 

	}
	
	
	
	
	
	public String classeMostProbable(String base, String tweet){
		
		float probNeutre = this.probabiliteClasseTweet(base, tweet, "neutre");
		float probPositif = this.probabiliteClasseTweet(base, tweet, "positif");
		float probNegatif = this.probabiliteClasseTweet(base, tweet, "negatif");
		
		//System.out.println(probNeutre+"....."+probPositif+"....."+probNegatif);
		
		if(probNeutre >= probPositif && probNeutre >= probNegatif)
			return "neutre" ; 
		else if (probPositif >= probNeutre && probPositif >= probNegatif)
			return "positif" ; 
		else return "negatif" ; 
		
	}
	
	
	public int nbOccurenceMotDansTweet(String mot , String tw ){
		
		//String[] twsplit = tw.split(" ");
		String[] twsplit ;
		if(!this.uUb)
			twsplit = this.buildNgrammeSplit(tw);
		else
			twsplit = this.uniUbigramme(tw);
		
		return Collections.frequency(Arrays.asList(twsplit), mot);
		
	}

	public String getRidOfSmallWords(String tw){
		
		String[] twsplit = tw.split(" ") ;
		String res = "" ; 
		if(twsplit[0].length() > 3)
			res = twsplit[0]; 
		for(int i=1;i<twsplit.length;i++)
			if(twsplit[i].length() > 3)
				res += " "+twsplit[i] ; 
		return res; 
	}
	
	
	public String[] buildNgrammeSplit(String tw){
		
		ArrayList<String> list = new ArrayList<String>();
		String[] twsplit = tw.split(" ") ;
		
		
		for(int i=0 ;i<twsplit.length-(this.n-1);i++){
			
			String str = twsplit[i] ;
			
			for(int j=i+1;j<i+this.n;j++)
				str += " "+twsplit[j] ;  
			
			list.add(str) ;
	
		}
		String[] listArr = new String[list.size()];
		listArr = list.toArray(listArr) ; 
		return listArr ;
	}
	
	
	
	public String[] uniUbigramme(String tw){
		
		this.n = 1 ; 
		String[] uni = this.buildNgrammeSplit(tw);
		this.n = 2 ; 
		String[] bi = this.buildNgrammeSplit(tw) ; 

		String[] res = new String[uni.length+bi.length] ; 
		int i =0 ;
		for(i=0;i<uni.length;i++)
			res[i] = uni[i] ;
		int j = 0 ;
		for(i=uni.length;i< uni.length + bi.length ;i++){
			res[i] = bi[j] ;
			j++;
		}
		
		return res ; 
		
	}
	


	
}

