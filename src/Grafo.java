import java.awt.*;
import java.io.*;
import java.util.*;
public class Grafo implements Serializable{
    //Variables
        private final static Integer inf=999999;
        Integer noNodos;
        private int [][] mA;
        private boolean marcas[];
        private String salida;
        private Integer vel;
        LinkedList <NodoG> nodosA;
        LinkedList<Arista> aristasA,aristasB;

        public Grafo(){
            initVariables();
            init();
        }

        private void init(){
            for(int i = 0;i<100;i++)
                for(int j = 0;j<100;j++){
                    if(i==j)mA[i][j]=0;
                    else mA[i][j]=inf;
                }
        }

        public void setMA(){
            init();
            for(NodoG no: nodosA){
                mA[0][no.id] = mA[no.id][0] =no.id;
            }
            for(Arista aris:aristasA){
                mA[0][aris.noEn.id] = mA[aris.noEn.id][0] =aris.noEn.id;
                mA[0][aris.noSal.id] = mA[aris.noSal.id][0] =aris.noSal.id;
                mA[aris.noEn.id][aris.noSal.id] = mA[aris.noSal.id][aris.noEn.id] = aris.getPeso();
            }
        }
        private void initVariables(){
            noNodos=0;
            vel=500;
            mA = new int[100][100];
            marcas = new boolean[100];
            nodosA=new LinkedList();
            aristasA=new LinkedList();
            aristasB=new LinkedList();
        }

        public void InsertaNodo(Integer nodo,int x,int y){
            NodoG no= new NodoG(nodo,x,y);
            mA[0][nodo]=mA[nodo][0]=nodo;
            nodosA.add(no);

        }

        public void InsertaArista(int p,int q){
            Arista arista = new Arista(nodosA.get(p-1),nodosA.get(q-1));
            mA[p][q] = mA[q][p]=arista.getPeso();
            aristasA.add(arista);
        }

        private void borraMarcas(){
        for(int i=1;i<=noNodos;i++){
            marcas[i]=false;
        }
    }

    public String busquedaProfundidad(){
        borraMarcas();
        salida="Recorrido de Profundidad";
        for(int k=1;k<=noNodos;k++){
            if(marcas[k]==false){
                bbp(k);
            }
        }
        return salida;
    }

    private void bbp(int k) {
        salida+="\nNodo: "+k;
        if(getGradoNodo(k)==0)aristasB.add(buscaAris(k,k));
        marcas[k]=true;
        for(int i=1;i<=noNodos;i++){
            if(mA[k][i]!=inf){
                int x=i;
                if (marcas[x]==false){
                    aristasB.add(buscaAris(k,x));
                    bbp(x);
                }
            }
        }
    }

    public String busquedaAnchura(){
        borraMarcas();
        salida="Recorrido de Anchura";
        for(int i=1;i<=noNodos;i++){
            if(marcas[i]==false)
                bpa(i);
        }
        return salida;
    }

    private void bpa(int i) {
        Cola cola = new Cola();
        marcas[i]=true;
                cola.encolar(i,i,0);
                while(!cola.esVacia()){
                    int x = cola.desencolar().en;
                    if(getGradoNodo(x)==0)aristasB.add(buscaAris(x,x));
                    salida+="\nNodo: "+x;
                    for(int j=1;j<=noNodos;j++){
                        if(mA[x][j]!=inf){
                            int y=j;
                            if(marcas[y]==false){
                                aristasB.add(buscaAris(x,y));
                                marcas[y]=true;
                                cola.encolar(y,y,0);
                            }
                        }
                    }
                }
    }

        private Arista buscaAris(int en,int sa){
            for(Arista arist:aristasA){
                if((arist.noEn.id==en && arist.noSal.id==sa)||(arist.noEn.id==sa && arist.noSal.id==en)){
                    return arist;
                }
            }
            return new Arista(nodosA.get(en-1),nodosA.get(en-1));
        }

        public boolean exNodo(int k){
            if(mA[0][k]==inf || k>100 ||k<1)return false;
            return true;
        }

        public boolean exArista(int p,int q){
            if(mA[p][q]==inf)return false;
            return true;
        }


        public int [][] getMatAdy(){
            int  C [][] = new int [100][100];
            for(int i=0;i<=noNodos;i++){
              System.arraycopy(mA[i], 0, C[i], 0,noNodos+1);
            }
            return C;
        }

        public void pintarze(Graphics g){
            for(NodoG n:nodosA)
                n.paint(g);

            for(Arista a:aristasA)
                a.paint(g);
        }
        
        public void setVel(int v){
            vel=v;
        }

    public int  getVel() {
        return vel;
    }

    public void serializar(File archivo) throws IOException{
         ObjectOutputStream sali = new ObjectOutputStream(new FileOutputStream(archivo));
         sali.writeObject(this);
    }

    public int getGradoNodo(int x){
        int g=0;
        for(int i=1;i<=noNodos;i++)
            if(mA[x][i]!=inf)g++;
        return g-1;
    }

    public String showMA(){
        String sa="";
        for(int i=0;i<=noNodos;i++){
            for(int j=0;j<=noNodos;j++){
                if(mA[i][j]==inf)sa+="i  ";
                else
                sa+=mA[i][j]+"  ";
            }
            sa+="\n";
        }
         return sa;
    }

    public Integer [] getVectorNo(){
        Integer [] x=new Integer[noNodos+1];
        int p=0;
        for(NodoG nodo:nodosA){
            x[p++]=nodo.id;
        }
        return x;
    }

}


