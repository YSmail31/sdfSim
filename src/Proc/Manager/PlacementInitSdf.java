package Proc.Manager;

import java.util.Collections;
import javax.swing.JOptionPane;
import Simulation.ParametreSdf;
import Simulation.StaticParametre;
import Architecture.*;
import Mapping_Dynamique.Strategie_Mapping_SDF1;
import Mapping_Dynamique.Strategie_Mapping_SDF2;
import Mapping_Dynamique.Strategie_Mapping_SDF3;
import Mapping_Dynamique.Strategie_Mapping_SDF4;
import Mapping_Dynamique.Strategie_Mapping_SDF5;
import Application.*;
import Routage_Algorithme.*;

public class PlacementInitSdf {

    public static void Start() throws InterruptedException {
        boolean a;
        int b[] = new int[2];
        for (int i = 0; i < ParametreSdf.listApplicationSdf.size(); i++) {
            for (int j = 0; j < ParametreSdf.listApplicationSdf.get(i).getListActor().size(); j++) {
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
        for (int i = 0; i < Math.min(ParametreSdf.listApplicationSdf.size(), StaticParametre.nbcluster); i++) {
            for (int j = 0; j < ParametreSdf.listactAttente.size(); j++) {
                if (i == ParametreSdf.listactAttente.get(j)[0]) {
                    if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_MANHATAN") {
                        b = Strategie_Mapping_SDF1.Init(i, i, ParametreSdf.listactAttente.get(j)[1]);
                    } else if (ParametreSdf.ALGORITHME_PLACEMENT == "FF") {
                        b = Strategie_Mapping_SDF2.FF(i, i, ParametreSdf.listactAttente.get(j)[1]);
                    } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_MANHATAN") {
                        b = Strategie_Mapping_SDF3.Init(i, i, ParametreSdf.listactAttente.get(j)[1]);
                        System.out.println("init " + b[0] + " y " + b[1]);
                    } else if (ParametreSdf.ALGORITHME_PLACEMENT == "NN_GBHD") {
                        b = Strategie_Mapping_SDF4.Init(i, i, ParametreSdf.listactAttente.get(j)[1]);
                        System.out.println("*******placement init ");
                    } else if (ParametreSdf.ALGORITHME_PLACEMENT == "BN_GBHD") {
                        b = Strategie_Mapping_SDF5.Init(i, i, ParametreSdf.listactAttente.get(j)[1]);
                    }
                    if ((b[0] >= 0) && (b[1] >= 0)) {
                        StaticParametre.listClusters[i].Free = false;
                        ParametreSdf.listApplicationSdf.get(i).setIdCluster(i);
                        ParametreSdf.listApplicationSdf.get(i).etat = 2;
                        ParametreSdf.listApplicationSdf.get(i).temp_debut = ParametreSdf.Tnow;
                        ParametreSdf.listApplicationSdf.get(i).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).x = b[0];
                        ParametreSdf.listApplicationSdf.get(i).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).y = b[1];
                        ParametreSdf.listApplicationSdf.get(i).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).etat = 3;
                        ParametreSdf.listApplicationSdf.get(i).getListActor().get(ParametreSdf.listactAttente.get(j)[1]).debut_execution = ParametreSdf.Tnow;
                        Actor tache = new Actor();
                        tache = ParametreSdf.listApplicationSdf.get(i).getListActor().get(ParametreSdf.listactAttente.get(j)[1]);
                        Create_NOC.getNOC()[tache.x][tache.y].set_state(false);
                        Create_NOC.getNOC()[tache.x][tache.y].ajoutActor(tache);
                        ;
                        ParametreSdf.listactcours.add(ParametreSdf.listactAttente.get(j));
                        int x_source, y_source, x_destination, y_destination;
                        x_source = StaticParametre.LEGNHT_NOC / 2;
                        y_source = StaticParametre.LEGNHT_NOC / 2;
                        x_destination = tache.x;
                        y_destination = tache.y;
                        for (int s = 0; s < tache.Ports.size(); s++) {
                            if (tache.Ports.get(s).type.equals("in")) {
                                new conevtion_jeton_paquet(i, tache.id, x_source, y_source, x_destination, y_destination, s);
                            }
                        }
                    }
                }
            }
        }
        int z = 0;
        while (z < ParametreSdf.listactAttente.size()) {
            if (ParametreSdf.listApplicationSdf.get(ParametreSdf.listactAttente.get(z)[0]).getListActor().get(ParametreSdf.listactAttente.get(z)[1]).etat == 3) {
                ParametreSdf.listactAttente.remove(z);
                z = 0;
            } else {
                z += 1;
            }
        }
        new RoutageSdf();
        int type_pe, a2, b2;
        for (int j = 0; j < ParametreSdf.listactcours.size(); j++) {
            a2 = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).x;
            b2 = ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).y;
            type_pe = Create_NOC.getNOC()[a2][b2].getType();
            for (int s = 0; s < ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Processors.size(); s++) {
                if (type_pe == ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Processors.get(s).id_type) {
                    ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).fin_execution = ParametreSdf.Tnow + ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).Processors.get(s).time;
                }
            }
            ParametreSdf.T_next_event.add(ParametreSdf.listApplicationSdf.get(ParametreSdf.listactcours.get(j)[0]).getListActor().get(ParametreSdf.listactcours.get(j)[1]).fin_execution);
        }
    }
}
