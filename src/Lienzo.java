import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class Lienzo extends Panel implements MouseMotionListener{
    int c =0;
    private int xA,yA;
    public Grafo arbol;
    LinkedList<NodoG>nodos;
    private Dibujable dibujandose=null;
    public Lienzo(Grafo arbol){
        setBackground(Color.darkGray);
        addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(500,500));
        nodos=arbol.nodosA;
        this.arbol = arbol;
    }

    @Override
    public void paint(Graphics g){
        arbol.pintarze(g);
    }

    public void mouseDragged(MouseEvent e)
   {
      if (dibujandose == null)
        {
            // Se guardan las posiciones del ratón
            xA= e.getX();
            yA = e.getY();
            dibujandose = dameFigura(e);
        }
        else
        {
            // Si ya había empezado el arrastre, se calculan las nuevas
            // coordenadas del rectángulo
            dibujandose.setPosicion(
                dibujandose.getX() + (e.getX() - xA),
                dibujandose.getY() + (e.getY() - yA));

            // Se guarda la posición del ratón para el siguiente cálculo
            xA = e.getX();
            yA = e.getY();
            repaint();
            arbol.setMA();
        }
   }

    public void mouseMoved(MouseEvent e) {
        dibujandose=null;
    }

    public  Dibujable dameFigura(MouseEvent e)
    {
        for (Dibujable figura : nodos)
        {
            if (figura.estaDentro(e.getX(), e.getY()))
            {
                return figura;
            }
        }

        return null;
    }


}
