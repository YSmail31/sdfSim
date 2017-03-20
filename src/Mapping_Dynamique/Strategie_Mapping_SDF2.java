package Mapping_Dynamique;

import Application.Actor;
import Architecture.Create_NOC;
import Architecture.ProcessorElement;
import Simulation.ParametreSdf;
import Simulation.StaticParametre;

public class Strategie_Mapping_SDF2 {

    public static int[] FF(int idcluster, int idapp, Integer idact) {
        int pos[] = new int[2];
        pos[0] = -1;
        pos[1] = -1;
        Actor act = new Actor();
        act = ParametreSdf.listApplicationSdf.get(idapp).getListActor().get(idact);
        xxx: for (int i = 0; i < StaticParametre.LEGNHT_NOC; i++) {
            for (int j = 0; j < StaticParametre.LEGNHT_NOC; j++) {
                ParametreSdf.nb_pe_consulter += 1;
                if (test(act, Create_NOC.getNOC()[i][j]) && Create_NOC.getNOC()[i][j].getFree()) {
                    pos[0] = i;
                    pos[1] = j;
                    break xxx;
                }
            }
        }
        return pos;
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
