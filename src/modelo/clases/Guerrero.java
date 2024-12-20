package modelo.clases;

public class Guerrero extends Clase
{

    public Guerrero() 
    {
        Estadisticas estGuerrero = new Estadisticas(20, 5, 2, 4, 0, 2, 0, 5, 11);
        
        this.setNombre("Guerrero");
        this.setDescripcion("Muy fuerte pero baja defensa");
        this.setEstadisticas(estGuerrero);
    }
}
