package modelo.clases;

public class Espadachin extends Clase
{

    public Espadachin() 
    {
        Estadisticas estEspadachin = new Estadisticas(21, 6, 11, 12, 0, 5, 3, 6, 7);
        Estadisticas estEspMaximas = new Estadisticas(60, 25, 30, 26, 30, 25, 22, 15, 25);
        
        this.setNombre("Espadachin");
        this.setDescripcion("Alta evasión y porcentaje de evitar golpes");
        this.setEstadisticas(estEspadachin);
        
        this.setPromocionada(true);
        
        this.setEstadisticasMaximas(estEspMaximas);
    }
}