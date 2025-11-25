package estructuras;

import clases.*;

public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Agregar al final
    public void agregar(Carta carta) {
        NodoLista nuevoNodo = new NodoLista(carta);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    // Obtener carta en posición
    public Carta obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Índice fuera de rango");
            return null;
        }

        NodoLista actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getCarta();
    }

    // Eliminar carta en posición
    public Carta eliminar(int indice) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Índice fuera de rango");
            return null;
        }

        Carta cartaEliminada;

        if (indice == 0) {
            cartaEliminada = cabeza.getCarta();
            cabeza = cabeza.getSiguiente();
        } else {
            NodoLista actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguiente();
            }
            cartaEliminada = actual.getSiguiente().getCarta();
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }

        tamaño--;
        return cartaEliminada;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    // Mezclar cartas (algoritmo Fisher-Yates)
    public void mezclar() {
        if (tamaño <= 1)
            return;

        // Convertir a array temporal para mezclar
        Carta[] cartas = new Carta[tamaño];
        NodoLista actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
            cartas[i] = actual.getCarta();
            actual = actual.getSiguiente();
        }

        // Mezclar
        for (int i = tamaño - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }

        // Reconstruir lista
        cabeza = null;
        tamaño = 0;
        for (Carta carta : cartas) {
            agregar(carta);
        }
    }
}