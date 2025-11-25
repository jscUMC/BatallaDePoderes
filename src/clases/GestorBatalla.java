package clases;

import estructuras.*;

public class GestorBatalla {
    private TablaHash registroJugadores;
    
    public GestorBatalla() {
        this.registroJugadores = new TablaHash();
    }
    
    public void registrarJugador(String nombre) {
        if (!registroJugadores.contiene(nombre)) {
            registroJugadores.insertar(nombre, new EstadoJugador());
        }
    }
    
    /**
     * Ejecuta una ronda de batalla entre dos cartas.
     * @return Nombre del ganador o null en caso de empate
     */
    public String ejecutarRonda(Jugador jugador1, Carta carta1, Jugador jugador2, Carta carta2) {
        // Registrar cartas jugadas
        EstadoJugador estado1 = registroJugadores.obtener(jugador1.getNombre());
        EstadoJugador estado2 = registroJugadores.obtener(jugador2.getNombre());
        
        if (estado1 != null) {
            estado1.registrarCartaJugada(carta1);
            estado1.acumularPoder(carta1.getPoder());
        }
        
        if (estado2 != null) {
            estado2.registrarCartaJugada(carta2);
            estado2.acumularPoder(carta2.getPoder());
        }
        
        // Determinar ganador
        int poder1 = carta1.getPoder();
        int poder2 = carta2.getPoder();
        
        String ganador;
        Carta cartaPerdedora;
        Jugador jugadorGanador;
        Jugador jugadorPerdedor;
        
        if (poder1 > poder2) {
            ganador = jugador1.getNombre();
            cartaPerdedora = carta2;
            jugadorGanador = jugador1;
            jugadorPerdedor = jugador2;
        } else if (poder2 > poder1) {
            ganador = jugador2.getNombre();
            cartaPerdedora = carta1;
            jugadorGanador = jugador2;
            jugadorPerdedor = jugador1;
        } else {
            // Empate: ambas cartas se registran como derrotadas
            jugador1.registrarCartaDerrotada(carta1);
            jugador2.registrarCartaDerrotada(carta2);
            return null;
        }
        
        // Actualizar estadísticas del ganador
        EstadoJugador estadoGanador = registroJugadores.obtener(ganador);
        if (estadoGanador != null) {
            estadoGanador.incrementarRondasGanadas();
        }
        
        // Registrar carta derrotada
        jugadorPerdedor.registrarCartaDerrotada(cartaPerdedora);
        
        // La carta ganadora vuelve al final del mazo
        jugadorGanador.agregarCartaAlMazo(poder1 > poder2 ? carta1 : carta2);
        
        return ganador;
    }
    
    public EstadoJugador obtenerEstadoJugador(String nombre) {
        return registroJugadores.obtener(nombre);
    }
}