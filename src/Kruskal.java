
import javax.swing.JOptionPane;

public class Kruskal {
    //VARIABLES
    private Grafo grafo,arbol;
    private int suma=0;
    private final static int inf=999999;
    private int[][] C,CC;
    private boolean P[],noVis[][];
    private Cola cola;
    private int ciclo=0;
    //

    //CONSTRUCTOR
    public Kruskal(Grafo grafo){
        this.grafo=grafo;
        this.C = grafo.getMatAdy();
        arbol=new Grafo();
        P=new boolean[100];
        cola=new Cola();
        noVis=new boolean[100][100];
    }

    public int empezar(){
        //INSERCION DE NODOS EN EL NUEVO ARBOL
        for(NodoG nodo:grafo.nodosA){
             arbol.InsertaNodo(++arbol.noNodos,nodo.x,nodo.y);
        }
        
        //SE ENCONLAN TODAS LAS ARISTAS DEL GRAFO
        borraMarcas();
        for(int i=1;i<=grafo.noNodos;i++)
            buscaAdy(i);
         
        //SE INICIA EL ALGORITMO
        int k=1;
        int n=grafo.noNodos;
        while(k<n && !cola.esVacia()){
            ciclo=0;
            CC=arbol.getMatAdy();
            NodoLista hel = cola.desencolar();
            borraMarcas();
            int cic = ciclo(hel.en,hel.sal);
            //System.out.println(cic);
            if(cic==0){
                arbol.InsertaArista(hel.en,hel.sal);
                for(Arista arist : grafo.aristasA){
                        if((arist.noEn.id==hel.en && arist.noSal.id==hel.sal)||(arist.noEn.id==hel.sal && arist.noSal.id==hel.en)){
                            grafo.aristasB.add(arist);
                            suma+=arist.getPeso();
                        }
                }
                P[hel.sal]=true;
                k++;
            }
        }
        return suma;
    }

    private void borraMarcas(){
        for(int i=0;i<100;i++)
            for(int j=0;j<100;j++)
                noVis[i][j]=false;
    }

    private void buscaAdy(int x){
        for(int i=1;i<=grafo.noNodos;i++){
            if(C[x][i]!=inf && !noVis[x][i]){
                noVis[x][i]=true;
                noVis[i][x]=true;
                cola.encolar(C[0][x], C[0][i], C[x][i]);
            }
        }
    }

    private int ciclo(int x,int y)
    {
        if(ciclo==1)return 1;
        if(CC[x][y]!=inf){
            ciclo=1;
            return 1;
        }
        else{
            for(int i=1;i<=arbol.noNodos && !noVis[x][i];i++){
                noVis[x][i]=true;
                if(CC[x][i]!=inf)ciclo(i,y);

            }
        }
        return ciclo;
    }
    public Grafo retArbol(){
        return this.arbol;
    }
}