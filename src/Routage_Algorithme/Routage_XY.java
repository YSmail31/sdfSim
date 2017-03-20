package Routage_Algorithme;

import java.util.LinkedList;

import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;


public class Routage_XY
{
	private int x_source;
	private int y_source;
	private int x_destination;
	private int y_destination;
	private int temps_attente;
	private int temps_routage;
	private int donnee;
	private int Energie_routage;
	private int Energie_attente;
	private  Tache t;
	private int succ;

	private LinkedList<Integer> cheminx= new LinkedList<Integer>();
	private LinkedList<Integer> cheminy= new LinkedList<Integer>();

     public Routage_XY(int x_source,int y_source,int x_destination,int y_destination,Tache tache,int succ)
   	{
	   this.x_source=x_source;
	   this.y_source=y_source;
	   this.x_destination=x_destination;
	   this.y_destination=y_destination;
	   this.temps_attente=0;
	   this.temps_routage=0;
	   this.Energie_attente=0;
	   this.Energie_routage=0;
	   t=tache;
	   this.succ=succ;
	   

			
	   
	   if(succ >-1)
	   {
		   this.donnee=tache.getDonneePartager(succ);
	   }
		   else { if (succ==-1) donnee=100;
		   else donnee=StaticParametre.DEBIT;
		   }
	   

    }
   
  public void start() throws InterruptedException{
	  
	  int saut_x=Math.abs(this.x_source-this.x_destination);
	//  int saut_y=Math.abs(this.y_source-this.y_destination);
	  
	  
	  
	  
	  if(saut_x>0) // pas sur la même colonne
   	{
  	 	if((this.x_source-this.x_destination)< 0) // de haut en bas 
  	 	
  	 	{
  	 		
  	 				//retourne la charge du lien
  	 			if(verifier_lien(this.x_source,this.y_source,this.x_source+1,this.y_source)>0)
  	 			{
  	 				set_lien(this.x_source,this.y_source,this.x_source+1,this.y_source);
  	 				attendre(this.x_source,this.y_source,this.x_source+1,this.y_source);
  	 				lancer_envoie(this.x_source,this.y_source,this.x_source+1,this.y_source,2);
  	 				
  	 				
  	 				}	
  	 			
  	 			else  {
  	 				
  	 				set_lien(this.x_source,this.y_source,this.x_source+1,this.y_source);
  	 				lancer_envoie(this.x_source,this.y_source,this.x_source+1,this.y_source,2);
  	 				
  	 					}
  	 			
  	 			cheminx.add(this.x_source+1);
	    	 	 cheminy.add(this.y_source);
	    	 	 
  	 	}
  	 	   
  	 		
  	 	
  	 	
  	 else   //de bas vers le haut 
  	 	
  	 {
  		 
  		 if(verifier_lien(this.x_source-1,this.y_source,this.x_source,this.y_source)>0)
  		 {
  			 set_lien(this.x_source-1,this.y_source,this.x_source,this.y_source); 
  			 attendre(this.x_source-1,this.y_source,this.x_source,this.y_source);
  			 lancer_envoie(this.x_source-1,this.y_source,this.x_source,this.y_source,1);
  			}	
  		 
  		 else
  		 {
  	     set_lien(this.x_source-1,this.y_source,this.x_source,this.y_source); 
  		 lancer_envoie(this.x_source-1,this.y_source,this.x_source,this.y_source,1);
  		}
  		 
  		 //this.x_source--;
  		 
  	 			cheminx.add(this.x_source-1);
  	 	        cheminy.add(this.y_source);	
  	 	    
  	 		
  		 
  		 
  	 	}
  		 
  		 
  	 	 
  	 	
  	 	
  	  
  		 
  	 }
   

   ///////////////////////////// deuxieme étape du routage y -> y -> y ///////////////
	  else
   {
  	 if((this.y_source-this.y_destination)< 0) // de gauche à droite 

  	 {

  		 
  			 if(verifier_lien(this.x_source,this.y_source,this.x_source,this.y_source+1)>0) 
  			 {
  				set_lien(this.x_source,this.y_source,this.x_source,this.y_source+1); 
  				 attendre(this.x_source,this.y_source,this.x_source,this.y_source+1);
  				 lancer_envoie(this.x_source,this.y_source,this.x_source,this.y_source+1,2);
  				 }	 
  			 else
  				 {
  				 set_lien(this.x_source,this.y_source,this.x_source,this.y_source+1); 
  				 lancer_envoie(this.x_source,this.y_source,this.x_source,this.y_source+1,2);
  				
  				 }
  			
  		 cheminx.add(this.x_source);
  	 	    cheminy.add(this.y_source+1);
       }
  		 
  		 
  	 	   
  	 

  	 else   //de droite à gauche  

  	 {

  		  if (verifier_lien(this.x_source,this.y_source-1,this.x_source,this.y_source)>0)
  		  {
  			  set_lien(this.x_source,this.y_source-1,this.x_source,this.y_source);
  			  attendre(this.x_source,this.y_source-1,this.x_source,this.y_source);
  			  lancer_envoie(this.x_source,this.y_source-1,this.x_source,this.y_source,1);
  			  } 
  		  
  		  else { 
  			  set_lien(this.x_source,this.y_source-1,this.x_source,this.y_source);
  			  lancer_envoie(this.x_source,this.y_source-1,this.x_source,this.y_source,1);
  			  } 
  		 		   		 		 
  		 		cheminx.add(this.x_source);
	    	 	cheminy.add(this.y_source-1);
	    	 	    
  	 }

  		 		 
  	 }
	  
	  

}
	    	 


	    
	  	 
	    		 
	    		 
    		 
	    		 
	    		

	   
	  

  
  
  
  
