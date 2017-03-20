package Mapping_Dynamique;


import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class BN_GBHD {
	
	private boolean place ;
	private int temps_recherche,Energie;
	private int x;
	private int y;
	public int temps_execution;
	private int [] charge= new  int[4]; 
	private int c=-1;
	

	
	
	
	public BN_GBHD(){
		
		temps_recherche=0;
		temps_execution=0;
		Energie=0;
	}

	/////////////////////////////////////////////////////////////////
public void start(Tache tachePlacer,int x,int y) throws InterruptedException
	{
	this.x=x;
	this.y=y;
	
	
	for(int i=0;i<=3;i++) this.charge[i]=Integer.MAX_VALUE;
	
	place=false;
while(place==false)
{
	// recherche � gauche 
	recherche_gauche(tachePlacer,this.x,this.y);
	//recherche en bas
	recherche_bas(tachePlacer,this.x,this.y);
	//recherche en haut
	recherche_haut(tachePlacer,this.x,this.y);
	// recherche a droite
	recherche_droite(tachePlacer,this.x,this.y);

	
	
	if(place==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=3;i++)
			{
		
			if(this.charge[i] < min) {min=this.charge[i];c=i;}
			}
		
		lancer(tachePlacer,c,this.x,this.y);
		
		
		
		}
	else {
       int i=0;
		while (place==false && i<=7)

		{
			i++;


		  	temps_recherche=temps_recherche+50;
		  	
		  refaire(tachePlacer,x,y-i); // refaire la recherche � partir du proc gauche
		  

		 
		  if(place==false)		  
				  
	      refaire(tachePlacer,x+i,y); //refaire la recherche � partir du proc bas
		  
		  
		  
		  
		  if(place==false)
		      refaire(tachePlacer,x-i,y); //refaire la recherche � partir du proc haut
		  
		  
		if (place==false)
		
			  refaire(tachePlacer,x,y+i); //refaire la recherche � partir du proc droit
			

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


/////////////////////// 

public  void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{


if(y-1>=0 && y<=7 && x>=0 && x<=7)
	{
	  this.temps_recherche=this.temps_recherche+50;
	  Energie+=1;
		  for(int i=0;i<tachePlacer.getType().size();i++)
		  {
		  if(Create_NOC.getNOC()[x][y-1].getType() == tachePlacer.getType().get(i))
			{
				if (Create_NOC.getNOC()[x][y-1].getFree()==true)
				{
					
					this.charge[0]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId());
	                place=true;
	
				}
				//else System.out.println("youupi gauche "+Mesh2dNOC.getNOC()[x][y-1].getType());
	
			}
		  }
	}
}

/////////////////////////////////////	
	
	
public  void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x+1<=7 && x>=0 && y>=0 && y<=7)
	  {
		this.temps_recherche=this.temps_recherche+50;
		 Energie+=1;
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		  {
		 
	        if(Create_NOC.getNOC()[x+1][y].getType() == tachePlacer.getType().get(i))
				{
						if (Create_NOC.getNOC()[x+1][y].getFree()==true)
						{
							 			               
							this.charge[1]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId());
							place=true;
		
						}
	
				}
		  }
     }
	
																			}

//////////////////////////////////////////	
	
	

public  void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	
	if(x-1>=0 && x<=7 && y>=0 && y<=7)
	{
		this.temps_recherche=this.temps_recherche+50;
		 Energie+=1;
		 
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	 		{
			 if(Create_NOC.getNOC()[x-1][y].getType() == tachePlacer.getType().get(i)) 
			  {
				  
				 
				if (Create_NOC.getNOC()[x-1][y].getFree()==true)
				{
				
					
		            this.charge[2]= GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x][y].getId());
		    		place=true;
					
				}
				
			  }
		
	   }
	}	

	
	

}


//////////////////////////////////
   	
   
public  void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
		
	
	if (y+1<=7 && y>=0 && x>=0 && x<=7)
	{	this.temps_recherche=this.temps_recherche+50;
	 Energie+=1;
	 
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		 {
			if (Create_NOC.getNOC()[x][y+1].getType() == tachePlacer.getType().get(i))
		    {
			if (Create_NOC.getNOC()[x][y+1].getFree()==true)
			{
				
	            this.charge[3]= GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x][y+1].getId());
				
	            place=true;
				}
		
				    }	
		 }
	}
	
}


////////////////////////////////////////////

public  void refaire(Tache tachePlacer,int x,int y) throws InterruptedException
{

		// recherche a gauche
		recherche_gauche(tachePlacer,x,y);
		//recherche en bas
		recherche_bas(tachePlacer,x,y);
		
		//recherche en haut
		recherche_haut(tachePlacer,x,y);
		
		// recher a droite
		recherche_droite(tachePlacer,x,y);	
		
		//temps_recherche=temps_recherche+40;
		
		if(place==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=3;i++)
			{            
			
			if(this.charge[i] < min) {min=this.charge[i];c=i;}
			
			}
		
      
		 lancer(tachePlacer,c,x,y);
		 
		
		
		
		}
	
	
	
}
//////////////////////////


////////////////////////////////////////

public  void lancer(Tache t,int K,int x,int y) throws InterruptedException
{

int x1=-1,y1=-1;
	
	switch (K)
	{ 
	case 0 : 	{x1=x;y1=y-1;}break;
	case 1 :	{x1=x+1;y1=y;}break;
	case 2 :	{x1=x-1;y1=y;}break;
	case 3 :	{x1=x;y1=y+1;}break;
	
	}
	
	
	
	
	
t.x=x1;
t.y=y1;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;


Simulator.set_temps_recherche(this.temps_recherche);

Create_NOC.getNOC()[x1][y1].set_state(false);

StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie);//ajouter l energie consomm� lors de la recherche � l energie total consomm� par l application

}






}
