package clases;

public class NodoLista {
    private Carta carta;
    private NodoLista siguiente;
    
    public NodoLista(Carta carta) {
        this.carta = carta;
        this.siguiente = null;
    }
    
    public Carta getCarta() {
        return carta;
    }
    
    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    
    public NodoLista getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
