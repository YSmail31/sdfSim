package Proc.Manager;

import Application.Tache;
import Routage_Algorithme.Methode_Routage;
import Simulation.StaticParametre;

public class routage {
	Tache tache_fils,tache_pere;
	
  public routage(int Tnow)
  
  
  {
		  for(int j=0;j<StaticParametre.application_en_cours.length;j++)// parcour la list des tache qui nécissent la communication
		 	{
		 		int c;
		 				for(int i=0;i<StaticParametre.application_en_cours[j].getListTache().size();i++)
		 				{
		 					tache_pere=StaticParametre.application_en_cours[j].getListTache().get(i);
		 					
		 					for(int k=0;k<tache_pere.getSucc().size();k++)
		 					{
	 			 				
	 			 				
	 			 				c=tache_pere.getSucc().get(k);
	 			 				tache_fils=StaticParametre.application_en_cours[j].getListTache().get(c);	
		 					
		 					{
		 						if(tache_fils.debut_execution == Tnow)
		 						{// lancer le routage entre le pere et le fils
		 							
		 										
		 							
		 							new Methode_Routage(tache_pere,tache_fils.x,tache_fils.y,k);
		 							
		 							
		 						}
		 						
		 					}
		  
		 				}
		 	}
	  }
  }
}
