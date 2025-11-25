package estructuras;

public class EntradaTabla {
    String clave;
    EstadoJugador valor;
    EntradaTabla siguiente;

    EntradaTabla(String clave, EstadoJugador valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}