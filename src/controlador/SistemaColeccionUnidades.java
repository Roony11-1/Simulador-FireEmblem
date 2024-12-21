package controlador;

import java.util.ArrayList;
import modelo.Unidad;

public class SistemaColeccionUnidades 
{
    private ArrayList<Unidad> unidades;

    public SistemaColeccionUnidades() 
    {
        this.unidades = new ArrayList<>();
    }

    public boolean agregarUnidad(Unidad unidad)
    {
        try 
        {
            unidades.add(unidad);
        } 
        catch (Exception e) 
        {
            System.out.println("Error al agregar: "+e.getMessage());
            return false;
        }
        return true;
    }
}
