package Architecture;

import Simulation.StaticParametre;

public class CreationCluster {

    private static boolean[] etat_cluster;

    public CreationCluster() {
        etat_cluster = null;
    }

    public static void creation_cluster() {
        int tailleCluster = StaticParametre.TAILLE_CLUSTER;
        int id = 0;
        int r = 0;
        for (int i = 0; i < StaticParametre.LEGNHT_NOC; i = i + tailleCluster) {
            for (int j = 0; j < StaticParametre.LEGNHT_NOC; j = j + tailleCluster) {
                if (i + tailleCluster <= StaticParametre.LEGNHT_NOC && j + tailleCluster <= StaticParametre.LEGNHT_NOC) {
                    Cluster c = new Cluster();
                    c.setCoordonneesDebut(i, j);
                    c.setCoordonneesFin(i + 2, j + 2);
                    c.setCenter(i, i + 2, j, j + 2);
                    c.setId(id);
                    StaticParametre.listClusters[r] = c;
                } else if (i + tailleCluster > StaticParametre.LEGNHT_NOC && j + tailleCluster <= StaticParametre.LEGNHT_NOC) {
                    int dif = tailleCluster - (StaticParametre.LEGNHT_NOC - i);
                    Cluster c = new Cluster();
                    c.setCoordonneesDebut(i - dif, j);
                    c.setCoordonneesFin((i - dif) + 2, j + 2);
                    c.setCenter(i - dif, (i - dif) + 2, j, j + 2);
                    c.setId(id);
                    StaticParametre.listClusters[r] = c;
                } else if (j + tailleCluster > StaticParametre.LEGNHT_NOC && i + tailleCluster <= StaticParametre.LEGNHT_NOC) {
                    int dif = tailleCluster - (StaticParametre.LEGNHT_NOC - j);
                    Cluster c = new Cluster();
                    c.setCoordonneesDebut(i, (j - dif));
                    c.setCoordonneesFin(i + 2, (j - dif) + 2);
                    c.setCenter(i, i + 2, (j - dif), (j - dif) + 2);
                    c.setId(id);
                    StaticParametre.listClusters[r] = c;
                } else if (j + tailleCluster > StaticParametre.LEGNHT_NOC && i + tailleCluster > StaticParametre.LEGNHT_NOC) {
                    int dif = tailleCluster - (StaticParametre.LEGNHT_NOC - j);
                    int dif1 = tailleCluster - (StaticParametre.LEGNHT_NOC - i);
                    Cluster c = new Cluster();
                    c.setCoordonneesDebut((i - dif1), (j - dif));
                    c.setCoordonneesFin((i - dif1) + 2, (j - dif + 2));
                    c.setId(id);
                    c.setCenter(i - dif1, (i - dif1) + 2, (j - dif), (j - dif) + 2);
                    StaticParametre.listClusters[r] = c;
                }
                r++;
                id++;
            }
        }
        etat_cluster = new boolean[StaticParametre.listClusters.length];
    }

    public static void setEtatCluster(int id_cluster, boolean etat) {
        etat_cluster[id_cluster] = etat;
    }

    public static boolean[] getEtat_Cluster() {
        return etat_cluster;
    }
}
