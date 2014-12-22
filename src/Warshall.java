public class Warshall {
    private final int inf=999999;
    private boolean DE[][];
    private int C[][],D[][];
    private boolean T=true,F=false;
    private int P[][];
    private Grafo grafo;
    private String salida,salida1;
    private int e,f;
    int vC[]=new int[100];
    int vc=0;

    public Warshall(Grafo grafo,int e,int f){
        this.grafo=grafo;
        this.e=e;
        this.f=f;
        DE=new boolean[100][100];
        P = new int[100][100];
        D = new int[100][100];
        C = grafo.getMatAdy();
        inicio();
    }

    private void inicio() {
       

        for(int i=1;i<=grafo.noNodos;i++){
                for(int j=1;j<=grafo.noNodos;j++){
                    if(i==j){D[i][j]=1;C[i][j]=0;}
                    else
                        if(C[i][j]!=inf)D[i][j]=1;
                        else D[i][j]=0;
                }
        }
        
        for(int i=1;i<=grafo.noNodos;i++){
                for(int j=1;j<=grafo.noNodos;j++){
                    if(C[i][j]==inf)C[i][j]=999999;
                    P[i][j]=C[0][i];
                    P[0][j]=C[0][j];
                    P[j][0]=C[0][j];
                    DE[i][j]=F;
                }
                D[0][0]=0;
        }
    }

    public String empezar(){
        int i,j,k;
        for(i=0;i<100;i++)
            vC[i]=999;
        //DETERMINA SI EXISTE CAMINO
        for(k=1;k<=grafo.noNodos;k++)
            for(i=1;i<=grafo.noNodos;i++)
                for(j=1;j<=grafo.noNodos;j++)
                    if((D[i][k]==1 && D[k][j]==1) || D[i][j]==1){
                        D[i][j]=1;
                    }
        //DETERMINA EL CAMINO MINIMO
        for(k=1;k<=grafo.noNodos;k++)
            for(i=1;i<=grafo.noNodos;i++)
                for(j=1;j<=grafo.noNodos;j++)
                    if(C[i][k]+C[k][j]<C[i][j]){
                        C[i][j]=C[i][k]+C[k][j];
                        P[i][j]=P[k][j];
                    }
        return imprimir();
    }

    private void imprimeCamino(int i,int j){
        int k=P[i][j];
        if(P[i][j]!=e){
            imprimeCamino(i,k);
        }
        vC[vc++]=k;
        salida+=k+" ";
    }
    
    private String imprimir(){
        int i,j;
        salida="Camino:\n";
        salida1="Matriz de Clausura Transitiva\n";
        for(i=0;i<=grafo.noNodos;i++){
            salida1+=C[0][i]+" ";
        }
        salida1+="\n";
        for(i=1;i<=grafo.noNodos;i++){
                salida1+=C[0][i]+" ";
                for(j=1;j<=grafo.noNodos;j++)
                    salida1+=D[i][j]+" ";
                salida1+="\n";
        }
        
        if(D[e][f]!=0){
            imprimeCamino(e,f);
            salida+=f;
            vC[vc++]=f;
        }
        else{
            salida+="No existe camino";
            return salida;
        }
        genAris();
        return salida;
    }

    public String matTran(){
        return salida1;
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