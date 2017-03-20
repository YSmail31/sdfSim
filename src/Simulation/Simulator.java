package Simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Application.Create_Application;
import Architecture.Create_NOC;
import Architecture.CreationCluster;
import Architecture.GenererChannel;
import Proc.Manager.ProcessorMaitre;

/**
 * c'est a partir de cette classe que la simulation demmare 
 * 
 * @author Nabil
 *
 *
 */
public class Simulator{
	/** le processeur Maitre*/
	public static ProcessorMaitre MAITRE;
	
	public static int Tnow=0,temps_recherche=0,Energy=0;	//Tnow repr�sente le tempc actuel de la simulation
	

	
	/*
	 *  Liste qui contient les temps des prochains evenements
	 * 
	 */
	public static ArrayList<Integer> T_next_event=new ArrayList<Integer>();
	
	public Simulator() throws IOException{

	}
	
	// procedure qui lance le simulateur
	public void start(FileWriter fw ) throws IOException, InterruptedException{
		
/*****    Configuration du systeme (Architecture,Application)                    **************/

			
		
		// lire le fichier des param�tres d'ex�cution
		new InputFile();
		
		// charger les tache d'application (selon le scenario choisit)
		Create_Application.remplirTaches("scenario1_1.xml");
		
		// creation du NOC
		Create_NOC.creationNoc();

		// cr�ation des clusters
		CreationCluster.creation_cluster();
		
		// g�nerer les lien entre les processeurs		
		GenererChannel gen = new GenererChannel();
		gen.start();
		
		// le processeur ma�tre
		MAITRE  = new ProcessorMaitre() ;
		
		
/*************************  debut de la simuation	********************/	

		StaticParametre.end_application=0;
		Tnow=0;
		Energy=0;
		temps_recherche=0;
		
		while(StaticParametre.end_application!=StaticParametre.NOMBRE_APPLICATION )// condition d'arret de la simuation
	
		{
			// Affichage du temps actuel
			System.out.println("Tnow   =  "+Tnow);
			
			// Le parcours des taches qui demandes a etre execut�s
			MAITRE.Mapping(Tnow);
			
			// Le parcours des taches qui n�c�ssitent le placement de communication
			MAITRE.Routage(Tnow);
			
			// Mise � jour de la plateforme
			MAITRE.Mise_A_Jour(Tnow);
	
		////////  R�cup�ration du temps du prochain evenement /////
 			if(!T_next_event.isEmpty())
			{	
			Tnow=T_next_event.get(0);
			T_next_event.remove(0);
			}
	   ///////////
 	
	}


			/************************ Affichage du R�sultat ***********************/		
			
			for(int j=0;j<StaticParametre.listApplication.size();j++)
		 	{
		 		// Affichage du r�sultat du placement, temps d'execution ainsi que la consommation d'�nergie de chaque tache
				
		 				for(int r=0;r<StaticParametre.getListApplication().get(j).getListTache().size();r++)
		 				{
		 				int x=StaticParametre.getListApplication().get(j).getListTache().get(r).x;
		 				int y=StaticParametre.getListApplication().get(j).getListTache().get(r).y;
		 				System.out.println("x   "+x+"  y  "+y+"  fin execution  "+StaticParametre.getListApplication().get(j).getListTache().get(r).fin_execution+ "   Debut execution    "+StaticParametre.getListApplication().get(j).getListTache().get(r).debut_execution+"  id tache  "+r+ " id application  "+j  );
		 					
		 					
		 			}
		 	}
			
			
			
			for(int r=0;r<8;r++)
			{
				for(int j=0;j<8;j++)
				
					System.out.println("etat du proc "+Create_NOC.getNOC()[r][j].getFree()+"   son type  "+Create_NOC.getNOC()[r][j].getType()+"  x   "+r+"    y   "+j+" File memoire   "+Create_NOC.getNOC()[r][j].getMem());
					
			}
			
/***************************************** fin affichage  *************************/

	System.out.println("fin   "+Tnow+"  "+Energy+"    temps recherche "+temps_recherche);
			OutputFileReader.generateOutputFile(Tnow, Energy,fw);

		
		
	
	
	
/************************ Affichage du R�sultat ***********************/		

			
	}

	
////////////////////////////  Proc�dure qui ajoute et trie les temps des evenements par ordre croissant 	
public static void Add_Event(int evt)
	{
		
	if(!T_next_event.isEmpty()) // Si la liste n'est pas vide, placer l'evenement dans la position appropri�e 
	{
	int i;
	 for( i=0;i<T_next_event.size();i++)
	 {
		 if(evt<T_next_event.get(i))
		 { 
		 T_next_event.add(i,evt);
		 break;
		 }
		 
		 else { 
			 if(evt==T_next_event.get(i))
		 	{
			break;
			 
		 	}
		 }
		}
	 
	 if(i==T_next_event.size()) T_next_event.add(evt); //si evt est plus grand que tt les evts qui sont pr�sent dans la liste je l ajoute � la fin
	
	}
	
	
	else // si la liste est vide, ajouter l'evenement dans la lise
		 {
		 T_next_event.add(evt);
		 }
	
	}
////////////////////////////


public static void setEnergy(int energie)
{
	
	Energy+=energie;
}
///////////////

public static void set_temps_recherche(int time)
{
	temps_recherche+=time;
}

///////////////////////////////	
	public static void main(String[] args) throws Throwable
	{

		StaticParametre.List_Algo_Mapping.add("NN");
		//StaticParametre.List_Algo_Mapping.add("BN");
		StaticParametre.List_Strategie.add("GBHD");
		StaticParametre.List_Strategie.add("MANHATAN_Ciruclaire");
		//StaticParametre.List_Strat�gie.add("MANHATAN_GBHD");
		//StaticParametre.List_Strat�gie.add("MANHATAN_Circ_HAUT");
		

		File file=new File("result_scenario1.txt");
		
		FileWriter fw = new FileWriter(file);
	
		for(int i=0;i<StaticParametre.List_Algo_Mapping.size();i++)
		{	
			StaticParametre.ALGORITHME_PLACEMENT=StaticParametre.List_Algo_Mapping.get(i);
		
			for(int b=0;b<StaticParametre.List_Strategie.size();b++)
			{
		    
			StaticParametre.Strategie_De_Recherche =StaticParametre.List_Strategie.get(b);
			System.out.println("Algo    =  "+StaticParametre.ALGORITHME_PLACEMENT+"   Strategie "+StaticParametre.List_Strategie.get(b));
			System.out.println("fin   "+Tnow+"  "+Energy+"    ");
			
			Simulator sim = new Simulator();
			sim.start(fw);
			
			}
		}
		

		fw.close();
	}
	


}
