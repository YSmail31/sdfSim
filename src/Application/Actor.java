package Application;

import java.util.LinkedList;

public class Actor {

    public static class port {

        public String name = "", type = "";

        public Integer id;

        public Integer idchannel;

        public Integer rate = 0;
    }

    public class processor {

        public String type = "";

        public Integer id, time = 0, memory = 0, id_type = 0;
    }

    public class token {

        Integer id_port, nb_token_cp = 0;
    }

    public LinkedList<port> Ports = new LinkedList<port>();

    public LinkedList<processor> Processors = new LinkedList<processor>();

    public int idApplication;

    public int id;

    public String nom;

    public int x;

    public int y;

    public int etat;

    public LinkedList<token> Tokens = new LinkedList<token>();

    public boolean mapped = false;

    public int debut_execution, fin_execution, iteration = 0;

    public void addPort(String name, String type, Integer id, Integer rate) {
        port p = new port();
        p.name = name;
        p.type = type;
        p.id = id;
        p.rate = rate;
        this.Ports.add(p);
    }

    public void addproc(Integer id, String type, Integer time, Integer memory, Integer id_type) {
        processor pr = new processor();
        pr.id = id;
        pr.type = type;
        pr.time = time;
        pr.memory = memory;
        pr.id_type = id_type;
        this.Processors.add(pr);
    }

    public Actor() {
        debut_execution = -1;
        fin_execution = -1;
        x = -1;
        y = -1;
        etat = 1;
        iteration = 0;
    }
}
