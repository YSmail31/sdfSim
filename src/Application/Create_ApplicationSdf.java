package Application;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import Simulation.ParametreSdf;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import Simulation.StaticParametre;

public class Create_ApplicationSdf {

    private static Document document;

    public static void remplirTaches(String lienFichier, Integer m) {
        SAXBuilder sax = new SAXBuilder();
        try {
            document = sax.build(new File("" + lienFichier));
            Element racine = document.getRootElement();
            List element = racine.getChildren("applicationGraph");
            Element BaliseAppGrpah = (Element) element.get(0);
            List sdf = BaliseAppGrpah.getChildren("sdf");
            List sdfProp = BaliseAppGrpah.getChildren("sdfProperties");
            Element BalisSdf = (Element) sdf.get(0);
            Element BaliseSdfprop = (Element) sdfProp.get(0);
            List baliseActors = BalisSdf.getChildren("actor");
            List baliseChannels = BalisSdf.getChildren("channel");
            List baliseActorsprop = BaliseSdfprop.getChildren("actorProperties");
            List baliseChannelsprop = BaliseSdfprop.getChildren("channelProperties");
            ApplicationSdf appliSdf = new ApplicationSdf();
            appliSdf.setId_appli(m);
            for (int i = 0; i < baliseActors.size(); i++) {
                Actor a = new Actor();
                a.idApplication = m;
                Element baliseAct = (Element) baliseActors.get(i);
                a.nom = baliseAct.getAttributeValue("name");
                a.id = i;
                a.mapped = false;
                List listPorts = baliseAct.getChildren("port");
                for (int j = 0; j < listPorts.size(); j++) {
                    Element baliseport = (Element) listPorts.get(j);
                    a.addPort(baliseport.getAttributeValue("name"), baliseport.getAttributeValue("type"), j, Integer.parseInt(baliseport.getAttributeValue("rate")));
                }
                Element baliseActprop = (Element) baliseActorsprop.get(i);
                List baliseprocessors = baliseActprop.getChildren("processor");
                for (int j = 0; j < baliseprocessors.size(); j++) {
                    int memory = ParametreSdf.Default_memory;
                    Element baliseproc = (Element) baliseprocessors.get(j);
                    if (!baliseproc.getChildren("memory").isEmpty())
                        memory = Integer.parseInt(baliseproc.getChild("memory").getChild("stateSize").getAttributeValue("max"));
                    int id_type = 0;
                    if (baliseproc.getAttributeValue("type").equals("p1")) {
                        id_type = 0;
                    } else if (baliseproc.getAttributeValue("type").equals("proc_0")) {
                        id_type = 1;
                    } else if (baliseproc.getAttributeValue("type").equals("microblaze0")) {
                        id_type = 2;
                    } else if (baliseproc.getAttributeValue("type").equals("microblaze1")) {
                        id_type = 2;
                    } else if (baliseproc.getAttributeValue("type").equals("arm")) {
                        id_type = 2;
                    } else if (baliseproc.getAttributeValue("type").equals("encoder")) {
                        id_type = 3;
                    } else if (baliseproc.getAttributeValue("type").equals("motion")) {
                        id_type = 3;
                    } else if (baliseproc.getAttributeValue("type").equals("synth")) {
                        id_type = 3;
                    }
                    String t;
                    t = baliseproc.getChild("executionTime").getAttributeValue("time");
                    if (t != null) {
                        a.addproc(j, baliseproc.getAttributeValue("type"), Integer.parseInt(t), memory, id_type);
                    } else {
                        t = baliseproc.getChild("executionTime").getText();
                        a.addproc(j, baliseproc.getAttributeValue("type"), Integer.parseInt(t), memory, id_type);
                    }
                }
                appliSdf.AddActor(a);
            }
            for (int i = 0; i < baliseChannels.size(); i++) {
                Channel c = new Channel();
                c.idApplication = m;
                c.id = i;
                Element baliseChan = (Element) baliseChannels.get(i);
                c.nom = baliseChan.getAttributeValue("name");
                c.srcActor = baliseChan.getAttributeValue("srcActor");
                c.srcPort = baliseChan.getAttributeValue("srcPort");
                c.dstActor = baliseChan.getAttributeValue("dstActor");
                c.dstPort = baliseChan.getAttributeValue("dstPort");
                if (baliseChan.getAttributeValue("initialTokens") != null) {
                    c.initialTokens = Integer.parseInt(baliseChan.getAttributeValue("initialTokens"));
                    c.nb_Token = Integer.parseInt(baliseChan.getAttributeValue("initialTokens"));
                }
                if (!baliseChannelsprop.isEmpty()) {
                    Element baliseChanprop = (Element) baliseChannelsprop.get(i);
                    List baliseTokens = baliseChanprop.getChildren("tokenSize");
                    if (!baliseTokens.isEmpty()) {
                        Element tokensize = (Element) baliseTokens.get(0);
                        c.tokenSize = Integer.parseInt(tokensize.getAttributeValue("sz"));
                    }
                }
                appliSdf.AddChannel(c);
            }
            ParametreSdf.listApplicationSdf.add(appliSdf);
        } catch (JDOMException e) {
        } catch (IOException e) {
        }
    }
}
