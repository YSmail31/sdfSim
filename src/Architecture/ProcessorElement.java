package Architecture;

import java.util.ArrayList;
import java.util.LinkedList;
import Simulation.Simulator;
import Simulation.StaticParametre;
import Simulation.ParametreSdf;
import Application.Tache;
import Application.Actor;
import Routage_Algorithme.Paquet;

public class ProcessorElement {

    protected int type = 0;

    public int x;

    public int y;

    protected int id;

    protected boolean etat = true;

    protected int memoire = 900;

    private int Frequence = 20;

    private int Energie = 20;

    public int Energie_routage = 0;

    protected int idCluster;

    public LinkedList<Tache> File = new LinkedList<Tache>();

    public int com_x_dest, com_y_dest, nbr_paquet;

    public int fin_routage;

    public ArrayList<Paquet> List_Paquet = new ArrayList<Paquet>();

    private int Energie_consome;

    public ProcessorElement() {
        Energie_routage = 0;
        Energie_consome = 0;
    }

    public ProcessorElement(int x, int y) {
        Energie_routage = 0;
        Energie_consome = 0;
        this.x = x;
        this.y = y;
    }

    public synchronized boolean getFree() {
        return this.etat;
    }

    public synchronized void set_state(boolean etat) {
        this.etat = etat;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setMem(int taille_tache, int n) {
        if (n == 1)
            this.memoire -= taille_tache;
        else
            this.memoire += taille_tache;
    }

    public int getMem() {
        return memoire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }

    public void ajoutTache(Tache tache) throws InterruptedException {
        switch(this.type) {
            case 0:
                {
                    Frequence = StaticParametre.getFrequence_CPU(StaticParametre.Mode_GP);
                    Energie = StaticParametre.getEnergy_CPU(StaticParametre.Mode_GP);
                }
                break;
            case 1:
                {
                    Frequence = StaticParametre.getFrequence_CPU(StaticParametre.Mode_GP);
                    Energie = StaticParametre.getEnergy_CPU(StaticParametre.Mode_GP);
                }
                break;
            case 2:
                {
                    Frequence = StaticParametre.getFrequence_FPGA(StaticParametre.Mode_FPGA);
                    Energie = StaticParametre.getEnergy_FPGA(StaticParametre.Mode_FPGA);
                }
                break;
            case 3:
                {
                    Frequence = StaticParametre.getFrequence_ASIC(StaticParametre.Mode_ASIC);
                    Energie = StaticParametre.getEnergy_ASIC(StaticParametre.Mode_ASIC);
                }
                break;
            case 4:
                {
                    Frequence = StaticParametre.getFrequence_DSP(StaticParametre.Mode_DSP);
                    Energie = StaticParametre.getEnergy_DSP(StaticParametre.Mode_DSP);
                }
                break;
        }
        Strategy_Switching(tache);
        StaticParametre.listApplication.get(tache.getIdApplication()).setEnergie(this.Energie);
    }

    public void ajoutActor(Actor tache) throws InterruptedException {
        switch(this.type) {
            case 0:
                {
                    Frequence = ParametreSdf.FREQUENCE_TYPE0;
                    Energie = ParametreSdf.ENERGIE_TYPE0;
                }
                break;
            case 1:
                {
                    Frequence = ParametreSdf.FREQUENCE_TYPE1;
                    Energie = ParametreSdf.ENERGIE_TYPE1;
                }
                break;
            case 2:
                {
                    Frequence = ParametreSdf.FREQUENCE_TYPE2;
                    Energie = ParametreSdf.ENERGIE_TYPE2;
                }
                break;
            case 3:
                {
                    Frequence = ParametreSdf.FREQUENCE_TYPE3;
                    Energie = ParametreSdf.ENERGIE_TYPE3;
                }
                break;
        }
        int t = 1;
        for (int i = 0; i < tache.Processors.size(); i++) {
            if (tache.Processors.get(i).id_type == Create_NOC.getNOC()[tache.x][tache.y].getType()) {
                t = tache.Processors.get(i).time;
            }
        }
        ParametreSdf.listApplicationSdf.get(tache.idApplication).setEnergie(this.Frequence * this.Energie * t * 1000);
        this.Energie_consome += this.Frequence * this.Energie * t * 1000;
    }

    private void Strategy_Switching(Tache tache) {
        int fin_exec = Simulator.Tnow;
        if (tache.getId() == 1 && tache.getIdApplication() == 1)
            System.out.println("tyyype " + type + "  taille  " + tache.getTailleTache(type));
        if (!tache.getSucc().isEmpty()) {
            tache.fin_execution = ((tache.getTailleTache(type) * Frequence) / 2) + fin_exec;
            Energie = (tache.getTailleTache(type) * Energie) / 2;
        } else {
            tache.fin_execution = (tache.getTailleTache(type) * Frequence) + fin_exec;
            Energie = (tache.getTailleTache(type) * Energie);
        }
        fin_exec = tache.fin_execution;
    }

    public int Nbr_Paquet_recu(int x, int y) {
        return 0;
    }
}
