package clases;

import estructuras.*;

public class Jugador {
    private final String nombre;
    private final Cola mazo;
    private final Pila cartasDerrotadas;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new Cola();
        this.cartasDerrotadas = new Pila();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Cola getMazo() {
        return mazo;
    }
    
    public Pila getCartasDerrotadas() {
        return cartasDerrotadas;
    }
    
    public void agregarCartaAlMazo(Carta carta) {
        mazo.encolar(carta);
    }
    
    public Carta jugarCarta() {
        if (mazo.estaVacia()) {
            throw new IllegalStateException("El mazo está vacío");
        }
        return mazo.desencolar();
    }
    
    public void registrarCartaDerrotada(Carta carta) {
        cartasDerrotadas.apilar(carta);
    }
    
    public boolean tieneCarta() {
        return !mazo.estaVacia();
    }
    
    public int cartasRestantes() {
        return mazo.getTamaño();
    }
}
