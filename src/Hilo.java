
import javax.swing.*;

public class Hilo extends Thread{
    private Grafo arbol;
    private Lienzo lienzo;
    private String c[];
    public Hilo(Grafo arbol, Lienzo lienzo,String[] cad){
        this.arbol = arbol;
        this.lienzo = lienzo;
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
            sleep(100);
        } catch (InterruptedException ex) {
        }
        arbol.aristasB.clear();
        dialog(c[0],c[1]);
    }

    private void dialog(String title,String cad){
        JScrollPane scr = new JScrollPane();
        scr.setSize(300,500);
        JDialog dialogo = new JDialog();
            dialogo.setTitle(title);
            JTextArea area = new JTextArea();
            dialogo.setSize(300,500);
            area.setSize(300,500);
            scr.setViewportView(area);
            dialogo.add(scr);
            area.setText(cad);
            dialogo.setVisible(true);
    }
}
