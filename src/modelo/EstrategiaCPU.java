package modelo;

import clases.*;
import estructuras.*;

public class EstrategiaCPU {
    private ArbolBinario arbolDecision;
    
    public EstrategiaCPU() {
        this.arbolDecision = new ArbolBinario();
    }
    
    /**
     * Decide que tipo de carta jugar según la estrategia del árbol binario.
     * Por ahora solo simplemente juega la carta del frente.
     */
    public Carta decidirJugada(Carta cartaJugador, Cola mazoCPU) {
        if (mazoCPU.estaVacia()) {
            return null;
        }
        
        // Usar arbol de decisión
        String tipoJugada = arbolDecision.evaluarEstrategia(cartaJugador, mazoCPU);
        
        // Por ahora, solo desencolar la carta frontal
        return mazoCPU.desencolar();
    }
}