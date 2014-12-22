
public class Cola {
    ListaLigada lista;
    public Cola(){
        lista=new ListaLigada();
    }
    
    public boolean esVacia(){
        return lista.esVacia();
    }
    
    public void encolar(int e,int s,int pr){
        lista.insertaNodos(e, s, pr);
    }
    
    public NodoLista desencolar(){
        return lista.deletePrimerNodo();
    }
}
