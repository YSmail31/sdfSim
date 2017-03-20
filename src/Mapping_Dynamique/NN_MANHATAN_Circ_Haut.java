package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class NN_MANHATAN_Circ_Haut {
	
	// variable qui d�termine si la t�che a �t� place
	public boolean place ;
	// temps de l'execution de l'algorithme
	public int temps_recherche;
	// x,y position du processeur sur lequel la t�che pere est place ( processeur courant)
	public int x;
	public int y,Saut;
	
	// Energie consomm�e lors de la recherche
	public int Energie;
	
	
	public NN_MANHATAN_Circ_Haut(){
		
		temps_recherche=0;
		Energie=0;
		
	}





///////////////////////////////////////



public  void lancer(Tache t,int x,int y) throws InterruptedException
{


t.x=x;
t.y=y;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;

Simulator.set_temps_recherche(this.temps_recherche);

Create_NOC.getNOC()[x][y].set_state(false);
StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie);//ajouter l energie consomm� lors de la recherche � l energie total consomm� par l application





}








////////////////////////////////


//////////////////////////////////////////////////////Spiral ////////////////////////////////////////////

public   void start_MANHATAN(Tache tachePlacer,int x,int y) throws InterruptedException 
{
this.x=x;
this.y=y;
Saut=1;
place=false;															

while(place==false &&  Saut<=7)	
{
//recherche gauche 
recherche_proc_libre(tachePlacer,this.x,this.y-Saut);

for(int i=1;i<Saut;i++)
{
	
		if(place==false)
		recherche_proc_libre(tachePlacer,this.x-(Saut-i),this.y-i); // recherche Haut_Gauche
}	



if(place==false)
	recherche_proc_libre(tachePlacer,this.x-Saut,this.y);// Recherche Haut




for(int i=1;i<Saut;i++)
	{
	if(place==false)
	recherche_proc_libre(tachePlacer,this.x-i,this.y+(Saut-i));// Recherche Haut_droite
	}




if(place==false)
	recherche_proc_libre(tachePlacer,this.x,this.y+Saut); // Recherche droite


for(int i=1;i<Saut;i++)
{
	if(place==false)
recherche_proc_libre(tachePlacer,this.x+(Saut-i),this.y+i); // Recherche bas droite
}


if(place==false)
	recherche_proc_libre(tachePlacer,this.x+Saut,this.y); // Recherche Bas

for(int i=1;i<Saut;i++)
{
	if(place==false)
recherche_proc_libre(tachePlacer,this.x+i,this.y-(Saut-i)); // Recherche Bas Gauche
}









		
				

	if(place==false && Saut==7) {
						int c=0;
						place=true;
						
						if(StaticParametre.not_mapped.isEmpty())
						StaticParametre.not_mapped.add(tachePlacer);
									else
											{
												for(int k=0;k<StaticParametre.not_mapped.size();k++)
												{
													if(StaticParametre.not_mapped.get(k).equals(tachePlacer))
														{c=1;break;}
												}
											if(c!=1)	StaticParametre.not_mapped.add(tachePlacer);
												
											}
									}
							

																																												


	Saut++;
	}
		
		


}





 
/////////////////////// 
 
public void recherche_proc_libre(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if(y>=0 && y<=7 && x>=0 && x<=7)
	{
	temps_recherche=temps_recherche+50;
	
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	{
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{
			lancer(tachePlacer,x,y);
			place=true;
			
		}
	}
	}
	
	}
}


/////////////////////////////////////	



public  int Energie()
	{
	return(this.Energie);
	}

}
