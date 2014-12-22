import java.awt.*;
import java.io.Serializable;
public class NodoG implements Dibujable,Serializable {
    Integer id;
    int x;
    int y;
    private int m;
    public NodoG(int ca,int xx, int yy){
        id=ca;
        x=xx;
        y=yy;
        sit();
    }

    public void paint(Graphics g,Color color){
        g.setColor(color);
        g.fillOval(x-m, y-17, 25, 25);
        g.setColor(Color.black);
        g.drawString(id.toString(), x, y);
        g.setColor(Color.black);
        g.drawOval(x-m, y-17, 25, 25);
        
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x-m, y-17, 25, 25);
        g.setColor(Color.black);
        g.drawString(id.toString(), x, y);
        g.setColor(Color.black);
        g.drawOval(x-m, y-17, 25, 25);
    }

    private void sit() {
        if(id.toString().length()==1)
            m=9;
        else m=4;
    }

    public boolean estaDentro(int x, int y) {
        if (
            Math.sqrt(
                    (((this.x-m )- x) * ((this.x-m ) - x)) +
                    (((this.y-17) - y) * ((this.y-17) - y))) < 25)
        {
            return true;
        }

        return false;
    }

    public void setPosicion(int x, int y) {
        this.x=x;
        this.y=y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
