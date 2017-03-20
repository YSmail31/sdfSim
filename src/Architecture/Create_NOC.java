package Architecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import Simulation.StaticParametre;

public class Create_NOC {

    private static ProcessorElement[][] NOC;

    public static void creationNoc() throws IOException {
        NOC = new ProcessorElement[StaticParametre.LEGNHT_NOC][StaticParametre.LEGNHT_NOC];
        BufferedReader in = new BufferedReader(new FileReader("value_lien.txt"));
        int compteurLigne = 0;
        int id = 0;
        while (true) {
            String line = in.readLine();
            if (line == null)
                break;
            String word;
            StringTokenizer tok = new StringTokenizer(line, ",");
            int count_word = tok.countTokens();
            for (int i = 0; i < count_word; i++) {
                ProcessorElement processeur = new ProcessorElement();
                NOC[compteurLigne][i] = new ProcessorElement(compteurLigne, i);
                word = tok.nextToken();
                processeur.setType(Integer.parseInt(word));
                processeur.setId(id);
                processeur.x = compteurLigne;
                processeur.y = i;
                NOC[compteurLigne][i] = processeur;
                id++;
            }
            compteurLigne++;
        }
        remplir_map();
    }

    public static void remplir_map() {
        for (int i = 0; i < NOC.length; i++) {
            for (int j = 0; j < NOC.length; j++) {
                StaticParametre.listProcesseur.put(NOC[i][j].getId(), getNOC()[i][j]);
            }
        }
    }

    public static synchronized ProcessorElement[][] getNOC() {
        return NOC;
    }
}
