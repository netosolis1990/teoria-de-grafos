
import java.awt.Color;


public class ColoracionFB {
    int[][]C;
    char[][]V;
    boolean []noCol,nodVi;
    Grafo grafo;
    Color[]colores;
    Cola cola=new Cola();
    Lienzo ll;
    Integer perm[];
    public ColoracionFB(Grafo grafo,Integer[] perm,Lienzo l){
        this.perm =perm;
        ll=l;
        this.C=grafo.getMatAdy();
        this.grafo=grafo;
        V=new char [100][100];
        nodVi=new boolean[100];
        noCol=new boolean[grafo.noNodos+1];
    }


    private void carga(){
        colores=new Color[6];
        colores[1]=Color.red;
        colores[2]=Color.blue;
        colores[3]=Color.green;
        colores[4]=Color.orange;
        colores[5]=Color.pink;

        for(int i=1;i<100;i++){
            for(int j=1;j<100;j++){
                V[i][j]='N';
            }
            nodVi[i]=false;
        }
    }

    private void limpiaCol(){
        for(int i=1;i<=5;i++){
            noCol[i]=false;
        }
    }

    public int empezar(){
        carga();
        int var=1;
        for(int k=0;k<grafo.noNodos;k++){
                limpiaCol();
                int i=perm[k];
                for(int j=1;j<=grafo.noNodos;j++){
                    if(V[i][j]!='N'){
                        marca(V[i][j]);
                    }
                }

                if(noCol[1]==false){
                    grafo.nodosA.get(i-1).paint(ll.getGraphics(),colores[1]);
                    colorear(i,queColor(colores[1]));
                    if(var<=1)var=1;

                }
                else if(noCol[2]==false){
                    grafo.nodosA.get(i-1).paint(ll.getGraphics(),colores[2]);
                    colorear(i,queColor(colores[2]));
                    if(var<=2)var=2;
                }
                else if(noCol[3]==false){
                    grafo.nodosA.get(i-1).paint(ll.getGraphics(),colores[3]);
                    colorear(i,queColor(colores[3]));
                    if(var<=3)var=3;
                }
                else if(noCol[4]==false){
                    grafo.nodosA.get(i-1).paint(ll.getGraphics(),colores[4]);
                    colorear(i,queColor(colores[4]));
                   if(var<=4)var=4;
                }
                else if(noCol[5]==false){
                    grafo.nodosA.get(i-1).paint(ll.getGraphics(),colores[5]);
                    colorear(i,queColor(colores[5]));
                   if(var<=5)var=5;
                }

            //}
        }
        return var;
    }

    private void buscaCol(int no){
        for(int i=1;i<=grafo.noNodos;i++){
            if(V[no][i]=='N');
        }
    }

    private void colorear(int no,char c){
        for(int i=1;i<=grafo.noNodos;i++){
            if(C [no][i]!=999999 && V[no][i]=='N'){
                V[no][i]=c;
                V[i][no]=c;
            }
        }
    }

    private char queColor(Color color){
        if (color==colores[1])return 'r';
        if (color==colores[2])return 'a';
        if (color==colores[3])return 'n';
        if (color==colores[4])return 'b';
        if(color==colores[5])return 'p';
        return 'r';
    }

    private void marca(char c){
        int x=retColorNo(c);
        noCol[x]=true;
    }

    private Color retColor(char c){
        if (c=='r')return colores[1];
        if (c=='a')return colores[2];
        if (c=='n')return colores[3];
        if (c=='b')return colores[4];
        if(c=='p')return colores[5];
        return colores[1];
    }

    private int retColorNo(char c){
        if (c=='r')return 1;
        if (c=='a')return 2;
        if (c=='n')return 3;
        if (c=='b')return 4;
        if(c=='p')return 5;
        return 1;
    }

}
