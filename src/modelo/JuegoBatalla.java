package modelo;

import clases.*;
import estructuras.*;
import java.util.Scanner;

public class JuegoBatalla {
    private Scanner scanner;
    private BarajaCartas baraja;
    private GestorBatalla gestorBatalla;
    private EstrategiaCPU estrategiaCPU;
    private Jugador jugadorHumano;
    private Jugador jugadorCPU;
    private Personaje personajeJugador;
    

    public JuegoBatalla() {
        this.scanner = new Scanner(System.in);
        this.baraja = new BarajaCartas();
        this.gestorBatalla = new GestorBatalla();
        this.estrategiaCPU = new EstrategiaCPU();
    }

    public void iniciar() {
        mostrarBienvenida();
        String nombreJugador = solicitarNombre();
        personajeJugador = seleccionarPersonaje();
        System.out
                .println("\nHas elegido a " + personajeJugador.getNombre() + ": " + personajeJugador.getDescripcion());

        // Crear jugadores
        jugadorHumano = new Jugador(nombreJugador);
        jugadorCPU = new Jugador("CPU");

        // Registrar jugadores
        gestorBatalla.registrarJugador(nombreJugador);
        gestorBatalla.registrarJugador("CPU");

        // Repartir cartas
        repartirCartas();

        // Jugar
        jugarPartida();

        // Mostrar resultados
        mostrarResultadosFinales();
    }

    private void mostrarBienvenida() {
        System.out.println(" Bienvenido a Batalla de Poderes");
    }

    private String solicitarNombre() {
        System.out.print("Ingrese su nombre: ");
        return scanner.nextLine().trim();
    }

    private void repartirCartas() {
        System.out.println("\nSe reparten cartas...");
        baraja.mezclar();
        for (int i = 0; i < personajeJugador.getTamañoMazo(); i++) {
            Carta base = baraja.quitarCarta(0);
            
            int nuevoPoder = base.getPoder() + personajeJugador.getModificadorPoder();
            // Se limita el valor de poder de las cartas
            if (nuevoPoder < 1)
                nuevoPoder = 1;
            if (nuevoPoder > 100)
                nuevoPoder = 100;
            Carta ajustada = new Carta(base.getNombre(), base.getTipo(), nuevoPoder);
            jugadorHumano.agregarCartaAlMazo(ajustada);
        }
        // El CPU puede usar tamaño estandar
        for (int i = 0; i < 10; i++) {
            jugadorCPU.agregarCartaAlMazo(baraja.quitarCarta(0));
        }
        System.out.println("Tu mazo tiene " + jugadorHumano.cartasRestantes() + " cartas.");
        System.out.println("El oponente CPU también tiene " + jugadorCPU.cartasRestantes() + " cartas.");
    }

    // Jugador elige cartas
    private Carta elegirCartaJugador(Jugador jugador) {
        System.out.println("\nTus próximas cartas:");
        int max = Math.min(3, jugador.cartasRestantes());
        for (int i = 0; i < max; i++) {
            Carta carta = jugador.getMazo().verCartaEnPosicion(i);
            System.out.printf("%d. %s\n", i + 1, carta.toString());
        }
        int eleccion;
        do {
            System.out.print("Elija la carta a jugar: ");
            eleccion = scanner.nextInt();
            scanner.nextLine();
        } while (eleccion < 1 || eleccion > max);
        return jugador.getMazo().removerCartaEnPosicion(eleccion - 1);
    }

    private void jugarPartida() {
        int numeroRonda = 1;

        while (jugadorHumano.tieneCarta() && jugadorCPU.tieneCarta()) {
            System.out.println("\n--- Ronda " + numeroRonda + " ---");

            // Jugador humano juega
            Carta cartaHumano = elegirCartaJugador(jugadorHumano);
            System.out.println(jugadorHumano.getNombre() + " juega: \"" + cartaHumano.toString() + "\"");

            // CPU juega
            Carta cartaCPU = estrategiaCPU.decidirJugada(cartaHumano, jugadorCPU.getMazo());
            System.out.println("CPU juega: \"" + cartaCPU.toString() + "\"");

            // Ejecutar batalla
            String ganador = gestorBatalla.ejecutarRonda(jugadorHumano, cartaHumano, jugadorCPU, cartaCPU);

            if (ganador != null) {
                System.out.println("\nGanadora de la ronda: " + ganador);
            } else {
                System.out.println("\n¡Empate! Ambas cartas derrotadas.");
            }

            numeroRonda++;

            // Pausa
            esperarTecla();
        }
    }

    private Personaje seleccionarPersonaje() {
        Personaje[] personajes = {
                new Personaje("Hechicero", 12, +5, "Mazo amplio, poder incrementado"),
                new Personaje("Guerrero", 8, +10, "Mazo limitado, mucho poder"),
                new Personaje("Guardiana", 10, 0, "Balanceada"),
                new Personaje("Sabio", 15, -8, "Mazo más grande, poder reducido")
        };
        System.out.println("\nElija su personaje:");
        for (int i = 0; i < personajes.length; i++) {
            System.out.printf("%d. %s: %s (Mazo: %d, Modificador de poder: %+d)\n",
                    i + 1, personajes[i].getNombre(), personajes[i].getDescripcion(),
                    personajes[i].getTamañoMazo(), personajes[i].getModificadorPoder());
        }
        int eleccion;
        do {
            System.out.print("Ingrese opción (1-4): ");
            eleccion = scanner.nextInt();
            scanner.nextLine();
        } while (eleccion < 1 || eleccion > 4);
        return personajes[eleccion - 1];
    }

    private void mostrarResultadosFinales() {
        System.out.println("\n--- Cartas derrotadas de " + jugadorHumano.getNombre() + " ---");
        if (!jugadorHumano.getCartasDerrotadas().estaVacia()) {
            System.out.println("Top: \"" + jugadorHumano.getCartasDerrotadas().verTope().toString() + "\"");
            System.out.println("...");
        } else {
            System.out.println("No hay cartas derrotadas");
        }

        System.out.println("\n--- Estadísticas Finales ---");

        EstadoJugador estadoHumano = gestorBatalla.obtenerEstadoJugador(jugadorHumano.getNombre());
        EstadoJugador estadoCPU = gestorBatalla.obtenerEstadoJugador("CPU");

        System.out.println(jugadorHumano.getNombre() + ": " + estadoHumano.getRondasGanadas() + " rondas ganadas");
        System.out.println("CPU: " + estadoCPU.getRondasGanadas() + " rondas ganadas");

        // Determinar ganador final
        if (estadoHumano.getRondasGanadas() > estadoCPU.getRondasGanadas()) {
            System.out.println("\n ¡" + jugadorHumano.getNombre() + " gana la partida! ");
        } else if (estadoCPU.getRondasGanadas() > estadoHumano.getRondasGanadas()) {
            System.out.println("\n ¡CPU gana la partida! ");
        } else {
            System.out.println("\n ¡Empate en la partida! ");
        }
    }

    private void esperarTecla() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

}