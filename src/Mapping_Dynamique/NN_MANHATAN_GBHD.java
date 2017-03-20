package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class NN_MANHATAN_GBHD {
	
	// variable qui d�termine si la t�che a �t� place
	public boolean place ;
	// temps de l'execution de l'algorithme
	public int temps_recherche;
	// x,y position du processeur sur lequel la t�che pere est place ( processeur courant)
	public int x;
	public int y,Saut;
	
	// Energie consomm�e lors de la recherche
	public int Energie;
	
	
	public NN_MANHATAN_GBHD(){
		
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
place=false;																																																																			//if (StaticParametre.f.equals("scenario1.xml")) { this.temps_execution=this.temps_execution+3500;this.Energie=this.Energie+380;}	

while(place==false &&  Saut<=7)	
{
//recherche gauche 
	if(place==false)	
recherche_gauche(tachePlacer,this.x,this.y-Saut);


if(place==false)
	recherche_bas(tachePlacer,this.x+Saut,this.y);



if(place==false)
	recherche_haut(tachePlacer,this.x-Saut,this.y);


if(place==false)
	recherche_droite(tachePlacer,this.x,this.y+Saut);


for(int i=1;i<Saut;i++)
	{
	
		if(place==false)
	recherche_bas_gauche(tachePlacer,this.x+i,this.y-(Saut-i));
	}
	

	


for(int i=1;i<Saut;i++)
	{
	if(place==false)
	recherche_bas_droite(tachePlacer,this.x+(Saut-i),this.y+i);
	}





for(int i=1;i<Saut;i++)
	{
	if(place==false)
	recherche_haut_droite(tachePlacer,this.x-i,this.y+(Saut-i));
	}



for(int i=1;i<Saut;i++)
{
	
	if(place==false)
		recherche_haut_gauche(tachePlacer,this.x-(Saut-i),this.y-i);
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
 
public void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
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
	
public void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x<=7 && x>=0 && y>=0 && y<=7)
	  {temps_recherche=temps_recherche+50;
	  this.Energie+=1; 
	  for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	  {
	  if(Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
				{
		  				
						if (Create_NOC.getNOC()[x][y].getFree()==true)
						{
								place=true;
		lancer(tachePlacer,x,y);
		
		
		
						}
						
	
				}
	  }
     }
	
																			}

//////////////////////////////////////////



public void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	if(x>=0 && x<=7 && y>=0 && y<=7)
	{temps_recherche=temps_recherche+50;
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


//////////////////////////////////


public void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
		
	
	if (y<=7 && y>=0 && x>=0 && x<=7)
	{temps_recherche=temps_recherche+50;	
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
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


////////////////////////// verifier le proceseur haut_gauche
public  void recherche_haut_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if (y>=0 && x>=0 && y<=7 && x<=7)
	{temps_recherche=temps_recherche+50;
	this.Energie+=1;
	
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
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

///////////////////////////// rechercher le processeur haut_droite 


public  void recherche_haut_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x>=0 && y<=7 && x<=7 && y>=0)
	{temps_recherche=temps_recherche+50;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{
			
			//System.out.println("haut_droite !!! ");			
			lancer(tachePlacer,x,y);
			place=true;
			}
			    }	
	}
	}
}


////////////////////////////// rechercher le processeur bas_gauche 

public  void recherche_bas_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x<=7 && y>=0 && x>=0 && y<=7)
	{temps_recherche=temps_recherche+50;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{
			//	System.out.println("bas_gauche !!! ");	
			lancer(tachePlacer,x,y);
			place=true;
			}
			    }	
	}
	}
}




//////////////////////////////////// recherche processeur bas_droite 

public  void recherche_bas_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	
	
	if (x<=7 && y<=7 && x>=0 && y>=0)
	{temps_recherche=temps_recherche+50;
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{
			//System.out.println("bas_droite !!! ");	
			lancer(tachePlacer,x,y);
			place=true;
			}
			    }	
		}
	}
}


///////////////////////////////////// 





public  int getY(){
	return this.y;
}


public  int getX(){
	return this.x;
}




public  int Energie()
	{
	return(this.Energie);
	}

}
