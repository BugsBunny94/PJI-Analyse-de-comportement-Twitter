package model;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import views.Tweet;

public class Model extends Observable {

	
	
	Twitter twitter ; 
	QueryResult result ; 
	

	

	private String annotationMem = "" ;
	private ArrayList<Tweet> twlist = new ArrayList<Tweet>();
	private ArrayList<String> tweetsInbase ; 
	
	public Model(){
		this.tweetsInbase = this.getTweetsInBase("cleaned.csv") ;
	}
	
	public ArrayList<String> getAllTweets(){return this.tweetsInbase ;}
	
	public void setTwList(ArrayList<Tweet> lst){ this.twlist = lst ; }
	public ArrayList<Tweet> getTwList(){return this.twlist ; }

	
	
	public void setAnnotationMem(String value){ this.annotationMem = value ;}
	
	public String getAnnotationMem(){ return this.annotationMem ; }
	
	public void run(String keyword) throws TwitterException{
		this.result = this.searchTweets(keyword);
		this.updateObservers();
		System.out.println("run a fini avec sucess ............");
		
	}
	
	
	public void updateObservers(){
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public QueryResult getResult(){
		return this.result;
	}
	
	
	public QueryResult searchTweets(String keyword) throws TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("nCzD0oVv3ijqVUEdLgMeo5wCd")
		  .setOAuthConsumerSecret("ZKOFdCy5S0vzy70ZEtbIG7INh10LpWvTXdF5KxSorXbyrpPFoA")
		  .setOAuthAccessToken("780664418220716032-yRHsiql88Pl58pcm82bJvY2wZHSG540")
		  .setOAuthAccessTokenSecret("ffc6pURNTLDl9ePdEIvv279EY5Bi2gLQw6mdJeo4B9gtR");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		
	   
	    Query query = new Query(keyword);
	    return twitter.search(query);
	    
	}
	
	
	
	
	public String statusToString(Status status, String annotation){
		
		String s = status.getText().replace('\n', ' ') ; 
		
		String res = status.getId() + "," + status.getUser().getScreenName() + "," + s + "," + status.getCreatedAt() + "," + annotation ; 
		
		return res ; 
		
	} 
	
	
	
	
	public File createBase(String s){
		
	      File f =new File(s) ;
	      
	      try{
	         
	         if(f.createNewFile()){
	        	 FileWriter fw = new FileWriter(f.getAbsoluteFile());
		         BufferedWriter bw = new BufferedWriter(fw);
		         bw.write("id,user,tweet,createdAt,annotation" + "\n");
		         bw.close();
		         return f ; 
	         }
	         else{
	        	 System.out.println("le fichier existe deja !!") ;
	         }
	         
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	    
	     return  f ;
	}
	
	
	
	public void addToBase(FileWriter f , Status status , String annotation) throws IOException {
		try(
			    BufferedWriter bw = new BufferedWriter(f);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(statusToString(status,annotation));
			  
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
		
	}
	
    
    public boolean exists(Status status){
    	
		String chaine="";
		String fichier ="tweetter.csv";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			br.readLine();
			while ((ligne=br.readLine())!=null){
				if(Long.parseLong(ligne.split(",")[0].substring(0,ligne.split(",")[0].length()-1)) == status.getId()){
					return true ; 
				}
			}
			br.close(); 
			if(ligne == null){
				return false ; 
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
    	return false;
    	
    }
    
    
	public String cleanTweet(String tweet) {
		
		
		// Pattern - Matcher
		Pattern pattern = Pattern.compile("([@#\"\r\n]|RT |https?:[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)*");
		Matcher matcher = pattern.matcher(tweet);

		String cleanedHalf = matcher.replaceAll(""); 

		Pattern patternPonctu = Pattern.compile("\\p{Punct}");
		Matcher matcherPonctu = patternPonctu.matcher(cleanedHalf);

		String cleaned = matcherPonctu.replaceAll("");
		return cleaned ;
		
	}
	
	
	
	
	public String getRidOfSpaces(String s){
		
		String[] str = s.split(" ");
		String res = str[0] ; 
		for(int i=1;i<str.length;i++){
			if(!str[i].equals(""))
				res = res +" "+ str[i] ; 
		}
		
		return res;
		
	}
	
	
	public ArrayList<String> getTweetsInBase(String base){
		
		ArrayList<String> list = new ArrayList<String>();
		
		try{
			InputStream ips=new FileInputStream(base); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			br.readLine();
			while ((ligne=br.readLine())!=null){ 
				list.add(ligne);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
    	
		return list ; 
	}
	
	
	
	public int countNbTweets(String base){
		
		int cpt = 0; 
		
		try{
			InputStream ips=new FileInputStream(base); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			br.readLine();
			while ((ligne=br.readLine())!=null){ 
				cpt++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return cpt ; 
	}
    
    

}
