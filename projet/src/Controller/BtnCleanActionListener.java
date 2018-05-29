package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Model;

public class BtnCleanActionListener implements ActionListener{
	
	private Model model ; 
	
	public BtnCleanActionListener(Model mdl){
		this.model = mdl ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.model.createBase("cleaned.csv");
		String[] split;
		String chaine = "" ; 
		String fichier ="tweetter.csv";
		
		//lecture du fichier texte	
		try(InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				FileWriter fw = new FileWriter("cleaned.csv",true);
			    PrintWriter out = new PrintWriter(fw))
			    
		{
			
			String ligne;
			
			br.readLine();
			while ((ligne=br.readLine())!=null){
				
				
				split = ligne.split(",") ;
				split[2] = this.model.cleanTweet(split[2]); 
				//System.out.println(ligne);
				
				
				chaine = split[0] ; 
				
				for(int i=1 ; i<split.length; i++){
					
					chaine = chaine + "," + split[i] ; 
				}
				
				
				out.println(chaine);
				
			}
			br.close(); 
			
		}		
		catch (Exception e1){
			System.out.println(e1.toString());
		}

	}

}
