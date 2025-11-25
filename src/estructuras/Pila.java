package estructuras;

import clases.*;

/**
 * Pila LIFO para registrar cartas derrotadas.
 */
public class Pila {
    private Carta[] elementos;
    private int tope;
    private static final int CAPACIDAD_INICIAL = 20;
    
    public Pila() {
        this.elementos = new Carta[CAPACIDAD_INICIAL];
        this.tope = -1;
    }
    
    public void apilar(Carta carta) {
        if (tope == elementos.length - 1) {
            expandirCapacidad();
        }
        elementos[++tope] = carta;
    }
    
    public Carta desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        Carta carta = elementos[tope];
        elementos[tope--] = null;
        return carta;
    }
    
    public Carta verTope() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return elementos[tope];
    }
    
    public boolean estaVacia() {
        return tope == -1;
    }
    
    public int getTamaño() {
        return tope + 1;
    }
    
    private void expandirCapacidad() {
        Carta[] nuevoArray = new Carta[elementos.length * 2];
        for (int i = 0; i <= tope; i++) {
            nuevoArray[i] = elementos[i];
        }
        elementos = nuevoArray;
    }
    
    // Ver todas las cartas derrotadas
    public String verHistorial() {
        if (estaVacia()) {
            return "No hay cartas derrotadas";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = tope; i >= 0; i--) {
            sb.append(i + 1).append(". ").append(elementos[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
