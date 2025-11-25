package estructuras;

import clases.*;

/**
 * Árbol binario de decisión para la estrategia del CPU.
 */
public class ArbolBinario {
    
    private static class NodoArbol {
        String condicion;
        String decision;
        NodoArbol izquierdo;
        NodoArbol derecho;
        
        NodoArbol(String texto) {
            if (texto.contains("?")) {
                this.condicion = texto;
            } else {
                this.decision = texto;
            }
        }
    }
    
    private NodoArbol raiz;
    
    public ArbolBinario() {
        construirArbolEstrategia();
    }
    
    private void construirArbolEstrategia() {
        // Raíz: ¿Carta jugador > 80 de poder?
        raiz = new NodoArbol("¿Carta jugador > 80 de poder?");
        
        // Rama izquierda (Sí)
        raiz.izquierdo = new NodoArbol("¿Tipo débil?");
        raiz.izquierdo.izquierdo = new NodoArbol("Jugar carta media");
        raiz.izquierdo.derecho = new NodoArbol("Jugar carta fuerte");
        
        // Rama derecha (No)
        raiz.derecho = new NodoArbol("Jugar carta fuerte");
    }
    
    /**
     * Evalúa la estrategia del CPU basada en la carta del jugador.
     * @param cartaJugador Carta que jugó el jugador
     * @param cartasCPU Cola de cartas disponibles del CPU
     * @return "fuerte" o "media" según la estrategia
     */
    public String evaluarEstrategia(Carta cartaJugador, Cola cartasCPU) {
        if (cartasCPU.estaVacia()) {
            return "media";
        }
        
        NodoArbol nodoActual = raiz;
        
        // Evaluar condición raíz
        if (cartaJugador.getPoder() > 80) {
            nodoActual = nodoActual.izquierdo;
            
            // Evaluar si CPU tiene carta tipo débil
            if (tipoDebilFrenteAlJugador(cartasCPU.verFrente(), cartaJugador)) {
                nodoActual = nodoActual.izquierdo; // Jugar carta media
            } else {
                nodoActual = nodoActual.derecho; // Jugar carta fuerte
            }
        } else {
            nodoActual = nodoActual.derecho; // Jugar carta fuerte
        }
        
        // Retornar decisión
        if (nodoActual.decision.contains("fuerte")) {
            return "fuerte";
        } else {
            return "media";
        }
    }
    
    private boolean tipoDebilFrenteAlJugador(Carta cartaCPU, Carta cartaJugador) {
        String tipoCPU = cartaCPU.getTipo();
        String tipoJugador = cartaJugador.getTipo();
        
        // Fuego < Agua, Agua < Tierra, Tierra < Aire, Aire < Fuego
        if (tipoCPU.equals("Fuego") && tipoJugador.equals("Agua")) return true;
        if (tipoCPU.equals("Agua") && tipoJugador.equals("Tierra")) return true;
        if (tipoCPU.equals("Tierra") && tipoJugador.equals("Aire")) return true;
        if (tipoCPU.equals("Aire") && tipoJugador.equals("Fuego")) return true;
        
        return false;
    }
}