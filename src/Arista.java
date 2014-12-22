import java.awt.*;
import java.io.Serializable;
import javax.swing.UIManager.*;
public class Arista implements Serializable{
    NodoG noEn,noSal;

    public Arista(NodoG in,NodoG out){
        noEn=in;
        noSal=out;
    }

    public void paint(Graphics g){
        g.setColor(Color.yellow);
        g.drawLine(noEn.x+4, noEn.y, noSal.x+4, noSal.y);
        g.drawString(distancia(),(noEn.x+noSal.x)/2,(noEn.y+noSal.y)/2);
        noEn.paint(g);
        noSal.paint(g);
    }

    public void marcar(Graphics g){
        g.setColor(Color.red);
        g.drawLine(noEn.x+4, noEn.y, noSal.x+4, noSal.y);
        g.drawString(distancia(),(noEn.x+noSal.x)/2,(noEn.y+noSal.y)/2);
        noEn.paint(g,Color.red);
        noSal.paint(g,Color.red);
    }
    
    private String distancia(){
        int x =(int) Math.sqrt(Math.pow(noEn.x-noSal.x, 2)+Math.pow(noEn.y-noSal.y, 2));
        return String.valueOf(x);
    }
    
    public int  getPeso(){
        return Integer.parseInt(distancia());
    }
}
