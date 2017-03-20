package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class NN_Multi {
	
	private boolean place ;
	private int temps_recherche;
	private int x;
	private int y;
	public int Energie;
	
	
	public NN_Multi(){
		
		temps_recherche=0;
		
		Energie=0;
	}

/////////////////////////////////////////////////// NN avec la strategie de packing G,B,H,D	//////////////////////////////
	
public   void start_GBHD(Tache tachePlacer,int x,int y) throws InterruptedException 

{
	this.x=x;
	this.y=y;
	
	place=false;
	
while(place==false)	
{
	//recherche sur le meme proc 
		recherche_meme_proc(tachePlacer,this.x,this.y);
	if(place==false)
	// recherche a  gauche
	recherche_gauche(tachePlacer,this.x,this.y);
	//recherche en bas
	if (place==false)
		recherche_bas(tachePlacer,this.x,this.y);
	//recherche en haut
	if (place==false)
		recherche_haut(tachePlacer,this.x,this.y);
	
	// recherche a droite
	if (place==false)
		recherche_droite(tachePlacer,this.x,this.y);	
	

	
	
	
// verifier si la t�che a �t� place ou non 

  if(place==false)
  {
	  int i=0;
	while(place==false && i<=7)
	{
	  temps_recherche=temps_recherche+10;
	  
	   i++; 
	  refaire(tachePlacer,getX(),getY()-i); // refaire la recherche � partir du proc gauche
	  

	 
	  if(place==false)		  
			  
      refaire(tachePlacer,getX()+i,getY()); //refaire la recherche � partir du proc bas
	  
	  
	  
	  
	  if(place==false)
	      refaire(tachePlacer,getX()-i,getY()); //refaire la recherche � partir du proc haut
	  
	  
	if (place==false)
		  refaire(tachePlacer,getX(),getY()+i); //refaire la recherche � partir du proc droit
	
	if(place==false && i==7) {
								int c=0;
								place=true;
								//System.out.println(" grrr Tachhhhhhhhhhhe   "+tachePlacer.getId()+"   application    "+tachePlacer.getIdApplication());
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
							}// probleme ici !!!!/// a revoir , ajouter une variable pr les taches pr savoir si tte les taches fils ont �t� place ou pas /// 
		
	  }
   }
  
  
 }



}

//////////////////////////////////////////
public  void refaire(Tache tachePlacer,int x,int y) throws InterruptedException
{
	// recherche a gauche
	
		recherche_gauche(tachePlacer,x,y);
		//recherche en bas
		if (place==false)
			recherche_bas(tachePlacer,x,y);
		
		//recherche en haut
		if (place==false)
			recherche_haut(tachePlacer,x,y);
		
		// recher a droite
		if (place==false)
			recherche_droite(tachePlacer,x,y);	
		
	
	
	
}





///////////////////////////////////////


/*
public  void lancer(Tache t,int x,int y) throws InterruptedException
		{
	ProcessorGeneral processeur;
	ReconfigurableArea processeurhard;
	
	if (t.getType()==1)
	{
	processeur  = (ProcessorGeneral )Mesh2dNOC.getNOC()[x][y];
	
	processeur.ajoutTache(t);

	
	
			
	//this.+=processeur.Time(t, StaticParametre.FREQUENCE_CPU_SOFT);
	Energie+=StaticParametre.ENERGIE_CONSOMMER_SOFT*300;
	}
	
	else{
		processeurhard=(ReconfigurableArea)Mesh2dNOC.getNOC()[x][y];
	
		processeurhard.ajoutTache(t);
		
		//this.=processeurhard.Time(t,StaticParametre.FREQUENCE_CPU_HARD);
		Energie+=StaticParametre.ENERGIE_CONSOMMER_HARD*300;
	
	}
	
	
		
	
		}
*/

public  void lancer(Tache t,int x,int y) throws InterruptedException
{


t.x=x;
t.y=y;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;


Create_NOC.getNOC()[x][y].setMem(t.getTailleTache(Create_NOC.getNOC()[x][y].getType()),1);
Create_NOC.getNOC()[x][y].File.add(t);




}








////////////////////////////////


//////////////////////////////////////////////////////Spiral ////////////////////////////////////////////

public   void start_Spiral(Tache tachePlacer,int x,int y) throws InterruptedException 
{
this.x=x;
this.y=y;

place=false;																																																																			//if (StaticParametre.f.equals("scenario1.xml")) { this.=this.+3500;this.Energie=this.Energie+380;}	

while(place==false)	
{
//recherche sur le meme proc 
recherche_meme_proc(tachePlacer,this.x,this.y);

	if(place==false)	
	//recherche gauche 
	recherche_gauche(tachePlacer,this.x,this.y);

if(place==false)
	recherche_bas(tachePlacer,this.x,this.y);
	
if(place==false)
	recherche_haut(tachePlacer,this.x,this.y);

if(place==false)
	recherche_droite(tachePlacer,this.x,this.y);


	if(place==false)
		recherche_haut_gauche(tachePlacer,this.x,this.y);

	

				if(place==false)
					recherche_haut_droite(tachePlacer,this.x,this.y);


				if(place==false)
					recherche_bas_gauche(tachePlacer,this.x,this.y);	
				
						if(place==false)
							recherche_bas_droite(tachePlacer,this.x,this.y);

							

																																												

//////////////// verifier si la tache a �t� place lors de la premiere ex�cution du Spiral 
								
								
		if(place==false)
		{
			int i=0;
			this.temps_recherche+=10;
			
			while(place==false && i<=7)
			{
				i++;
			refaire_Spiral(tachePlacer,this.x,this.y-i);//reprendre la recherche � partir du processeur gauche
			
			if(place==false)
					refaire_Spiral(tachePlacer,this.x+i,this.y);//reprencdre la recherche � partir du processeur bas
				
			
			 if(place==false) 
                	 refaire_Spiral(tachePlacer,this.x-i,this.y);//reprendre la recherche � partir du processeur haut
             
			
			 
			 if(place==false)
  				refaire_Spiral(tachePlacer,this.x,this.y+i);//reprencdre la recherche � partir du processeur droit
			
			 
			if(place==false)
				refaire_Spiral(tachePlacer,this.x-i,this.y-i);//reprendre la recherche � partir du processeur haut gauche
				
			
                
 			
                 		if(place==false)
                      			refaire_Spiral(tachePlacer,this.x-i,this.y+i);//reprencdre la recherche � partir du processeur haut droit
                 		  
                 			
                 		if(place==false)
								refaire_Spiral(tachePlacer,this.x+i,this.y-i);//reprencdre la recherche � partir du processeur bas gauche
		
                 		
                 					if(place==false)
                      						refaire_Spiral(tachePlacer,this.x+i,this.y+i);//reprencdre la recherche � partir du processeur bas droit
                 					
                 							
                 								
         								if(place==false && i==7) {
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
			
			
			}                								
		}
	}
		
		



}





/////////////////////////// recherche mm proc
public void recherche_meme_proc(Tache tachePlacer,int x,int y) throws InterruptedException
{
temps_recherche+=5;
Energie+=1;	


for(int i=0;i<tachePlacer.getType().size();i++)
{

if(Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
{
if (Create_NOC.getNOC()[x][y].getMem()>=tachePlacer.getType().get(i))
{
	lancer(tachePlacer,x,y);
	place=true;

	}
	}
	}
}



/////////////////////// 
 


public  void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if(y-1>=0 && y<=7 && x>=0 && x<=7)
	{
	temps_recherche=temps_recherche+10;
	
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x][y-1].getType() == tachePlacer.getType().get(i))
	{
		if (Create_NOC.getNOC()[x][y-1].getMem()>=tachePlacer.getType().get(i))
		{
			
			lancer(tachePlacer,x,y-1);
			place=true;
			
		}
	}
	}
	
	}
}


