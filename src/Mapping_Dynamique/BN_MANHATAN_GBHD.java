package Mapping_Dynamique;

import java.util.ArrayList;
import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class BN_MANHATAN_GBHD {
	
	// variable qui d�termine si la t�che a �t� place
	public boolean place ;
	// temps de l'execution de l'algorithme
	public int temps_recherche;
	// x,y position du processeur sur lequel la t�che pere est place ( processeur courant)
	public int x;
	public int y,Saut;
	private int c=-1;
	// Energie consomm�e lors de la recherche
	public int Energie;
	public  ArrayList<Integer> Charge= new ArrayList<Integer>(); // list qui contien la charge des processeur qui peuvent executer une tache
	public  ArrayList<Integer> x1= new ArrayList<Integer>(); // list qui contien la position x des processeur qui peuvent executer une tache
	public  ArrayList<Integer> y1= new ArrayList<Integer>();// list qui contien la position y des processeur qui peuvent executer une tache
	
	
	
	public BN_MANHATAN_GBHD(){
		
		temps_recherche=0;
		Energie=0;
		
	}





///////////////////////////////////////
///////////////////////////


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
recherche_gauche(tachePlacer,this.x,this.y-Saut);

recherche_bas(tachePlacer,this.x+Saut,this.y);

recherche_haut(tachePlacer,this.x-Saut,this.y);

recherche_droite(tachePlacer,this.x,this.y+Saut);


for(int i=1;i<Saut;i++)
recherche_bas_gauche(tachePlacer,this.x+i,this.y-(Saut-i));




for(int i=1;i<Saut;i++)
recherche_bas_droite(tachePlacer,this.x+(Saut-i),this.y+i);




for(int i=1;i<Saut;i++)
	recherche_haut_droite(tachePlacer,this.x-i,this.y+(Saut-i));



for(int i=1;i<Saut;i++)

		recherche_haut_gauche(tachePlacer,this.x-(Saut-i),this.y-i);
	














if(place==true)
{ int min=Integer.MAX_VALUE;
for(int i=0;i<Charge.size();i++)
	{

	if(Charge.get(i) < min) {min=Charge.get(i);c=i;}
	}

lancer(tachePlacer,c,this.x,this.y);



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
			

			Charge.add(GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(),Create_NOC.getNOC()[x][y+1].getId()));
			x1.add(x);
			y1.add(y);
			
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
							
							Charge.add(GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x][y].getId()));
							x1.add(x);
							y1.add(y);
								place=true;
		
		
		
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
			
			Charge.add(GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId()));
			x1.add(x);
			y1.add(y);
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
			Charge.add(GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(), Create_NOC.getNOC()[x][y].getId()));
			x1.add(x);
			y1.add(y);
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
			 int min1=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x+1][y].getId(),Create_NOC.getNOC()[x+1][y+1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(),Create_NOC.getNOC()[x+1][y].getId());// lien gauche puis gauche_haut
             int min2=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y+1].getId(), Create_NOC.getNOC()[x+1][y+1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(),Create_NOC.getNOC()[x][y+1].getId());// lien haut puis haut_gauche
	
             if(min1<=min2) Charge.add(min1);
             else Charge.add(min2);
			
			
			
			x1.add(x);
			y1.add(y);
			place=true;
			}
			    }	
	}
	}
}

///////////////////////////// rechercher le processeur haut_droite 


public  void recherche_haut_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x>=0 && y<=7 && x<=7 && y>=0)
	{
		temps_recherche=temps_recherche+50;
        Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{
			int min1=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x+1][y-1].getId(),Create_NOC.getNOC()[x+1][y].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(),Create_NOC.getNOC()[x+1][y].getId());// lien droite puis haut 
            int min2=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(), Create_NOC.getNOC()[x+1][y-1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId());// lien haut puis haut_droite
	

            if(min1<=min2) Charge.add(min1);
            else Charge.add(min2);
			x1.add(x);
			y1.add(y);
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

			int min1=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(),Create_NOC.getNOC()[x-1][y+1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(),Create_NOC.getNOC()[x][y].getId());// lien gauche puis bas
            int min2=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y+1].getId(), Create_NOC.getNOC()[x][y+1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(),Create_NOC.getNOC()[x][y+1].getId());// lien bas puis gauche
	

            if(min1<=min2) Charge.add(min1);
            else Charge.add(min2);	
			
			x1.add(x);
			y1.add(y);
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
			int min1=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y-1].getId(),Create_NOC.getNOC()[x-1][y].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(),Create_NOC.getNOC()[x][y].getId());// lien droite puis bas 
            int min2=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y-1].getId(), Create_NOC.getNOC()[x][y-1].getId()) + GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId());// lien haut puis bas_droite
		
            if(min1<=min2) Charge.add(min1);
            else Charge.add(min2);	
            
			x1.add(x);
			y1.add(y);
			place=true;
			}
			    }	
		}
	}
}


///////////////////////////////////// 


public  void lancer(Tache t,int K,int x,int y) throws InterruptedException
{


	
	
	
	
	
t.x=x1.get(K);
t.y=y1.get(K);

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;

Simulator.set_temps_recherche(this.temps_recherche);

Create_NOC.getNOC()[t.x][t.y].set_state(false);

StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie);//ajouter l energie consomm� lors de la recherche � l energie total consomm� par l application

}






public  int Energie()
	{
	return(this.Energie);
	}

}
