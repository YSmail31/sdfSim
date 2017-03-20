package Architecture;

import java.util.ArrayList;
import java.util.LinkedList;
import Simulation.StaticParametre;
import Routage_Algorithme.Paquet;

public class LienNoc {

    private int idSource;

    private int idDestination;

    public ArrayList<Integer> event = new ArrayList<Integer>();

    private int idLien;

    private LinkedList<Integer> File;

    public LinkedList<Paquet> liste_paquets;

    public int charge;

    public LienNoc(int idSource, int idDestination, int idLien) {
        this.idSource = idSource;
        this.idDestination = idDestination;
        this.idLien = idLien;
        this.File = new LinkedList<Integer>();
        this.liste_paquets = new LinkedList<Paquet>();
        charge = 0;
    }

    public void setSource(int idSource) {
        this.idSource = idSource;
    }

    public int getSource() {
        return idSource;
    }

    public void setVoisin(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getVoisin() {
        return idDestination;
    }

    public void setIdLien(int idLien) {
        this.idLien = idLien;
    }

    public int getIdLien() {
        return idLien;
    }

    public int get_size_file(int source, int destination) {
        int somme = 0;
        for (int i = 1; i <= 112; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {
                if (!GenererChannel.getListChannel().get(i).File.isEmpty()) {
                    for (int j = 0; j < GenererChannel.getListChannel().get(i).File.size(); j++) {
                        somme = somme + GenererChannel.getListChannel().get(i).File.get(j);
                    }
                }
            }
        }
        return somme;
    }

    public void ajout_file(int source, int destination, int paquet) {
        for (int i = 1; i <= 112; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {
                GenererChannel.getListChannel().get(i).File.add(paquet);
            }
        }
    }

    public void set_file() {
        if (!File.isEmpty()) {
            if (!File.isEmpty()) {
                this.File.set(0, this.File.getFirst() - StaticParametre.DEBIT);
            }
            if (this.File.getFirst() == 0) {
                this.File.remove(0);
            }
        }
    }

    public void event(int time_event, int source, int destination) {
        for (int i = 1; i <= 112; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {
                GenererChannel.getListChannel().get(i).event.add(time_event);
            }
        }
    }
}