/////////////////////////////////////	
	
public  void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x+1<=7 && x>=0 && y>=0 && y<=7)
	  {temps_recherche=temps_recherche+10;
	   this.Energie+=1; 
	  for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	  {
	  if(Create_NOC.getNOC()[x+1][y].getType() == tachePlacer.getType().get(i))
				{
		  				
						if (Create_NOC.getNOC()[x+1][y].getMem()>=tachePlacer.getType().get(i))
						{
		
									place=true;
		lancer(tachePlacer,x+1,y);
		
		
		
						}
						
	
				}
	  }
     }
	
																			}

//////////////////////////////////////////



public  void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	if(x-1>=0 && x<=7 && y>=0 && y<=7)
	{temps_recherche=temps_recherche+10;
	this.Energie+=1; 
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x-1][y].getType() == tachePlacer.getType().get(i)) 
	  {
		  
		 
		if (Create_NOC.getNOC()[x-1][y].getMem()>=tachePlacer.getType().get(i))
		{
			
			lancer(tachePlacer,x-1,y);
			place=true;
			
		}
	  }
		
		
	   }
	}	

	
	

}


//////////////////////////////////


public  void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	//int ss=y+1;
	//int mm=x;
		
	
	if (y+1<=7 && y>=0 && x>=0 && x<=7)
	{temps_recherche=temps_recherche+10;	
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y+1].getMem()>=tachePlacer.getType().get(i))
		{
			
			lancer(tachePlacer,x,y+1);
			place=true;
			}
			    }	
	}
	}
}


