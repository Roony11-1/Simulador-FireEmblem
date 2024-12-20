package modelo;

import modelo.clases.Clase;

public class Unidad 
{
    private String nombre;
    private Clase clase;

    public Unidad() 
    {
        this.nombre = "NN";
        this.clase = null;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Clase getClase() { return clase; }
    public void setClase(Clase clase) { this.clase = clase; }
}
