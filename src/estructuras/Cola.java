package estructuras;

import clases.*;;

/**
 * Cola FIFO para representar el mazo de cada jugador.
 */
public class Cola {
    private NodoCola frente;
    private NodoCola finalCola;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamaño = 0;
    }

    // Encolar al final
    public void encolar(Carta carta) {
        NodoCola nuevoNodo = new NodoCola(carta);

        if (estaVacia()) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.setSiguiente(nuevoNodo);
            finalCola = nuevoNodo;
        }
        tamaño++;
    }

    // Desencolar del frente
    public Carta desencolar() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia");
            return null;
        }

        Carta carta = frente.getCarta();
        frente = frente.getSiguiente();

        if (frente == null) {
            finalCola = null;
        }

        tamaño--;
        return carta;
    }

    // Ver el frente sin desencolar
    public Carta verFrente() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia");
            return null;
        }
        return frente.getCarta();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Carta verCartaEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamaño)
            return null;
        NodoCola actual = frente;
        for (int i = 0; i < posicion; i++)
            actual = actual.getSiguiente();
        return actual.getCarta();
    }

    public Carta removerCartaEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamaño)
            return null;
        NodoCola actual = frente, anterior = null;
        for (int i = 0; i < posicion; i++) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (anterior == null) { // Frente
            return desencolar();
        } else {
            Carta carta = actual.getCarta();
            anterior.setSiguiente(actual.getSiguiente());
            if (actual == finalCola)
                finalCola = anterior;
            tamaño--;
            return carta;
        }
    }

}