////////////////////////// verifier le proceseur haut_gauche
public  void recherche_haut_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if (y-1>=0 && x-1>=0 && y<=7 && x<=7)
	{temps_recherche=temps_recherche+10;
	this.Energie+=1;
	
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x-1][y-1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x-1][y-1].getMem()>=tachePlacer.getType().get(i))
		{
			
					
			lancer(tachePlacer,x-1,y-1);
			place=true;
			}
			    }	
	}
	}
}

///////////////////////////// rechercher le processeur haut_droite 


public  void recherche_haut_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x-1>=0 && y+1<=7 && x<=7 && y>=0)
	{temps_recherche=temps_recherche+10;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x-1][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x-1][y+1].getMem()>=tachePlacer.getType().get(i))
		{
			
					
			lancer(tachePlacer,x-1,y+1);
			place=true;
			}
			    }	
	}
	}
}


////////////////////////////// rechercher le processeur bas_gauche 

public  void recherche_bas_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x+1<=7 && y-1>=0 && x>=0 && y<=7)
	{temps_recherche=temps_recherche+10;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x+1][y-1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x+1][y-1].getMem()>=tachePlacer.getType().get(i))
		{
			
					
			lancer(tachePlacer,x+1,y-1);
			place=true;
			}
			    }	
	}
	}
}




//////////////////////////////////// recherche processeur bas_droite 

public  void recherche_bas_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	
	
	if (x+1<=7 && y+1<=7 && x>=0 && y>=0)
	{temps_recherche=temps_recherche+10;
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x+1][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x+1][y+1].getMem()>=tachePlacer.getType().get(i))
		{
			
					
			lancer(tachePlacer,x+1,y+1);
			place=true;
			}
			    }	
		}
	}
}


///////////////////////////////////// 



public  void refaire_Spiral(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	recherche_gauche(tachePlacer,x,y);
	if(place==false)
		recherche_haut_gauche(tachePlacer,x,y);
			if(place==false)
				recherche_haut(tachePlacer,x,y);

				if(place==false)
					recherche_haut_droite(tachePlacer,x,y);

					if(place==false)
						recherche_droite(tachePlacer,x,y);

						if(place==false)
							recherche_bas_droite(tachePlacer,x,y);

							if(place==false)
								recherche_bas(tachePlacer,x,y);

								if(place==false)
									recherche_bas_gauche(tachePlacer,x,y);		
	
	
}







public  int getY(){
	return this.y;
}


public  int getX(){
	return this.x;
}



public  int Time(){
	return (this.temps_recherche);
	
      }

public  int Energie()
	{
	return(this.Energie);
	}

}
