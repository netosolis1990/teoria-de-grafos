public class ListaLigada {
    NodoLista primer,ultimo;
    
    public ListaLigada(){
        primer=ultimo=null;
    }
    
    public void insertaNodos(int in,int out,int peso){
        NodoLista aux,act,ant;
        ant = act = primer;
        while(act!=null && act.prio<=peso){
            ant=act;
            act=act.sigNodo;
        }
        aux=new NodoLista(in,out,peso);
        if (ant == null || ant == act)
        {
            aux.sigNodo = ant;
            primer = aux;
        }
        else
        {
            aux.sigNodo = act;
            ant.sigNodo = aux;
        }
    }
    public boolean esVacia() {
    	return primer==null;
    }
    
    public NodoLista deletePrimerNodo(){
    	if (esVacia()){
    		return null;
    	}

    	NodoLista objetoEliminado=primer;

    	if(primer==ultimo){
    		primer = ultimo = null;
    	}

    	else
    		primer=primer.sigNodo;

    	return objetoEliminado;
    }
    
}
