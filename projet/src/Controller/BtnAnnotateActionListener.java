package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Dico_Model;
import model.Model;

public class BtnAnnotateActionListener implements ActionListener {
	
	private Dico_Model model ;

	public BtnAnnotateActionListener(){
		this.model = new Dico_Model() ; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.model.createBase("annotated.csv");
		String[] split;
		String chaine = "" ; 
		String fichier ="cleaned.csv";
		
		//lecture du fichier texte	
		try(InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				FileWriter fw = new FileWriter("annotated.csv",true);
			    PrintWriter out = new PrintWriter(fw))
			    
		{
			
			String ligne = br.readLine();
			
			
			
			while ((ligne=br.readLine())!=null){
				
				
				split = ligne.split(",") ;
				System.out.println(this.model.getAnnotation(split[2]));
				split[4] = this.model.getAnnotation(split[2]);
				
				
				chaine = split[0] ; 
				
				for(int i=1 ; i<split.length; i++)
					chaine = chaine + " , " + split[i] ;  
				
				out.println(chaine);
				
			}
			br.close(); 
			
		}		
		catch (Exception e1){
			System.out.println(e1.toString());
		}

	}

}
