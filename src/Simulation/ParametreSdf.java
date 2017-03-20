package Simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import Routage_Algorithme.Paquet;
import Application.Application;
import Application.ApplicationSdf;
import Application.Tache;
import Architecture.Cluster;
import Architecture.ProcessorElement;

public class ParametreSdf {

    public static int Tnow = 0;

    public static int nb_pe_consulter = 0;

    public static TreeSet<Integer> T_next_event = new TreeSet<Integer>();

    public static int Default_memory = 500;

    public static int Default_Paquets_Size = 50;

    public static int Debit = 250;

    public static int NB_APP = 20;

    public static int Default_token_size = 10000;

    public static LinkedList<ApplicationSdf> listApplicationSdf = new LinkedList<ApplicationSdf>();

    ;

    public static LinkedList<Integer[]> listactAttente = new LinkedList<Integer[]>();

    ;

    public static LinkedList<Integer[]> listactcours = new LinkedList<Integer[]>();

    ;

    public static String ALGORITHME_PLACEMENT = "BN_GBHD";

    public static int FREQUENCE_TYPE0 = 15, FREQUENCE_TYPE1 = 20, FREQUENCE_TYPE2 = 20, FREQUENCE_TYPE3 = 20;

    public static int ENERGIE_TYPE0 = 20, ENERGIE_TYPE1 = 20, ENERGIE_TYPE2 = 20, ENERGIE_TYPE3 = 20;
}
