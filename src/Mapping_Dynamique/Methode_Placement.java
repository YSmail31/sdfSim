package Mapping_Dynamique;


import Application.Tache;
import Architecture.ProcessorElement;
import Simulation.StaticParametre;

/**
 * 
 * 
 * Cette classe lance la méthode de placement dynamique cité dans le fichier Input
 * 
 * 
 * 
 */

public class Methode_Placement{

	private Tache tache;
	private ProcessorElement procCourant;
	Tache tache_fils;
	private NN near=new NN();
	private NN_MANHATAN_GBHD MANHATAN1=new NN_MANHATAN_GBHD();
	private NN_MANHATAN_Circ MANHATAN_Circ=new NN_MANHATAN_Circ();
	private BN_MANHATAN_Circ BN_MANHATAN_Circ=new BN_MANHATAN_Circ();
	private NN_MANHATAN_Circ_Haut MANHATAN_Circ_HAUT=new NN_MANHATAN_Circ_Haut();
	private BN_MANHATAN_GBHD BN_MANHATAN_GBHD=new BN_MANHATAN_GBHD();
	private BN_MANHATAN_Circ_Haut BN_MANHATAN_Circ_HAUT=new BN_MANHATAN_Circ_Haut();
	private NN_Multi near_Multi=new NN_Multi();
//	private BN_Spiral Best_Spiral=new BN_Spiral();
	private BN_GBHD Best_GBHD=new BN_GBHD();
	private BN_GBHD_Multi Best_GBHD_Multi=new BN_GBHD_Multi();
	private BN_Spiral_Multi Best_Spiral_Multi=new BN_Spiral_Multi();
	
	
public Methode_Placement(Tache tache,ProcessorElement procCourant)
		{
	
         this.procCourant=procCourant; // processeur sur lequel la tache pere a été placé
         
         this.tache=tache; // tache à placé 
     
         this.run();
     
			}
	
	public void run(){
	
		
		
	////////////// lancement du placement des taches fils ////////////////	
		if(StaticParametre.MONO_MULTI.equals("MONO")) //Methodes de placement MONO
		{
		
		try {
			
			
			if(StaticParametre.ALGORITHME_PLACEMENT.equals("NN"))
			{
				if (StaticParametre.Strategie_De_Recherche.equals("GBHD")) 
					near.start_GBHD(tache, procCourant.x,procCourant.y);
					
					
				
				else 
				{
					if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_Ciruclaire"))					
						MANHATAN_Circ.start_MANHATAN(tache,procCourant.x,procCourant.y);
					else
					{
						if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_GBHD"))
							MANHATAN1.start_MANHATAN(tache,procCourant.x,procCourant.y);
						else
							MANHATAN_Circ_HAUT.start_MANHATAN(tache,procCourant.x,procCourant.y);
						
					}
					}
				}
			else
			{
				if (StaticParametre.Strategie_De_Recherche.equals("GBHD")) 
					Best_GBHD.start(tache, procCourant.x,procCourant.y);

				else 
				{
					if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_Ciruclaire"))
					//near.start_Spiral(tache,procCourant.x,procCourant.y);
					
						BN_MANHATAN_Circ.start_MANHATAN(tache,procCourant.x,procCourant.y);
					else
					{
						if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_GBHD"))
							BN_MANHATAN_GBHD.start_MANHATAN(tache,procCourant.x,procCourant.y);
						else
							BN_MANHATAN_Circ_HAUT.start_MANHATAN(tache,procCourant.x,procCourant.y);
						
					}
					}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			}
		else		//Methodes de placement Multi
			{
			try {
					
				if(StaticParametre.ALGORITHME_PLACEMENT.equals("NN"))
				{
					if (StaticParametre.Strategie_De_Recherche.equals("GBHD")) 
						near_Multi.start_GBHD(tache, procCourant.x,procCourant.y);
					
					else 
						near_Multi.start_Spiral(tache,procCourant.x,procCourant.y);
				}
				else
				{
					if (StaticParametre.Strategie_De_Recherche.equals("GBHD")) 
						Best_GBHD_Multi.start(tache, procCourant.x,procCourant.y);
						 
					else 
						Best_Spiral_Multi.start(tache, procCourant.x,procCourant.y);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
	
	}
	
	
}
	
	
	



