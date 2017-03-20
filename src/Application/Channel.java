package Application;

import Simulation.ParametreSdf;

public class Channel {

    public int idApplication;

    public String nom;

    public int id;

    public String srcActor, srcPort, dstActor, dstPort;

    public int idsrcActor, idsrcPort, iddstActor, iddstPort;

    public int initialTokens = 0;

    public int nb_Token = 0;

    public int tokenSize = ParametreSdf.Default_token_size;
}
