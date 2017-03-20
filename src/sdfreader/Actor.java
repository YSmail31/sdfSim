package sdfreader;

import java.util.ArrayList;
import java.util.HashMap;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

public class Actor {
    
    String nom;
    HashMap<String,Port> ports;
    ArrayList<Actor> srcActors;
    ArrayList<Actor> dstActors;
    
    int largeur = 40;
    final int longueur = 40;
    int x=100,y=100;
    int fontSize=12;
    
    public Actor(Element actor) throws DataConversionException{
        nom = actor.getAttributeValue("name");
        largeur += nom.length()*fontSize/2;
        ports = new HashMap();
        
        for(Element port:actor.getChildren("port"))
            ports.put(port.getAttributeValue("name"), new Port(port));
        
        srcActors = new ArrayList();
        dstActors = new ArrayList();
    }

    public String getNom() {
        return nom;
    }

    public HashMap<String, Port> getPorts() {
        return ports;
    }

    public ArrayList<Actor> getSrcActors() {
        return srcActors;
    }

    public ArrayList<Actor> getDstActors() {
        return dstActors;
    }
    
    public void addRelatedActor(Actor actor){
        actor.srcActors.add(this);
        this.dstActors.add(actor);
    }
    
}
