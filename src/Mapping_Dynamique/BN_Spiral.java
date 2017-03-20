package Mapping_Dynamique;


import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class BN_Spiral {
	
	private boolean place ;
	private int temps_recherche,Energie;
	private int x;
	private int y;
	public int temps_execution;
	private int [] charge= new  int[8]; 
	private int c=-1;

	
	
	
	public BN_Spiral(){
		
		temps_recherche=0;
		temps_execution=0;
		Energie=0;
	}

	/////////////////////////////////////////////////////////////////
public void start(Tache tachePlacer,int x,int y) throws InterruptedException
	{
	this.x=x;
	this.y=y;
	for(int i=0;i<=7;i++) this.charge[i]=Integer.MAX_VALUE;																																												
	
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
		//recherche en haut � gauche
		recherche_haut_gauche(tachePlacer,this.x,this.y);
		// recherche en haut � droite 
		recherche_haut_droite(tachePlacer,this.x,this.y);
		//   recherche en bas � gauche 
		recherche_bas_gauche(tachePlacer,this.x,this.y);
		//recherche en bas � droite
		recherche_bas_droite(tachePlacer,this.x,this.y);
		
		
		
	if(place==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=7;i++)
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
			//temps_recherche=50;
		  temps_recherche=temps_recherche+10;
		 
		  refaire(tachePlacer,x,y-i); // refaire la recherche � partir du proc gauche
		  
		  if(place==false)		  
				refaire(tachePlacer,x+i,y); //refaire la recherche � partir du proc bas
		  
		  	if(place==false)
          		refaire(tachePlacer,x-i,y); //refaire la recherche � partir du proc haut
          
      			if (place==false)
          				refaire(tachePlacer,x,y+i); //refaire la recherche � partir du proc droit
          	
		  	
          		if(place==false)
          			refaire(tachePlacer,x-i,y-i); // refaire la recherche � partir du proc haut gauche
	
          	
		  
          	 
          			if(place==false)
          				refaire(tachePlacer,x-i,y+i); // refaire la recherche � partir du proc haut droit
          	
          	 
          						if(place==false)
          							refaire(tachePlacer,x+i,y-i); // refaire la recherche � partir du proc bas gauche
          				
          							if(place==false)
          								refaire(tachePlacer,x+i,y+i); // refaire la recherche � partir du proc bas droit
          	
          	
          					
		  
		  
          	 
		  
          						
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
    							}
          						
		  		}
	
	}
	
	
}
	}


/////////////////////// 

public  void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{


if(y-1>=0 && y<=7 && x>=0 && x<=7)
	{
	  this.temps_recherche=this.temps_recherche+10;
	  //StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
	  Energie+=1;
		  for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		  {
		  if(Create_NOC.getNOC()[x][y-1].getType() == tachePlacer.getType().get(i))
			{
				if (Create_NOC.getNOC()[x][y-1].getFree()==true)
				{
					
					this.charge[0]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId());
	                place=true;
	
				}
			
			}
		  }
	}
}

/////////////////////////////////////	

	//////////////////////////verifier le proceseur haut_gauche
public  void recherche_haut_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{

	if (y-1>=0 && x-1>=0 && y<=7 && x<=7)
	{
		temps_recherche=temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
		Energie+=1;	
		
		for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		{
		if (Create_NOC.getNOC()[x-1][y-1].getType() == tachePlacer.getType().get(i))
				{
					if (Create_NOC.getNOC()[x-1][y-1].getFree()==true)
						{
						
                           int min1=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y-1].getId(),Create_NOC.getNOC()[x][y-1].getId());// lien gauche puis gauche_haut
                           int min2=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y-1].getId(),Create_NOC.getNOC()[x-1][y].getId());// lien haut puis haut_gauche
				
                           
                           if(min1<=min2) this.charge[1]=min1;
                           else this.charge[1]=min2;
                           
							place=true;
						}
				}	
		}
	}

}
//////////////////////////


