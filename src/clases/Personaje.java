package clases;

public class Personaje {
    private String nombre;
    private int tamañoMazo;
    private int modificadorPoder; // Ejemplo: +10, -5, etc.
    private String descripcion;

    public Personaje(String nombre, int tamañoMazo, int modificadorPoder, String descripcion) {
        this.nombre = nombre;
        this.tamañoMazo = tamañoMazo;
        this.modificadorPoder = modificadorPoder;
        this.descripcion = descripcion;
    }

    public String getNombre() { 
        return nombre;
    }
    public int getTamañoMazo() { 
        return tamañoMazo; 
    }
    public int getModificadorPoder() { 
        return modificadorPoder; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
}