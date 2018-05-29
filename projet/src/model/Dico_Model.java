package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dico_Model extends Model {
	
	
		public String getTypeModel(){
			return "Dico_Model" ;
		}
	  
		
		public boolean existsIn(String pathName , String keyword){
			
			BufferedReader br ;
			
			String[] fr ;
			
			boolean res = false ; 

			try {
				
				br = new BufferedReader(new InputStreamReader(new FileInputStream(pathName), "ISO-8859-15"));

				br.readLine() ; 
				
				fr = br.readLine().split(",") ; // l'espace est bon ...; 
				
				res = Arrays.asList(fr).contains(keyword) ; 
				
				
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return res ; 
	}

		

		
				
			
	// attention il faudra nettoyer les tweets avant, pour pouvoir traiter un tweet il ne devera comporter que des mots, pas de ? pas de , ni ; etc . 

		public int nombreMots(String tw,String pathName){
		
			String[] tweet = tw.split(" ") ; 
			
	 		int nbMots = 0 ;
	 		
	 		
	 		for(int i=0;i<tweet.length;i++){
	 			
	 				
	 				if((tweet[i] != "") && existsIn(pathName,tweet[i])){
	 	 				nbMots ++ ; 
	 
	 	 			}
	 	 				
	 	 			
	 	 			String mot = tweet[i] ;
	 	 			
	 	 			for(int j = i+1 ;j<tweet.length;j++){
	 	 				
	 	 					mot += " " + tweet[j] ; 
	 	 	 				
	 	 	 				
	 	 	 				if((tweet[j] != "") && existsIn(pathName,mot)){ 
	 	 	 					
	 	 	 					nbMots++ ;
	 	 	 				}
	 	 				
	 	 				
	 	 					
	 	 				
	 	 			}
	 			
	 			
	 			
	 		}
			
			return nbMots ; 
		}


		
		
		public int nombreMotsPos(String tw){
			
			return nombreMots(tw , "positive.txt");
			
		}
		
		
		
		
		public int nombreMotsNeg(String tw){
			
			return nombreMots(tw , "negative.txt");
			
		}
		
		
		
		
		
		public String getAnnotation(String tweet){

			if(nombreMotsNeg(tweet) > nombreMotsPos(tweet))
				return "negatif" ; 
			else {
				if(nombreMotsNeg(tweet) < nombreMotsPos(tweet))
					return "positif" ; 
				else 
					return "neutre" ;
			}
			
		}
		
		
		


		
		


}
