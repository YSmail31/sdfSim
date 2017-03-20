package Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Architecture.Create_NOC;



public class OutputFileReader {
	//static File file=Simulator.file ;

	//static FileWriter fw=Simulator.fw;
	
	public OutputFileReader() throws IOException{

	}

public static void generateOutputFile(int Time,int Energie, FileWriter fw){

		
		


		try {
			//Création de l'objet
			
			String str = "*****************************************************************\r\n";
			str+= "Resultat de la simulation \r\n";
			str+= "Algorithme de placement utiliser "+StaticParametre.ALGORITHME_PLACEMENT+"\r\n";
			str+= "Stratégie de recherche "+StaticParametre.Strategie_De_Recherche+"\r\n";
			str+= "*****************************************************************\r\n";
			
			
			
			str+= "\r\n";
			
			for(int j=0;j<StaticParametre.listApplication.size();j++)
		 	{
		 		// Affichage du résultat du placement, temps d'execution ainsi que la consommation d'énergie de chaque tache
				
		 				for(int i=0;i<StaticParametre.getListApplication().get(j).getListTache().size();i++)
		 				{
		 				int x=StaticParametre.getListApplication().get(j).getListTache().get(i).x;
		 				int y=StaticParametre.getListApplication().get(j).getListTache().get(i).y;
		 				str+=("x   "+x+"  y  "+y+"  fin execution  "+StaticParametre.getListApplication().get(j).getListTache().get(i).fin_execution+ "   Debut execution    "+StaticParametre.getListApplication().get(j).getListTache().get(i).debut_execution+"  id tache  "+i+ " id application  "+j+"\r\n"  );
		 					
		 					
		 			}
		 	}
			
			
			
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				
					str+=("etat du proc "+Create_NOC.getNOC()[i][j].getFree()+"   son type  "+Create_NOC.getNOC()[i][j].getType()+"  x   "+i+"    y   "+j+" File memoire   "+Create_NOC.getNOC()[i][j].getMem()+"\r\n");
					
			}
			str+=" TIME:	"+Time+"\r\n";
			str+=" ENERGIE:	"+Energie+"\r\n";
			
			str+= "\r\n\r\n\r\n";
			//On écrit la chaîne
			
			fw.write(str);
			//On ferme le flux
//			fw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
