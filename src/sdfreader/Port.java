package sdfreader;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

public class Port {
    static int nbrPort = 0;
    String type;
    String name;
    int rate;
    int indice;
    int relatedIndice;
    public Port(Element port) throws DataConversionException{
        indice = nbrPort ;
        nbrPort ++ ;
        type = port.getAttributeValue("type");
        name = port.getAttributeValue("name");
        rate = port.getAttribute("rate").getIntValue();
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getIndice() {
        return indice;
    }

    public static int getNbrPort() {
        return nbrPort;
    }

    public void setRelatedIndice(int relatedIndice) {
        this.relatedIndice = relatedIndice;
    }

    public int getRelatedIndice() {
        return relatedIndice;
    }
    
}
