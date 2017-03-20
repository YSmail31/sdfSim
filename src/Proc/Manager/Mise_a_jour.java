package Proc.Manager;

import Mapping_Dynamique.Methode_Placement;
import Routage_Algorithme.Methode_Routage;
import Simulation.Simulator;
import Simulation.StaticParametre;
import Application.Tache;
import Architecture.Create_NOC;
import Architecture.ProcessorElement;
import Architecture.GenererChannel;

public class Mise_a_jour {

    Tache tache_fils, tache_pere, tache_fils1;

    public Mise_a_jour(int Tnow) throws InterruptedException {
        for (int k = 0; k < StaticParametre.List_Paquet.size(); k++) if (StaticParametre.List_Paquet.get(k).T_fin == Tnow) {
            if (StaticParametre.List_Paquet.get(k).x_inter == StaticParametre.List_Paquet.get(k).x_destination && StaticParametre.List_Paquet.get(k).y_inter == StaticParametre.List_Paquet.get(k).y_destination) {
                for (int m = 0; m < StaticParametre.getListApplication().size(); m++) {
                    for (int n = 0; n < StaticParametre.getListApplication().get(m).getListTache().size(); n++) {
                        tache_pere = StaticParametre.getListApplication().get(m).getListTache().get(n);
                        if (Tnow == tache_pere.fin_root_pere) {
                            if (tache_pere.x2 == StaticParametre.getListApplication().get(m).getListTache().get(tache_pere.getIdPere()).x && tache_pere.y2 == StaticParametre.getListApplication().get(m).getListTache().get(tache_pere.getIdPere()).y) {
                                StaticParametre.getListApplication().get(m).getListTache().get(tache_pere.getIdPere()).set_communication_recu();
                                StaticParametre.List_Paquet.remove(k);
                            }
                        }
                    }
                }
                for (int m = 0; m < StaticParametre.getListApplication().size(); m++) {
                    int c;
                    for (int n = 0; n < StaticParametre.getListApplication().get(m).getListTache().size(); n++) {
                        tache_pere = StaticParametre.getListApplication().get(m).getListTache().get(n);
                        if (!tache_pere.getSucc().isEmpty()) {
                            for (int p = 0; p < tache_pere.getSucc().size(); p++) {
                                c = tache_pere.getSucc().get(p);
                                tache_fils = StaticParametre.listApplication.get(m).getListTache().get(c);
                                if (tache_pere.x1.get(p) == tache_fils.x && tache_pere.y1.get(p) == tache_fils.y && tache_pere.fin < tache_pere.getSucc().size() && Tnow == tache_pere.fin_routage.get(p) && tache_fils.end_root == false) {
                                    tache_fils.end_root = true;
                                    Launch(tache_fils);
                                    StaticParametre.List_Paquet.remove(k);
                                } else {
                                }
                            }
                        } else {
                            if (tache_pere.end_root && tache_pere.k == false) {
                                tache_pere.k = true;
                                Launch(tache_pere);
                            }
                        }
                    }
                }
            } else {
            }
        }
        for (int j = 0; j < StaticParametre.getListApplication().size(); j++) {
            int c;
            for (int i = 0; i < StaticParametre.getListApplication().get(j).getListTache().size(); i++) {
                tache_pere = StaticParametre.getListApplication().get(j).getListTache().get(i);
                if ((Tnow == tache_pere.fin_execution && tache_pere.getSucc().size() == 0) || (tache_pere.nbr_message == tache_pere.getSucc().size() && !tache_pere.getSucc().isEmpty() && tache_pere.verify_comm_fils(Tnow))) {
                    if (StaticParametre.MONO_MULTI.equals("MONO")) {
                        Create_NOC.getNOC()[tache_pere.x][tache_pere.y].set_state(true);
                    } else {
                        Create_NOC.getNOC()[tache_pere.x][tache_pere.y].setMem(tache_pere.getTailleTache(Create_NOC.getNOC()[tache_pere.x][tache_pere.y].getType()), 2);
                        for (int o = 0; o < Create_NOC.getNOC()[tache_pere.x][tache_pere.y].File.size(); o++) {
                            if (Create_NOC.getNOC()[tache_pere.x][tache_pere.y].File.get(o) == tache_pere)
                                Create_NOC.getNOC()[tache_pere.x][tache_pere.y].File.remove(o);
                        }
                    }
                    StaticParametre.listApplication.get(tache_pere.getIdApplication()).setEnergie((Tnow - tache_pere.fin_execution) * 2);
                    tache_pere.fin_execution = Tnow;
                    if (tache_pere.getId() == 0) {
                        StaticParametre.setEnd_Application();
                        Simulator.setEnergy(StaticParametre.listApplication.get(tache_pere.getIdApplication()).getEnergie());
                        System.out.println("Clusterrr " + StaticParametre.getListApplication().get(tache_pere.getIdApplication()).getIdCluster() + "  size des apps   " + StaticParametre.getListApplication().size() + "   size des clust  " + StaticParametre.listClusters.length);
                        placement.launch_new_application(StaticParametre.getListApplication().get(tache_pere.getIdApplication()).getIdCluster());
                    }
                    if (i != 0)
                        new Methode_Routage(tache_pere, StaticParametre.getListApplication().get(j).getListTache().get(tache_pere.getIdPere()).x, StaticParametre.getListApplication().get(j).getListTache().get(tache_pere.getIdPere()).y, -2);
                    System.out.println("end  de la tache  " + i + " id app  " + j + "    !!!!!!!!!!!!!!!!!!!!!!!!!     Tnow   " + Tnow + "                  fin execution    " + tache_pere.fin_execution);
                    if (!StaticParametre.not_mapped.isEmpty()) {
                        for (int k = 0; k < StaticParametre.not_mapped.size(); k++) {
                            int r;
                            tache_fils = StaticParametre.not_mapped.get(k);
                            for (r = 0; r < StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).getSucc().size(); r++) {
                                c = StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).getSucc().get(r);
                                tache_fils1 = StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(c);
                                if (tache_fils1.equals(tache_fils)) {
                                    break;
                                }
                            }
                            new Methode_Placement(tache_fils1, Create_NOC.getNOC()[StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x][StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y]);
                            if (tache_fils.debut_execution != -1) {
                                Simulator.Add_Event(tache_fils.debut_execution);
                                StaticParametre.not_mapped.remove(k);
                                tache_fils.y2 = tache_fils.y;
                                tache_fils.x2 = tache_fils.x;
                                StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x1.set(r, StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x);
                                StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y1.set(r, StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= 112; i++) {
            for (int j = 0; j < GenererChannel.getListChannel().get(i).event.size(); j++) {
                if (GenererChannel.getListChannel().get(i).event.get(j) == Tnow) {
                    GenererChannel.getListChannel().get(i).set_file();
                }
            }
        }
    }

    public void Launch(Tache t) throws InterruptedException {
        ProcessorElement processeur = Create_NOC.getNOC()[t.x][t.y];
        processeur.ajoutTache(t);
        Simulator.Add_Event(t.fin_execution);
    }
}
