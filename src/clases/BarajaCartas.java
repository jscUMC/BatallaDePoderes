package clases;

import estructuras.*;

public class BarajaCartas {
    private ListaEnlazada cartas;
    
    public BarajaCartas() {
        this.cartas = new ListaEnlazada();
        inicializarBaraja();
    }
    
    private void inicializarBaraja() {
        String[] tipos = {"Fuego", "Agua", "Tierra", "Aire"};
        String[][] nombres = {
            {"Fénix de Fuego", "Dragón Infernal", "Salamandra de Lava", "Meteoro Ardiente", "Llama Eterna", 
             "Volcán Rugiente", "Cometa Ígneo", "Serpiente Flamígera", "Titán del Magma", "Espíritu de Ceniza"},
            {"Kraken Marino", "Sirena de Agua", "Leviatán Profundo", "Ola Gigante", "Tifón Acuático",
             "Tritón Guardian", "Ballena Ancestral", "Cascada Celestial", "Hydra Marina", "Espíritu del Océano"},
            {"Golem de Tierra", "Titán de Roca", "Guardián del Bosque", "Terremoto Viviente", "Montaña Sagrada",
             "Coloso de Piedra", "Raíces Antiguas", "Avalancha Imparable", "Guardián de Cristal", "Espíritu de la Tierra"},
            {"Grifo Celestial", "Fénix del Viento", "Tornado Etéreo", "Espíritu del Cielo", "Huracán Divino",
             "Águila Legendaria", "Ciclón Místico", "Viento Cortante", "Arpía del Norte", "Nube de Tormenta"}
        };
        
        // Generar 10 cartas por tipo (40 total)
        for (int t = 0; t < tipos.length; t++) {
            for (int i = 0; i < 10; i++) {
                int poder = (int)(Math.random() * 100) + 1; // Poder entre 1 y 100
                Carta carta = new Carta(nombres[t][i], tipos[t], poder);
                cartas.agregar(carta);
            }
        }
    }
    
    public void mezclar() {
        cartas.mezclar();
    }
    
    public Carta obtenerCarta(int indice) {
        return cartas.obtener(indice);
    }
    
    public Carta quitarCarta(int indice) {
        return cartas.eliminar(indice);
    }
    
    public int cartasDisponibles() {
        return cartas.getTamaño();
    }
    
    public boolean hayCartas() {
        return !cartas.estaVacia();
    }
}
