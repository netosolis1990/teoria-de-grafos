public class Floyd {
    int [][]D;
    private int P[][];
    private Grafo grafo;
    private int e,f;
    private String salida,salida1;
    private final static int inf=999999;
    int vC[]=new int[100];
    int vc=0;

    public Floyd(Grafo grafo,int e,int f){
        this.grafo=grafo;
        this.e=e;
        this.f=f;
        D=grafo.getMatAdy();
        P=new int[100][100];
        for(int i=1;i<=grafo.noNodos;i++){
                for(int j=1;j<=grafo.noNodos;j++){
                    P[i][j]=D[0][i];
                    P[0][j]=D[0][j];
                    P[j][0]=D[0][j];
                }
        }
    }

    public String empezar(){
        int i,j,k;

        for(i=0;i<100;i++)
            vC[i]=inf;

        for(k=1;k<=grafo.noNodos;k++)
            for(i=1;i<=grafo.noNodos;i++)
                for(j=1;j<=grafo.noNodos;j++)
                    if(D[i][k]+D[k][j]<D[i][j]){
                        D[i][j]=D[i][k]+D[k][j];
                        P[i][j]=P[k][j];
                    }
        return imprimir();
    }

    private String imprimir(){
        int i,j;
        salida="Matriz Menores Costos\n";
        for(i=0;i<=grafo.noNodos;i++){
              for(j=0;j<=grafo.noNodos;j++){
                  if(D[i][j]==999999){salida+="i ";
                  }
                  else{ salida+=D[i][j]+" ";
                  }
              }
              System.out.println();
          salida+="\n";
        }

        if(D[e][f]!=999999){
            salida1="Menor Costo = "+D[e][f]+"\n";
            salida1+="Camino:\n";
            if(D[e][f]!=999999)
                imprimeCamino(e,f);
            else{
                salida1+="No existe camino";
                return salida1;
            }
            salida1+=f;
            vC[vc++]=f;
        }
        else {salida1="No existe camino";}
        genAris();
        return salida1;
    }
    
    private void imprimeCamino(int i, int j) {
        int k=P[i][j];
        if(P[i][j]!=e){
            imprimeCamino(i,k);
        }
        vC[vc++]=k;
        salida1+=k+"--";
    }

    public String matMen(){
        return salida;
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