
import javax.swing.JOptionPane;

public class Dijkstra{
    //Variables
    private boolean PE []=new boolean[100];
    private int C [][] = new int[100][100];
    private int PA [] = new int [100];
    private int D [] = new int [100];
    private final static int inf=999999;
    private int s,f;
    private String salida;
    private Grafo grafo;
    private Cola cola;
    private int vC[],vc=0;
    //

    public Dijkstra(Grafo grafo,int s,int f){
        this.grafo=grafo;
        this.C=grafo.getMatAdy();
        this.s=s;
        this.f=f;
        vC=new int[100];
    }

    public String empezar(){
        cola=new Cola();
        for (int i=1; i<=grafo.noNodos; i++){
            D[i] = C[s][i];
            PA[i] = s;
            PE[i] = false;
        }
        D[s]=0;
        PA[s]=s;
        cola.encolar(s,s,C[s][s]);
        while(!cola.esVacia()){
            int ww=cola.desencolar().en;
            if(!PE[ww]){
                int v = menor();
                PE[v]=true;
                for(int i=1;i<=grafo.noNodos;i++){
                    if(C[v][i]!=inf){
                        int w=i;
                        if(PE[w]==false){
                            if(D[v]+C[v][w]<=D[w]){
                                D[w]=D[v]+C[v][w];
                                PA[w]=C[0][v];
                                cola.encolar(w,w,D[w]);
                            }
                        }
                    }
                }
            }
        }
        return this.imprimeCamino();
    }

    private int menor(){
        int menor=inf;
        int men=inf;
        for(int y=1;y<=grafo.noNodos;y++)
            if(D[y]<menor && PE[y]==false){
                menor=D[y];
                men=y;

            }
        return men;
    }

    private String imprimeCamino(){
        salida="El costo minimo de "+s+" a "+f+" es: ";
        if(D[f]!=inf)
            salida+=D[f]+"\n";
        else
            salida+="infinito";
        salida+="\n\n////Posible Camino\\\\\\\\\n";
        if(D[f]!=inf){             
            if(C[0][f]!=s)
            imprimeCamino(f);
            salida+=C[0][f]+"\n";
            vC[vc++]=C[0][f];
        }
        else{salida+="Camino para llegar de "+s+" a "+C[0][f]+"= No Existe\n";}
        genAris();
        return salida;
    }

    private void imprimeCamino(int v){
        if(PA[v]!=s){
            imprimeCamino(PA[v]);
        }
        salida+=PA[v]+"--";
        vC[vc++]=PA[v];
    }

    private  void genAris(){
        for(int i=1;i<vc;i++){
                    for(Arista arist : grafo.aristasA){
                        if((arist.noEn.id==vC[i]&&arist.noSal.id==vC[i-1])||(arist.noSal.id==vC[i]&&arist.noEn.id==vC[i-1]))
                            grafo.aristasB.add(arist);
                    }
            }
    }
}

