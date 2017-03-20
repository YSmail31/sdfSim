package Proc.Manager;

import Simulation.SimulatorSdf;
import Simulation.StaticParametre;
import Simulation.ParametreSdf;
import Architecture.Create_NOC;
import Routage_Algorithme.Paquet;

public class conevtion_jeton_paquet {

    public conevtion_jeton_paquet(int id_app, int id_tache, int x_source, int y_source, int x_destination, int y_destination, int idport) {
        int taille_jeton = 0, nbjeton = 0, id_channel = -1, nbpaquet = 0;
        int taille_paquet = ParametreSdf.Default_Paquets_Size;
        Paquet paq = new Paquet(x_source, y_source, x_destination, y_destination, id_app, id_tache);
        paq.set_source(x_source, y_source);
        id_channel = ParametreSdf.listApplicationSdf.get(id_app).getListActor().get(id_tache).Ports.get(idport).idchannel;
        taille_jeton = ParametreSdf.listApplicationSdf.get(id_app).getListChannel().get(id_channel).tokenSize;
        nbjeton = ParametreSdf.listApplicationSdf.get(id_app).getListActor().get(id_tache).Ports.get(idport).rate;
        nbpaquet = (taille_jeton / taille_paquet + 1) * nbjeton;
        for (int j = 0; j < nbpaquet; j++) {
            Create_NOC.getNOC()[x_source][y_source].List_Paquet.add(paq);
        }
        ParametreSdf.listApplicationSdf.get(id_app).getListChannel().get(id_channel).nb_Token -= nbjeton;
    }
}
