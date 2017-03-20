package Application;

import java.util.ArrayList;
import java.util.LinkedList;
import Simulation.StaticParametre;


/**
 * 
 * Cette classe donne la repr�sentation d'une t�che avec ces diff�rentes caract�ristiques
 *
 */



public class Tache {
	
	/**
	 * le type de la Tache
	 */
	public 	ArrayList<Integer> type=new ArrayList<Integer>();

	
	/**
	 * la taille de la Tache
	 */
	//private int tailleTache;
	public 	ArrayList<Integer> tailleTache=new ArrayList<Integer>();

	/**
	 * identificateur de l'application de la Tache
	 */
	private int idApplication;
	
	/**
	 * identificateur de la Tache
	 */
	private int id;
	
	/**
	 * la coordonn�es x(par rapport a ligne du NOC)
	 */
	public int x;
	
	/**
	 * la coordonn�es y(par rapport a colone du NOC)
	 */
	public int y;
	
	/**
	 *  Nombre de messages re�u en provenance des fils (1 message par fils)
	 * 
	 */
	public int nbr_message=0;
	
	
	
	public int fin;
	
	/**
	 * la quantit� de donn�es � partager
	 */
	private LinkedList <Integer> donneePartager=new LinkedList<Integer>();
	
	/**
	 * identificateur du p�re
	 */
	private int idPere=0;
	
	/**
	 * successeurs de la Tache (fils)
	 */
	private LinkedList<Integer> succ = new LinkedList<Integer>();
	

	/**
	 *  la variable mapped d�termine si la t�che est plac� ou non 
	 */
	public boolean mapped = false;
	
	/**
	 *  x1,y1 d�termine � quel niveau(processeur) se trouve la communication avec mes fils
	 */
	public ArrayList<Integer> x1=new ArrayList<Integer>();
	
	public ArrayList<Integer> y1=new ArrayList<Integer>();
	
	
	
	public ArrayList<Integer> communication_de_fils=new ArrayList<Integer>();
	
	/**
	 *  Temps du debut et fin d'execution sur un proc
	 * 
	 */
	public int debut_execution,fin_execution;
	
	/**
	 *  Temps du d�but et fin de routage avec le pere
	 */
	public int fin_root_pere=-1;
	public int debut_root_pere=-2;
	
	/**
	 *  x2,y2 d�termine � quel niveau(processeur) se trouve la communication avec mon pere
	 */
	public int x2,y2;
	
	
	/**
	 *  Temps du debut de routage
	 * 
	 */
	public int debut_routage;
	
	
	/**
	 * 
	 *  Variable qui d�termine si le routage est termin�
	 */
	public boolean end_root=false;
	
	public boolean k=false;
	
	/**
	 *  Temps de fin de routage pour chaque fils
	 * 
	 */
	public ArrayList<Integer> fin_routage=new ArrayList<Integer>();
	
	
	
	public Tache(){
		// Initialisation des valeurs
		debut_execution=-1;
		fin_execution=-1;
		debut_routage=-1;
		fin=0;
		
		x=-1;
		y=-1;
		x2=-1;
		y2=-1;
	
		for(int i=0;i<=100;i++)
		{
			x1.add(-2);
			y1.add(-2);
			fin_routage.add(-1);
			type.add(-1);
			tailleTache.add(-1);
		}
	}
	
	/**
	 * donner un identificateur a la t�che
	 * 
	 */
	public void setId(int id){
		this.id= id;
	}
	
	/**
	 * renvoyer l'indentificateur de la t�che
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Modifier un type a la t�che
	 */
	public void setType(String typ){
		
		
		type.add(Integer.parseInt(typ),Integer.parseInt(typ));
			
	}
	

		
	/**
	 * renvoyer  le type de la t�che
	 */
	public ArrayList<Integer> getType() {
		return type;
	}
	
	/**
	 * Modifier la taille de la t�che
	 */
	
		public void setTailleTache(String type,String taille){
			
			// ajouter la taille de la tache selon le type
			tailleTache.add(Integer.parseInt(type),Integer.parseInt(taille));
			
		}
		
	
	
	/**
	 * renvoyer la taille de la t�che selon le type
	 */
	public int getTailleTache(int typ) {
		return tailleTache.get(typ); 
	}
	
	/**
	 * donner l'indentificateur de l'application de la t�che
	 */ 
	public void setIdApplication(int id_application) {
		this.idApplication = id_application;
	}
	
	/**
	 * renvoyer l'indentificateur de l'identificateur de l'application de la t�che
	 */
	public int getIdApplication() {
		return idApplication;
	}	

	/**
	 * retourner la taille des donn� a partag�
	 */ 
	public int  getDonneePartager(int k) 
		{
		return donneePartager.get(k);
		}	
	
	/**
	 * d�finir le nombre de donn�s a partager
	 */
	public void setDonneePartager(LinkedList<Integer> donnePartage) {
	this.donneePartager=donnePartage;
	}	
	
	/**
	 * retourner l'identificateur du p�re
	 */
	public int getIdPere() {
		return idPere;
	}

	/**
	 * d�finir l'identificateur du p�re
	 */ 
	public void setIdPere(int idPere) {
		this.idPere = idPere;
	}

	/** d�finir les successeur	*/
	public void setSucc(LinkedList<Integer> succ) {
		this.succ = succ;
	}

	/** 
	 * 
	 * @return : listes des successeur
	 */
	public LinkedList<Integer> getSucc() {
		return succ;
	}

	
	/**
	 *  incr�ment le nombre de message re�u en provenance des fils	
	 */
	public void set_communication_recu()
	{
		nbr_message++;
	}

/**
 * 
 * retourne le nbr de message re�u
 */
public int get_nbr_message()
	{
	return this.nbr_message;
	
	}

/**
 *  temps du d�but de routage
 */
public void debut_routage(int Time)
	{
	debut_routage=Time;
	}


/**
 *  modifie le temps du d�but de routage avec mon pere
 * @param Time
 */
public void debut_root_pere(int Time)
	{
	debut_root_pere=Time;
	}
	
/**
 * Modifie le temps de fin de routage
 * 
 * @param Time
 * @param x1
 * @param y1
 * @param j
 */
public void fin_routage(int Time,int x1,int y1,int j)

	{
	
	fin_routage.set(j,Time);
	this.x1.set(j,x1);
	this.y1.set(j,y1);
	
	}

/**
 *  
 *  Modifie le temps de fin de routage avec mon pere
 * 
 * @param Time
 * @param x1
 * @param y1
 */
public void fin_root_pere(int Time,int x1,int y1)

{
	fin_root_pere=Time;
	StaticParametre.getListApplication().get(idApplication).getListTache().get(idPere).communication_de_fils.add(fin_root_pere);
	x2=x1;
	y2=y1;
}

/**
 * 
 * 
 */

public void set_fin()
{
	this.fin++;
	
}

/**
 * 
 * @param Time
 * @return
 */
public boolean verify_comm_fils(int Time) // une procedure qui verifie si le tnow correspond � la fin du routage du fils vers le pere


{
	boolean t=false;
	for(int i=0;i<communication_de_fils.size();i++)
	{
		if(Time ==communication_de_fils.get(i) )
			{t=true;break;}
	}
	
	return(t);
	
}

}