public  void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	
	if(x-1>=0 && x<=7 && y>=0 && y<=7)
	{
		this.temps_recherche=this.temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
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
///////////////////////////// rechercher le processeur haut_droite 


public  void recherche_haut_droite(Tache tachePlacer,int x,int y) throws InterruptedException{


if (x-1>=0 && y+1<=7 && x<=7 && y>=0)
		{	
	temps_recherche=temps_recherche+10;
	//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
	Energie+=1;	
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if (Create_NOC.getNOC()[x-1][y+1].getType() == tachePlacer.getType().get(i))
			{
				if (Create_NOC.getNOC()[x-1][y+1].getFree()==true)
					{
					
						int min3=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x-1][y+1].getId());// lien haut puis haut_droit
						int min4=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x][y+1].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y+1].getId(), Create_NOC.getNOC()[x][y+1].getId());// lien droit puis droit_haut
						
						if(min3<=min4) this.charge[3]=min3;
                         else this.charge[3]=min4;
                         
						place=true;
					}
			}	
		}
		}
}
//////////////////////////////////
   	
   
public  void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
		
	
	if (y+1<=7 && y>=0 && x>=0 && x<=7)
	{	
		this.temps_recherche=this.temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
		Energie+=1;
		for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		{
		if (Create_NOC.getNOC()[x][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y+1].getFree()==true)
		{
			
            this.charge[4]= GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x][y+1].getId());
			
            place=true;
			}
	
			    }	
		}
	}
	
}

////////////////////////////////////recherche processeur bas_droite 

public  void recherche_bas_droite(Tache tachePlacer,int x,int y) throws InterruptedException{




	if (x+1<=7 && y+1<=7 && x>=0 && y>=0)
		{	temps_recherche=temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
		Energie+=1;	
		for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		{
		if (Create_NOC.getNOC()[x+1][y+1].getType() == tachePlacer.getType().get(i))
				{
					if (Create_NOC.getNOC()[x+1][y+1].getFree()==true)
						{
						
						
 							int min5=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x][y+1].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y+1].getId(), Create_NOC.getNOC()[x+1][y+1].getId());//lien droit puis droit_bas
							int min6=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x+1][y].getId(), Create_NOC.getNOC()[x+1][y+1].getId());//lien bas puis bas_droit
														
							
							if(min5<=min6) this.charge[5]=min5;
	                         else this.charge[5]=min6;
							
							place=true;
						}
				}	
		}

		}
}


///////// verifier le processeur bas 
public  void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x+1<=7 && x>=0 && y>=0 && y<=7)
	  {
		this.temps_recherche=this.temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
		Energie+=1;  
		for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		{
		if(Create_NOC.getNOC()[x+1][y].getType() == tachePlacer.getType().get(i))
				{
						if (Create_NOC.getNOC()[x+1][y].getFree()==true)
						{
							 			               
							this.charge[6]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId());
							place=true;
		
						}
						
	
				}
		}
     }
	
																			}


	

//////////////////////////////rechercher le processeur bas_gauche 

public  void recherche_bas_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{


	if (x+1<=7 && y-1>=0 && x>=0 && y<=7)
		{	temps_recherche=temps_recherche+10;
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(1);
		Energie+=1;	
		for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		{
		if (Create_NOC.getNOC()[x+1][y-1].getType() == tachePlacer.getType().get(i))
			{
				if (Create_NOC.getNOC()[x+1][y-1].getFree()==true)
				{
                    int min7=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x+1][y-1].getId(),Create_NOC.getNOC()[x+1][y].getId());// lien bas puis bas_gauche
                    int min8=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId())+GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(), Create_NOC.getNOC()[x+1][y-1].getId());// lien gauche puis gauche_bas

                    
                	if(min7<=min8) this.charge[7]=min7;
                    else this.charge[7]=min8;
                	
					place=true;
				}
			}	
		}
		}
}





///////////////////////////////////// 



