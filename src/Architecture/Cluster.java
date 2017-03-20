package Architecture;

public class Cluster {

    public int id;

    public int xCentre;

    public int yCentre;

    private int xDebut;

    private int yDebut;

    private int xFin;

    private int yFin;

    public boolean Free = true;

    public void setCenter(int x_debut, int x_fin, int y_debut, int y_fin) {
        this.xCentre = (x_fin + x_debut) / 2;
        this.yCentre = (y_fin + y_debut) / 2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoordonneesDebut(int xDebut, int yDebut) {
        this.xDebut = xDebut;
        this.yDebut = yDebut;
    }

    public void setCoordonneesFin(int xFin, int yFin) {
        this.xFin = xFin;
        this.yFin = yFin;
    }

    public int getxDebut() {
        return xDebut;
    }

    public int getyDebut() {
        return yDebut;
    }

    public int getxFin() {
        return xFin;
    }

    public int getyFin() {
        return yFin;
    }

    public synchronized void set_Free(boolean F) {
        Free = F;
    }

    public synchronized boolean get_Free() {
        return Free;
    }
}
