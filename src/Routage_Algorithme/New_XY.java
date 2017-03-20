package Routage_Algorithme;

import java.util.LinkedList;
import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class New_XY {

    private int x_source;

    private int y_source;

    private int x_destination;

    private int y_destination;

    private int temps_attente;

    private int temps_routage;

    private int donnee;

    private int Energie_routage;

    private int Energie_attente;

    private Tache t;

    private int succ;

    private Paquet p;

    private LinkedList<Integer> cheminx = new LinkedList<Integer>();

    private LinkedList<Integer> cheminy = new LinkedList<Integer>();

    public New_XY(int x_source, int y_source, int x_destination, int y_destination, Tache tache, int succ, int taille, Paquet p) {
        this.x_source = x_source;
        this.y_source = y_source;
        this.x_destination = x_destination;
        this.y_destination = y_destination;
        this.temps_attente = 0;
        this.temps_routage = 0;
        this.Energie_attente = 0;
        this.Energie_routage = 0;
        t = tache;
        this.succ = succ;
        this.p = p;
        donnee = taille;
    }

    public void start() throws InterruptedException {
        int saut_x = Math.abs(this.x_source - this.x_destination);
        if (saut_x > 0) {
            if ((this.x_source - this.x_destination) < 0) {
                if (verifier_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source) > 0) {
                    set_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    attendre(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    lancer_envoie(this.x_source, this.y_source, this.x_source + 1, this.y_source, 2);
                } else {
                    set_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    lancer_envoie(this.x_source, this.y_source, this.x_source + 1, this.y_source, 2);
                }
                cheminx.add(this.x_source + 1);
                cheminy.add(this.y_source);
            } else {
                if (verifier_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source) > 0) {
                    set_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    attendre(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    lancer_envoie(this.x_source - 1, this.y_source, this.x_source, this.y_source, 1);
                } else {
                    set_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    lancer_envoie(this.x_source - 1, this.y_source, this.x_source, this.y_source, 1);
                }
                cheminx.add(this.x_source - 1);
                cheminy.add(this.y_source);
            }
        } else {
            if ((this.y_source - this.y_destination) < 0) {
                if (verifier_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1) > 0) {
                    set_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    attendre(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    lancer_envoie(this.x_source, this.y_source, this.x_source, this.y_source + 1, 2);
                } else {
                    set_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    lancer_envoie(this.x_source, this.y_source, this.x_source, this.y_source + 1, 2);
                }
                cheminx.add(this.x_source);
                cheminy.add(this.y_source + 1);
            } else {
                if (verifier_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source) > 0) {
                    set_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    attendre(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    lancer_envoie(this.x_source, this.y_source - 1, this.x_source, this.y_source, 1);
                } else {
                    set_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    lancer_envoie(this.x_source, this.y_source - 1, this.x_source, this.y_source, 1);
                }
                cheminx.add(this.x_source);
                cheminy.add(this.y_source - 1);
            }
        }
    }

    public int verifier_lien(int x, int y, int x1, int y1) {
        int lien = -1;
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7) {
            lien = GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
        }
        return lien;
    }

    public synchronized void set_lien(int x, int y, int x1, int y1) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7)
            GenererChannel.getListChannel().get(1).ajout_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId(), donnee);
    }

    public void attendre(int x, int y, int x1, int y1) throws InterruptedException {
        int size_file = verifier_lien(x, y, x1, y1);
        this.temps_attente = (((size_file - donnee) / StaticParametre.DEBIT) * StaticParametre.Temps_envoie);
        Simulator.Add_Event(Simulator.Tnow + temps_attente);
        this.Energie_attente += (StaticParametre.Energie_attente_envoie * ((size_file - donnee) / StaticParametre.DEBIT));
        StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie_attente);
    }

    public void lancer_envoie(int x, int y, int x1, int y1, int M) throws InterruptedException {
        this.temps_routage = (StaticParametre.Temps_envoie * (donnee / StaticParametre.DEBIT));
        p.set_Tfin(temps_routage + temps_attente);
        this.Energie_routage = (StaticParametre.Energie_envoi * (donnee / StaticParametre.DEBIT));
        StaticParametre.listApplication.get(t.getIdApplication()).setEnergie(this.Energie_routage);
    }

    public synchronized int getTime_routage() {
        return (this.temps_routage + this.temps_attente);
    }
}
