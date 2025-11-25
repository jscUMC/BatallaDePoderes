package estructuras;

import clases.*;

public  class EstadoJugador {
        private Cola cartasJugadas;
        private int rondasGanadas;
        private int poderTotalAcumulado;
        
        public EstadoJugador() {
            this.cartasJugadas = new Cola();
            this.rondasGanadas = 0;
            this.poderTotalAcumulado = 0;
        }
        
        public void registrarCartaJugada(Carta carta) {
            cartasJugadas.encolar(carta);
        }
        
        public void incrementarRondasGanadas() {
            rondasGanadas++;
        }
        
        public void acumularPoder(int poder) {
            poderTotalAcumulado += poder;
        }
        
        public Cola getCartasJugadas() {
            return cartasJugadas;
        }
        
        public int getRondasGanadas() {
            return rondasGanadas;
        }
        
        public int getPoderTotalAcumulado() {
            return poderTotalAcumulado;
        }
    }