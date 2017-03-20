package Proc.Manager;

import java.util.LinkedList;
import Application.Actor;
import Architecture.Create_NOC;
import Mapping_Dynamique.Strategie_Mapping_SDF1;
import Mapping_Dynamique.Strategie_Mapping_SDF2;
import Mapping_Dynamique.Strategie_Mapping_SDF3;
import Mapping_Dynamique.Strategie_Mapping_SDF4;
import Mapping_Dynamique.Strategie_Mapping_SDF5;
import Routage_Algorithme.RoutageSdf;
import Routage_Algorithme.RoutageSdf_MORA;
import Simulation.ParametreSdf;
import Simulation.StaticParametre;

public class placementSdf {

    public static void star() throws InterruptedException {
        int xpe, ype, idlien, rat;
        for (int j = 0; j < ParametreSdf.listactcours.size(); j++) {
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).fin_execution == ParametreSdf.Tnow) {
                xpe = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).x;
                ype = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).y;
                ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).etat = 5;
                ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).iteration -= 1;
                for (int s = 0; s < ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Ports.size(); s++) {
                    if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Ports.get(s).type.equals("out")) {
                        idlien = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Ports.get(s).idchannel;
                        rat = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Ports.get(s).rate;
                        ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListChannel().get(idlien).nb_Token += rat;
                    }
                }
                boolean clstat = true;
                for (int j2 = 0; j2 < ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().size(); j2++) {
                    if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(j2).iteration > 0) {
                        clstat = false;
                        break;
                    }
                }
                if (clstat == true) {
                    if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getIdCluster() >= 0) {
                        StaticParametre.listClusters[ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getIdCluster()].Free = true;
                    }
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).etat = 3;
                }
            }
        }
        int aze = 0;
        while (aze < ParametreSdf.listactcours.size()) {
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(aze)[0]).getListActor().get(ParametreSdf.listactcours.get(aze)[1]).etat == 4) {
                ParametreSdf.listactcours.remove(aze);
                aze = 0;
            } else {
                aze += 1;
            }
        }
        boolean a;
        int b[] = new int[2];
        for (int i = 0; i < ParametreSdf.listApplicationSdf.size(); i++) {
            for (int j = 0; j < ParametreSdf.listApplicationSdf.get(i).getListActor().size(); j++) {
                if (ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).iteration > 0 && ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat != 2 && ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat != 3 && ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat != 5) {
                    a = true;
                    for (int s = 0; s < ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).Ports.size(); s++) {
                        if (ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).Ports.get(s).type.equals("in")) {
                            int idchan = ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).Ports.get(s).idchannel;
                            int rate = ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).Ports.get(s).rate;
                            int nb_token = ParametreSdf.listApplicationSdf.get(i).getListChannel().get(idchan).nb_Token;
                            if (rate > nb_token) {
                                a = false;
                            }
                        }
                    }
                    if (a == true) {
                        ParametreSdf.listactAttente.add(new Integer[] { i, j });
                        ParametreSdf.listApplicationSdf.get(i).getListActor().get(j).etat = 2;
                    }
                }
            }
        }
        for (int j = 0; j < ParametreSdf.listactAttente.size(); j++) {
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).etat == 2) {
                if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_MANHATAN") {
                    b = Strategie_Mapping_SDF1.Dynamique(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getIdCluster(), ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "FF") {
                    b = Strategie_Mapping_SDF2.FF(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getIdCluster(), ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_MANHATAN") {
                    b = Strategie_Mapping_SDF3.Dynamique(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getIdCluster(), ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_GBHD") {
                    b = Strategie_Mapping_SDF4.Dynamique(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getIdCluster(), ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                    System.out.println("*******placement dyn sur x " + b[0] + " y " + b[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_GBHD") {
                    b = Strategie_Mapping_SDF5.Dynamique(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getIdCluster(), ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                }
                if ((b[0] >= 0) && (b[1] >= 0)) {
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).x = b[0];
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).y = b[1];
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).etat = 3;
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).debut_execution = ParametreSdf.Tnow;
                    Actor tache = new Actor();
                    tache = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]);
                    Create_NOC.getNOC()[tache.x][tache.y].set_state(false);
                    Create_NOC.getNOC()[tache.x][tache.y].ajoutActor(tache);
                    ;
                    ParametreSdf.listactcours.add(ParametreSdf.listactAttente.get(j));
                    for (int s = 0; s < tache.Ports.size(); s++) {
                        if (tache.Ports.get(s).type.equals("in")) {
                            int x_source, y_source, x_destination, y_destination, e, z;
                            e = tache.Ports.get(s).idchannel;
                            z = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListChannel().get(e).idsrcActor;
                            if (z == tache.id || ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).x == -1) {
                                x_source = StaticParametre.LEGNHT_NOC / 2;
                                y_source = StaticParametre.LEGNHT_NOC / 2;
                                x_destination = tache.x;
                                y_destination = tache.y;
                            } else {
                                x_source = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).x;
                                y_source = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).y;
                                x_destination = tache.x;
                                y_destination = tache.y;
                            }
                            new conevtion_jeton_paquet(ParametreSdf.listactAttente.get(j)[0], tache.id, x_source, y_source, x_destination, y_destination, s);
                        }
                    }
                } else {
                    System.out.println("  *********** ******* *******impossible de mapper la tache " + ParametreSdf.listactAttente.get(j)[1]);
                }
            }
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).etat == 1) {
                int idcl = -1;
                for (int s = 0; s < StaticParametre.nbcluster; s++) {
                    if (StaticParametre.listClusters[s].Free) {
                        idcl = s;
                        break;
                    }
                }
                if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_MANHATAN") {
                    b = Strategie_Mapping_SDF1.Dynamique(idcl, ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "FF") {
                    b = Strategie_Mapping_SDF2.FF(idcl, ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_MANHATAN") {
                    b = Strategie_Mapping_SDF3.Dynamique(idcl, ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_GBHD") {
                    b = Strategie_Mapping_SDF4.Dynamique(idcl, ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                    System.out.println("*******placement dyn ");
                } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_GBHD") {
                    b = Strategie_Mapping_SDF5.Dynamique(idcl, ParametreSdf.listactAttente.get(j)[0], ParametreSdf.listactAttente.get(j)[1]);
                }
                System.out.println("placement dyn app " + ParametreSdf.listactAttente.get(j)[0] + " tache " + ParametreSdf.listactAttente.get(j)[1]);
                if ((b[0] >= 0) && (b[1] >= 0)) {
                    if (idcl >= 0) {
                        ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).setIdCluster(idcl);
                        StaticParametre.listClusters[idcl].set_Free(false);
                    }
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).x = b[0];
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).y = b[1];
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).etat = 3;
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).debut_execution = ParametreSdf.Tnow;
                    Actor tache = new Actor();
                    tache = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(j)[0]).getListActor().get(ParametreSdf.listactAttente.get(j)[1]);
                    Create_NOC.getNOC()[tache.x][tache.y].set_state(false);
                    Create_NOC.getNOC()[tache.x][tache.y].ajoutActor(tache);
                    ;
                    ParametreSdf.listactcours.add(ParametreSdf.listactAttente.get(j));
                    for (int s = 0; s < tache.Ports.size(); s++) {
                        if (tache.Ports.get(s).type.equals("in")) {
                            int x_source, y_source, x_destination, y_destination, e, z;
                            e = tache.Ports.get(s).idchannel;
                            z = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListChannel().get(e).idsrcActor;
                            if (z == tache.id || ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).x == -1) {
                                x_source = StaticParametre.LEGNHT_NOC / 2;
                                y_source = StaticParametre.LEGNHT_NOC / 2;
                                x_destination = tache.x;
                                y_destination = tache.y;
                            } else {
                                x_source = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).x;
                                y_source = ParametreSdf.listApplicationSdf.get(tache.idApplication).getListActor().get(z).y;
                                x_destination = tache.x;
                                y_destination = tache.y;
                            }
                            new conevtion_jeton_paquet(ParametreSdf.listactAttente.get(j)[0], tache.id, x_source, y_source, x_destination, y_destination, s);
                        }
                    }
                } else {
                    System.out.println("impossible de mapper la tache");
                }
            }
        }
        int z = 0;
        LinkedList<Integer[]> listtemp = new LinkedList<Integer[]>();
        ;
        while (z < ParametreSdf.listactAttente.size()) {
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(z)[0]).getListActor().get(ParametreSdf.listactAttente.get(z)[1]).etat == 3) {
                listtemp.add(ParametreSdf.listactAttente.get(z));
                ParametreSdf.listactAttente.remove(z);
                z = 0;
            } else {
                z += 1;
            }
        }
        new RoutageSdf();
        int type_pe, a2, b2;
        for (int j = 0; j < listtemp.size(); j++) {
            a2 = ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).x;
            b2 = ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).y;
            type_pe = Create_NOC.getNOC()[a2][b2].getType();
            for (int s = 0; s < ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).Processors.size(); s++) {
                if (type_pe == ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).Processors.get(s).id_type) {
                    ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).fin_execution = ParametreSdf.Tnow + ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).Processors.get(s).time;
                }
            }
            ParametreSdf.T_next_event.add(ParametreSdf.listApplicationSdf.get(listtemp.get(j)[0]).getListActor().get(listtemp.get(j)[1]).fin_execution);
        }
    }
}
