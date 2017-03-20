package Architecture;

import java.util.Map;
import java.util.TreeMap;
import Simulation.StaticParametre;

public class GenererChannel {

    private static Map<Integer, LienNoc> listChannel = null;

    public GenererChannel() {
        listChannel = new TreeMap<Integer, LienNoc>();
    }

    public void start() {
        int id_channel = 0;
        int nbrNode = (int) Math.pow(StaticParametre.LEGNHT_NOC, 2);
        for (int i = 0; i < nbrNode; i++) {
            LienNoc lien = null;
            int id = StaticParametre.listProcesseur.get(i).getId();
            int taille_NOC = StaticParametre.LEGNHT_NOC;
            if (StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y == 0) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y > 0 && StaticParametre.listProcesseur.get(i).y < taille_NOC - 1) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y == taille_NOC - 1) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x < taille_NOC - 1 && StaticParametre.listProcesseur.get(i).y == 0) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x < taille_NOC - 1 && StaticParametre.listProcesseur.get(i).y > 0 && StaticParametre.listProcesseur.get(i).y < taille_NOC - 1) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x < taille_NOC - 1 && StaticParametre.listProcesseur.get(i).y == taille_NOC - 1) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + taille_NOC] = id_channel;
                StaticParametre.getMatriceChannel()[id + taille_NOC][id] = id_channel;
                lien = new LienNoc(id, id + taille_NOC, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x == taille_NOC - 1 && StaticParametre.listProcesseur.get(i).y == 0) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
            } else if (StaticParametre.listProcesseur.get(i).x == taille_NOC - 1 && StaticParametre.listProcesseur.get(i).y > 0 && StaticParametre.listProcesseur.get(i).y < taille_NOC - 1) {
                id_channel++;
                StaticParametre.getMatriceChannel()[id][id + 1] = id_channel;
                StaticParametre.getMatriceChannel()[id + 1][id] = id_channel;
                lien = new LienNoc(id, id + 1, id_channel);
                listChannel.put(id_channel, lien);
            }
        }
    }

    public static synchronized Map<Integer, LienNoc> getListChannel() {
        return listChannel;
    }
}
