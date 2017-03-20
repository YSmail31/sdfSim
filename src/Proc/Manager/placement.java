package Proc.Manager;

import Application.Tache;
import Architecture.Create_NOC;
import Architecture.ProcessorElement;
import Mapping_Dynamique.Methode_Placement;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class placement {

    Tache tache_fils;

    public placement(int Tnow) throws InterruptedException {
        if (Tnow == 0) {
            if (StaticParametre.NOMBRE_APPLICATION >= StaticParametre.getListCluster().length) {
                for (int i = 0; i < StaticParametre.getListCluster().length; i++) {
                    StaticParametre.application_en_cours[i] = StaticParametre.applicationAttente.getFirst();
                    PlacementStatic(i);
                }
            } else {
                for (int i = 0; i < StaticParametre.NOMBRE_APPLICATION; i++) {
                    StaticParametre.application_en_cours[i] = (StaticParametre.applicationAttente.getFirst());
                    PlacementStatic(i);
                }
            }
        } else {
            for (int j = 0; j < StaticParametre.application_en_cours.length; j++) {
                int c;
                for (int i = 0; i < StaticParametre.application_en_cours[j].getListTache().size(); i++) {
                    Tache tache = new Tache();
                    tache = StaticParametre.application_en_cours[j].getListTache().get(i);
                    if (Tnow == (tache.fin_execution)) {
                        if (!tache.getSucc().isEmpty()) {
                            System.out.println("placement des fils  de la tache  " + i + " de l application  " + j);
                            for (int k = 0; k < tache.getSucc().size(); k++) {
                                c = tache.getSucc().get(k);
                                tache_fils = StaticParametre.application_en_cours[j].getListTache().get(c);
                                tache_fils.setIdPere(i);
                                new Methode_Placement(tache_fils, Create_NOC.getNOC()[tache.x][tache.y]);
                                if (tache_fils.debut_execution != -1) {
                                    Simulator.Add_Event(tache_fils.debut_routage);
                                    tache_fils.x2 = tache_fils.x;
                                    tache_fils.y2 = tache_fils.y;
                                    tache.x1.set(k, tache.x);
                                    tache.y1.set(k, tache.y);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void PlacementStatic(int id_cluster) throws InterruptedException {
        StaticParametre.listClusters[id_cluster].Free = false;
        StaticParametre.applicationAttente.getFirst().setIdCluster(id_cluster);
        StaticParametre.application_en_cours[id_cluster] = StaticParametre.applicationAttente.getFirst();
        Tache tacheInit = new Tache();
        tacheInit = StaticParametre.applicationAttente.getFirst().getListTache().get(0);
        tacheInit.x = StaticParametre.listClusters[id_cluster].xCentre;
        tacheInit.y = StaticParametre.listClusters[id_cluster].yCentre;
        ProcessorElement processeurInit = (ProcessorElement) Create_NOC.getNOC()[tacheInit.x][tacheInit.y];
        tacheInit.debut_execution = Simulator.Tnow;
        if (StaticParametre.MONO_MULTI.equals("MONO"))
            processeurInit.set_state(false);
        else
            processeurInit.setMem(tacheInit.getTailleTache(processeurInit.getType()), 1);
        if (processeurInit.getType() != 1 && processeurInit.getType() != 0) {
            System.out.println("Milieu du cluster n'est pas une ressource logicielle");
            System.exit(0);
        }
        processeurInit.ajoutTache(tacheInit);
        Simulator.Add_Event(tacheInit.fin_execution);
        for (int k = 0; k <= tacheInit.getSucc().size(); k++) {
            tacheInit.x1.set(k, tacheInit.x);
            tacheInit.y1.set(k, tacheInit.y);
        }
        StaticParametre.applicationAttente.removeFirst();
    }

    public static void launch_new_application(int idcluster) throws InterruptedException {
        if (!StaticParametre.applicationAttente.isEmpty()) {
            for (int i = 0; i < StaticParametre.application_en_cours.length; i++) {
                if (StaticParametre.application_en_cours[i].getIdCluster() == idcluster) {
                    PlacementStatic(i);
                }
            }
        }
    }
}
