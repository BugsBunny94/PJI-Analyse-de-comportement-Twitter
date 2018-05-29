package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Knn_Model extends Model{

	  
	private int k ; 
	
	public Knn_Model(int newk ){
		this.k = newk ;
	}
	
	public int getK(){
		return this.k ;
	}
	
	public String getTypeModel(){
		return "Knn_Model" ;
	}
	
	public int nbTotalMots(String tw1, String tw2){
	
		return getRidOfSpaces(tw1).split(" ").length + getRidOfSpaces(tw2).split(" ").length ; 	
	}
	
	public int nbMotsCommun(String tw1, String tw2){
		
		String aux = tw1 ; 
		
		List auxList = new ArrayList(Arrays.asList(aux.split(" ")));
		List tw2List = Arrays.asList(tw2.split(" "));
		
		auxList.retainAll(tw2List) ;
		return auxList.size() ;
	}
	
	
	
	public int distance(String tw1, String tw2){
		
		return ( nbTotalMots(tw1,tw2) - nbMotsCommun(tw1,tw2) ) / nbTotalMots(tw1,tw2) ; 
		
	}
	
	
	
	
	public ArrayList<String> getKfirstTweets(){
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> tweetsInBase = this.getAllTweets();
		for(int i=0;i<this.k;i++)
			list.add(tweetsInBase.get(i));
		/*try{
			InputStream ips=new FileInputStream(base); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			br.readLine();
			int cpt = 1 ; 
			while ((ligne=br.readLine())!=null && cpt <= k){
				cpt++ ; 
				list.add(ligne);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}*/
    	
		return list ; 
	}
	
	
	
	
	
	public String voteAnnotation(Neighbour[] neighbours){
		
		int cptPos = 0 ;
		int cptNeg = 0 ;
		int cptNtr = 0 ;
		int max = 0 ; 
		
		for(int i=0;i<neighbours.length;i++){
			
			switch (neighbours[i].getAnnotation()){
			case "positif" : 
				cptPos++ ;
				break ; 
			case "negatif" : 
				cptNeg++ ; 
				break ; 
			default : 
				cptNtr++ ; 
				break ;
			}
		
		}
		
		if(cptPos == cptNeg || cptPos > cptNeg)
			return "positif";
		else if(cptNeg > cptPos)
			return "negatif" ; 
			else return "neutre" ;
			
	}
	
	
	
	
	// on travaille avec le fichier source cleaned.csv
	public String knn(String tweet, String base){
	
		//String[] tweetsplited = tweet.split(" , ") ; 
		String tw = tweet ;//String tw = tweetsplited[2]; 
		//String res = tweetsplited[0]; 
		Neighbour[] near_neighbours = new Neighbour[k] ;
		ArrayList<String> ktwlist = getKfirstTweets();
		ArrayList<String> alltwlist = getAllTweets(); 
		DistanceComparator distanceComparator = new DistanceComparator() ;
		int i ; 
		
		//System.out.println("hahahhahah"+ktwlist.size());
		for(i=0;i<k;i++)
			near_neighbours[i] = new Neighbour(ktwlist.get(i),tw);
		
		Arrays.sort(near_neighbours, distanceComparator);
		
		for(i=k;i< alltwlist.size();i++){
			if(distance(tw,alltwlist.get(i)) < near_neighbours[k-1].getDistance())
				near_neighbours[k-1] = new Neighbour(alltwlist.get(i),tw);
			Arrays.sort(near_neighbours, distanceComparator);
		}
		/*
		tweetsplited[4] = voteAnnotation(near_neighbours) ; 
		
		for(i= 1;i<tweetsplited.length;i++)
			res = res+" , "+tweetsplited[i]; 
		
		return res ;  */
		
		return voteAnnotation(near_neighbours) ;
		
	}
	
	
	
	
	private class Neighbour {
		
		String tweet ;
		int distance ;
		String[] tab ;
		
		public Neighbour(String tw, String twWeAreWorkingOn){
			tweet = tw ;
			distance = distance(twWeAreWorkingOn, tw) ;
			tab = tweet.split(","); 
		}
		
		public String getId(){
			return tab[0]; 
		}
		
		public String getUser(){
			return tab[1];
		}
		
		public String getTweet(){
			return tab[2];
		}
		
		public String getCreatedAt(){
			return tab[3] ;
		}
		
		public String getAnnotation(){
			return tab[4];
		}
		
		public int getDistance(){
			return distance ; 
		}
	
	}

	
	
	private class DistanceComparator implements Comparator<Neighbour> {

		@Override
		public int compare(Neighbour o1, Neighbour o2) {
			
			return Integer.compare(o1.getDistance(), o2.getDistance()); 
 			
		}
			
	}
	
	
	
	

	
	
	
	
	
	public ArrayList<String> partition(String base){
		
		ArrayList<String> list = new ArrayList<String>();
		int finpart1 = Math.round((this.countNbTweets(base)/3)*2); // 2/3
		//int debpart2; // 1/3
		int cpt =0 ; 
		
		createBase("testKnn.csv");
		
		try(InputStream ips=new FileInputStream(base); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			FileWriter fw = new FileWriter("testKnn.csv",true);
			PrintWriter out = new PrintWriter(fw)){
			
			String ligne;
			br.readLine();
			while ((ligne=br.readLine())!=null){ 
				if(cpt < finpart1){
					cpt++;
					out.println(ligne);
				}
				else list.add(ligne);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
    	
		return list ;

	}
	
	
	
	
	public int[][] confusionMatrix(String base){
		
		ArrayList<String> tweets = partition(base);
		int[][] matrix = new int[3][3] ;
		
		for(int i=0;i<3;i++){
			for(int j = 0 ;j<3;j++)
				matrix[i][j] = 0 ; 
		}
		
		for(int l=0;l<tweets.size();l++){
			String annotationReel = tweets.get(l).split(",")[4];
			String annotationEstimee = knn(tweets.get(l), base).split(",")[4];
			//System.out.println(annotationReel +".........." + annotationEstimee);
			if(annotationReel.equals("positif") && annotationEstimee.equals("positif"))
				matrix[0][0]++ ; 
			else if(annotationReel.equals("positif") && annotationEstimee.equals("negatif"))
				matrix[0][1]++ ;
			else if(annotationReel.equals("positif") && annotationEstimee.equals("neutre"))
				matrix[0][2]++ ; 
			else if(annotationReel.equals("negatif") && annotationEstimee.equals("positif"))
				matrix[1][0]++ ; 
			else if(annotationReel.equals("negatif") && annotationEstimee.equals("negatif"))
				matrix[1][1]++ ; 
			else if(annotationReel.equals("negatif") && annotationEstimee.equals("neutre"))
				matrix[1][2]++ ;
			else if(annotationReel.equals("neutre") && annotationEstimee.equals("positif"))
				matrix[2][0]++ ; 
			else if(annotationReel.equals("neutre") && annotationEstimee.equals("negatif"))
				matrix[2][1]++ ; 
			else if(annotationReel.equals("neutre") && annotationEstimee.equals("neutre"))
				matrix[2][2]++ ;
		}
		
		return matrix ;
		
	}

	
	
	
	



}
