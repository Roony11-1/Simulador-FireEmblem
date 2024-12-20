package modelo.clases;

public class Mirmidon extends Clase
{

    public Mirmidon() 
    {
        Estadisticas estMirmidon = new Estadisticas(16, 4, 9, 10, 0, 2, 1, 5, 5);
        
        this.setNombre("Mirmidon");
        this.setDescripcion("Espadachines con mucho potencial");
        this.setEstadisticas(estMirmidon);
    }
}
