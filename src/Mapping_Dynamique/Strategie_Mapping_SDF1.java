package Mapping_Dynamique;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import Simulation.ParametreSdf;
import Simulation.StaticParametre;
import Application.Actor;
import Architecture.*;

public class Strategie_Mapping_SDF1 {

    public static int[] Init(int id_cluster, int idapp, int id_actor) {
        int pos[] = new int[2];
        int pat = 1;
        pos[0] = -1;
        pos[1] = -1;
        if (Create_NOC.getNOC()[StaticParametre.listClusters[id_cluster].xCentre][StaticParametre.listClusters[id_cluster].yCentre].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(id_actor), Create_NOC.getNOC()[StaticParametre.listClusters[id_cluster].xCentre][StaticParametre.listClusters[id_cluster].yCentre])) {
            pos[0] = StaticParametre.listClusters[id_cluster].xCentre;
            pos[1] = StaticParametre.listClusters[id_cluster].yCentre;
            ParametreSdf.nb_pe_consulter += 1;
        } else {
            LinkedList<Integer[]> liste_pos = new LinkedList<Integer[]>();
            ;
            while (pat <= ((StaticParametre.TAILLE_CLUSTER - 1) * 2) && (pos[0] < 0 && pos[1] < 0)) {
                liste_pos = liste_pos_manhatan(pat, StaticParametre.listClusters[id_cluster].xCentre, StaticParametre.listClusters[id_cluster].yCentre);
                for (int i = 0; i < liste_pos.size(); i++) {
                    ParametreSdf.nb_pe_consulter += 1;
                    if (StaticParametre.listClusters[id_cluster].getxDebut() <= liste_pos.get(i)[0] && StaticParametre.listClusters[id_cluster].getyDebut() <= liste_pos.get(i)[1] && StaticParametre.listClusters[id_cluster].getxFin() >= liste_pos.get(i)[0] && StaticParametre.listClusters[id_cluster].getyFin() >= liste_pos.get(i)[1] && Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(id_actor), Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]])) {
                        pos[0] = liste_pos.get(i)[0];
                        pos[1] = liste_pos.get(i)[1];
                        break;
                    }
                }
                pat += 1;
            }
            if (pos[0] < 0 && pos[1] < 0) {
                pat = 1;
                while (pat <= ((StaticParametre.LEGNHT_NOC - 1) * 2) && (pos[0] < 0 && pos[1] < 0)) {
                    liste_pos = liste_pos_manhatan(pat, StaticParametre.listClusters[id_cluster].xCentre, StaticParametre.listClusters[id_cluster].yCentre);
                    for (int i = 0; i < liste_pos.size(); i++) {
                        ParametreSdf.nb_pe_consulter += 1;
                        if (Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(id_actor), Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]])) {
                            pos[0] = liste_pos.get(i)[0];
                            pos[1] = liste_pos.get(i)[1];
                            break;
                        }
                    }
                    pat += 1;
                }
            }
        }
        return pos;
    }

    public static int[] Dynamique(int idCluster, Integer idapp, Integer idact) {
        int pos[] = new int[2];
        int pat = 1;
        pos[0] = -1;
        pos[1] = -1;
        if (ParametreSdf.listApplicationSdf.get(idapp).etat == 1) {
            if (idCluster >= 0) {
                pos = Init(idCluster, idapp, idact);
            } else {
                LinkedList<Integer[]> liste_pos = new LinkedList<Integer[]>();
                ;
                pat = 1;
                while (pat <= ((StaticParametre.LEGNHT_NOC - 1) * 2) && (pos[0] < 0 && pos[1] < 0)) {
                    liste_pos = liste_pos_manhatan(pat, Create_NOC.getNOC()[StaticParametre.LEGNHT_NOC / 2][StaticParametre.LEGNHT_NOC / 2].x, Create_NOC.getNOC()[StaticParametre.LEGNHT_NOC / 2][StaticParametre.LEGNHT_NOC / 2].y);
                    for (int i = 0; i < liste_pos.size(); i++) {
                        ParametreSdf.nb_pe_consulter += 1;
                        if (Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact), Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]])) {
                            pos[0] = liste_pos.get(i)[0];
                            pos[1] = liste_pos.get(i)[1];
                            break;
                        }
                    }
                    pat += 1;
                }
            }
        } else {
            int xsrc, ysrc, idsrc = -1;
            idsrc = recherche_src(idapp, idact);
            xsrc = StaticParametre.LEGNHT_NOC / 2;
            ysrc = StaticParametre.LEGNHT_NOC / 2;
            if (ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idsrc).x >= 0 && ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idsrc).y >= 0) {
                xsrc = ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idsrc).x;
                ysrc = ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idsrc).y;
            }
            if (idCluster >= 0) {
                LinkedList<Integer[]> liste_pos = new LinkedList<Integer[]>();
                ;
                pat = 1;
                while (pat <= ((StaticParametre.LEGNHT_NOC - 1) * 2) && (pos[0] < 0 && pos[1] < 0)) {
                    liste_pos = liste_pos_manhatan(pat, xsrc, ysrc);
                    for (int i = 0; i < liste_pos.size(); i++) {
                        ParametreSdf.nb_pe_consulter += 1;
                        if (StaticParametre.listClusters[idCluster].getxDebut() <= liste_pos.get(i)[0] && StaticParametre.listClusters[idCluster].getyDebut() <= liste_pos.get(i)[1] && StaticParametre.listClusters[idCluster].getxFin() >= liste_pos.get(i)[0] && StaticParametre.listClusters[idCluster].getyFin() >= liste_pos.get(i)[1] && Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact), Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]])) {
                            pos[0] = liste_pos.get(i)[0];
                            pos[1] = liste_pos.get(i)[1];
                            break;
                        }
                    }
                    pat += 1;
                }
            }
            if (idCluster < 0 || pos[0] == -1) {
                LinkedList<Integer[]> liste_pos = new LinkedList<Integer[]>();
                ;
                pat = 1;
                while (pat <= ((StaticParametre.LEGNHT_NOC - 1) * 2) && (pos[0] < 0 && pos[1] < 0)) {
                    liste_pos = liste_pos_manhatan(pat, xsrc, ysrc);
                    for (int i = 0; i < liste_pos.size(); i++) {
                        ParametreSdf.nb_pe_consulter += 1;
                        if (Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]].getFree() && test(ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact), Create_NOC.getNOC()[liste_pos.get(i)[0]][liste_pos.get(i)[1]])) {
                            pos[0] = liste_pos.get(i)[0];
                            pos[1] = liste_pos.get(i)[1];
                            break;
                        }
                    }
                    pat += 1;
                }
            }
        }
        return pos;
    }

    private static int recherche_src(Integer idapp, Integer idact) {
        int rate = 1, idlien, idsrcact = -1, data = 0;
        for (int s = 0; s < ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact).Ports.size(); s++) {
            if (ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact).Ports.get(s).type.equals("in")) {
                rate = ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact).Ports.get(s).rate;
                idlien = ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact).Ports.get(s).idchannel;
                if ((rate * ParametreSdf.listApplicationSdf.get(idapp).getListChannel().get(idlien).tokenSize) > data) {
                    data = rate * ParametreSdf.listApplicationSdf.get(idapp).getListChannel().get(idlien).tokenSize;
                    idsrcact = ParametreSdf.listApplicationSdf.get(idapp).getListChannel().get(idlien).idsrcActor;
                }
            }
        }
        return idsrcact;
    }

    private static LinkedList<Integer[]> liste_pos_manhatan(int pat, int x, int y) {
        LinkedList<Integer[]> temp = new LinkedList<Integer[]>();
        ;
        for (int i = 0; i < pat; i++) {
            if (((x - i) < StaticParametre.LEGNHT_NOC) && ((y - pat + i) < StaticParametre.LEGNHT_NOC) && ((x - i) >= 0) && ((y - pat + i) >= 0)) {
                temp.add(new Integer[] { x - i, y - pat + i });
            }
        }
        for (int i = 0; i < pat; i++) {
            if (((x - pat + i) < StaticParametre.LEGNHT_NOC) && ((y + i) < StaticParametre.LEGNHT_NOC) && ((x - pat + i) >= 0) && ((y + i) >= 0)) {
                temp.add(new Integer[] { x - pat + i, y + i });
            }
        }
        for (int i = 0; i < pat; i++) {
            if (((x + i) < StaticParametre.LEGNHT_NOC) && ((y + pat - i) < StaticParametre.LEGNHT_NOC) && ((x + i) >= 0) && ((y + pat - i) >= 0)) {
                temp.add(new Integer[] { x + i, y + pat - i });
            }
        }
        for (int i = 0; i < pat; i++) {
            if (((x + pat - i) < StaticParametre.LEGNHT_NOC) && ((y - i) < StaticParametre.LEGNHT_NOC) && ((x + pat - i) >= 0) && ((y - i) >= 0)) {
                temp.add(new Integer[] { x + pat - i, y - i });
            }
        }
        return temp;
    }

    private static boolean test(Actor ACT, ProcessorElement PE) {
        boolean etat = false;
        for (int i = 0; i < ACT.Processors.size(); i++) {
            if (ACT.Processors.get(i).id_type == PE.getType()) {
                etat = true;
            }
        }
        return etat;
    }
}
