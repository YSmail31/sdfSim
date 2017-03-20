package Application;

import java.util.LinkedList;

public class ApplicationSdf {

    private LinkedList<Actor> listActor = new LinkedList<Actor>();

    private LinkedList<Channel> listChannel = new LinkedList<Channel>();

    private int cluster = -1;

    private int energie;

    private int id_application;

    public int etat;

    public int temp_debut;

    public int temp_fin;

    public ApplicationSdf() {
        this.energie = 0;
        etat = 1;
        temp_debut = -1;
        temp_fin = -1;
    }

    public LinkedList<Actor> getListActor() {
        return listActor;
    }

    public LinkedList<Channel> getListChannel() {
        return listChannel;
    }

    public void AddActor(Actor actor) {
        this.listActor.add(actor);
    }

    public void AddChannel(Channel channel) {
        this.listChannel.add(channel);
    }

    public void setIdCluster(int idCluster) {
        cluster = idCluster;
    }

    public int getIdCluster() {
        return cluster;
    }

    public int getEnergie() {
        return this.energie;
    }

    public void setEnergie(int energie) {
        this.energie += energie;
    }

    public void setId_appli(int id) {
        this.id_application = id;
    }

    public int getId_appli() {
        return id_application;
    }

    public void initialiser_app(Integer idchan) {
        Channel chan = new Channel();
        chan = this.getListChannel().get(idchan);
        for (int i = 0; i < this.getListActor().size(); i++) {
            Actor act = new Actor();
            act = this.getListActor().get(i);
            if (chan.srcActor.equals(act.nom)) {
                this.getListChannel().get(idchan).idsrcActor = act.id;
                for (int j = 0; j < act.Ports.size(); j++) {
                    if (chan.srcPort.equals(act.Ports.get(j).name)) {
                        this.getListChannel().get(idchan).idsrcPort = act.Ports.get(j).id;
                        this.getListActor().get(i).Ports.get(j).idchannel = idchan;
                    }
                }
            }
            if (chan.dstActor.equals(act.nom)) {
                this.getListChannel().get(idchan).iddstActor = act.id;
                for (int j = 0; j < act.Ports.size(); j++) {
                    if (chan.dstPort.equals(act.Ports.get(j).name)) {
                        this.getListChannel().get(idchan).iddstPort = act.Ports.get(j).id;
                        this.getListActor().get(i).Ports.get(j).idchannel = idchan;
                    }
                }
            }
        }
    }
}
