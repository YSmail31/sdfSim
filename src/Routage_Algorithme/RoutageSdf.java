package Routage_Algorithme;

import Architecture.*;
import Simulation.*;

public class RoutageSdf {

    public RoutageSdf() throws InterruptedException {
        routageinitsdf();
    }

    public static void routageinitsdf() throws InterruptedException {
        boolean routage = true, etat_lien = true;
        int x_suivant, y_suivant, id_lien;
        while (routage == true) {
            for (int i = 0; i < StaticParametre.LEGNHT_NOC; i++) {
                for (int j = 0; j < StaticParametre.LEGNHT_NOC; j++) {
                    if (!Create_NOC.getNOC()[i][j].List_Paquet.isEmpty()) {
                        etat_lien = true;
                        while (etat_lien && !Create_NOC.getNOC()[i][j].List_Paquet.isEmpty()) {
                            Paquet paq;
                            paq = Create_NOC.getNOC()[i][j].List_Paquet.get(0);
                            if (paq.x_destination == i) {
                                if (paq.y_destination == j) {
                                    Create_NOC.getNOC()[i][j].List_Paquet.remove(0);
                                } else if (paq.y_destination < j) {
                                    x_suivant = i;
                                    y_suivant = j - 1;
                                    id_lien = Recherche_Lien(i, j, x_suivant, y_suivant);
                                    if ((ParametreSdf.Debit - GenererChannel.getListChannel().get(id_lien).charge) >= ParametreSdf.Default_Paquets_Size) {
                                        paq.x_inter = x_suivant;
                                        paq.y_inter = y_suivant;
                                        GenererChannel.getListChannel().get(id_lien).liste_paquets.add(paq);
                                        Create_NOC.getNOC()[i][j].List_Paquet.remove(0);
                                        Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_envoi;
                                        ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_envoi);
                                        GenererChannel.getListChannel().get(id_lien).charge -= ParametreSdf.Default_Paquets_Size;
                                    } else {
                                        etat_lien = false;
                                        Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_attente_envoie;
                                        ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_attente_envoie);
                                    }
                                } else if (paq.y_destination > j) {
                                    x_suivant = i;
                                    y_suivant = j + 1;
                                    id_lien = Recherche_Lien(i, j, x_suivant, y_suivant);
                                    if ((ParametreSdf.Debit - GenererChannel.getListChannel().get(id_lien).charge) >= ParametreSdf.Default_Paquets_Size) {
                                        paq.x_inter = x_suivant;
                                        paq.y_inter = y_suivant;
                                        GenererChannel.getListChannel().get(id_lien).liste_paquets.add(paq);
                                        Create_NOC.getNOC()[i][j].List_Paquet.remove(0);
                                        Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_envoi;
                                        ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_envoi);
                                        GenererChannel.getListChannel().get(id_lien).charge -= ParametreSdf.Default_Paquets_Size;
                                    } else {
                                        etat_lien = false;
                                        Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_attente_envoie;
                                        ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_attente_envoie);
                                    }
                                }
                            } else if (paq.x_destination < i) {
                                x_suivant = i - 1;
                                y_suivant = j;
                                id_lien = Recherche_Lien(i, j, x_suivant, y_suivant);
                                if ((ParametreSdf.Debit - GenererChannel.getListChannel().get(id_lien).charge) >= ParametreSdf.Default_Paquets_Size) {
                                    paq.x_inter = x_suivant;
                                    paq.y_inter = y_suivant;
                                    GenererChannel.getListChannel().get(id_lien).liste_paquets.add(paq);
                                    Create_NOC.getNOC()[i][j].List_Paquet.remove(0);
                                    Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_envoi;
                                    ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_envoi);
                                    GenererChannel.getListChannel().get(id_lien).charge -= ParametreSdf.Default_Paquets_Size;
                                } else {
                                    etat_lien = false;
                                    Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_attente_envoie;
                                    ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_attente_envoie);
                                }
                            } else if (paq.x_destination > i) {
                                x_suivant = i + 1;
                                y_suivant = j;
                                id_lien = Recherche_Lien(i, j, x_suivant, y_suivant);
                                if ((ParametreSdf.Debit - GenererChannel.getListChannel().get(id_lien).charge) >= ParametreSdf.Default_Paquets_Size) {
                                    paq.x_inter = x_suivant;
                                    paq.y_inter = y_suivant;
                                    GenererChannel.getListChannel().get(id_lien).liste_paquets.add(paq);
                                    Create_NOC.getNOC()[i][j].List_Paquet.remove(0);
                                    Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_envoi;
                                    ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_envoi);
                                    GenererChannel.getListChannel().get(id_lien).charge -= ParametreSdf.Default_Paquets_Size;
                                } else {
                                    etat_lien = false;
                                    Create_NOC.getNOC()[i][j].Energie_routage += StaticParametre.Energie_attente_envoie;
                                    ParametreSdf.listApplicationSdf.get(paq.idapp).setEnergie(StaticParametre.Energie_attente_envoie);
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 1; i < GenererChannel.getListChannel().size() + 1; i++) {
                while (!GenererChannel.getListChannel().get(i).liste_paquets.isEmpty()) {
                    Paquet paq1 = GenererChannel.getListChannel().get(i).liste_paquets.get(0);
                    Create_NOC.getNOC()[paq1.x_inter][paq1.y_inter].List_Paquet.add(paq1);
                    GenererChannel.getListChannel().get(i).liste_paquets.remove(0);
                }
                GenererChannel.getListChannel().get(i).charge = 0;
            }
            routage = false;
            for (int i = 0; i < StaticParametre.LEGNHT_NOC; i++) {
                for (int j = 0; j < StaticParametre.LEGNHT_NOC; j++) {
                    if (!Create_NOC.getNOC()[i][j].List_Paquet.isEmpty()) {
                        routage = true;
                    }
                }
            }
            if (!ParametreSdf.T_next_event.isEmpty()) {
                if ((ParametreSdf.T_next_event.first() - ParametreSdf.Tnow) <= StaticParametre.Temps_envoie) {
                    ParametreSdf.Tnow = ParametreSdf.T_next_event.first();
                    ParametreSdf.T_next_event.pollFirst();
                    Proc.Manager.placementSdf.star();
                } else {
                    ParametreSdf.Tnow += StaticParametre.Temps_envoie;
                }
            } else {
                ParametreSdf.Tnow += StaticParametre.Temps_envoie;
            }
            for (int i = 0; i < ParametreSdf.listApplicationSdf.size(); i++) {
                for (int j = 0; j < ParametreSdf.listApplicationSdf.get(i).getListActor().size(); j++) {
                    if (ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat == 5) {
                        boolean enroutage = false;
                        for (int x = 0; x < StaticParametre.LEGNHT_NOC; x++) {
                            for (int y = 0; y < StaticParametre.LEGNHT_NOC; y++) {
                                if (!Create_NOC.getNOC()[x][y].List_Paquet.isEmpty()) {
                                    for (int s = 0; s < Create_NOC.getNOC()[x][y].List_Paquet.size(); s++) {
                                        if (Create_NOC.getNOC()[x][y].List_Paquet.get(s).idapp == i && Create_NOC.getNOC()[x][y].List_Paquet.get(s).idact == j) {
                                            enroutage = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (enroutage == false) {
                            ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat = 4;
                            Create_NOC.getNOC()[ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).x][ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).y].set_state(true);
                            ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).fin_execution = ParametreSdf.Tnow;
                        }
                    }
                }
            }
            System.out.println(" boucle routage time : " + ParametreSdf.Tnow);
        }
    }

    private static int Recherche_Lien(int x_source, int y_source, int x_suivant, int y_suivant) {
        int id = -1, source, destination;
        source = Create_NOC.getNOC()[x_source][y_source].getId();
        destination = Create_NOC.getNOC()[x_suivant][y_suivant].getId();
        for (int i = 1; i <= GenererChannel.getListChannel().size(); i++) {
            if ((GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) || GenererChannel.getListChannel().get(i).getSource() == destination && GenererChannel.getListChannel().get(i).getVoisin() == source) {
                id = GenererChannel.getListChannel().get(i).getIdLien();
                break;
            }
        }
        return id;
    }
}
