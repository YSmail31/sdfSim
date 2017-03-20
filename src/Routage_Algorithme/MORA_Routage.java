package Routage_Algorithme;

import java.util.LinkedList;
import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class MORA_Routage {

    private int x_source;

    private int y_source;

    private int x_destination;

    private int y_destination;

    private int temps_attente;

    private int temps_routage;

    private int Energie_routage;

    private int Energie_attente;

    private int donnee, succ;

    private Tache tache;

    private LinkedList<Integer> cheminx = new LinkedList<Integer>();

    private LinkedList<Integer> cheminy = new LinkedList<Integer>();

    public MORA_Routage(int x_source, int y_source, int x_destination, int y_destination, Tache tache, int succ) {
        this.x_source = x_source;
        this.y_source = y_source;
        this.x_destination = x_destination;
        this.y_destination = y_destination;
        this.temps_attente = 0;
        this.temps_routage = 0;
        this.Energie_attente = 0;
        this.Energie_routage = 0;
        this.tache = tache;
        this.succ = succ;
        if (succ > -1)
            this.donnee = tache.getDonneePartager(succ);
        else {
            if (succ == -1)
                donnee = 100;
            else
                donnee = StaticParametre.DEBIT;
        }
    }

    public void start() throws InterruptedException {
        if (x_source < x_destination) {
            haut_vers_bas();
        } else {
            if (x_source > x_destination) {
                bas_vers_haut();
            } else {
                if ((y_source - y_destination) < 0) {
                    if (get_lien(x_source, y_source, x_source, y_source + 1) > 0) {
                        set_lien(x_source, y_source, x_source, y_source + 1);
                        attendre(x_source, y_source, x_source, y_source + 1);
                        lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                    } else {
                        set_lien(x_source, y_source, x_source, y_source + 1);
                        lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                    }
                    cheminx.add(this.x_source);
                    cheminy.add(this.y_source);
                } else {
                    if (get_lien(x_source, y_source - 1, x_source, y_source) > 0) {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        attendre(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    } else {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    }
                    cheminx.add(x_source);
                    cheminy.add(y_source);
                }
            }
        }
    }

    public int get_lien(int x, int y, int x1, int y1) {
        int lien = -1;
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7) {
            lien = GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
        }
        return lien;
    }

    public void set_lien(int x, int y, int x1, int y1) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7)
            GenererChannel.getListChannel().get(1).ajout_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId(), donnee);
    }

    public void attendre(int x, int y, int x1, int y1) throws InterruptedException {
        int size_file = get_lien(x, y, x1, y1);
        this.temps_attente = (((size_file - donnee) / StaticParametre.DEBIT) * StaticParametre.Temps_envoie);
        if (succ != -2) {
            int c = tache.getSucc().get(succ);
            Tache tache_fils = StaticParametre.listApplication.get(tache.getIdApplication()).getListTache().get(c);
            tache_fils.debut_routage(Simulator.Tnow + temps_attente);
        } else
            tache.debut_root_pere(Simulator.Tnow + temps_attente);
        Simulator.Add_Event(Simulator.Tnow + temps_attente);
        this.Energie_attente += (StaticParametre.Energie_attente_envoie * (size_file / StaticParametre.DEBIT));
        StaticParametre.listApplication.get(tache.getIdApplication()).setEnergie(this.Energie_attente);
    }

    public void lancer_envoie(int x, int y, int x1, int y1, int M) throws InterruptedException {
        int c = 0;
        for (int i = 1; i <= (donnee / StaticParametre.DEBIT); i++) {
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7) {
                c = temps_attente + ((StaticParametre.Temps_envoie) * i) + Simulator.Tnow;
                GenererChannel.getListChannel().get(1).event(c, Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
                Simulator.Add_Event(c);
            }
        }
        this.temps_routage = (StaticParametre.Temps_envoie * (donnee / StaticParametre.DEBIT));
        if (donnee != StaticParametre.DEBIT) {
            if (M == 2)
                tache.fin_routage((Simulator.Tnow + temps_routage + temps_attente), x1, y1, succ);
            else
                tache.fin_routage((Simulator.Tnow + temps_routage + temps_attente), x, y, succ);
        } else {
            if (M == 2)
                tache.fin_root_pere((Simulator.Tnow + temps_routage + temps_attente), x1, y1);
            else
                tache.fin_root_pere((Simulator.Tnow + temps_routage + temps_attente), x, y);
        }
        this.Energie_routage += (StaticParametre.Energie_envoi * (donnee / StaticParametre.DEBIT));
        StaticParametre.listApplication.get(tache.getIdApplication()).setEnergie(this.Energie_routage);
    }

    public void haut_vers_bas() throws InterruptedException {
        if (y_source < y_destination) {
            if (get_lien(x_source, y_source, x_source, y_source + 1) < get_lien(x_source, y_source, x_source + 1, y_source)) {
                if (get_lien(x_source, y_source, x_source, y_source + 1) > 0) {
                    set_lien(x_source, y_source, x_source, y_source + 1);
                    attendre(x_source, y_source, x_source, y_source + 1);
                    lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                } else {
                    set_lien(x_source, y_source, x_source, y_source + 1);
                    lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            } else {
                if (get_lien(x_source, y_source, x_source + 1, y_source) > 0) {
                    set_lien(x_source, y_source, x_source + 1, y_source);
                    attendre(x_source, y_source, x_source + 1, y_source);
                    lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                } else {
                    set_lien(x_source, y_source, x_source + 1, y_source);
                    lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            }
        } else {
            if (y_source > y_destination) {
                if (get_lien(x_source, y_source - 1, x_source, y_source) < get_lien(x_source, y_source, x_source + 1, y_source)) {
                    if (get_lien(x_source, y_source - 1, x_source, y_source) > 0) {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        attendre(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    } else {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    }
                    cheminx.add(x_source);
                    cheminy.add(y_source);
                } else {
                    if (get_lien(x_source, y_source, x_source + 1, y_source) > 0) {
                        set_lien(x_source, y_source, x_source + 1, y_source);
                        attendre(x_source, y_source, x_source + 1, y_source);
                        lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                    } else {
                        set_lien(x_source, y_source, x_source + 1, y_source);
                        lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                    }
                    cheminx.add(x_source);
                    cheminy.add(y_source);
                }
            } else {
                if (get_lien(x_source, y_source, x_source + 1, y_source) > 0) {
                    set_lien(x_source, y_source, x_source + 1, y_source);
                    attendre(x_source, y_source, x_source + 1, y_source);
                    lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                } else {
                    set_lien(x_source, y_source, x_source + 1, y_source);
                    lancer_envoie(x_source, y_source, x_source + 1, y_source, 2);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            }
        }
    }

    public void bas_vers_haut() throws InterruptedException {
        if (y_source < y_destination) {
            if (get_lien(x_source, y_source, x_source, y_source + 1) < get_lien(x_source - 1, y_source, x_source, y_source)) {
                if (get_lien(x_source, y_source, x_source, y_source + 1) > 0) {
                    set_lien(x_source, y_source, x_source, y_source + 1);
                    attendre(x_source, y_source, x_source, y_source + 1);
                    lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                } else {
                    set_lien(x_source, y_source, x_source, y_source + 1);
                    lancer_envoie(x_source, y_source, x_source, y_source + 1, 2);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            } else {
                if (get_lien(x_source - 1, y_source, x_source, y_source) > 0) {
                    set_lien(x_source - 1, y_source, x_source, y_source);
                    attendre(x_source - 1, y_source, x_source, y_source);
                    lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                } else {
                    set_lien(x_source - 1, y_source, x_source, y_source);
                    lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            }
        } else {
            if (y_source > y_destination) {
                if (get_lien(x_source, y_source - 1, x_source, y_source) < get_lien(x_source - 1, y_source, x_source, y_source)) {
                    if (get_lien(x_source, y_source - 1, x_source, y_source) > 0) {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        attendre(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    } else {
                        set_lien(x_source, y_source - 1, x_source, y_source);
                        lancer_envoie(x_source, y_source - 1, x_source, y_source, 1);
                    }
                    cheminx.add(x_source);
                    cheminy.add(y_source);
                } else {
                    if (get_lien(x_source - 1, y_source, x_source, y_source) > 0) {
                        set_lien(x_source - 1, y_source, x_source, y_source);
                        attendre(x_source - 1, y_source, x_source, y_source);
                        lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                    } else {
                        set_lien(x_source - 1, y_source, x_source, y_source);
                        lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                    }
                    cheminx.add(x_source);
                    cheminy.add(y_source);
                }
            } else {
                if (get_lien(x_source - 1, y_source, x_source, y_source) > 0) {
                    set_lien(x_source - 1, y_source, x_source, y_source);
                    attendre(x_source - 1, y_source, x_source, y_source);
                    lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                } else {
                    set_lien(x_source - 1, y_source, x_source, y_source);
                    lancer_envoie(x_source - 1, y_source, x_source, y_source, 1);
                }
                cheminx.add(x_source);
                cheminy.add(y_source);
            }
        }
    }
}
