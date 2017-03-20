package Routage_Algorithme;

import Application.Tache;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class Methode_rout2 {

    private int x_source;

    private int y_source;

    private int x_destination;

    private int y_destination;

    private Tache t;

    private Routage_XY rout;

    private MORA_Routage mora;

    private int succ;

    public Methode_rout2(Tache t, Paquet p, int succ) {
        if (succ > -1) {
            this.x_source = t.x1.get(succ);
            this.y_source = t.y1.get(succ);
        } else {
            this.x_source = t.x2;
            this.y_source = t.y2;
        }
        this.t = t;
        this.x_destination = p.x_destination;
        this.y_destination = p.y_destination;
        this.succ = succ;
        if (x_source == x_destination && y_source == y_destination) {
            if (succ != -2) {
                t.fin_routage((Simulator.Tnow + 2), x_source, y_source, succ);
            } else {
                t.fin_root_pere((Simulator.Tnow + 2), x_source, y_source);
            }
            Simulator.Add_Event(Simulator.Tnow + 2);
        } else
            this.run();
    }

    public Methode_rout2(Tache tache_pere, int x, int y, int k) {
    }

    public void run() {
        if (StaticParametre.ALGORITHME_ROUTAGE.equals("XY"))
            rout = new Routage_XY(this.x_source, this.y_source, this.x_destination, this.y_destination, t, succ);
        else
            mora = new MORA_Routage(this.x_source, this.y_source, this.x_destination, this.y_destination, this.t, succ);
        try {
            if (StaticParametre.ALGORITHME_ROUTAGE.equals("XY"))
                rout.start();
            else
                mora.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
