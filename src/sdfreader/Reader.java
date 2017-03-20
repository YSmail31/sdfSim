package sdfreader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Reader {

    private HashMap<String, Actor> actors;
    private Element racine;
    private int matRelation[][];
    private int n;

    public Reader(String file) {
        ouvrir(file);
        relation();
        construireMat();
    }
    
    private void ouvrir(String nomF) {
        SAXBuilder sxb = new SAXBuilder();
        actors = new HashMap();
        try
        {
            Document document = sxb.build(new File(nomF));
            racine = document.getRootElement();
            Element applicationGraph = racine.getChild("applicationGraph");
            Element sdf = applicationGraph.getChild("sdf");
            for(Element actor:sdf.getChildren("actor"))
            {
                Actor act = new Actor(actor);
                actors.put(act.getNom(),act);
            }
        }
        catch(IOException e){
            System.out.println("erreur fichier "+nomF+" "+e.getMessage());
        } 
        catch (DataConversionException ex) {
            System.out.println("erreur conversion"+ex.getMessage());
        } catch (JDOMException ex) {
            System.out.println("erreur jdom "+ex.getMessage());
        }
    }
    
    private void relation() {
        Element a = racine.getChild("applicationGraph");
        Element b = a.getChild("sdf");
        for(Element channel:b.getChildren("channel"))
        {
            Actor actSrc=actors.get(channel.getAttributeValue("srcActor"));
            Actor actDst=actors.get(channel.getAttributeValue("dstActor"));
            Port portSrc=actSrc.getPorts().get(channel.getAttributeValue("srcPort"));
            Port portDst=actDst.getPorts().get(channel.getAttributeValue("dstPort"));
            portSrc.setRelatedIndice(portDst.getIndice());
            portDst.setRelatedIndice(portSrc.getIndice());
        }
    }
    
    public void construireMat(){
        n = Port.getNbrPort();
        matRelation = new int[n][n];
        Iterator iterator1 = actors.keySet().iterator();
        while(iterator1.hasNext())
        {
            Actor actor = actors.get(iterator1.next());
            Iterator iterator2 = actor.getPorts().keySet().iterator();
            while(iterator2.hasNext())
            {
                Port p = actor.getPorts().get(iterator2.next());
                int j = p.getIndice();
                int i = p.getRelatedIndice();
                if(p.getType().equals("in")){
                    i = p.getRelatedIndice();
                    j = p.getIndice();
                }
                matRelation[i][j] = p.getRate();
            }  
        }
        afficheMat();
    }
    
    public void afficheMat(){
        for(int i = 0 ; i < n ; i++)
        {
            for(int j = 0 ; j < n ; j++)
                //if(matRelation[i][j] != 0)
                System.out.print(matRelation[i][j]+"\t");
            
            System.out.println("");
        }
    }

    public int[][] getMatRelation() {
        return matRelation;
    }
    
    public String[][] convertString()
    {
        String[][] mat = new String[n][n+1];
        for(int i = 0 ; i < n ; i++)
            for(int j = 0 ; j < n ; j++)
                mat[i][j+1] = matRelation[i][j]+"";
        
        return mat;
    }

    public int getN() {
        return n;
    }
    
    
}
