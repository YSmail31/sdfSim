package Simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import Routage_Algorithme.Paquet;
import Application.Application;
import Application.ApplicationSdf;
import Application.Tache;
import Architecture.Cluster;
import Architecture.ProcessorElement;

public class StaticParametre {

    private static int[][] Matrice_channel = new int[256][256];

    ;

    public static Map<Integer, ProcessorElement> listProcesseur = new TreeMap<Integer, ProcessorElement>();

    ;

    public static LinkedList<Application> listApplication = new LinkedList<Application>();

    ;

    public static LinkedList<Application> applicationAttente = new LinkedList<Application>();

    public static Application[] application_en_cours = new Application[9];

    public static Cluster[] listClusters = new Cluster[16];

    ;

    public static String MONO_MULTI = "MONO";

    public static String ALGORITHME_PLACEMENT;

    public static String ALGORITHME_ROUTAGE = "XY";

    public static String Strategie_De_Recherche;

    public static int LEGNHT_NOC = 16;

    public static final int LEGNHT_CHANNEL = 112;

    public static String Lien_Fichier_XML;

    public static int TAILLE_CLUSTER = 4;

    public static int end_application = 0;

    public static ArrayList<Tache> not_mapped = new ArrayList<Tache>();

    public static int NOMBRE_APPLICATION = 10;

    public static int DEBIT = 5;

    public static int FREQUENCE_FPGA, FREQUENCE_ASIC, FREQUENCE_DSP, FREQUENCE_GP;

    public static int Mode_GP = 3, Mode_FPGA = 3, Mode_ASIC = 3, Mode_DSP = 1;

    public static int ENERGIE_GP, ENERGIE_FPGA, ENERGIE_ASIC, ENERGIE_DSP;

    public static int MEMOIRE_GP = 900;

    public static int MEMOIRE_HARD = 600;

    public static int Energie_envoi = 20;

    public static int Energie_attente_envoie = 2;

    public static int Temps_envoie = 100;

    public static String scenario;

    public static ArrayList<String> List_Algo_Mapping = new ArrayList<String>();

    public static ArrayList<String> List_Strategie = new ArrayList<String>();

    public static ArrayList<Paquet> List_Paquet = new ArrayList<Paquet>();

    public static int nbcluster = 16;

    public static LinkedList<Application> getListApplication() {
        return listApplication;
    }

    public static int[][] getMatriceChannel() {
        return Matrice_channel;
    }

    public static Cluster[] getListCluster() {
        return listClusters;
    }

    public static void setEnd_Application() {
        end_application++;
    }

    public static int getFrequence_CPU(int Mode) {
        switch(Mode) {
            case 1:
                {
                    FREQUENCE_GP = 60;
                }
                break;
            case 2:
                {
                    FREQUENCE_GP = 40;
                }
                break;
            case 3:
                {
                    FREQUENCE_GP = 20;
                }
                break;
        }
        return (FREQUENCE_GP);
    }

    public static int getEnergy_CPU(int Mode) {
        switch(Mode) {
            case 1:
                {
                    ENERGIE_GP = 5;
                }
                break;
            case 2:
                {
                    ENERGIE_GP = 10;
                }
                break;
            case 3:
                {
                    ENERGIE_GP = 15;
                }
                break;
        }
        return (ENERGIE_GP);
    }

    public static int getFrequence_FPGA(int Mode) {
        switch(Mode) {
            case 1:
                {
                    FREQUENCE_FPGA = 60;
                }
                break;
            case 2:
                {
                    FREQUENCE_FPGA = 40;
                }
                break;
            case 3:
                {
                    FREQUENCE_FPGA = 20;
                }
                break;
        }
        return (FREQUENCE_FPGA);
    }

    public static int getEnergy_FPGA(int Mode) {
        switch(Mode) {
            case 1:
                {
                    ENERGIE_FPGA = 10;
                }
                break;
            case 2:
                {
                    ENERGIE_FPGA = 15;
                }
                break;
            case 3:
                {
                    ENERGIE_FPGA = 20;
                }
                break;
        }
        return (ENERGIE_FPGA);
    }

    public static int getFrequence_ASIC(int Mode) {
        switch(Mode) {
            case 1:
                {
                    FREQUENCE_ASIC = 60;
                }
                break;
            case 2:
                {
                    FREQUENCE_ASIC = 40;
                }
                break;
            case 3:
                {
                    FREQUENCE_ASIC = 20;
                }
                break;
        }
        return (FREQUENCE_ASIC);
    }

    public static int getEnergy_ASIC(int Mode) {
        switch(Mode) {
            case 1:
                {
                    ENERGIE_ASIC = 10;
                }
                break;
            case 2:
                {
                    ENERGIE_ASIC = 15;
                }
                break;
            case 3:
                {
                    ENERGIE_ASIC = 20;
                }
                break;
        }
        return (ENERGIE_ASIC);
    }

    public static int getFrequence_DSP(int Mode) {
        switch(Mode) {
            case 1:
                {
                    FREQUENCE_DSP = 60;
                }
                break;
            case 2:
                {
                    FREQUENCE_DSP = 40;
                }
                break;
            case 3:
                {
                    FREQUENCE_DSP = 20;
                }
                break;
        }
        return (FREQUENCE_DSP);
    }

    public static int getEnergy_DSP(int Mode) {
        switch(Mode) {
            case 1:
                {
                    ENERGIE_DSP = 10;
                }
                break;
            case 2:
                {
                    ENERGIE_DSP = 15;
                }
                break;
            case 3:
                {
                    ENERGIE_DSP = 20;
                }
                break;
        }
        return (ENERGIE_DSP);
    }
}
