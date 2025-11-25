package clases;

public class Carta {
    private final String nombre;
    private final String tipo; // Fuego, Agua, Tierra, Aire
    private final int poder;
    
    public Carta(String nombre, String tipo, int poder) {
        validarDatos(nombre, tipo, poder);
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }
    
    private void validarDatos(String nombre, String tipo, int poder) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacio");
            return;
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println("El tipo no puede estar vacio");
            return;
        }
        if (poder < 1 || poder > 100) {
            System.out.println("El poder debe estar entre 1 y 100");
            return;
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getPoder() {
        return poder;
    }

    @Override
    public String toString() {
        return nombre + " " + " - Poder: " + poder;
    }
}