  ///////////// cette methode nous retourne le volume de la file d'attente du lien   ////////
  
    public synchronized int verifier_lien(int x,int y,int x1,int y1)
 	{
    	
    
    	
    	int lien=-1;
    if(x>=0 && x<=7 && y>=0 && y<=7 && x1>=0 && x1<=7 && y1>=0 && y1<=7)	{
    lien=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
   
    } 
    return lien;
 	}

////////////////////////// cette methode permet d'ajouter le nombre de paquet à envoyer sur la file d'attente du lien  /////////////
    
    public synchronized void set_lien(int x,int y,int x1,int y1)
    {
    	if(x>=0 && x<=7 && y>=0 && y<=7 && x1>=0 && x1<=7 && y1>=0 && y1<=7)
    	GenererChannel.getListChannel().get(1).ajout_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId(),donnee);
   
    }

///////////////////////////// cette methode permet de rendre le lien libre ///////////
    
   
///////////////////////////////
    
    public synchronized void attendre(int x,int y,int x1,int y1) throws InterruptedException
    {
    	int size_file=verifier_lien(x,y,x1,y1);
    	// ajouter un evt , pour indiquer le debut d un routage et donc la fin de l attente !!
    	this.temps_attente=(((size_file-donnee)/StaticParametre.DEBIT)*StaticParametre.Temps_envoie);
    	
    	
    	
    	
    	if(succ!=-2)
    	{
    	int c=t.getSucc().get(succ);
    	Tache tache_fils=StaticParametre.listApplication.get(t.getIdApplication()).getListTache().get(c);
    	
    	tache_fils.debut_routage(Simulator.Tnow+temps_attente);
    	}
    	
    	else
    		this.t.debut_root_pere(Simulator.Tnow+temps_attente);
    	
    	
    	Simulator.Add_Event(Simulator.Tnow+temps_attente);
    	
    	
    	
    	this.Energie_attente+=(StaticParametre.Energie_attente_envoie*(size_file/StaticParametre.DEBIT));
    	
    	StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie_attente);
    	
    }
//////////////////////////    
    
    public  void lancer_envoie(int x,int y,int x1,int y1,int M) throws InterruptedException
    {
    	int c=0;
    	
    	
    	for (int i=1;i<=(donnee/StaticParametre.DEBIT);i++)//nombre d'envoi
    	{
    		if(x>=0 && x<=7 && y>=0 && y<=7 && x1>=0 && x1<=7 && y1>=0 && y1<=7)
        	{
    		c=temps_attente+((StaticParametre.Temps_envoie)*i)+Simulator.Tnow;
    		
    		GenererChannel.getListChannel().get(1).event(c,Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
    		Simulator.Add_Event(c);
    		
    		
    			
        	}
    		
    	}
			
//// trouver un lien entre les données partagées ainsi que  la bande passante dispo ////
 			
    	this.temps_routage=(StaticParametre.Temps_envoie*(donnee/StaticParametre.DEBIT));
    	
    	
    	if(donnee!=StaticParametre.DEBIT)
    	{
    	if(M==2)
    		
    		t.fin_routage((Simulator.Tnow+temps_routage+temps_attente),x1,y1,succ);
    	else 
    		t.fin_routage((Simulator.Tnow+temps_routage+temps_attente),x,y,succ);
    	}
    	
    	else
    		
    	{
        	if(M==2)
        		
        		t.fin_root_pere((Simulator.Tnow+temps_routage+temps_attente),x1,y1);
        	else 
        		t.fin_root_pere((Simulator.Tnow+temps_routage+temps_attente),x,y);
        	}
        	
    	
    	
    	
    	this.Energie_routage+=(StaticParametre.Energie_envoi*(donnee/StaticParametre.DEBIT));
    
    	StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie_routage);
    	
    	    }
    
 //////
    
    public synchronized int getTime_routage(){
    	return (this.temps_routage+this.temps_attente);
    	
          }

}