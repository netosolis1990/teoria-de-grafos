import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class Hilo2 extends Thread{
    private Grafo arbol,arbol2;
    private Lienzo lienzo;
    private String c[];
    public Hilo2(Grafo arbol,Grafo graf, Lienzo lienzo,String[] cad){
        this.arbol = arbol;
        this.lienzo = lienzo;
        arbol2=graf;
        c=cad;
    }

    @SuppressWarnings("SleepWhileHoldingLock")
    @Override
    public void run(){
        for(Arista actual: arbol.aristasB){
            try {
                actual.marcar(lienzo.getGraphics());
                sleep(arbol.getVel());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
        }
        arbol.aristasB.clear();
        genCuadro(arbol2,c[1]);
    }

    private void genCuadro(Grafo gra,String title){
        JDialog dialogo= new JDialog();
        dialogo.setTitle(title);
        Lienzo l = new Lienzo(gra);
        dialogo.add(l);
        dialogo.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialogo.setVisible(true);
        l.paint(lienzo.getGraphics());
    }
}
