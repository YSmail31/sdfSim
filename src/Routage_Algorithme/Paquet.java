package Routage_Algorithme;

public class Paquet {

    public int x_source, y_source, x_destination, y_destination, T_fin, x_inter, y_inter, idapp, idact;

    public boolean fin;

    public Paquet(int x, int y, int x1, int y1, int a, int b) {
        x_inter = x;
        y_inter = y;
        x_destination = x1;
        y_destination = y1;
        fin = false;
        idapp = a;
        idact = b;
    }

    public void set_Tfin(int Time) {
        this.T_fin = Time;
    }

    public void set_source(int x, int y) {
        x_source = x;
        y_source = y;
    }
}
