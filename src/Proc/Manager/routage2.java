package Proc.Manager;

import Application.Tache;
import Routage_Algorithme.Methode_Routage;
import Routage_Algorithme.Methode_rout2;
import Routage_Algorithme.Paquet;
import Simulation.StaticParametre;

public class routage2 {

    Tache tache_fils, tache_pere;

    public routage2(int Tnow) {
        for (int j = 0; j < StaticParametre.application_en_cours.length; j++) {
            int c;
            for (int i = 0; i < StaticParametre.application_en_cours[j].getListTache().size(); i++) {
                tache_pere = StaticParametre.application_en_cours[j].getListTache().get(i);
                for (int k = 0; k < tache_pere.getSucc().size(); k++) {
                    c = tache_pere.getSucc().get(k);
                    tache_fils = StaticParametre.application_en_cours[j].getListTache().get(c);
                    {
                        if (tache_fils.debut_execution == Tnow) {
                            for (int r = 0; r < tache_pere.getDonneePartager(k) / StaticParametre.DEBIT; r++) {
                                Paquet p = new Paquet(tache_pere.x, tache_pere.y, tache_fils.x, tache_fils.y, 0, 0);
                                if (r == (tache_pere.getDonneePartager(k) / StaticParametre.DEBIT) - 1)
                                    p.fin = true;
                                new Methode_rout2(tache_pere, tache_fils.x, tache_fils.y, k);
                            }
                        }
                    }
                }
            }
        }
    }
}
