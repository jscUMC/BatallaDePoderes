package estructuras;

import clases.*;

public class TablaHash {
    private static final int TAMAÑO_TABLA = 10;
    private EntradaTabla[] tabla;
    
    public TablaHash() {
        this.tabla = new EntradaTabla[TAMAÑO_TABLA];
    }
    
    private int funcionHash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (hash + clave.charAt(i)) % TAMAÑO_TABLA;
        }
        return Math.abs(hash);
    }
    
    public void insertar(String nombreJugador, EstadoJugador estado) {
        int indice = funcionHash(nombreJugador);
        EntradaTabla nuevaEntrada = new EntradaTabla(nombreJugador, estado);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevaEntrada;
        } else {
            // Manejo de colisiones por encadenamiento
            EntradaTabla actual = tabla[indice];
            while (actual.siguiente != null) {
                if (actual.clave.equals(nombreJugador)) {
                    actual.valor = estado;
                    return;
                }
                actual = actual.siguiente;
            }
            if (actual.clave.equals(nombreJugador)) {
                actual.valor = estado;
            } else {
                actual.siguiente = nuevaEntrada;
            }
        }
    }
    
    public EstadoJugador obtener(String nombreJugador) {
        int indice = funcionHash(nombreJugador);
        EntradaTabla actual = tabla[indice];
        
        while (actual != null) {
            if (actual.clave.equals(nombreJugador)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        
        return null;
    }
    
    public boolean contiene(String nombreJugador) {
        return obtener(nombreJugador) != null;
    }
}