public  void refaire(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	// recherche � gauche 
	recherche_gauche(tachePlacer,x,y);
	//recherche en haut � gauche
	recherche_haut_gauche(tachePlacer,x,y);
	//recherche en haut
	recherche_haut(tachePlacer,x,y);
	// recherche en haut � droite 
	recherche_haut_droite(tachePlacer,x,y);
	// recherche a droite
	recherche_droite(tachePlacer,x,y);
	//recherche en bas � droite
	recherche_bas_droite(tachePlacer,x,y);
	//recherche en bas
	recherche_bas(tachePlacer,x,y);
	//   recherche en bas � gauche 
	recherche_bas_gauche(tachePlacer,x,y);
	
	
	if(place==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=7;i++)
			{            
			
			if(this.charge[i] < min) {min=this.charge[i];c=i;}
			
			}
		
      
		 lancer(tachePlacer,c,x,y);
		 
		
		
		
		}
	
	
	
}
//////////////////////////
/*
public  void lancer(Tache tachePlacer,int K,int x,int y) throws InterruptedException
{
	int x1=-1,y1=-1;
	
	switch (K)
	{ 
	case 0 : 	{x1=x;y1=y-1;}break;
	case 1 :	{x1=x-1;y1=y-1;}break;
	case 2 :	{x1=x-1;y1=y;}break;
	case 3 :	{x1=x-1;y1=y+1;}break;
	case 4 :	{x1=x;y1=y+1;}break;
	case 5 :	{x1=x+1;y1=y+1;}break;
	case 6 :	{x1=x+1;y1=y;}break;
	case 7 :	{x1=x+1;y1=y-1;}break;
	}
	
	
	
	ProcessorGeneral processeur;
	ReconfigurableArea processeurhard;
	
	if (tachePlacer.getType()==1)
	{
	processeur  = (ProcessorGeneral )Mesh2dNOC.getNOC()[x1][y1];
	
	processeur.ajoutTache(tachePlacer);
	
	this.temps_execution+=processeur.Time(tachePlacer, StaticParametre.FREQUENCE_CPU_SOFT);
	//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(StaticParametre.ENERGIE_CONSOMMER_SOFT*300);
	Energie+=StaticParametre.ENERGIE_CONSOMMER_SOFT*300;
	
	}
	
	else{
		
		processeurhard=(ReconfigurableArea)Mesh2dNOC.getNOC()[x1][y1];
	
		processeurhard.ajoutTache(tachePlacer);
		
		this.temps_execution=processeurhard.Time(tachePlacer,StaticParametre.FREQUENCE_CPU_HARD);
		//StaticParametre.listApplication.get(tachePlacer.getIdApplication()).setEnergie(StaticParametre.ENERGIE_CONSOMMER_HARD*300);
		Energie+=StaticParametre.ENERGIE_CONSOMMER_HARD*300;
	
	}
	
	
}
*/
////////////////////////////////////////

public  void lancer(Tache t,int K,int x,int y) throws InterruptedException
{

int x1=-1,y1=-1;
	


	switch (K)
	{ 
	case 0 : 	{x1=x;y1=y-1;}break;
	case 1 :	{x1=x-1;y1=y-1;}break;
	case 2 :	{x1=x-1;y1=y;}break;
	case 3 :	{x1=x-1;y1=y+1;}break;
	case 4 :	{x1=x;y1=y+1;}break;
	case 5 :	{x1=x+1;y1=y+1;}break;	
	case 6 :	{x1=x+1;y1=y;}break;
	case 7 :	{x1=x+1;y1=y-1;}break;
	}
	
	
	
	
t.x=x1;
t.y=y1;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;



Create_NOC.getNOC()[x1][y1].set_state(false);
StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie);//ajouter l energie consomm� lors de la recherche � l energie total consomm� par l application


}




public  int Time(){
	return (this.temps_recherche+this.temps_execution);
	
      }

////////////////////////////////////////
public  int Energie()
{
return(this.Energie);
}

}

