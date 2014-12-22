import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.event.ChangeEvent;

public class GrafoForm extends JFrame implements ActionListener{

    //VARIABLES
    private Lienzo lienzo;
    private JScrollPane scroll;
    private JMenuBar menuBar;
    private JMenu archivo, algoritmos, recorridos;
    private JMenuItem nuevo,abrir,guardar,salir,dijkstra,floyd,warshall,prim,kruskal,prof,anchu,colo,coloFb;
    private Grafo grafo;
    private Font fuente;
    private JButton inAr;
    private JTextField txtEn,txtSal;
    private JSlider slVel;
    private Dibujable dib;
    private Integer perm,permutacion[],menor,no,permutacionV[];


    public GrafoForm(){
            super("Teoria de Grafos");
            initComponents();
    }


    private void initComponents() {
        dib=null;
        permutacion = new Integer[1000];
        permutacionV =new Integer[1000];
        grafo=new Grafo();
        fuente = new Font("Purisa", 1, 12);
        lienzo = new Lienzo(grafo);
        menuBar= new JMenuBar();
        menuBar.setFont(new Font("Purisa", 1, 12));
        inAr = new JButton("Insertar Arista");
        inAr.addActionListener(this);
        txtEn = new JTextField();
        txtSal = new JTextField();
        txtEn.setColumns(10);
        txtSal.setColumns(10);
        txtEn.setSize(10,txtEn.getHeight());
        txtSal.setSize(10,txtEn.getHeight());
        txtEn.addActionListener(this);
        txtSal.addActionListener(this);
        slVel = new JSlider(0,1000,500);
        slVel.setForeground(Color.white);

        archivo = new JMenu("Archivo");
        algoritmos = new JMenu("Algoritmos");
        recorridos = new JMenu("Recorridos");
        archivo.setFont(new Font("Purisa", 1, 12));
        algoritmos.setFont(new Font("Purisa", 1, 12));
        recorridos.setFont(new Font("Purisa", 1, 12));

        nuevo =new JMenuItem("Nuevo");
        nuevo.setFont(fuente);
        abrir =new JMenuItem("Abrir");
        abrir.setFont(fuente);
        guardar =new JMenuItem("Guardar");
        guardar.setFont(fuente);
        salir =new JMenuItem("Salir");
        salir.setFont(fuente);
        dijkstra =new JMenuItem("Dijkstra");
        dijkstra.setFont(fuente);
        floyd =new JMenuItem("Floyd");
        floyd.setFont(fuente);
        warshall =new JMenuItem("Warshall");
        warshall.setFont(fuente);
        prim =new JMenuItem("Prim");
        prim.setFont(fuente);
        kruskal =new JMenuItem("Kruskal");
        kruskal.setFont(fuente);
        prof =new JMenuItem("Profundidad");
        prof.setFont(fuente);
        anchu =new JMenuItem("Anchura");
        anchu.setFont(fuente);
        colo = new JMenuItem("Coloracion");
        coloFb = new JMenuItem("ColoracionFB");
        colo.setFont(fuente);
        coloFb.setFont(fuente);

        archivo.add(nuevo).addActionListener(this);
        archivo.add(abrir).addActionListener(this);
        archivo.add(guardar).addActionListener(this);
        archivo.add(salir).addActionListener(this);

        algoritmos.add(dijkstra).addActionListener(this);
        algoritmos.add(floyd).addActionListener(this);
        algoritmos.add(warshall).addActionListener(this);
        algoritmos.add(prim).addActionListener(this);
        algoritmos.add(kruskal).addActionListener(this);
        algoritmos.add(colo).addActionListener(this);
        algoritmos.add(coloFb).addActionListener(this);

        recorridos.add(prof).addActionListener(this);
        recorridos.add(anchu).addActionListener(this);


        menuBar.add(archivo);
        menuBar.add(algoritmos);
        menuBar.add(recorridos);
        JLabel aris = new JLabel("      Nodos-->");
        aris.setFont(fuente);
        aris.setForeground(Color.red);
        menuBar.add(aris);
        


        //setLayout(new BorderLayout());
        
        menuBar.add(txtEn);
        menuBar.add(txtSal);
        menuBar.add(inAr);
        menuBar.add(slVel);
        this.setJMenuBar(menuBar);
        add(lienzo);
        

        this.setMinimumSize(new Dimension(700,700));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        lienzo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lienzoMousePressed(evt);
            }
        });
        slVel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slVelStateChanged(evt);
            }

            private void slVelStateChanged(ChangeEvent evt) {
                grafo.setVel(slVel.getValue());
            }
        });

        try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
         } catch (Exception ex) {}
        pack();
    }

    private void lienzoMousePressed(MouseEvent evt) {
        if((dib=lienzo.dameFigura(evt))==null){
        grafo.InsertaNodo(++grafo.noNodos, evt.getX(),evt.getY());
        lienzo.paint(lienzo.getGraphics());
        }
        else dib=null;
     }

 
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==nuevo){
            grafo=new Grafo();
            lienzo.arbol=grafo;
            lienzo.nodos=grafo.nodosA;
             lienzo.repaint();
        }

        if(e.getSource()==coloFb){
            if(grafo.noNodos==0)return;
            lienzo.repaint();
            perm=0;
            menor=5;
            no=0;
            long f =factorial(grafo.noNodos);
            if(f>10000000){
                JOptionPane.showMessageDialog(null,"Esto tardara demaciado");
                return;
            }
            permutacion = grafo.getVectorNo();
            genPermu(grafo.noNodos,new Integer(0),permutacion);
            JOptionPane.showMessageDialog(null, "FB No Cromatico: "+menor+" Posicion: "+no);
            ColoracionFB col = new ColoracionFB(grafo,permutacionV,lienzo);
            col.empezar();
        }

        if(e.getSource()==colo){
            if(grafo.noNodos==0)return;
            lienzo.repaint();
            Coloracion col = new Coloracion(grafo,lienzo);
             JOptionPane.showMessageDialog(null, " No Cromatico: "+col.empezar());
             col.empezar();
        }

        if(e.getSource()==inAr){
            inAris();
            return;
        }
        if(e.getSource()==txtEn){
            inAris();
            return;
        }
        if(e.getSource()==txtSal){
            inAris();
            return;
        }
        
        if(e.getSource()==prof){
            if(grafo.noNodos==0)return;
            String[]c=new String[2];
            c[0]="Recorrido de Profundidad";
            lienzo.paint(lienzo.getGraphics());
            c[1] = grafo.busquedaProfundidad();
            Hilo hilo = new Hilo(grafo,lienzo,c);
            hilo.start();
        }
        if(e.getSource()==anchu){
            if(grafo.noNodos==0)return;
            String[]c=new String[2];
            c[0]="Recorrido de Anchura";
            lienzo.paint(lienzo.getGraphics());
            c[1] = grafo.busquedaAnchura();
            Hilo hilo = new Hilo(grafo,lienzo,c);
            hilo.start();
        }

        if(e.getSource()==guardar){
            try{
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                if(fc.showOpenDialog(this)!=JFileChooser.APPROVE_OPTION)return;
                grafo.serializar(fc.getSelectedFile());
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error al intentar guardar el archivo!");
            }
            return;
        }

        if(e.getSource()==abrir){
            try{
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                if(fc.showOpenDialog(this)!=JFileChooser.APPROVE_OPTION)return;
                FileInputStream file = new FileInputStream(fc.getSelectedFile());
                ObjectInputStream output = new ObjectInputStream(file);
                grafo = (Grafo)output.readObject();
                lienzo.repaint();
                lienzo.nodos=grafo.nodosA;
                lienzo.arbol=grafo;
                slVel.setValue(500);
                txtEn.setText(null);
                txtSal.setText(null);
                output.close();
                JOptionPane.showMessageDialog(null,"Archivo cargado exitosamente!");
                lienzo.paint(lienzo.getGraphics());
            }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error de archivo!");
        }
            return;
        }

        if(e.getSource()==dijkstra){
            lienzo.paint(lienzo.getGraphics());
            if(grafo.noNodos==0)return;
            String[]c=new String[2];
            c[0]="Algortimo de Dijkstra";
            String de =JOptionPane.showInputDialog("Id del nodo");
            if(de.equals(""))return;
            String a =JOptionPane.showInputDialog("Id del nodo");
            if(a.equals(""))return;
            try{
                if(grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))){
                    Dijkstra dij = new Dijkstra(grafo,parser(de),parser(a));
                    c[1]=dij.empezar();
                    Hilo hilo = new Hilo(grafo,lienzo,c);
                    hilo.start();
                }
                else{
                    JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
                }
            }
            catch(Exception ex){
                                JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
            }
        }
        if(e.getSource()==floyd){
            lienzo.paint(lienzo.getGraphics());
            if(grafo.noNodos==0)return;
            String[]c=new String[2];
            c[0]="Algortimo de Floyd";
            String de =JOptionPane.showInputDialog("Id del nodo");
            if(de.equals(""))return;
            String a =JOptionPane.showInputDialog("Id del nodo");
            if(a.equals(""))return;
            try{
                if(grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))){
                    Floyd floy = new Floyd(grafo,parser(de),parser(a));
                    c[1]=floy.empezar();
                    Hilo hilo = new Hilo(grafo,lienzo,c);
                    hilo.start();
                }
                else{
                    JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
                }
            }
            catch(Exception ex){
                                JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
            }
        }

        if(e.getSource()==warshall){
            lienzo.paint(lienzo.getGraphics());
            if(grafo.noNodos==0)return;
            String[]c=new String[2];
            c[0]="Algortimo de Warshall";
            String de =JOptionPane.showInputDialog("Id del nodo");
            if(de.equals(""))return;
            String a =JOptionPane.showInputDialog("Id del nodo");
            if(a.equals(""))return;
            try{
                if(grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))){
                    Warshall war= new Warshall(grafo,parser(de),parser(a));
                    c[1]=war.empezar();
                    Hilo hilo = new Hilo(grafo,lienzo,c);
                    hilo.start();
                }
                else{
                    JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
                }
            }
            catch(Exception ex){
                                JOptionPane.showMessageDialog(null,"El id de un nodo no es valido");
            }
        }

        if(e.getSource()==kruskal){
            lienzo.repaint();
            if(grafo.noNodos==0)return;
            Kruskal krus = new Kruskal(grafo);
            String[]c=new String[2];
            c[0]="Algoritmo de Kruskal";
            c[1]="Peso Arbol de Expansion Minima: "+krus.empezar();
            Hilo2 hilo =new Hilo2(grafo,krus.retArbol(),lienzo,c);
            hilo.start();
        }


    }

    private void genCuadro(Grafo gra,String title){
        JDialog dialogo= new JDialog();
        dialogo.setTitle(title);
        Lienzo l = new Lienzo(gra);
        dialogo.add(l);
        dialogo.setSize(new Dimension(400,300));
        dialogo.setVisible(true);
        l.paint(lienzo.getGraphics());
    }

    private void inAris(){
        if(txtEn.getText().equals("") || txtSal.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Seleccione los nodos a unir");
            return;
        }
        try{
            if(parser(txtEn.getText())==parser(txtSal.getText())){
                JOptionPane.showMessageDialog(null,"Inserte ID de nodos diferentes");
                return;
            }
            if(!grafo.exNodo(parser(txtEn.getText())) || !grafo.exNodo(parser(txtSal.getText()))){
                JOptionPane.showMessageDialog(null,"Inserte ID de nodos validos");
                return;
            }
            if(grafo.exArista(parser(txtEn.getText()), parser(txtSal.getText()))){
                JOptionPane.showMessageDialog(null,"Estos nodos ya estan unidos");
                return;
            }
            grafo.InsertaArista(parser(txtEn.getText()), parser(txtSal.getText()));
            lienzo.paint(lienzo.getGraphics());

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Inserte ID de nodos validos");
        }
    }

    private Integer parser(String p){
        return Integer.parseInt(p);
    }

    private void dialog(String title,String cad){
        JScrollPane scr = new JScrollPane();
        scr.setSize(300,500);
        JDialog dialogo = new JDialog();
            dialogo.setTitle(title);
            JTextArea area = new JTextArea();
            dialogo.setSize(300,500);
            area.setSize(300,500);
            scroll.setViewportView(area);
            dialogo.add(scroll);
            area.setText(cad);
    }

    public void genPermu(Integer n,Integer j,Integer [] x){
            Integer i;

       if (j < n) {
          for (i = j; i < n; i++) {
             int t =x[i];
             x[i]=x[j];
             x[j]=t;
             genPermu(n, j+1, x);
             t =x[i];
             x[i]=x[j];
             x[j]=t;
          }
       }
       else {
              ColoracionFB col = new ColoracionFB(grafo,x,lienzo);
              int val = col.empezar();
              if(val<menor){
                  System.arraycopy(x, 0, permutacionV, 0, grafo.noNodos);
                  menor=val;
                  no=perm;
              }
         perm++;
       }
    }

    private long factorial(long n){
        if(n==1)return 1;
        else return n*factorial(n-1);
    }

    public static void main(String[] args){
         java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
        GrafoForm x = new GrafoForm();
        x.setExtendedState(GrafoForm.MAXIMIZED_BOTH);
        x.setVisible(true);
    }});
    }


}
